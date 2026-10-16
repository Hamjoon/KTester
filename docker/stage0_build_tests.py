#!/usr/bin/env python3
"""Run inside compose container; preserve clean upstream source and Maven logs."""
import concurrent.futures,io,json,os,pathlib,signal,subprocess,tarfile,time,sys,xml.etree.ElementTree as ET
ROOT=pathlib.Path('/work');OUT=ROOT/'results/stage0-survey';SCR=ROOT/'scratch';PROJECTS=['commons-codec','commons-collections','gson','jdom2','ruler']
def execute(cmd,cwd,log,timeout):
 start=time.monotonic()
 with log.open('w') as f:
  proc=subprocess.Popen(cmd,cwd=cwd,stdout=f,stderr=subprocess.STDOUT,start_new_session=True)
  try:rc=proc.wait(timeout=max(1,timeout))
  except subprocess.TimeoutExpired:os.killpg(proc.pid,signal.SIGKILL);proc.wait();rc=124;f.write('\nTIMEOUT: project time budget exhausted.\n')
 return {'command':' '.join(cmd),'cwd':str(cwd),'exit_code':rc,'seconds':round(time.monotonic()-start,3),'log':str(log.relative_to(ROOT))}
def work(p):
 start=time.monotonic();dest=SCR/'build'/p;dest.mkdir(parents=True,exist_ok=True);m=json.loads((OUT/'match'/f'{p}.json').read_text());arc=subprocess.check_output(['git','-C',str(SCR/'upstream'/p),'archive',m['sha']]);tarfile.open(fileobj=io.BytesIO(arc)).extractall(dest,filter='data');logs=OUT/'build';logs.mkdir(exist_ok=True)
 base=['mvn','-q'];pkg=base+['-DskipTests','-Drat.skip','-Dcheckstyle.skip','-Dspotbugs.skip','-Dpmd.skip','-Dmaven.javadoc.skip','package']
 runs=[execute(pkg,dest,logs/f'{p}-package.txt',1800-(time.monotonic()-start))]
 if time.monotonic()-start<1800:runs.append(execute(base+['test'],dest,logs/f'{p}-test.txt',1800-(time.monotonic()-start)))
 files=list(dest.rglob('surefire-reports/TEST-*.xml'));counts={k:0 for k in ['tests','failures','errors','skipped']};slow=[];suites=[]
 for f in files:
  e=ET.parse(f).getroot()
  for k in counts:counts[k]+=int(e.get(k,0))
  if float(e.get('time',0))>60:suites.append({'name':e.get('name'),'seconds':float(e.get('time'))})
  for t in e.iter('testcase'):
   if float(t.get('time',0))>60:slow.append({'name':t.get('classname','')+'#'+t.get('name',''),'seconds':float(t.get('time'))})
 r={'project':p,'sha':m['sha'],'jdk':subprocess.check_output(['java','-version'],stderr=subprocess.STDOUT,text=True).strip(),'maven':subprocess.check_output(['mvn','-version'],stderr=subprocess.STDOUT,text=True).strip(),'runs':runs,'surefire_xml_files':len(files),'counts':counts,'slow_testcases':slow,'slow_suites':suites,'total_seconds':round(time.monotonic()-start,3),'second_test_run':'not run; no flakiness claim'}
 (logs/f'{p}.json').write_text(json.dumps(r,indent=2)+'\n');print(p,[(x['exit_code'],x['seconds']) for x in runs],counts,flush=True);return r
def repeat(r):
 if not r['surefire_xml_files'] or r['runs'][-1]['exit_code']!=0:return r
 p=r['project'];run=execute(['mvn','-q','test'],SCR/'build'/p,OUT/'build'/f'{p}-test-repeat.txt',max(1,1800-r['total_seconds']))
 counts={k:0 for k in ['tests','failures','errors','skipped']};files=list((SCR/'build'/p).rglob('surefire-reports/TEST-*.xml'))
 for f in files:
  e=ET.parse(f).getroot()
  for k in counts:counts[k]+=int(e.get(k,0))
 r['repeat']={**run,'counts':counts,'surefire_xml_files':len(files),'same_counts_as_first':counts==r['counts']};r['second_test_run']='recorded in repeat';r['total_seconds']=round(r['total_seconds']+run['seconds'],3)
 (OUT/'build'/f'{p}.json').write_text(json.dumps(r,indent=2)+'\n');print(p,'repeat',run['exit_code'],counts,flush=True);return r
if __name__=='__main__' and '--repeat-existing' in sys.argv:
 rs=json.loads((OUT/'build-at-t.json').read_text())
 with concurrent.futures.ThreadPoolExecutor(max_workers=2) as pool:rs=list(pool.map(repeat,rs))
 (OUT/'build-at-t.json').write_text(json.dumps(rs,indent=2)+'\n');sys.exit(0)
if __name__=='__main__':
 with concurrent.futures.ThreadPoolExecutor(max_workers=2) as pool:rs=list(pool.map(work,PROJECTS))
 (OUT/'build-at-t.json').write_text(json.dumps(rs,indent=2)+'\n')
 lines=['# Build and test at t','','Invocation: from `docker/`, `docker compose run --rm ktester bash -c \'python /work/docker/stage0_build_tests.py\'`. Every command, elapsed time and environment is recorded in `build/<project>.json`; full logs accompany it. Clean git archives are under scratch/build. Shared Maven cache: existing compose `ktester-m2` volume at /root/.m2. Each project has a combined 30-minute budget; each test invocation is bounded by the remaining budget. Two projects run concurrently, so timings include contention. Tests run once; flakiness was not measured. Zero XML files means no executed-test evidence, not an empty developer suite.','','| Project | Package exit / seconds | Test exit / seconds | Tests / failures / errors / skipped | Surefire XML files |','|---|---|---|---|---:|']
 for r in rs:
  a=r['runs'][0];b=r['runs'][1] if len(r['runs'])>1 else {'exit_code':'not run','seconds':''};c=r['counts'];lines.append(f"| {r['project']} | {a['exit_code']} / {a['seconds']} | {b['exit_code']} / {b['seconds']} | {c['tests']} / {c['failures']} / {c['errors']} / {c['skipped']} | {r['surefire_xml_files']} |")
 lines+=['','## Environment and repeated-loop considerations','','JDK: OpenJDK 17 (exact version in each JSON); Maven 3.9.9. Maven may download dependencies/plugins on initial execution; retain the shared cache. Quiet logs do not enumerate successful downloads. Surefire XML identifies slow test cases/suites in build JSON. Plugin configuration and failure diagnoses are recorded below after log inspection.']
 (OUT/'build-at-t.md').write_text('\n'.join(lines)+'\n')
