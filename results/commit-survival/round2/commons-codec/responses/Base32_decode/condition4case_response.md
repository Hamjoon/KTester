Invalid control character at: line 40 column 65 (char 1528)
{
  "testCases": [
    {
      "group": "Early EOF handling (inAvail < 0)",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{ }" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "-1" },
            { "parameter": "context", "value": "new Base32().new Context()" }
          ],
          "expected": "context.eof == true and no bytes written to the internal buffer",
          "description": "When inAvail is negative the decoder must treat it as an EOF marker, set context.eof to true and return immediately without producing output."
        }
      ]
    },
    {
      "group": "Pad character handling",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{ (byte)'=' }" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "1" },
            { "parameter": "context", "value": "new Base32().new Context()" }
          ],
          "expected": "context.eof == true and loop terminates after the pad byte",
          "description": "The decoder must recognise the padding character (the value of BaseNCodec.pad) and set eof, then break out of the processing loop."
        }
      ]
    },
    {
      "group": "Full block decoding (modulus reaches 0)",
      "cases": [
        {
          "input": [
            {
              "parameter": "input",
              "value": "Base32TestHelper.encodeBase32(\"Hello\") // returns the 8‑byte Base32 representation of \"Hello\""
            },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "8" },
            { "parameter": "context", "value": "new Base32().new Context()" }
          ],
          "expected": "context.pos == 5 and the buffer contains the bytes of \"Hello\" (0x48,0x65,0x6C,0x6C,0x6F)",
          "description": "Eight valid Base32 characters produce exactly five decoded bytes; after the eighth character the internal modulus becomes 0 and the decoder writes the five bytes to the buffer."
        }
      ]
    },
    {
      "group": "EOF handling – modulus = 1 (validateTrailingCharacters)",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{}" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "0" },
            {
              "parameter": "context",
              "value": "Base32TestHelper.contextWith(eof=true, modulus=1, lbitWorkArea=0L)"
            }
          ],
          "expected": "IllegalArgumentException thrown by validateTrailingCharacters()",
          "description": "When EOF is true and modulus == 1 the decoder must call validateTrailingCharacters(), which for an illegal trailing length raises an exception."
        }
      ]
    },
    {
      "group": "EOF handling – modulus = 2 (validateCharacter, 1 byte output)",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{}" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "0" },
            {
              "parameter": "context",
              "value": "Base32TestHelper.contextWith(eof=true, modulus=2, lbitWorkArea=0b101010L)"  }
          ],
          "expected": "buffer[0] == (byte)0b00101010 (0x2A) and context.pos == 1",
          "description": "For modulus 2 the decoder validates the two trailing bits, discards the two lowest bits and writes a single byte derived from the remaining 8 bits."
        }
      ]
    },
    {
      "group": "EOF handling – modulus = 3 (validateTrailingCharacters, 1 byte output)",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{}" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "0" },
            {
              "parameter": "context",
              "value": "Base32TestHelper.contextWith(eof=true, modulus=3, lbitWorkArea=0b111000111L)"
            }
          ],
          "expected": "IllegalArgumentException thrown by validateTrailingCharacters()",
          "description": "Modulus 3 is not a valid length for a correct Base32 stream; the decoder must invoke validateTrailingCharacters() which raises an exception."
        }
      ]
    },
    {
      "group": "EOF handling – modulus = 4 (validateCharacter, 2 bytes output)",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{}" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "0" },
            {
              "parameter": "context",
              "value": "Base32TestHelper.contextWith(eof=true, modulus=4, lbitWorkArea=0b110011001100L)"
            }
          ],
          "expected": "buffer[0] == (byte)0b11001100 (0xCC) and buffer[1] == (byte)0b11001100 (0xCC) and context.pos == 2",
          "description": "With modulus 4 the decoder validates the low‑order 4 bits, discards them, then extracts two full bytes from the remaining 16 bits."
        }
      ]
    },
    {
      "group": "EOF handling – modulus = 5 (validateCharacter, 3 bytes output)",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{}" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "0" },
            {
              "parameter": "context",
              "value": "Base32TestHelper.contextWith(eof=true, modulus=5, lbitWorkArea=0b1010101010101010101L)"
            }
          ],
          "expected": "buffer[0]==(byte)0b10101010 (0xAA), buffer[1]==(byte)0b10101010 (0xAA), buffer[2]==(byte)0b10101010 (0xAA) and context.pos==3",
          "description": "For modulus 5 the decoder validates the single trailing bit, shifts right by one, and writes three bytes derived from the remaining 24 bits."
        }
      ]
    },
    {
      "group": "EOF handling – modulus = 6 (validateTrailingCharacters, 3 bytes output)",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{}" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "0" },
            {
              "parameter": "context",
              "value": "Base32TestHelper.contextWith(eof=true, modulus=6, lbitWorkArea=0b11110000111100001111L)"
            }
          ],
          "expected": "IllegalArgumentException thrown by validateTrailingCharacters()",
          "description": "Modulus 6 is not a legal trailing length; the decoder must call validateTrailingCharacters() which raises an exception."
        }
      ]
    },
    {
      "group": "EOF handling – modulus = 7 (validateCharacter, 4 bytes output)",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{}" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "0" },
            {
              "parameter": "context",
              "value": "Base32TestHelper.contextWith(eof=true, modulus=7, lbitWorkArea=0b111111110000000011111111L)"
            }
          ],
          "expected": "buffer[0]==(byte)0xFF, buffer[1]==(byte)0x00, buffer[2]==(byte)0xFF, buffer[3]==(byte)0xFF and context.pos==4",
          "description": "When modulus is 7 the decoder validates the low‑order 3 bits, discards them, and writes four bytes extracted from the remaining 32 bits."
        }
      ]
    },
    {
      "group": "Invalid decode table index (byte out of range)",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{ (byte)0xFF }" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "1" },
            { "parameter": "context", "value": "new Base32().new Context()" }
          ],
          "expected": "No change to context (modulus stays 0, eof stays false, no bytes written)",
          "description": "If the input byte is outside the decodeTable range the decoder ignores it; no exception should be thrown and no output produced."
        }
      ]
    }
  ]
}