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
Here is the code of focal method, `analyzeMultiText(MultiText, int, int)`, with the focal class `WalkerTRIM`:
```Java
package org.jdom2.output.support;
import org.jdom2.Content;
import org.jdom2.Text;
import java.util.List;
import org.jdom2.Verifier;
public class WalkerTRIM extends AbstractFormattedWalker {
    
    @Override
    protected void analyzeMultiText(final MultiText mtext, int offset, int len) {
        while (len > 0) {
            final Content c = get(offset);
            if (c instanceof Text) {
                // either Text or CDATA
                if (!Verifier.isAllXMLWhitespace(c.getValue())) {
                    break;
                }
            } else {
                break;
            }
            offset++;
            len--;
        } 
        while (len > 0) {
            final Content c = get((offset + len) - 1);
            if (c instanceof Text) {
                // either Text or CDATA
                if (!Verifier.isAllXMLWhitespace(c.getValue())) {
                    break;
                }
            } else {
                break;
            }
            len--;
        } 
        for (int i = 0; i < len; i++) {
            Trim trim = Trim.NONE;
            if ((i + 1) == len) {
                trim = Trim.RIGHT;
            }
            if (i == 0) {
                trim = Trim.LEFT;
            }
            if (len == 1) {
                trim = Trim.BOTH;
            }
            final Content c = get(offset + i);
            switch (c.getCType()) {
                case Text :
                    mtext.appendText(trim, c.getValue());
                    break;
                case CDATA :
                    mtext.appendCDATA(trim, c.getValue());
                    break;
                case EntityRef :
                    // treat like any other content.
                    // raw.
                default :
                    mtext.appendRaw(c);
                    break;
            }
        }
    }
;
}
```
@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```

@output{test cases}: output the test cases in the JSON format: