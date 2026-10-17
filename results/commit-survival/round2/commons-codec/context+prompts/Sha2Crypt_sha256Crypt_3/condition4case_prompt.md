As a professional Java software testing expert, your task is to generate test cases for a focal method.

You will be provided with:
1. The focal method that needs testing.
2. Basic information of the target class to which the focus method belongs, including fields and methods.

Ensure the generated test cases meet the following requirements:

Test Coverage Requirements:
- Create test cases to cover all conditional branches in the target method, including `if`, `else if`, `else`, `switch`, and `case` statements.

Output Format:
- Organize the test cases by its functions logically.
- Include input parameters, expected output, and a description for each test case.
- Use empty list `[]` for the input when a test case does not require input parameters.
- Provide an expected return value for each test case, or specify the behavior that should be verified if there's no return value.
- Clearly describe the intention of each test case in the description.

Follow the JSON format provided here for the output:
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
Here is the code of focal method, `sha256Crypt(byte[], String, Random)`, with the focal class `Sha2Crypt`:
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
    private static final int SHA256_BLOCKSIZE = 32;
    static final String SHA256_PREFIX = "$5$";
    static final String SHA512_PREFIX = "$6$";
    public static String sha256Crypt(final byte[] keyBytes, String salt, final Random random) {
        if (salt == null) {
            salt = SHA256_PREFIX + B64.getRandomSalt(8, random);
        }
        return sha2Crypt(keyBytes, salt, SHA256_PREFIX, SHA256_BLOCKSIZE, MessageDigestAlgorithms.SHA_256);
    }
public static String sha256Crypt(final byte[] keyBytes);
    public static String sha256Crypt(final byte[] keyBytes, String salt);
    private static String sha2Crypt(final byte[] keyBytes, final String salt, final String saltPrefix, final int blocksize, final String algorithm);
    public static String sha512Crypt(final byte[] keyBytes);
    public static String sha512Crypt(final byte[] keyBytes, String salt);
    public static String sha512Crypt(final byte[] keyBytes, String salt, final Random random);
}
```

@output{test cases}: output the test cases in the JSON format: