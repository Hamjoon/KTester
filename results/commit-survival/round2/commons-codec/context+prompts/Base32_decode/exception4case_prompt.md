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
Here is the code of focal method, `decode(byte[], int, int, Context)`, with the focal class `Base32`:
```Java
package org.apache.commons.codec.binary;
import java.util.Objects;
import org.apache.commons.codec.CodecPolicy;
public class Base32 extends BaseNCodec {
    private static final int BITS_PER_ENCODED_BYTE = 5;
    private static final int BYTES_PER_ENCODED_BLOCK = 8;
    private static final long MASK_4BITS = 0xfL;
    private static final long MASK_3BITS = 0x7L;
    private static final long MASK_2BITS = 0x3L;
    private static final long MASK_1BITS = 0x1L;
    private final byte[] decodeTable;
    private final int encodeSize;
    @Override
    void decode(final byte[] input, int inPos, final int inAvail, final Context context) {
        // package protected for access from I/O streams
        if (context.eof) {
            return;
        }
        if (inAvail < 0) {
            context.eof = true;
        }
        final int decodeSize = this.encodeSize - 1;
        for (int i = 0; i < inAvail; i++) {
            final byte b = input[inPos++];
            if (b == pad) {
                // We're done.
                context.eof = true;
                break;
            }
            final byte[] buffer = ensureBufferSize(decodeSize, context);
            if ((b >= 0) && (b < this.decodeTable.length)) {
                final int result = this.decodeTable[b];
                if (result >= 0) {
                    context.modulus = (context.modulus + 1) % BYTES_PER_ENCODED_BLOCK;
                    // collect decoded bytes
                    context.lbitWorkArea = (context.lbitWorkArea << BITS_PER_ENCODED_BYTE) + result;
                    if (context.modulus == 0) {
                        // we can output the 5 bytes
                        buffer[context.pos++] = ((byte) ((context.lbitWorkArea >> 32) & MASK_8BITS));
                        buffer[context.pos++] = ((byte) ((context.lbitWorkArea >> 24) & MASK_8BITS));
                        buffer[context.pos++] = ((byte) ((context.lbitWorkArea >> 16) & MASK_8BITS));
                        buffer[context.pos++] = ((byte) ((context.lbitWorkArea >> 8) & MASK_8BITS));
                        buffer[context.pos++] = ((byte) (context.lbitWorkArea & MASK_8BITS));
                    }
                }
            }
        }
        // Two forms of EOF as far as Base32 decoder is concerned: actual
        // EOF (-1) and first time '=' character is encountered in stream.
        // This approach makes the '=' padding characters completely optional.
        if (context.eof && (context.modulus > 0)) {
            // if modulus == 0, nothing to do
            final byte[] buffer = ensureBufferSize(decodeSize, context);
            // We ignore partial bytes, i.e. only multiples of 8 count.
            // Any combination not part of a valid encoding is either partially decoded
            // or will raise an exception. Possible trailing characters are 2, 4, 5, 7.
            // It is not possible to encode with 1, 3, 6 trailing characters.
            // For backwards compatibility 3 & 6 chars are decoded anyway rather than discarded.
            // See the encode(byte[]) method EOF section.
            switch (context.modulus) {
                // case 0 : // impossible, as excluded above
                case 1 :
                    // 5 bits - either ignore entirely, or raise an exception
                    validateTrailingCharacters();
                case 2 :
                    // 10 bits, drop 2 and output one byte
                    validateCharacter(MASK_2BITS, context);
                    buffer[context.pos++] = ((byte) ((context.lbitWorkArea >> 2) & MASK_8BITS));
                    break;
                case 3 :
                    // 15 bits, drop 7 and output 1 byte, or raise an exception
                    validateTrailingCharacters();
                    // Not possible from a valid encoding but decode anyway
                    buffer[context.pos++] = ((byte) ((context.lbitWorkArea >> 7) & MASK_8BITS));
                    break;
                case 4 :
                    // 20 bits = 2*8 + 4
                    validateCharacter(MASK_4BITS, context);
                    context.lbitWorkArea = context.lbitWorkArea >> 4;// drop 4 bits
    
                    buffer[context.pos++] = ((byte) ((context.lbitWorkArea >> 8) & MASK_8BITS));
                    buffer[context.pos++] = ((byte) (context.lbitWorkArea & MASK_8BITS));
                    break;
                case 5 :
                    // 25 bits = 3*8 + 1
                    validateCharacter(MASK_1BITS, context);
                    context.lbitWorkArea = context.lbitWorkArea >> 1;
                    buffer[context.pos++] = ((byte) ((context.lbitWorkArea >> 16) & MASK_8BITS));
                    buffer[context.pos++] = ((byte) ((context.lbitWorkArea >> 8) & MASK_8BITS));
                    buffer[context.pos++] = ((byte) (context.lbitWorkArea & MASK_8BITS));
                    break;
                case 6 :
                    // 30 bits = 3*8 + 6, or raise an exception
                    validateTrailingCharacters();
                    // Not possible from a valid encoding but decode anyway
                    context.lbitWorkArea = context.lbitWorkArea >> 6;
                    buffer[context.pos++] = ((byte) ((context.lbitWorkArea >> 16) & MASK_8BITS));
                    buffer[context.pos++] = ((byte) ((context.lbitWorkArea >> 8) & MASK_8BITS));
                    buffer[context.pos++] = ((byte) (context.lbitWorkArea & MASK_8BITS));
                    break;
                case 7 :
                    // 35 bits = 4*8 +3
                    validateCharacter(MASK_3BITS, context);
                    context.lbitWorkArea = context.lbitWorkArea >> 3;
                    buffer[context.pos++] = ((byte) ((context.lbitWorkArea >> 24) & MASK_8BITS));
                    buffer[context.pos++] = ((byte) ((context.lbitWorkArea >> 16) & MASK_8BITS));
                    buffer[context.pos++] = ((byte) ((context.lbitWorkArea >> 8) & MASK_8BITS));
                    buffer[context.pos++] = ((byte) (context.lbitWorkArea & MASK_8BITS));
                    break;
                default :
                    // modulus can be 0-7, and we excluded 0,1 already
                    throw new IllegalStateException("Impossible modulus " + context.modulus);
            }
        }
    }
public static Builder builder();
    @Override
void encode(final byte[] input, int inPos, final int inAvail, final Context context);
    byte[] getLineSeparator();
    @Override
public boolean isInAlphabet(final byte octet);
    private void validateCharacter(final long emptyBitsMask, final Context context);
    private void validateTrailingCharacters();
}
```
@input{existing test case}
here's the existing test cases:
```json
<cases_json>
```

@output{test cases}: output the test cases in the JSON format: