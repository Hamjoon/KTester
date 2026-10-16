#!/usr/bin/env python3
"""Independent archive-based verification and release-tag diagnostics."""
import io,json,pathlib,re,subprocess,tarfile
from stage0_upstream import ROOT,SCR,OUT,git
from stage0_match_snapshot import norm

def archived(p,sha,root):
 data=subprocess.check_output(['git','-C',str(SCR/'upstream'/p),'archive',sha]);files={}
 with tarfile.open(fileobj=io.BytesIO(data)) as t:
  for f in t:
   if f.isfile() and f.name.startswith(root+'/'):files[f.name]=norm(t.extractfile(f).read())
 return files

def differences(p,sha,uproot):
 m=json.loads((OUT/'match'/f'{p}.json').read_text());snap=ROOT/'data/projects'/p
 a={str(f.relative_to(snap)):norm(f.read_bytes()) for f in (snap/m['snapshot_root']).rglob('*') if f.is_file()};b=archived(p,sha,uproot)
 if p=='jdom2':a={k.removeprefix('src/main/java/'):v for k,v in a.items()};b={k.removeprefix(uproot+'/'):v for k,v in b.items()}
 return {'differing':sorted(k for k in a.keys()&b.keys() if a[k]!=b[k]),'snapshot_only':sorted(a.keys()-b.keys()),'commit_only':sorted(b.keys()-a.keys())}

if __name__=='__main__':
 result={'chosen_matches':{},'release_tags':{},'checks':{}}
 for u in json.loads((OUT/'upstream.json').read_text()):
  p=u['project'];m=json.loads((OUT/'match'/f'{p}.json').read_text());d=differences(p,m['sha'],m['upstream_root']);assert d==m['difference'],p;result['chosen_matches'][p]={'independent_archive_equal':True,'difference_counts':{k:len(v) for k,v in d.items()}}
  version=m['snapshot_versions'][0];line=set(git(p,'rev-list','--first-parent',u['head']).splitlines());checks=[]
  if version and 'SNAPSHOT' not in version:
   for tag in git(p,'tag').splitlines():
    if re.search(r'(?<!\d)'+re.escape(version)+r'(?![\d.])',tag):
     sha=git(p,'rev-list','-1',tag);d=differences(p,sha,m['upstream_root']);checks.append({'tag':tag,'sha':sha,'on_first_parent':sha in line,'difference_counts':{k:len(v) for k,v in d.items()}})
  result['release_tags'][p]=checks
 h={r['project']:r for r in json.loads((OUT/'history.json').read_text())};sizes=json.loads((OUT/'sizes.json').read_text())
 for s in sizes:
  p=s['project'];assert not s['production']['parse_error_files'];assert not s['tests']['parse_error_files'];assert h[p]['after_t']==sum(h[p][k] for k in ['src_main_changes','only_test_doc_build','other_non_src'])
 result['checks']['history_partitions_and_java_parse_errors']='passed'
 result['checks']['protected_tracked_diff']=subprocess.check_output(['git','diff','experiment/2026-09-week2-ktester-replication','--','code','data/projects','data/project_index'],text=True)
 assert not result['checks']['protected_tracked_diff']
 (OUT/'verification.json').write_text(json.dumps(result,indent=2)+'\n')
 lines=['# Independent verification and release-tag diagnostics','','All values: `python3 docker/stage0_verify.py`. It independently exports each selected commit with git archive and recomputes normalized path/content differences, checking against the batch-object matcher. It also inspects release tags matching the snapshot version even when the tag lies off the default first-parent line. The selected t remains the best commit on that line, as required for subsequent first-parent traversal.','','| Project | Independent match check | Release tag | On first-parent line | Δ changed / snapshot-only / commit-only |','|---|---|---|---|---|']
 for p,r in result['chosen_matches'].items():
  checks=result['release_tags'][p]
  if not checks:lines.append(f'| {p} | passed | no matching release-version tag probed | | |')
  for c in checks:lines.append(f"| {p} | passed | {c['tag']} | {c['on_first_parent']} | "+' / '.join(str(c['difference_counts'][k]) for k in ['differing','snapshot_only','commit_only'])+' |')
 lines+=['','JDOM counts above compare relocated Java source, not literal src/main paths. A release on a side branch may be closer than any commit on the default first-parent line; that does not make it an exact first-parent snapshot match. Full tag SHAs are in verification.json.','','History category totals balance and all Java inputs parse without syntax-error nodes. The tracked diff against the starting branch is empty for code/, data/projects/ and data/project_index/. Ignored snapshots were accessed read-only; this Git check alone cannot prove the state of ignored files.']
 (OUT/'verification.md').write_text('\n'.join(lines)+'\n');print(json.dumps(result,indent=2))
