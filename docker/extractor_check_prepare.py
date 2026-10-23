#!/usr/bin/env python3
"""Prepare initial commons-cli extractor inputs in scratch; run before index checks."""
import io,json,shutil,subprocess,tarfile
from stage0_upstream import ROOT,SCR
if __name__=='__main__':
 dataset=SCR/'extractor-check/cli-snapshot';dest=dataset/'projects/commons-cli';dest.mkdir(parents=True,exist_ok=True)
 archive=subprocess.check_output(['git','-C',str(SCR/'upstream/commons-cli'),'archive','90b3f2e'])
 tarfile.open(fileobj=io.BytesIO(archive)).extractall(dest,filter='data')
 for rel in ['libs','dependencies.txt','src/test-original']:
  source=ROOT/'data/projects/commons-cli'/rel;target=dest/rel
  if source.is_dir():shutil.copytree(source,target,dirs_exist_ok=True)
  else:shutil.copy2(source,target)
 original=json.loads((ROOT/'data/dataset_info.json').read_text())
 (dataset/'dataset_info.json').write_text(json.dumps({'commons-cli':original['commons-cli']},indent=2)+'\n')
 print('Prepared initial commons-cli dataset. DatasetPrepare later replaces it with the enlarged population.')
