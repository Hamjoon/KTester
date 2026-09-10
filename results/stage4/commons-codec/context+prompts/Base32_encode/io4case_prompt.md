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
Here is the code of focal method, `encode(byte[], int, int, Context)`, with the focal class `Base32`:
```Java
package org.apache.commons.codec.binary;
import java.util.Objects;
import org.apache.commons.codec.CodecPolicy;
public class Base32 extends BaseNCodec {
    private static final int BYTES_PER_ENCODED_BLOCK = 8;
    private static final int BYTES_PER_UNENCODED_BLOCK = 5;
    private static final int MASK_5BITS = 0x1f;
    private final int encodeSize;
    private final byte[] encodeTable;
    private final byte[] lineSeparator;
    @Override
    void encode(final byte[] input, int inPos, final int inAvail, final Context context) {
        // package protected for access from I/O streams
        if (context.eof) {
            return;
        }
        // inAvail < 0 is how we're informed of EOF in the underlying data we're
        // encoding.
        if (inAvail < 0) {
            context.eof = true;
            if ((0 == context.modulus) && (lineLength == 0)) {
                return;// no leftovers to process and not using chunking
    
            }
            final byte[] buffer = ensureBufferSize(encodeSize, context);
            final int savedPos = context.pos;
            switch (context.modulus) {
                // % 5
                case 0 :
                    break;
                case 1 :
                    // Only 1 octet; take top 5 bits then remainder
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 3)) & MASK_5BITS];// 8-1*5 = 3
    
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea << 2)) & MASK_5BITS];// 5-3=2
    
                    buffer[context.pos++] = pad;
                    buffer[context.pos++] = pad;
                    buffer[context.pos++] = pad;
                    buffer[context.pos++] = pad;
                    buffer[context.pos++] = pad;
                    buffer[context.pos++] = pad;
                    break;
                case 2 :
                    // 2 octets = 16 bits to use
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 11)) & MASK_5BITS];// 16-1*5 = 11
    
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 6)) & MASK_5BITS];// 16-2*5 = 6
    
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 1)) & MASK_5BITS];// 16-3*5 = 1
    
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea << 4)) & MASK_5BITS];// 5-1 = 4
    
                    buffer[context.pos++] = pad;
                    buffer[context.pos++] = pad;
                    buffer[context.pos++] = pad;
                    buffer[context.pos++] = pad;
                    break;
                case 3 :
                    // 3 octets = 24 bits to use
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 19)) & MASK_5BITS];// 24-1*5 = 19
    
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 14)) & MASK_5BITS];// 24-2*5 = 14
    
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 9)) & MASK_5BITS];// 24-3*5 = 9
    
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 4)) & MASK_5BITS];// 24-4*5 = 4
    
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea << 1)) & MASK_5BITS];// 5-4 = 1
    
                    buffer[context.pos++] = pad;
                    buffer[context.pos++] = pad;
                    buffer[context.pos++] = pad;
                    break;
                case 4 :
                    // 4 octets = 32 bits to use
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 27)) & MASK_5BITS];// 32-1*5 = 27
    
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 22)) & MASK_5BITS];// 32-2*5 = 22
    
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 17)) & MASK_5BITS];// 32-3*5 = 17
    
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 12)) & MASK_5BITS];// 32-4*5 = 12
    
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 7)) & MASK_5BITS];// 32-5*5 = 7
    
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 2)) & MASK_5BITS];// 32-6*5 = 2
    
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea << 3)) & MASK_5BITS];// 5-2 = 3
    
                    buffer[context.pos++] = pad;
                    break;
                default :
                    throw new IllegalStateException("Impossible modulus " + context.modulus);
            }
            context.currentLinePos += context.pos - savedPos;// keep track of current line position
    
            // if currentPos == 0 we are at the start of a line, so don't add CRLF
            if ((lineLength > 0) && (context.currentLinePos > 0)) {
                // add chunk separator if required
                System.arraycopy(lineSeparator, 0, buffer, context.pos, lineSeparator.length);
                context.pos += lineSeparator.length;
            }
        } else {
            for (int i = 0; i < inAvail; i++) {
                final byte[] buffer = ensureBufferSize(encodeSize, context);
                context.modulus = (context.modulus + 1) % BYTES_PER_UNENCODED_BLOCK;
                int b = input[inPos++];
                if (b < 0) {
                    b += 256;
                }
                context.lbitWorkArea = (context.lbitWorkArea << 8) + b;// BITS_PER_BYTE
    
                if (0 == context.modulus) {
                    // we have enough bytes to create our output
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 35)) & MASK_5BITS];
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 30)) & MASK_5BITS];
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 25)) & MASK_5BITS];
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 20)) & MASK_5BITS];
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 15)) & MASK_5BITS];
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 10)) & MASK_5BITS];
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea >> 5)) & MASK_5BITS];
                    buffer[context.pos++] = encodeTable[((int) (context.lbitWorkArea)) & MASK_5BITS];
                    context.currentLinePos += BYTES_PER_ENCODED_BLOCK;
                    if ((lineLength > 0) && (lineLength <= context.currentLinePos)) {
                        System.arraycopy(lineSeparator, 0, buffer, context.pos, lineSeparator.length);
                        context.pos += lineSeparator.length;
                        context.currentLinePos = 0;
                    }
                }
            }
        }
    }
public static Builder builder();
    @Override
void decode(final byte[] input, int inPos, final int inAvail, final Context context);
    byte[] getLineSeparator();
    @Override
public boolean isInAlphabet(final byte octet);
}
```

@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```

@output{test cases}: output the test cases in the JSON format: