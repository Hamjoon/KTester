#!/usr/bin/env python3
"""Count parsed Java declarations in clean upstream exports, never author snapshots."""
import json,pathlib,re,subprocess,sys,tarfile,io,time
from stage0_upstream import ROOT,SCR,OUT
sys.path.insert(0,str(SCR/'python'))
from tree_sitter import Language,Parser
import tree_sitter_java
PARSER=Parser(Language(tree_sitter_java.language()))
def walk(n):
 yield n
 for c in n.children:yield from walk(c)
def text(n):return n.text.decode(errors='replace') if n else ''
def analyze(base,tests=False):
 r={'java_files':0,'classes':0,'types':0,'public':0,'protected':0,'package_private':0,'private_excluded':0,'bodyless_excluded':0,'parse_error_files':[],'Test':0,'ParameterizedTest':0,'RepeatedTest':0,'TestFactory':0,'abstract_classes':0,'junit3_test_methods':0,'suite_factories':0,'imports':set(),'class_names':set(),'test_classes':0}
 for f in sorted(base.rglob('*.java')):
  r['java_files']+=1;b=f.read_bytes();tree=PARSER.parse(b);nodes=list(walk(tree.root_node));pkg=next((text(n).removeprefix('package ').rstrip(';').strip() for n in nodes if n.type=='package_declaration'),'')
  if tree.root_node.has_error:r['parse_error_files'].append(str(f))
  for n in nodes:
   if n.type=='import_declaration' and ('junit' in text(n) or 'testng' in text(n)):r['imports'].add(text(n))
   if n.type in ['class_declaration','enum_declaration','interface_declaration','annotation_type_declaration','record_declaration']:
    r['types']+=1
    if n.type=='class_declaration':
     r['classes']+=1;names=[text(n.child_by_field_name('name'))];parent=n.parent
     while parent:
      if parent.type in ['class_declaration','interface_declaration','enum_declaration']:names.insert(0,text(parent.child_by_field_name('name')))
      parent=parent.parent
     r['class_names'].add(pkg+'.'+'.'.join(names))
     mods=' '.join(text(c) for c in n.children if c.type=='modifiers');r['abstract_classes']+=bool(re.search(r'\babstract\b',mods))
     methods=[c for c in walk(n) if c.type=='method_declaration']
     if any(re.search(r'@(Test|ParameterizedTest|RepeatedTest|TestFactory)\b',text(x)) or text(x.child_by_field_name('name')).startswith('test') for x in methods) or re.search(r'extends\s+\w*Test',text(n)[:500]):r['test_classes']+=1
   if n.type in ['marker_annotation','annotation']:
    name=text(n.child_by_field_name('name')).split('.')[-1]
    if name in ['Test','ParameterizedTest','RepeatedTest','TestFactory']:r[name]+=1
   if n.type!='method_declaration':continue
   name=text(n.child_by_field_name('name'));mods=' '.join(text(c) for c in n.children if c.type=='modifiers')
   if name=='suite':r['suite_factories']+=1
   if name.startswith('test') and re.search(r'\bpublic\b',mods):r['junit3_test_methods']+=1
   if n.child_by_field_name('body') is None:r['bodyless_excluded']+=1;continue
   if re.search(r'\bprivate\b',mods):r['private_excluded']+=1;continue
   kind='public' if re.search(r'\bpublic\b',mods) else 'protected' if re.search(r'\bprotected\b',mods) else 'package_private'
   parent=n.parent
   if kind=='package_private' and parent and parent.parent and parent.parent.type=='interface_declaration':kind='public'
   r[kind]+=1
 r['imports']=sorted(r['imports']);r['class_names']=sorted(r['class_names']);r['nonprivate_methods']=sum(r[k] for k in ['public','protected','package_private']);return r
if __name__=='__main__':
 dataset=json.loads((ROOT/'data/dataset_info.json').read_text());rs=[]
 for u in json.loads((OUT/'upstream.json').read_text()):
  p=u['project'];m=json.loads((OUT/'match'/f'{p}.json').read_text());dest=SCR/'at-t'/p;dest.mkdir(parents=True,exist_ok=True)
  archive=subprocess.check_output(['git','-C',str(SCR/'upstream'/p),'archive',m['sha']]);tarfile.open(fileobj=io.BytesIO(archive)).extractall(dest,filter='data')
  main=dest/('gson/src/main/java' if p=='gson' else 'core/src/java' if p=='jdom2' else 'src/main' if p=='ruler' else 'src/main/java');test=dest/('gson/src/test/java' if p=='gson' else 'test/src/java' if p=='jdom2' else 'src/test' if p=='ruler' else 'src/test/java')
  a=analyze(main);b=analyze(test,True);fc={x['class'] for x in dataset[p]['focal-methods']};r={'project':p,'main_root':str(main.relative_to(dest)),'test_root':str(test.relative_to(dest)),'production':a,'tests':b,'dataset_focal_classes':len(fc),'focal_classes_present':len(fc&set(a['class_names'])),'focal_methods':len(dataset[p]['focal-methods'])};rs.append(r);print(p,a['nonprivate_methods'],b['Test'],flush=True)
 (OUT/'sizes.json').write_text(json.dumps(rs,indent=2)+'\n')
 lines=['# Developer suite and production population at t','','All counts: `python3 docker/stage0_count_methods.py`. Parser installed with `python3 -m pip install --target scratch/python tree-sitter==0.25.2 tree-sitter-java==0.23.5`. Counts use Java syntax trees from clean `git archive` exports. Constructors, private methods and bodyless methods are excluded; nested/local/anonymous method declarations are included. Classes count named class declarations including nested/local classes; types additionally include interfaces/enums/annotations/records. Test classes are a heuristic: class containing a test annotation/test-prefixed method, or extending a Test-named class. JUnit-3-style method and suite() counts are signals, not executed counts. Annotations in comments/strings are excluded. JDOM counts use its actual upstream roots (`core/src/java`, `test/src/java`); literal src/main/java and src/test/java are absent.','','| Project | Test Java files / test classes | @Test | @ParameterizedTest / @RepeatedTest / @TestFactory | Abstract test classes / suite() | JUnit imports | Non-private methods (public/protected/package) | Production classes / types | Focal classes present / dataset | Parse-error files |','|---|---|---:|---|---|---|---|---|---|---:|']
 for r in rs:
  a=r['production'];b=r['tests'];framework=','.join(x for x,pat in [('JUnit 3','junit.framework'),('JUnit 4','org.junit.Test'),('JUnit 5','org.junit.jupiter'),('TestNG','org.testng')] if any(pat in i for i in b['imports'])) or 'see imports in sizes.json'
  lines.append(f"| {r['project']} | {b['java_files']} / {b['test_classes']} | {b['Test']} | {b['ParameterizedTest']} / {b['RepeatedTest']} / {b['TestFactory']} | {b['abstract_classes']} / {b['suite_factories']} | {framework} | {a['nonprivate_methods']} ({a['public']}/{a['protected']}/{a['package_private']}) | {a['classes']} / {a['types']} | {r['focal_classes_present']} / {r['dataset_focal_classes']} | {len(a['parse_error_files'])+len(b['parse_error_files'])} |")
 lines+=['','Abstract classes, inherited tests and suite factories (especially commons-collections), JUnit 3 naming and parameterized tests make annotation counts unsuitable as executed-test counts. Full import evidence and JUnit-3-style method counts are in sizes.json.']
 lines+=['','Ruler uses src/main and src/test directly as declared by its upstream POM; src/main/java is absent upstream.']
 (OUT/'sizes.md').write_text('\n'.join(lines)+'\n')
