#!/usr/bin/env python3
"""Verify upstream identity and clone complete history. Run from repository root."""
import concurrent.futures,json,pathlib,subprocess,time,xml.etree.ElementTree as ET
ROOT=pathlib.Path(__file__).resolve().parents[1]; OUT=ROOT/'results/stage0-survey'; SCR=ROOT/'scratch'
REPOS={'batch-processing-gateway':'apple/batch-processing-gateway','commons-cli':'apache/commons-cli','commons-codec':'apache/commons-codec','commons-collections':'apache/commons-collections','commons-csv':'apache/commons-csv','datafaker':'datafaker-net/datafaker','gson':'google/gson','jdom2':'hunterhacker/jdom','ruler':'aws/event-ruler','windward':'Flmelody/windward'}
def run(args,cwd=None): return subprocess.check_output(args,cwd=cwd,text=True).strip()
def git(p,*args): return run(['git','-C',str(SCR/'upstream'/p),*args])
def work(item):
 p,repo=item; dest=SCR/'upstream'/p; dest.parent.mkdir(parents=True,exist_ok=True)
 evidence=[]; snap=ROOT/'data/projects'/p
 pom=(snap/'pom.xml').read_text(); tree=ET.fromstring(pom); ns={'m':tree.tag.split('}')[0][1:]} if '}' in tree.tag else {}; pre='m:' if ns else ''
 for key in ['url','scm/url','scm/connection']:
  val=tree.find('/'.join(pre+k for k in key.split('/')),ns)
  if val is not None and val.text: evidence.append(key+': '+val.text.strip())
 if p=='commons-cli': evidence.append('snapshot remote: '+run(['git','-C',str(snap),'remote','get-url','origin']))
 if not dest.exists():
  for attempt in range(2):
   cp=subprocess.run(['git','clone','https://github.com/'+repo+'.git',str(dest)],capture_output=True,text=True)
   (SCR/f'clone-{p}-{attempt+1}.log').write_text(cp.stdout+cp.stderr)
   if cp.returncode==0: break
   if dest.exists(): break
  else: return {'project':p,'repo':repo,'error':cp.stderr,'evidence':evidence}
 branch=git(p,'symbolic-ref','--short','refs/remotes/origin/HEAD').removeprefix('origin/')
 sha=git(p,'rev-parse','origin/'+branch)
 if p in ['jdom2','batch-processing-gateway']:
  # These snapshots omit upstream metadata. Validate against shared tracked source blobs.
  evidence.append('Snapshot omits own repository URL; candidate from survey instructions, verified by source comparison in Step 2 (identity provisional here).')
 result={'project':p,'repo':repo,'default_branch':branch,'head':sha,'date':git(p,'show','-s','--format=%cI',sha),'first_parent_count':int(git(p,'rev-list','--first-parent','--count',sha)),'clone_kib':int(run(['du','-sk',str(dest)]).split()[0]),'shallow':git(p,'rev-parse','--is-shallow-repository'),'evidence':evidence}
 print(p,branch,sha,flush=True); return result
if __name__=='__main__':
 OUT.mkdir(parents=True,exist_ok=True)
 with concurrent.futures.ThreadPoolExecutor(max_workers=4) as pool: results=list(pool.map(work,REPOS.items()))
 (OUT/'upstream.json').write_text(json.dumps(results,indent=2)+'\n')
 lines=['# Upstream survey','', 'Invocation for every value below: `python3 docker/stage0_upstream.py` (repository root). Full clones use `git clone` without depth/filter; default branch uses `git symbolic-ref refs/remotes/origin/HEAD`; HEAD/date/count use `rev-parse`, `show --format=%cI`, `rev-list --first-parent --count`; allocated clone size uses `du -sk`. Remote observation is the clone time, not a historical cutoff.','', '| Project | Upstream | Default branch | HEAD | Commit date | First-parent commits | Clone KiB |','|---|---|---|---|---|---:|---:|']
 for r in results:
  lines.append(f"| {r['project']} | {r['repo']} | {r.get('default_branch','ERROR')} | {r.get('head','')} | {r.get('date','')} | {r.get('first_parent_count','')} | {r.get('clone_kib','')} |")
 lines+=['','## Snapshot identity evidence','']
 for r in results: lines.append('- '+r['project']+': '+'; '.join(r['evidence'])+(' ERROR: '+r['error'] if 'error' in r else ''))
 lines+=['','Apache gitbox SCM URLs refer to the same Apache projects as their GitHub mirrors. Preserve the snapshot SCM URL above; GitHub mirror is used for requested GitHub history.']
 (OUT/'upstream.md').write_text('\n'.join(lines)+'\n')
