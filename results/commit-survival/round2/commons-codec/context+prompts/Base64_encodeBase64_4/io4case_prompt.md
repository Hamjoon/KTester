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
Here is the code of focal method, `encodeBase64(byte[], boolean, boolean, int)`, with the focal class `Base64`:
```Java
package org.apache.commons.codec.binary;
import java.math.BigInteger;
import java.util.Objects;
import org.apache.commons.codec.CodecPolicy;
import java.util.Arrays;
public class Base64 extends BaseNCodec {
    
    public static byte[] encodeBase64(final byte[] binaryData, final boolean isChunked, final boolean urlSafe, final int maxResultSize) {
        if (BinaryCodec.isEmpty(binaryData)) {
            return binaryData;
        }
        // Create this so can use the super-class method
        // Also ensures that the same roundings are performed by the ctor and the code
        final Base64 b64 = (isChunked) ? new Base64(urlSafe) : new Base64(0, CHUNK_SEPARATOR, urlSafe);
        final long len = b64.getEncodedLength(binaryData);
        if (len > maxResultSize) {
            throw new IllegalArgumentException((("Input array too big, the output array would be bigger (" + len) + ") than the specified maximum size of ") + maxResultSize);
        }
        return b64.encode(binaryData);
    }
public static Builder builder();
    @Override
void decode(final byte[] input, int inPos, final int inAvail, final Context context);
    public static byte[] decodeBase64(final byte[] base64Data);
    public static byte[] decodeBase64(final String base64String);
    public static BigInteger decodeInteger(final byte[] pArray);
    @Override
void encode(final byte[] in, int inPos, final int inAvail, final Context context);
    public static byte[] encodeBase64(final byte[] binaryData);
    public static byte[] encodeBase64(final byte[] binaryData, final boolean isChunked);
    public static byte[] encodeBase64(final byte[] binaryData, final boolean isChunked, final boolean urlSafe);
    public static byte[] encodeBase64Chunked(final byte[] binaryData);
    public static String encodeBase64String(final byte[] binaryData);
    public static byte[] encodeBase64URLSafe(final byte[] binaryData);
    public static String encodeBase64URLSafeString(final byte[] binaryData);
    public static byte[] encodeInteger(final BigInteger bigInteger);
    byte[] getLineSeparator();
    public static boolean isBase64(final byte octet);
    public static boolean isBase64(final byte[] arrayOctet);
    public static boolean isBase64(final String base64);
    @Override
protected boolean isInAlphabet(final byte octet);
    public boolean isUrlSafe();
    static byte[] toIntegerBytes(final BigInteger bigInt);
}
```

@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```

@output{test cases}: output the test cases in the JSON format: