{
  "test_cases": [
    {
      "group": "Early exit when EOF already true, with pending input",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{0x01, 0x02}" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "2" },
            { "parameter": "context", "value": "new Base32().new Context(){ eof=true, pos=0, buffer=new byte[0] }" }
          ],
          "expected": "Context remains unchanged (eof stays true, pos stays 0, buffer stays empty). No data is written.",
          "description": "When the context is already marked as EOF the method must return immediately, ignoring any supplied input."
        }
      ]
    },
    {
      "group": "Empty input with inAvail = 0 (no EOF) – no output",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{}" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "0" },
            { "parameter": "context", "value": "new Base32().new Context(){ eof=false, pos=0, buffer=new byte[0] }" }
          ],
          "expected": "Context.buffer stays empty, pos remains 0, modulus stays 0.",
          "description": "When there is no data and EOF has not been signaled the encoder should do nothing."
        }
      ]
    },
    {
      "group": "Partial block without EOF – no output until block is full",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{0x01,0x02,0x03}" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "3" },
            { "parameter": "context", "value": "new Base32().new Context(){ eof=false, pos=0, buffer=new byte[8] }" }
          ],
          "expected": "Context.buffer remains unchanged (no bytes written), context.modulus becomes 3, lbitWorkArea contains the three bytes shifted left appropriately.",
          "description": "The encoder must wait until it has 5 bytes before emitting any Base‑32 characters."
        }
      ]
    },
    {
      "group": "Partial block followed by EOF – leftover handling after prior processing",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{0x01,0x02,0x03}" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "3" },
            { "parameter": "context", "value": "new Base32().new Context(){ eof=false, pos=0, buffer=new byte[8] }" }
          ],
          "expected": "After the first call the buffer is still empty and context.modulus == 3.",
          "description": "First call processes three bytes; no output is produced yet."
        },
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{}" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "-1" },
            { "parameter": "context", "value": "the same Context instance from the previous call" }
          ],
          "expected": "Encoded bytes = \"AEBAG===\" (5 data characters followed by 3 padding characters).",
          "description": "When EOF is signaled after a partial block of three bytes, the encoder must emit the correct leftover encoding."
        }
      ]
    },
    {
      "group": "Buffer expansion – initial buffer too small",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{0x01,0x02,0x03,0x04,0x05}" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "5" },
            { "parameter": "context", "value": "new Base32().new Context(){ eof=false, pos=0, buffer=new byte[2] }" }
          ],
          "expected": "Context.buffer length is >=8 after encoding, and the first 8 bytes contain \"AEBAGBA=\".",
          "description": "ensureBufferSize should allocate a larger buffer when the supplied one cannot hold the encoded block."
        }
      ]
    },
    {
      "group": "Custom line separator (space) with lineLength smaller than a block",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{0x01,0x02,0x03,0x04,0x05}" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "5" },
            { "parameter": "context", "value": "new Base32(){ lineLength=4, lineSeparator=\" \".getBytes() }.new Context(){ eof=false, pos=0, buffer=new byte[10] }" }
          ],
          "expected": "Encoded bytes = \"AEBAGBA= \" (space added after the block).",
          "description": "When lineLength is reached (or exceeded) the configured separator (a single space) must be appended."
        }
      ]
    },
    {
      "group": "Line separator ignored when lineLength = 0",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{0x01,0x02,0x03,0x04,0x05}" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "5" },
            { "parameter": "context", "value": "new Base32(){ lineLength=0, lineSeparator=\"\\r\\n\".getBytes() }.new Context(){ eof=false, pos=0, buffer=new byte[8] }" }
          ],
          "expected": "Encoded bytes = \"AEBAGBA=\" (no CRLF appended).",
          "description": "A non‑zero lineSeparator should have no effect when lineLength is disabled."
        }
      ]
    },
    {
      "group": "Large input causing multiple buffer expansions",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{0x00,0x01,0x02,0x03,0x04, 0x05,0x06,0x07,0x08,0x09, 0x0A,0x0B,0x0C,0x0D,0x0E, 0x0F,0x10,0x11,0x12,0x13}" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "20" },
            { "parameter": "context", "value": "new Base32().new Context(){ eof=false, pos=0, buffer=new byte[4] }" }
          ],
          "expected": "Context.buffer length >= 32, containing 32 Base‑32 characters (no padding) that correspond to the 20‑byte input.",
          "description": "Encoding 20 bytes (four full blocks) should trigger several buffer growths; the final output length must be 4 × 8 = 32 characters."
        }
      ]
    },
    {
      "group": "Multiple negative bytes in one block",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{(byte)0xFF,(byte)0x80,0x00,0x01,0x02}" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "5" },
            { "parameter": "context", "value": "new Base32().new Context(){ eof=false, pos=0, buffer=new byte[8] }" }
          ],
          "expected": "Encoded bytes = \"7777777A\" (the first two bytes become 255 and 128, producing '7' characters; the remaining three bytes encode to \"7A\").",
          "description": "Ensures that the unsigned conversion (b += 256) works for several high‑bit bytes within the same block."
        }
      ]
    },
    {
      "group": "Modulus wrap‑around after more than one block",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{0x01,0x02,0x03,0x04,0x05, 0x06}" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "6" },
            { "parameter": "context", "value": "new Base32().new Context(){ eof=false, pos=0, buffer=new byte[16] }" }
          ],
          "expected": "After processing the first five bytes the buffer contains \"AEBAGBA=\" and context.modulus == 0. After the sixth byte the buffer is unchanged (still only the first block) and context.modulus == 1, lbitWorkArea holds the value of the sixth byte shifted left by 0.",
          "description": "Verifies that after a complete block the modulus resets to 0 and that the next byte starts a new partial block."
        }
      ]
    }
  ]
}