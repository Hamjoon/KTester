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
Here is the code of focal method, `md5Crypt(byte[])`, with the focal class `Md5Crypt`:
```Java
package org.apache.commons.codec.digest;
import java.util.regex.Pattern;
import java.util.Random;
import java.util.regex.Matcher;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.MessageDigest;
import java.util.Arrays;
public class Md5Crypt {
    static final String APR1_PREFIX = "$apr1$";
    static final String MD5_PREFIX = "$1$";
    public static String md5Crypt(final byte[] keyBytes) {
        return md5Crypt(keyBytes, MD5_PREFIX + B64.getRandomSalt(8));
    }
public static String apr1Crypt(final byte[] keyBytes);
    public static String apr1Crypt(final byte[] keyBytes, String salt);
    public static String apr1Crypt(final byte[] keyBytes, final Random random);
    public static String apr1Crypt(final String keyBytes);
    public static String apr1Crypt(final String keyBytes, final String salt);
    public static String md5Crypt(final byte[] keyBytes, final String salt);
    public static String md5Crypt(final byte[] keyBytes, final String salt, final String prefix);
    public static String md5Crypt(final byte[] keyBytes, final String salt, final String prefix, final Random random);
    public static String md5Crypt(final byte[] keyBytes, final Random random);
}
```
@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```

@output{test cases}: output the test cases in the JSON format: