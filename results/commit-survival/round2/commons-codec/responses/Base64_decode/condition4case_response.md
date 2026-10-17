{
  "testCases": [
    {
      "group": "Early EOF – method should return immediately when context.eof is true",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{'A','B'}" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "2" },
            { "parameter": "context", "value": "Context with eof=true, pos=0, modulus=0, ibitWorkArea=0" }
          ],
          "expected": "No change to context (pos remains 0, buffer unchanged) – method returns without processing",
          "description": "Verifies the first guard clause `if (context.eof) return;`"
        }
      ]
    },
    {
      "group": "Negative inAvail – should mark EOF and not produce output",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{'A','B'}" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "-1" },
            { "parameter": "context", "value": "fresh Context (eof=false, pos=0, modulus=0, ibitWorkArea=0)" }
          ],
          "expected": "context.eof == true after call, no bytes written to buffer",
          "description": "Ensures the branch `if (inAvail < 0) { context.eof = true; }` works"
        }
      ]
    },
    {
      "group": "Padding character detection – should set EOF and stop processing",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{'A', Base64.pad, 'B'}" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "3" },
            { "parameter": "context", "value": "fresh Context (eof=false, pos=0, modulus=0, ibitWorkArea=0)" }
          ],
          "expected": "context.eof == true, only first character processed, pos may be 0 because a full block was not completed",
          "description": "Tests the `if (b == pad) { context.eof = true; break; }` path"
        }
      ]
    },
    {
      "group": "Full block decoding – four valid Base64 chars produce three output bytes",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "Base64.encodeBase64(new byte[]{0x01,0x02,0x03})" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "4" },
            { "parameter": "context", "value": "fresh Context (eof=false, pos=0, modulus=0, ibitWorkArea=0)" }
          ],
          "expected": "context.pos == 3 and context.buffer[0..2] == {0x01,0x02,0x03}",
          "description": "Runs through the main loop, hits `context.modulus == 0` after four chars and writes three decoded bytes"
        }
      ]
    },
    {
      "group": "Trailing bits – modulus == 1 (invalid, should invoke validateTrailingCharacter)",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{'A'}" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "1" },
            { "parameter": "context", "value": "Context with eof=true (simulated by inAvail<0), modulus=1, ibitWorkArea=0" }
          ],
          "expected": "IllegalArgumentException thrown by validateTrailingCharacter()",
          "description": "Ensures the switch case `case 1` is taken and the validator raises an exception"
        }
      ]
    },
    {
      "group": "Trailing bits – modulus == 2 (produces one byte)",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{'A','B'}" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "2" },
            { "parameter": "context", "value": "Context with eof=true, modulus=2, ibitWorkArea set to result of two Base64 chars (e.g., 0b001100 0b001001)" }
          ],
          "expected": "context.pos == 1 and context.buffer[0] equals the decoded single byte (0x0F for the chosen chars)",
          "description": "Tests `case 2` – validateCharacter is called, extra 4 bits are discarded and one byte is written"
        }
      ]
    },
    {
      "group": "Trailing bits – modulus == 3 (produces two bytes)",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{'A','B','C'}" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "3" },
            { "parameter": "context", "value": "Context with eof=true, modulus=3, ibitWorkArea set to result of three Base64 chars" }
          ],
          "expected": "context.pos == 2 and context.buffer[0..1] contain the two decoded bytes",
          "description": "Covers `case 3` – validates the low 2 bits, discards them, and writes two bytes"
        }
      ]
    },
    {
      "group": "Invalid modulus – should throw IllegalStateException",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{}" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "0" },
            { "parameter": "context", "value": "Context with eof=true, modulus=5 (forced), ibitWorkArea=0" }
          ],
          "expected": "IllegalStateException with message containing \"Impossible modulus 5\"",
          "description": "Forces the default branch of the switch by setting an impossible modulus value"
        }
      ]
    },
    {
      "group": "Non‑alphabet characters – should be ignored",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{'A', (byte)0xFF, 'B', 'C', 'D'}" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "5" },
            { "parameter": "context", "value": "fresh Context (eof=false)" }
          ],
          "expected": "Only the valid Base64 characters ('A','B','C','D') affect decoding; the 0xFF byte is skipped. After processing a full block, three decoded bytes are produced.",
          "description": "Ensures that bytes outside the range of `decodeTable` or with a negative table entry are ignored"
        }
      ]
    }
  ]
}