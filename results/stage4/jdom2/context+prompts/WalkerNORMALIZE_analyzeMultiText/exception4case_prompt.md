You are a professional software tester. Write test cases for a focal method in a target class.

**Instructions:**
1. Analyze method behavior and identify ALL possible exception scenarios
2. Design test cases that trigger each identified exception
3. Follow existing test case structure, Avoid duplication and create new test cases for uncovered exception scenarios

Test Case Format:
1. Group test cases by test function name
2. Each test case MUST include:
   - Input parameters with values (use empty list `[]` if none)
   - Expected exception or observable behavior
   - Clear description of test scenario
3. Return empty array if no exception scenarios found

Output Schema (STRICT JSON format):
```json
[
    {
        "group": "test name",
        "cases": [
            {
                "input": [
                    {
                        "parameter": "param name",
                        "value": "param value"
                    }
                ],
                "expected": "expected exception or behavior",
                "description": "test scenario description"
            }
        ]
    },
    {...}
]
```

@input{focal method and target class}
Here is the code of focal method, `analyzeMultiText(MultiText, int, int)`, with the focal class `WalkerNORMALIZE`:
```Java
package org.jdom2.output.support;
import org.jdom2.Content;
import java.util.List;
import org.jdom2.Verifier;
public class WalkerNORMALIZE extends AbstractFormattedWalker {
    
    @Override
    protected void analyzeMultiText(final MultiText mtext, final int offset, final int len) {
        boolean needspace = false;
        boolean between = false;
        String ttext = null;
        for (int i = 0; i < len; i++) {
            final Content c = get(offset + i);
            switch (c.getCType()) {
                case Text :
                    ttext = c.getValue();
                    if (Verifier.isAllXMLWhitespace(ttext)) {
                        if (between && (ttext.length() > 0)) {
                            needspace = true;
                        }
                    } else {
                        if (between && (needspace || isSpaceFirst(ttext))) {
                            mtext.appendText(Trim.NONE, " ");
                        }
                        mtext.appendText(Trim.COMPACT, ttext);
                        between = true;
                        needspace = isSpaceLast(ttext);
                    }
                    break;
                case CDATA :
                    ttext = c.getValue();
                    if (Verifier.isAllXMLWhitespace(ttext)) {
                        if (between && (ttext.length() > 0)) {
                            needspace = true;
                        }
                    } else {
                        if (between && (needspace || isSpaceFirst(ttext))) {
                            mtext.appendText(Trim.NONE, " ");
                        }
                        mtext.appendCDATA(Trim.COMPACT, ttext);
                        between = true;
                        needspace = isSpaceLast(ttext);
                    }
                    break;
                case EntityRef :
                    // treat like any other content.
                    // raw.
                default :
                    ttext = null;
                    if (between && needspace) {
                        mtext.appendText(Trim.NONE, " ");
                    }
                    mtext.appendRaw(c);
                    between = true;
                    needspace = false;
                    break;
            }
        }
    }
private boolean isSpaceFirst(String text);
    private boolean isSpaceLast(String text);
}
```
@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```

@output{test cases}: output the test cases in the JSON format: