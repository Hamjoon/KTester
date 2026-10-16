#!/usr/bin/env python3
"""Normalized snapshot comparison; batch object reads equal git show REV:path."""
import concurrent.futures,hashlib,json,pathlib,re,subprocess,time,xml.etree.ElementTree as ET
from stage0_upstream import ROOT,SCR,OUT,git

def norm(b): return b'\n'.join(x.rstrip() for x in b.replace(b'\r\n',b'\n').split(b'\n'))
class Objects:
 def __init__(self,p):
  self.proc=subprocess.Popen(['git','-C',str(SCR/'upstream'/p),'cat-file','--batch'],stdin=subprocess.PIPE,stdout=subprocess.PIPE); self.cache={}
 def get(self,ref):
  self.proc.stdin.write((ref+'\n').encode());self.proc.stdin.flush(); h=self.proc.stdout.readline().split()
  if h[-1]==b'missing':return b''
  b=self.proc.stdout.read(int(h[-1])); self.proc.stdout.read(1);return b
 def digest(self,ref):
  if ref not in self.cache:self.cache[ref]=hashlib.sha256(norm(self.get(ref))).hexdigest()
  return self.cache[ref]
 def close(self): self.proc.stdin.close();self.proc.wait()
def versions(b):
 try:
  t=ET.fromstring(b); ns=t.tag.split('}')[0]+'}' if '}' in t.tag else ''
  return t.findtext(ns+'version'),t.findtext(ns+'parent/'+ns+'version')
 except ET.ParseError:return None,None

def tags(p,line):
 out={}
 for tag in git(p,'tag').splitlines():
  sha=git(p,'rev-list','-1',tag)
  if sha in line:out.setdefault(sha,[]).append(tag)
 return out

def work(u):
 p=u['project'];start=time.time();obs=Objects(p); root='gson/src/main' if p=='gson' else 'src/main'; snap=ROOT/'data/projects'/p
 sd={str(f.relative_to(snap)):hashlib.sha256(norm(f.read_bytes())).hexdigest() for f in (snap/root).rglob('*') if f.is_file()}
 line=git(p,'rev-list','--first-parent',u['head']).splitlines(); positions={s:i for i,s in enumerate(line)}; ts=tags(p,positions)
 ver=versions((snap/'pom.xml').read_bytes()); narrow=[]
 for s in line:
  v=versions(obs.get(s+':pom.xml'))
  if v[0]==ver[0] and (not p.startswith('commons-') or v[1]==ver[1]):narrow.append(s)
 release=[s for s,names in ts.items() if ver[0] and any(re.search(r'(?<!\d)'+re.escape(ver[0])+r'(?![\d.])',n) for n in names)]
 seeds=[]
 if p=='commons-cli':seeds=[git(p,'rev-parse','90b3f2e')]
 for s in release:
  i=positions[s];seeds+=line[max(0,i-15):min(len(line),i+16)]; seeds.insert(0,s)
 candidates=list(dict.fromkeys(seeds+narrow)); windows=[]
 for s in candidates:
  i=positions.get(s)
  if i is None:continue
  before=[positions[t] for t in ts if positions[t]>=i];after=[positions[t] for t in ts if positions[t]<i]
  lo=max(after) if after else 0;hi=min(before) if before else len(line)-1
  windows.extend(line[lo:hi+1])
 phases=[('version/release window',candidates),('adjacent-tag intervals',list(dict.fromkeys(windows))),('whole first-parent history',line)]
 seen=set();best=None;unique={}; checked=0
 # JDOM source layout is upstream core/src/java, not the authors' Maven-style src/main/java.
 upstream_root='core/src/java' if p=='jdom2' else root
 compare_sd={k.removeprefix('src/main/java/'):v for k,v in sd.items()} if p=='jdom2' else sd
 for phase,commits in phases:
  for s in commits:
   if s in seen:continue
   seen.add(s);checked+=1
   tree=obs.get(s+':'+upstream_root)
   treeid=hashlib.sha256(tree).hexdigest()
   if treeid in unique:diff=unique[treeid]
   else:
    files={}
    for row in subprocess.check_output(['git','-C',str(SCR/'upstream'/p),'ls-tree','-rz',s,'--',upstream_root]).split(b'\0'):
     if not row:continue
     head,path=row.split(b'\t',1);mode,typ,blob=head.split()
     if typ!=b'blob':continue
     key=path.decode();key=key.removeprefix(upstream_root+'/') if p=='jdom2' else key
     files[key]=obs.digest(blob.decode())
    changed=sorted(k for k in compare_sd.keys()&files.keys() if compare_sd[k]!=files[k]);only_s=sorted(compare_sd.keys()-files.keys());only_c=sorted(files.keys()-compare_sd.keys());diff={'differing':changed,'snapshot_only':only_s,'commit_only':only_c};unique[treeid]=diff
   score=sum(len(v) for v in diff.values())
   if best is None or score<best['score']:best={'sha':s,'score':score,'difference':diff,'phase':phase}
   if score==0:break
  if best and best['score']==0:break
 obs.close();s=best['sha'];i=positions[s];before=sorted((positions[t],t) for t in ts if positions[t]>=i);after=sorted(((positions[t],t) for t in ts if positions[t]<i),reverse=True)
 result={'project':p,**best,'date':git(p,'show','-s','--format=%cI',s),'exact':best['score']==0 and p!='jdom2','snapshot_root':root,'upstream_root':upstream_root,'snapshot_files':len(sd),'candidate_commits_checked':checked,'unique_source_trees_checked':len(unique),'snapshot_versions':ver,'version_candidates':len(narrow),'nearest_tag_before':ts[before[0][1]] if before else [],'nearest_tag_after':ts[after[0][1]] if after else [],'elapsed_seconds':round(time.time()-start,3)}
 if p=='jdom2':result['layout_note']='No literal src/main tree in upstream: authors relocated core/src/java to src/main/java and supplied a Maven pom. Counts shown separately for relocated source comparison; never an exact literal tree match.'; result['literal_difference']={'differing':0,'snapshot_only':len(sd),'commit_only':0}
 (OUT/'match').mkdir(exist_ok=True);(OUT/'match'/f'{p}.json').write_text(json.dumps(result,indent=2)+'\n'); print(p,s,best['score'],phase,checked,flush=True);return result
if __name__=='__main__':
 upstream=json.loads((OUT/'upstream.json').read_text())
 with concurrent.futures.ThreadPoolExecutor(max_workers=4) as pool:rs=list(pool.map(work,upstream))
 lines=['# Snapshot match','', 'Invocation for all numbers: `python3 docker/stage0_match_snapshot.py`. File contents are read using persistent `git cat-file --batch` (same blobs as `git show SHA:path`), with CRLF→LF and trailing whitespace stripped per line. Candidates: known CLI commit, release-tag neighborhoods, matching project/Apache-parent versions, adjacent tag intervals, then complete first-parent history. Stop at first exact source match; this does not establish a unique original commit. Dates are committer dates. Tags must point directly to the first-parent line; nearest before includes the matched commit.','', '| Project | Commit | Date | Exact / Δ changed, snapshot-only, commit-only | Tag at/before | Tag after | Candidates checked |','|---|---|---|---|---|---|---:|']
 for r in rs:
  d=r['difference'];counts=','.join(str(len(d[k])) for k in ['differing','snapshot_only','commit_only']); label='exact' if r['exact'] else 'Δ '+counts
  if r['project']=='jdom2':label=f"literal Δ 0,{r['snapshot_files']},0; relocated Δ {counts}"
  lines.append(f"| {r['project']} | {r['sha']} | {r['date']} | {label} | {', '.join(r['nearest_tag_before'])} | {', '.join(r['nearest_tag_after'])} | {r['candidate_commits_checked']} |")
  if not r['exact']:
   lines+=[]
 lines+=['','## Non-exact cases','']
 for r in rs:
  if not r['exact']:
   lines+=['### '+r['project'],'',r.get('layout_note','Closest normalized source tree; no exact match in searched history.'),'']
   for k,v in r['difference'].items():lines.append(f"- {k} ({len(v)}): "+', '.join(v[:20]))
 lines+=['','Ruler retains its unchanged upstream src/main/software tree plus a second snapshot-only copy under src/main/java/software. Subsequent sizes use its actual upstream src/main and src/test roots. See verification.md for release tags off the first-parent line; these can be closer source candidates than the selected first-parent t.']
 (OUT/'snapshot-match.md').write_text('\n'.join(lines)+'\n')
