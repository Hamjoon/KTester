#!/usr/bin/env python3
import csv,json,re
from stage0_upstream import OUT,git
from stage0_match_snapshot import tags

def category(path):
 return bool(re.search(r'(^|/)(test[^/]*|docs?|site|\.github|\.mvn|maven|gradle)(/|$)|(^|/)(pom\.xml|build\.[^/]+|.*\.md|.*\.txt|LICENSE.*|NOTICE.*|CHANGES.*|README.*|mvnw.*|\.gitignore|\.gitattributes|checkstyle.*|.*\.yml|.*\.yaml|.*\.properties|.*\.xml)$',path,re.I))
if __name__=='__main__':
 (OUT/'history').mkdir(exist_ok=True); rs=[]
 for u in json.loads((OUT/'upstream.json').read_text()):
  p=u['project'];m=json.loads((OUT/'match'/f'{p}.json').read_text());t=m['sha'];line=git(p,'rev-list','--first-parent','--reverse',u['head']).splitlines();after=line[line.index(t)+1:];ts=tags(p,set(line));rows=[];milestones={};n=0;only=0;other=0;mapped=0
  for idx,s in enumerate(after,1):
   files=git(p,'diff-tree','--no-commit-id','--name-only','-r',s+'^1',s).splitlines(); changed=[x for x in files if x.startswith(m['snapshot_root']+'/')]
   n+=bool(changed);date=git(p,'show','-s','--format=%cI',s)
   if changed and n in [100,200]:milestones[str(n)]={'first_parent_index':idx,'sha':s,'date':date}
   if not changed:
    if files and all(category(x) for x in files):only+=1
    else:other+=1
   if p=='jdom2' and any(x.startswith('core/src/java/') for x in files):mapped+=1
   rows.append([idx,s,date,'yes' if changed else 'no',len(changed),';'.join(ts.get(s,[]))])
  with (OUT/'history'/f'{p}-commits.csv').open('w') as f:
   w=csv.writer(f);w.writerow(['index','sha','date','changes-src-main','files-changed-src-main','tags']);w.writerows(rows)
  release=[{'sha':s,'tags':[x for x in ts[s] if re.search(r'\d',x) and not re.search(r'(RC|alpha|beta)',x,re.I)]} for s in after if s in ts];release=[x for x in release if x['tags']]
  r={'project':p,'after_t':len(after),'date_start':rows[0][2] if rows else None,'date_end':rows[-1][2] if rows else None,'src_main_changes':n,'only_test_doc_build':only,'other_non_src':other,'release_tags':release,'release_tag_count':sum(len(x['tags']) for x in release),'milestones':milestones,'jdom_core_changes':mapped};rs.append(r);print(p,len(after),n,flush=True)
 (OUT/'history.json').write_text(json.dumps(rs,indent=2)+'\n')
 lines=['# History after the matched commit','', 'All values: `python3 docker/stage0_history.py`. Commits are first-parent descendants through the HEAD frozen in upstream.json; changes compare each commit to its first parent (including merges). Release tags point directly to that line, contain a digit, and exclude RC/alpha/beta names. Test/docs/build-only uses the explicit path classifier in the script; all remaining non-src/main commits are reported separately, not silently treated as test-only. Milestone index counts all commits through the specified source-changing commit.','', '| Project | After t | Date span | Change src/main | Only tests/docs/build | Other non-src | Release tags (first five) | 100th source change: index/date | 200th: index/date |','|---|---:|---|---:|---:|---:|---|---|---|']
 for r in rs:
  ms=lambda k: str(r['milestones'][k]['first_parent_index'])+' / '+r['milestones'][k]['date'] if k in r['milestones'] else 'not reached'
  names=[n for x in r['release_tags'] for n in x['tags']]
  lines.append(f"| {r['project']} | {r['after_t']} | {r['date_start']} → {r['date_end']} | {r['src_main_changes']} | {r['only_test_doc_build']} | {r['other_non_src']} | {r['release_tag_count']}: {', '.join(names[:5])} | {ms('100')} | {ms('200')} |")
 j=next(x for x in rs if x['project']=='jdom2');lines+=['',f"JDOM has no upstream src/main. Its actual `core/src/java` changes in {j['jdom_core_changes']} commits (same invocation); literal src/main counts above must not be interpreted as no production evolution."]
 (OUT/'history.md').write_text('\n'.join(lines)+'\n')
