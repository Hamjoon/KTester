{
  "testCases": [
    {
      "group": "EOF handling – early exit when context already marked as EOF",
      "cases": [
        {
          "input": [
            {
              "parameter": "input",
              "value": "new byte[]{ }"
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
              "value": "Context{eof=true, pos=0, buffer=new byte[0]}"
            }
          ],
          "expected": "No change to context.buffer (empty) and method returns immediately.",
          "description": "When the context already has eof=true the encode method must return without touching the buffer."
        }
      ]
    },
    {
      "group": "EOF handling – no leftover bytes and no line‑chunking",
      "cases": [
        {
          "input": [
            {
              "parameter": "input",
              "value": "new byte[]{ }"
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
              "value": "Context{eof=false, modulus=0, lineLength=0, pos=0, buffer=new byte[0]}"
            }
          ],
          "expected": "Method returns after setting context.eof=true; buffer stays empty because there is no leftover data.",
          "description": "EOF signaled (inAvail < 0) with no pending bytes (modulus==0) and no line‑length chunking – nothing should be emitted."
        }
      ]
    },
    {
      "group": "EOF handling – leftover 1 byte (modulus = 1)",
      "cases": [
        {
          "input": [
            {
              "parameter": "input",
              "value": "new byte[]{ }"
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
              "value": "Context{eof=false, modulus=1, lbitWorkArea=0xF0, lineLength=0, pos=0, buffer=new byte[8]}"
            }
          ],
          "expected": "Encoded bytes = \"6=====\" (2 data chars followed by 6 padding chars).",
          "description": "When EOF is reached with a single leftover byte, the encoder must emit two Base‑32 characters derived from the high‑order 5‑bit groups and then seven pad characters."
        }
      ]
    },
    {
      "group": "EOF handling – leftover 2 bytes (modulus = 2)",
      "cases": [
        {
          "input": [
            {
              "parameter": "input",
              "value": "new byte[]{ }"
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
              "value": "Context{eof=false, modulus=2, lbitWorkArea=0xF00F, lineLength=0, pos=0, buffer=new byte[8]}"
            }
          ],
          "expected": "Encoded bytes = \"6P4====\" (4 data chars followed by 4 pads).",
          "description": "Two leftover bytes produce four Base‑32 characters and four pad characters."
        }
      ]
    },
    {
      "group": "EOF handling – leftover 3 bytes (modulus = 3)",
      "cases": [
        {
          "input": [
            {
              "parameter": "input",
              "value": "new byte[]{ }"
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
              "value": "Context{eof=false, modulus=3, lbitWorkArea=0xF00F0F, lineLength=0, pos=0, buffer=new byte[8]}"
            }
          ],
          "expected": "Encoded bytes = \"6P4J2===\" (5 data chars followed by 3 pads).",
          "description": "Three leftover bytes generate five Base‑32 characters and three pad characters."
        }
      ]
    },
    {
      "group": "EOF handling – leftover 4 bytes (modulus = 4)",
      "cases": [
        {
          "input": [
            {
              "parameter": "input",
              "value": "new byte[]{ }"
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
              "value": "Context{eof=false, modulus=4, lbitWorkArea=0xF00F0F0F, lineLength=0, pos=0, buffer=new byte[8]}"
            }
          ],
          "expected": "Encoded bytes = \"6P4J2L0=\" (7 data chars followed by 1 pad).",
          "description": "Four leftover bytes produce seven Base‑32 characters and a single pad character."
        }
      ]
    },
    {
      "group": "EOF handling – illegal modulus (default case)",
      "cases": [
        {
          "input": [
            {
              "parameter": "input",
              "value": "new byte[]{ }"
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
              "value": "Context{eof=false, modulus=5, lbitWorkArea=0, lineLength=0, pos=0, buffer=new byte[0]}"
            }
          ],
          "expected": "IllegalStateException with message \"Impossible modulus 5\"",
          "description": "If the context.modulus is outside the range 0‑4 when EOF is processed, the method must throw IllegalStateException."
        }
      ]
    },
    {
      "group": "Normal encoding – exact 5‑byte block, no line chunking",
      "cases": [
        {
          "input": [
            {
              "parameter": "input",
              "value": "new byte[]{0x01,0x02,0x03,0x04,0x05}"
            },
            {
              "parameter": "inPos",
              "value": "0"
            },
            {
              "parameter": "inAvail",
              "value": "5"
            },
            {
              "parameter": "context",
              "value": "Context{eof=false, modulus=0, lineLength=0, pos=0, buffer=new byte[8]}"
            }
          ],
          "expected": "Encoded bytes = \"AEBAGBA=\" (8 data chars, no padding).",
          "description": "A full 5‑byte input should be turned into exactly eight Base‑32 characters without any padding."
        }
      ]
    },
    {
      "group": "Normal encoding – line‑length chunking (lineLength = 8, separator = \"\\r\\n\")",
      "cases": [
        {
          "input": [
            {
              "parameter": "input",
              "value": "new byte[]{0x01,0x02,0x03,0x04,0x05}"
            },
            {
              "parameter": "inPos",
              "value": "0"
            },
            {
              "parameter": "inAvail",
              "value": "5"
            },
            {
              "parameter": "context",
              "value": "Context{eof=false, modulus=0, lineLength=8, lineSeparator=\"\\r\\n\".getBytes(), pos=0, buffer=new byte[10]}"
            }
          ],
          "expected": "Encoded bytes = \"AEBAGBA=\\r\\n\" (8 data chars followed by CRLF).",
          "description": "When lineLength is set, after a full block the encoder must insert the line separator."
        }
      ]
    },
    {
      "group": "Normal encoding – handling of negative byte values (b < 0 branch)",
      "cases": [
        {
          "input": [
            {
              "parameter": "input",
              "value": "new byte[]{(byte)0xFF,0x00,0x00,0x00,0x00}"
            },
            {
              "parameter": "inPos",
              "value": "0"
            },
            {
              "parameter": "inAvail",
              "value": "5"
            },
            {
              "parameter": "context",
              "value": "Context{eof=false, modulus=0, lineLength=0, pos=0, buffer=new byte[8]}"
            }
          ],
          "expected": "Encoded bytes = \"7777777=\" (the first byte -1 becomes 255 after adding 256).",
          "description": "When a byte with the high bit set is read, the code adds 256 to make it unsigned; the resulting encoding must reflect the unsigned value."
        }
      ]
    },
    {
      "group": "Normal encoding – multiple blocks with line‑length overflow",
      "cases": [
        {
          "input": [
            {
              "parameter": "input",
              "value": "new byte[]{0x01,0x02,0x03,0x04,0x05, 0x06,0x07,0x08,0x09,0x0A}"
            },
            {
              "parameter": "inPos",
              "value": "0"
            },
            {
              "parameter": "inAvail",
              "value": "10"
            },
            {
              "parameter": "context",
              "value": "Context{eof=false, modulus=0, lineLength=8, lineSeparator=\"\\r\\n\".getBytes(), pos=0, buffer=new byte[20]}"
            }
          ],
          "expected": "Encoded bytes = \"AEBAGBA=\\r\\nCIFCGEI=\\r\\n\" (two 8‑char blocks each followed by CRLF).",
          "description": "Two consecutive 5‑byte blocks exceed the lineLength after the first block, causing a separator to be inserted after each block."
        }
      ]
    }
  ]
}