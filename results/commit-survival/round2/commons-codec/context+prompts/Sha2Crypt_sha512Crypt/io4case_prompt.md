As a professional Java software testing expert, your task is to generate test cases for a focal method.

Guidelines:
1. Analyze the method's functionality and identify possible input combinations including normal cases and boundary conditions.
2. Review the provided existing test cases. Avoid duplication and create new test cases for untested input combinations.

Context Provided:
1. The focal method that needs testing.
2. Relevant code and metadata for the class containing the method, including fields and other methods.
3. The existing test cases (in JSON format) to help avoid redundancy.

Output Instructions:
- Organize the test cases by the name of the test function (group).
- Each test case must include:
    - input: A list of parameter names and values. Use an empty list `[]` if no input is required.
    - expected: The return value or expected observable behavior.
    - description: A clear and concise explanation of the scenario and the intent of the test case.

Maintain the following strict JSON format:
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
Here is the code of focal method, `sha512Crypt(byte[])`, with the focal class `Sha2Crypt`:
```Java
package org.apache.commons.codec.digest;
import java.util.regex.Pattern;
import java.util.Random;
import java.util.regex.Matcher;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.util.Arrays;
public class Sha2Crypt {
    static final String SHA256_PREFIX = "$5$";
    static final String SHA512_PREFIX = "$6$";
    public static String sha512Crypt(final byte[] keyBytes) {
        return sha512Crypt(keyBytes, null);
    }
public static String sha256Crypt(final byte[] keyBytes);
    public static String sha256Crypt(final byte[] keyBytes, String salt);
    public static String sha256Crypt(final byte[] keyBytes, String salt, final Random random);
    public static String sha512Crypt(final byte[] keyBytes, String salt);
    public static String sha512Crypt(final byte[] keyBytes, String salt, final Random random);
}
```

@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```

@output{test cases}: output the test cases in the JSON format: