#!/usr/bin/env python3
"""Exercise DatasetPrepare twice using scratch-only metadata and outputs."""
import json,pathlib,re,time
import jpype
from stage0_build_index import ROOT,start_jvm
DS=ROOT/'scratch/extract-check';OUT=ROOT/'results/stage0-survey'
def compare_entry(old,new):
 results={}
 for key in old.keys()|new.keys():
  a=old.get(key);b=new.get(key)
  if a==b:kind='identical'
  elif isinstance(a,str) and isinstance(b,str) and a.replace('\r\n','\n')==b.replace('\r\n','\n'):kind='line-endings only'
  elif isinstance(a,str) and isinstance(b,str) and re.sub(r'\s+','',a)==re.sub(r'\s+','',b):kind='whitespace-insensitive equal'
  else:kind='content difference'
  results[key]={'comparison':kind,'author_characters':len(a) if isinstance(a,str) else None,'generated_characters':len(b) if isinstance(b,str) else None}
 return results
if __name__=='__main__':
 start=time.monotonic();original=json.loads((ROOT/'data/dataset_info.json').read_text())['commons-cli'];link=DS/'commons-cli'
 if not link.exists():link.symlink_to('projects/commons-cli',target_is_directory=True)
 methods={m['class']+'.'+m['method-name']:i for i,m in enumerate(original['focal-methods'])};meta=[{'project_name':'commons-cli','put_path':'/root/experiments/puts/commons-cli','method_name_to_idx':methods}]
 (DS/'dataset_meta.json').write_text(json.dumps(meta,indent=2)+'\n');start_jvm();jpype.JClass('DatasetPrepare').main([str(DS)])
 generated=json.loads((DS/'dataset_info.json').read_text())['commons-cli'];(OUT/'dataset-prepare-existing.json').write_text(json.dumps(generated,indent=2)+'\n')
 by_id={m['id']:m for m in generated['focal-methods']};result={'original_count':len(original['focal-methods']),'generated_count':len(by_id),'project_fields':{k:original[k]==generated.get(k) for k in ['project-name','project-url']},'fields':{m['id']:compare_entry(m,by_id.get(m['id'],{})) for m in original['focal-methods']}}
 methods['org.apache.commons.cli.Options.hasOption(String)']=len(methods);(DS/'dataset_meta.json').write_text(json.dumps(meta,indent=2)+'\n');jpype.JClass('DatasetPrepare').main([str(DS)])
 enlarged=json.loads((DS/'dataset_info.json').read_text())['commons-cli'];(OUT/'dataset-prepare-enlarged.json').write_text(json.dumps(enlarged,indent=2)+'\n');added=[m for m in enlarged['focal-methods'] if m['id'] not in by_id];result['enlarged_count']=len(enlarged['focal-methods']);result['added_methods']=[{'id':m['id'],'method':m['method-name'],'nonempty_focal_method':bool(m.get('focal-method')),'nonempty_class_code':bool(m.get('class-code'))} for m in added];result['seconds']=round(time.monotonic()-start,3)
 (OUT/'dataset-prepare-check.json').write_text(json.dumps(result,indent=2)+'\n');print(json.dumps(result,indent=2));jpype.shutdownJVM()
