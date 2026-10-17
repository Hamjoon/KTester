#!/usr/bin/env python3
"""Enumerate the fixed Codec population at t with the bundled Spoon parser.

A conflicting fixed decision stops before DatasetPrepare or any metadata generation.
No settings, model modules, authors' datasets, or project indexes are modified.
"""
import collections,csv,hashlib,json,pathlib,subprocess,sys,time
sys.dont_write_bytecode=True
import jpype
KT=pathlib.Path(__file__).resolve().parents[1]
REPO=pathlib.Path('/repo');EXP=REPO/'experiments/ktester-commit-survival';OUT=KT/'data/commit-survival/commons-codec'
BASE='cd16988dd46ac7533771eb6856a5f56c7aeaffba'
SCOPE=['org.apache.commons.codec.binary.Base32','org.apache.commons.codec.binary.Base64','org.apache.commons.codec.digest.Md5Crypt','org.apache.commons.codec.digest.MurmurHash3','org.apache.commons.codec.digest.Sha2Crypt']

def signature(method):
 return str(method.getSimpleName())+'('+', '.join(str(p.getType().getSimpleName()) for p in method.getParameters())+')'

if __name__=='__main__':
 start=time.monotonic();original_path=KT/'data/dataset_info.json';original_hash=hashlib.sha256(original_path.read_bytes()).hexdigest();original=json.loads(original_path.read_text())['commons-codec']['focal-methods'];scoped_original=[m for m in original if m['class'] in SCOPE]
 assert all(any(m['class']==c for m in original) for c in SCOPE)
 upstream=EXP/'work/t';inputs={}
 for cls in SCOPE:
  path='src/main/java/'+cls.replace('.','/')+'.java';raw=(upstream/path).read_bytes();expected=subprocess.check_output(['git','-C',str(REPO),'show',BASE+':'+path]);assert raw==expected
  inputs[cls]={'path':path,'sha256':hashlib.sha256(raw).hexdigest()}
 jpype.startJVM(jpype.getDefaultJVMPath(),'-Xmx4g',classpath=[str(KT/'code/Java/project-info-process.jar'),str(KT/'code/Java/project-index-builder.jar')])
 extractor=jpype.JClass('extractor.SpoonExtractor')([str(upstream/'src/main/java')],[str(KT/'data/projects/commons-codec/libs')])
 rows=[];declarations={};excluded=[]
 for cls in SCOPE:
  ct=extractor.getCtClass(cls);assert ct is not None,cls
  for method in sorted(ct.getMethods(),key=lambda m:m.getPosition().getLine()):
   assert str(method.getDeclaringType().getQualifiedName())==cls
   sig=signature(method);vis='private' if method.isPrivate() else 'protected' if method.isProtected() else 'public' if method.isPublic() else 'package-private';eligible=not method.isPrivate() and not method.isAbstract() and method.getBody() is not None
   data={'class':cls,'method':str(method.getSimpleName()),'parameter-types':', '.join(str(p.getType().getSimpleName()) for p in method.getParameters()),'visibility':vis,'line':int(method.getPosition().getLine()),'in-dataset':'yes' if any(m['class']==cls and m['method-name']==sig for m in scoped_original) else 'no'}
   declarations[(cls,sig)]={**data,'eligible':eligible,'signature':sig}
   if eligible:rows.append(data)
   else:excluded.append({**data,'signature':sig,'reason':'private' if method.isPrivate() else 'abstract/bodyless'})
 OUT.mkdir(parents=True,exist_ok=True);(EXP/'population').mkdir(exist_ok=True)
 for destination in [OUT/'candidates.csv',EXP/'population/candidates.csv']:
  with destination.open('w',newline='') as f:
   writer=csv.DictWriter(f,fieldnames=['class','method','parameter-types','visibility','line','in-dataset'],lineterminator='\n');writer.writeheader();writer.writerows(rows)
 counts=dict(collections.Counter(r['class'] for r in rows));groups=collections.defaultdict(list)
 for r in rows:groups[r['class']+'.'+r['method']].append(r['method']+'('+r['parameter-types']+')')
 overloads={k:v for k,v in groups.items() if len(v)>1};checks=[]
 for m in scoped_original:
  decl=declarations.get((m['class'],m['method-name']));checks.append({'id':m['id'],'class':m['class'],'method-name':m['method-name'],'declaration_at_t':decl,'included':bool(decl and decl['eligible'])})
 missing=[m for m in checks if not m['included']]
 # Manual cross-check performed by reading these upstream declarations; assertion makes it reproducible.
 manual=['sha256Crypt(byte[])','sha256Crypt(byte[], String)','sha256Crypt(byte[], String, Random)','sha512Crypt(byte[])','sha512Crypt(byte[], String)','sha512Crypt(byte[], String, Random)']
 actual=[r['method']+'('+r['parameter-types']+')' for r in rows if r['class']==SCOPE[-1]];assert actual==manual
 result={'base':BASE,'parser':'bundled Spoon via extractor.SpoonExtractor','source':'upstream clean export at t; byte-checked against git show','source_inputs':inputs,'authors_dataset_sha256':original_hash,'total':len(rows),'per_class':counts,'within_60_to_100':60<=len(rows)<=100,'dataset_total_focal_methods':len(original),'scoped_original_count':len(scoped_original),'original_methods':checks,'excluded_declarations':excluded,'overload_groups':overloads,'overload_group_count':len(overloads),'methods_in_overload_groups':sum(map(len,overloads.values())),'manual_check':{'class':SCOPE[-1],'expected_signatures':manual,'matches_parser':True},'missing_required_original_methods':missing,'dataset_prepare_run':False,'generated_id_uniqueness':'not checked; stopped before DatasetPrepare','seconds':round(time.monotonic()-start,3)}
 result['status']='blocked_fixed_decisions_conflict' if missing else 'blocked_population_bound' if not result['within_60_to_100'] else 'enumeration_complete'
 assert hashlib.sha256(original_path.read_bytes()).hexdigest()==original_hash
 for destination in [OUT/'population-enumeration.json',EXP/'population/population-enumeration.json']:destination.write_text(json.dumps(result,indent=2)+'\n')
 lines=['# Fixed focal-method population — enumeration and stop report','','Status: **blocked by conflicting fixed decisions**. No DatasetPrepare run, dataset_meta.json, or dataset_info.json was produced. The candidates below satisfy the non-private rule, but exclude required original focal methods that are private. They are not a generation-ready population.','','All counts, signatures and line numbers: from KTester/docker, `docker compose run --rm -v /Users/donggi/_projects/experiment-projects/commons-codec:/repo ktester bash -c \'python /work/docker/part1_codec_population.py\'`. Evidence: population-enumeration.json and candidates.csv. Every scoped file in work/t was verified byte-for-byte against `git show '+BASE+':<source-path>` before parsing.','','Spoon is loaded through the existing project-info-process.jar and project-index-builder.jar, following the Stage 0 JVM setup. It reads upstream src/main/java at t and uses the authors’ libs only as a read-only parsing classpath. CtClass.getMethods supplies declarations of the named outer class; private, abstract and bodyless methods are excluded. Constructors and nested-class methods are not included. DatasetPrepare would need the authors’ snapshot through a local symlink as instructed; that stage has not been entered.','','| Class | Eligible methods |','|---|---:|']
 for cls in SCOPE:lines.append(f'| {cls} | {counts.get(cls,0)} |')
 lines+=['',f"Total eligible methods: {len(rows)}; within the required interval: {result['within_60_to_100']}. Existing scoped focal methods: {len(scoped_original)} of {len(original)} total dataset focal methods. Eligible existing focal methods: {len(scoped_original)-len(missing)}. Overload groups: {len(overloads)}, containing {result['methods_in_overload_groups']} methods (same invocation).",'', '## Existing dataset membership','','Every fixed class occurs in the authors’ dataset. Per-method declarations at t:','','| Original id | Class and method | Visibility at t | Line | Included |','|---|---|---|---:|---|']
 for check in checks:
  d=check['declaration_at_t'];lines.append(f"| {check['id']} | {check['class']}.{check['method-name']} | {d['visibility'] if d else 'missing'} | {d['line'] if d else ''} | {check['included']} |")
 lines+=['','## Manual cross-check','','Read upstream Sha2Crypt.java and counted these eligible public declarations; the parser result is asserted against this explicit list:','']
 for sig in manual:lines.append('- `'+sig+'`')
 lines+=['','The private sha2Crypt helper is intentionally excluded by the non-private rule. This independently explains the eligible count rather than silently including it to preserve dataset membership.','','## Overload groups (pre-generation)','','| Class and method | Overloads |','|---|---:|']
 for group,methods in overloads.items():lines.append(f'| {group} | {len(methods)} |')
 lines+=['','The Stage 0 duplicate-ID warning still applies. Generated-ID uniqueness and field-by-field author-entry comparison were not attempted because the fixed population is already inconsistent. No Java source was patched, no private method was added, and no existing focal method was silently dropped.','','## Required resolution','','Part 1 says decisions must not be reopened and directs a stop if one is impossible. It simultaneously requires every existing scoped focal method to belong to the population and excludes private methods. The private declarations listed above cannot meet both requirements. This report and candidates.csv preserve evidence only.','','TODO (Gary): Resolve whether the two original private focal methods should be excluded from the inclusion requirement or whether the non-private rule should change. Once clarified, resume the population driver and then the mandated DatasetPrepare collision/missing-entry checks.']
 for destination in [OUT/'README.md',EXP/'population/README.md']:destination.write_text('\n'.join(lines)+'\n')
 print(json.dumps({'status':result['status'],'total':result['total'],'per_class':counts,'missing_required_original_methods':[m['id'] for m in missing],'overload_groups':len(overloads)},indent=2));jpype.shutdownJVM()
 if missing or not result['within_60_to_100']:raise SystemExit('STOP: fixed population requirements cannot simultaneously be satisfied; do not generate metadata or patch pipeline.')
