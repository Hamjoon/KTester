Invalid control character at: line 22 column 51 (char 522)
{
  "tests": [
    {
      "group": "standardEncoding_NoChunking_EmptyInput",
      "cases": [
        {
          "input": [
            {
              "parameter": "inputBytes",
              "value": "new byte[]{}"
            },
            {
              "parameter": "inPos",
              "value": "0"
            },
            {
              "parameter": "inAvail",
              "value": "0"
            },
            {
              "parameter": "context",
              "value": "new Base64().new Context() // freshly created context"
            }
          ],
          "expected": "byte[] {} // no output bytes",
          "description": "Encoding an empty byte array should produce an empty result (no characters, no padding, no line‑separator)."
        }
      ]
    },
    {
      "group": "standardEncoding_NoChunking_SingleByte",
      "cases": [
        {
          "input": [
            {
              "parameter": "inputBytes",
              "value": "new byte[]{ (byte)0xFF }"
            },
            {
              "parameter": "inPos",
              "value": "0"
            },
            {
              "parameter": "inAvail",
              "value": "1"
            },
            {
              "parameter": "context",
              "value": "new Base64().new Context()"
            }
          ],
          "expected": "byte[]{ '/', 'w', '=', '=' } // \"/w==\" in ASCII",
          "description": "A single byte (0xFF) must be encoded to two Base64 characters followed by two padding characters using the standard alphabet."
        }
      ]
    },
    {
      "group": "standardEncoding_NoChunking_TwoBytes",
      "cases": [
        {
          "input": [
            {
              "parameter": "inputBytes",
              "value": "new byte[]{ (byte)0xFF, (byte)0xEE }"
            },
            {
              "parameter": "inPos",
              "value": "0"
            },
            {
              "parameter": "inAvail",
              "value": "2"
            },
            {
              "parameter": "context",
              "value": "new Base64().new Context()"
            }
          ],
          "expected": "byte[]{ '/', '+', '4', '=' } // \"/+4=\" in ASCII",
          "description": "Two bytes should produce three Base64 characters and one padding character."
        }
      ]
    },
    {
      "group": "standardEncoding_NoChunking_ThreeBytes",
      "cases": [
        {
          "input": [
            {
              "parameter": "inputBytes",
              "value": "new byte[]{ (byte)0xFF, (byte)0xEE, (byte)0xDD }"
            },
            {
              "parameter": "inPos",
              "value": "0"
            },
            {
              "parameter": "inAvail",
              "value": "3"
            },
            {
              "parameter": "context",
              "value": "new Base64().new Context()"
            }
          ],
          "expected": "byte[]{ '/', '+', '7', 'd' } // \"/+7d\" in ASCII",
          "description": "A complete 3‑byte block yields exactly four Base64 characters with no padding."
        }
      ]
    },
    {
      "group": "standardEncoding_NoChunking_MultipleBlocks",
      "cases": [
        {
          "input": [
            {
              "parameter": "inputBytes",
              "value": "new byte[]{ 0x01,0x02,0x03, 0x04,0x05,0x06 }"
            },
            {
              "parameter": "inPos",
              "value": "0"
            },
            {
              "parameter": "inAvail",
              "value": "6"
            },
            {
              "parameter": "context",
              "value": "new Base64().new Context()"
            }
          ],
          "expected": "byte[]{ 'A','Q','I','D','B','A','U','G' } // \"AQIDBAUG\"",
          "description": "Two full 3‑byte blocks are encoded back‑to‑back without any line breaks."
        }
      ]
    },
    {
      "group": "chunkedEncoding_LineLength4_CRLF",
      "cases": [
        {
          "input": [
            {
              "parameter": "inputBytes",
              "value": "new byte[]{ 0x01,0x02,0x03, 0x04,0x05,0x06 }"
            },
            {
              "parameter": "inPos",
              "value": "0"
            },
            {
              "parameter": "inAvail",
              "value": "6"
            },
            {
              "parameter": "context",
              "value": "Base64.builder().setLineLength(4).setLineSeparator(new byte[]{'\\r','\\n'}).build().new Context()"
            }
          ],
          "expected": "byte[]{ 'A','Q','I','D','\\r','\\n','B','A','U','G' } // \"AQID\\r\\nBAUG\"",
          "description": "With a line length of 4 characters and CRLF as separator, a newline is inserted after the first 4 encoded bytes."
        }
      ]
    },
    {
      "group": "urlSafeEncoding_NoChunking_SingleByte",
      "cases": [
        {
          "input": [
            {
              "parameter": "inputBytes",
              "value": "new byte[]{ (byte)0xFF }"
            },
            {
              "parameter": "inPos",
              "value": "0"
            },
            {
              "parameter": "inAvail",
              "value": "1"
            },
            {
              "parameter": "context",
              "value": "Base64.builder().setUrlSafe(true).build().new Context()"
            }
          ],
          "expected": "byte[]{ '_', 'w', '=', '=' } // \"_w==\" ( '/' replaced by '_' )",
          "description": "URL‑safe mode replaces the standard '/' character with '_' while still emitting padding."
        }
      ]
    },
    {
      "group": "eofHandling_WithLeftoverOneByte",
      "cases": [
        {
          "input": [
            {
              "parameter": "inputBytes",
              "value": "new byte[]{ (byte)0xAB }"
            },
            {
              "parameter": "inPos",
              "value": "0"
            },
            {
              "parameter": "inAvail",
              "value": "1"
            },
            {
              "parameter": "context",
              "value": "new Base64().new Context()"
            }
          ],
          "expected": "byte[]{ 'q', 'w', '=', '=' } // \"qw==\" after final EOF call",
          "description": "First call encodes the single byte, leaving a modulus of 1. A subsequent call with inAvail = -1 (EOF) must flush the remaining bits and add padding."
        },
        {
          "input": [
            {
              "parameter": "inputBytes",
              "value": "new byte[]{} // no new data"
            },
            {
              "parameter": "inPos",
              "value": "0"
            },
            {
              "parameter": "inAvail",
              "value": "-1"
            },
            {
              "parameter": "context",
              "value": "the same Context instance after the previous call"
            }
          ],
          "expected": "byte[]{ 'q', 'w', '=', '=' } // same buffer now contains the final result",
          "description": "Calling encode with inAvail < 0 signals EOF; the method should emit the buffered output (including padding) and set context.eof = true."
        }
      ]
    },
    {
      "group": "eofHandling_NoLeftover_NoChunkSeparator",
      "cases": [
        {
          "input": [
            {
              "parameter": "inputBytes",
              "value": "new byte[]{ 0x41,0x42,0x43 } // \"ABC\""
            },
            {
              "parameter": "inPos",
              "value": "0"
            },
            {
              "parameter": "inAvail",
              "value": "3"
            },
            {
              "parameter": "context",
              "value": "Base64.builder().setLineLength(4).setLineSeparator(new byte[]{'\\r','\\n'}).build().new Context()"
            }
          ],
          "expected": "byte[]{ 'Q','U','J','D' } // \"QUJD\" – no CRLF because there is no leftover when EOF is signalled",
          "description": "When the input ends exactly on a block boundary, the EOF handling must NOT add an extra line separator."
        }
      ]
    },
    {
      "group": "nullInput_ThrowsNPE",
      "cases": [
        {
          "input": [
            {
              "parameter": "inputBytes",
              "value": "null"
            },
            {
              "parameter": "inPos",
              "value": "0"
            },
            {
              "parameter": "inAvail",
              "value": "0"
            },
            {
              "parameter": "context",
              "value": "new Base64().new Context()"
            }
          ],
          "expected": "NullPointerException",
          "description": "Passing a null source array to encode should raise a NullPointerException, matching the contract of BaseNCodec."
        }
      ]
    }
  ]
}