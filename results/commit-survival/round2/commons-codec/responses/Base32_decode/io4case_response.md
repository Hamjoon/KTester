Invalid control character at: line 13 column 63 (char 428)
{
  "testCases": [
    {
      "group": "decodeFullBlockWithPadding",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "MZXW6YTBOI======" },
            { "parameter": "inPos", "value": 0 },
            { "parameter": "inAvail", "value": 16 },
            { "parameter": "context", "value": "new Context()" }
          ],
          "expected": "byte[6] {0x66,0x6F,0x6F,0x62,0x61,0x72} // \"foobar\"",
          "description": "Decodes a full 8‑character Base32 block with padding. The input represents the string \"foobar\"; after processing the padding character the decoder should output the original 6 bytes."
        }
      ]
    },
    {
      "group": "decodeFullBlockWithoutPadding",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "MZXW6YTBOI" },
            { "parameter": "inPos", "value": 0 },
            { "parameter": "inAvail", "value": 10 },
            { "parameter": "context", "value": "new Context()" }
          ],
          "expected": "byte[6] {0x66,0x6F,0x6F,0x62,0x61,0x72} // \"foobar\"",
          "description": "Decodes the same data as the previous test but without the optional padding characters. The decoder must handle the EOF condition when the input stream ends and still produce the correct 6‑byte result."
        }
      ]
    },
    {
      "group": "decodeSingleBytePartial (modulus 2)",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "MY======" },
            { "parameter": "inPos", "value": 0 },
            { "parameter": "inAvail", "value": 8 },
            { "parameter": "context", "value": "new Context()" }
          ],
          "expected": "byte[1] {0x66} // \"f\"",
          "description": "Two Base32 characters (\"M\",\"Y\") followed by padding produce a modulus of 2. The decoder should drop the two surplus bits and output the single original byte \"f\"."
        },
        {
          "input": [
            { "parameter": "input", "value": "MY" },
            { "parameter": "inPos", "value": 0 },
            { "parameter": "inAvail", "value": 2 },
            { "parameter": "context", "value": "new Context()" }
          ],
          "expected": "byte[1] {0x66} // \"f\"",
          "description": "Same as above but without padding. Padding is optional; the decoder must still recognise the EOF after the stream ends and produce the correct single byte."
        }
      ]
    },
    {
      "group": "decodeTwoBytesPartial (modulus 4)",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "NBSWY===" },
            { "parameter": "inPos", "value": 0 },
            { "parameter": "inAvail", "value": 8 },
            { "parameter": "context", "value": "new Context()" }
          ],
          "expected": "byte[2] {0x68,0x69} // \"hi\"",
          "description": "Four Base32 characters (\"N\",\"B\",\"S\",\"W\") encode 20 bits → two bytes. The trailing padding forces EOF; the decoder must validate the low‑order 4 bits and output \"hi\"."
        },
        {
          "input": [
            { "parameter": "input", "value": "NBSW" },
            { "parameter": "inPos", "value": 0 },
            { "parameter": "inAvail", "value": 4 },
            { "parameter": "context", "value": "new Context()" }
          ],
          "expected": "byte[2] {0x68,0x69} // \"hi\"",
          "description": "Same data without padding; the decoder should still handle EOF after the stream ends and produce the two‑byte result."
        }
      ]
    },
    {
      "group": "decodeThreeBytesPartial (modulus 5)",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "MFRGG===" },
            { "parameter": "inPos", "value": 0 },
            { "parameter": "inAvail", "value": 8 },
            { "parameter": "context", "value": "new Context()" }
          ],
          "expected": "byte[3] {0x63,0x61,0x74} // \"cat\"",
          "description": "Five Base32 characters encode 25 bits → three bytes. After padding the decoder validates the single trailing bit and outputs \"cat\"."
        },
        {
          "input": [
            { "parameter": "input", "value": "MFRGG" },
            { "parameter": "inPos", "value": 0 },
            { "parameter": "inAvail", "value": 5 },
            { "parameter": "context", "value": "new Context()" }
          ],
          "expected": "byte[3] {0x63,0x61,0x74} // \"cat\"",
          "description": "Same as above without padding; decoder must correctly handle EOF and produce the three‑byte result."
        }
      ]
    },
    {
      "group": "decodeFourBytesPartial (modulus 7)",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "ORSXG5A=" },
            { "parameter": "inPos", "value": 0 },
            { "parameter": "inAvail", "value": 8 },
            { "parameter": "context", "value": "new Context()" }
          ],
          "expected": "byte[4] {0x74,0x65,0x73,0x74} // \"test\"",
          "description": "Seven Base32 characters encode 35 bits → four bytes. The final padding character triggers EOF; the decoder validates the low‑order 3 bits and outputs \"test\"."
        },
        {
          "input": [
            { "parameter": "input", "value": "ORSXG5A" },
            { "parameter": "inPos", "value": 0 },
            { "parameter": "inAvail", "value": 7 },
            { "parameter": "context", "value": "new Context()" }
          ],
          "expected": "byte[4] {0x74,0x65,0x73,0x74} // \"test\"",
          "description": "Same data without padding; decoder must still handle the EOF condition and produce the correct four‑byte output."
        }
      ]
    },
    {
      "group": "decodeModulus3And6 (non‑standard lengths)",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "MFR" },  	{ "parameter": "inPos", "value": 0 },
            { "parameter": "inAvail", "value": 3 },
            { "parameter": "context", "value": "new Context()" }
          ],
          "expected": "byte[1] {0x63} // first byte of \"cat\" (decoder drops 7 bits)",
          "description": "Three Base32 characters produce a modulus of 3 (15 bits). Although not a valid encoding length, the decoder still processes the data, validates trailing characters, drops 7 bits and returns one byte."
        },
        {
          "input": [
            { "parameter": "input", "value": "MFRGGG" }, { "parameter": "inPos", "value": 0 },
            { "parameter": "inAvail", "value": 6 },
            { "parameter": "context", "value": "new Context()" }
          ],
          "expected": "byte[3] {0x63,0x61,0x74} // \"cat\" (decoder drops 6 bits)",
          "description": "Six Base32 characters give modulus 6 (30 bits). The decoder validates trailing characters, discards the low‑order 6 bits and outputs three bytes, matching the original \"cat\"."
        }
      ]
    },
    {
      "group": "decodeInvalidCharactersAreIgnored",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "M*Z!W6Y@B#O$I%" },
            { "parameter": "inPos", "value": 0 },
            { "parameter": "inAvail", "value": 16 },
            { "parameter": "context", "value": "new Context()" }
          ],
          "expected": "byte[6] {0x66,0x6F,0x6F,0x62,0x61,0x72} // \"foobar\"",
          "description": "All non‑alphabet characters map to a negative entry in the decode table and are silently ignored. The valid Base32 characters still decode to \"foobar\"."
        }
      ]
    },
    {
      "group": "decodeWhenContextAlreadyEof",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "ANY" },
            { "parameter": "inPos", "value": 0 },
            { "parameter": "inAvail", "value": 3 },
            { "parameter": "context", "value": "Context with eof=true" }
          ],
          "expected": "no change to context.buffer; empty output",
          "description": "If the supplied Context has eof already set to true, the method must return immediately without processing any input."
        }
      ]
    }
  ]
}