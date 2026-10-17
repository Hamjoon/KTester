{
  "groups": [
    {
      "group": "Early‑exit conditions",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "any byte[] (e.g., new byte[]{0x41})" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "1" },
            { "parameter": "context", "value": "Context{eof=true, pos=0, modulus=0, lbitWorkArea=0}" }
          ],
          "expected": "Method returns immediately – no change to context (eof stays true, pos remains 0).",
          "description": "When the context is already marked as EOF the decoder must exit without touching the input."
        },
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{0x41, 0x42}" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "-5" },
            { "parameter": "context", "value": "Context{eof=false, pos=0, modulus=0, lbitWorkArea=0}" }
          ],
          "expected": "Context.eof becomes true; no bytes are written to the output buffer.",
          "description": "A negative inAvail signals the logical end‑of‑stream; the decoder must set eof=true and stop processing."
        },
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{'='}" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "1" },
            { "parameter": "context", "value": "Context{eof=false, pos=0, modulus=0, lbitWorkArea=0}" }
          ],
          "expected": "Context.eof becomes true; loop breaks after the first byte; no output bytes are produced.",
          "description": "Encountering the padding character ('=') must terminate decoding and set eof."
        }
      ]
    },
    {
      "group": "Full‑block decoding (modulus reaches 0)",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{'A','B','C','D','E','F','G','H'}" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "8" },
            { "parameter": "context", "value": "Context{eof=false, pos=0, modulus=0, lbitWorkArea=0}" }
          ],
          "expected": "After processing the 8 characters context.modulus == 0 and 5 decoded bytes are written to the buffer (buffer[0]‑buffer[4]). Context.eof remains false.",
          "description": "Eight valid Base‑32 symbols constitute a complete block; the decoder must emit the corresponding 5 bytes."
        }
      ]
    },
    {
      "group": "Trailing‑character handling (switch on context.modulus)",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{}" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "0" },
            { "parameter": "context", "value": "Context{eof=true, modulus=1, lbitWorkArea=0x1FL, pos=0}" }
          ],
          "expected": "IllegalArgumentException thrown by validateTrailingCharacters().",
          "description": "Modulus 1 represents a solitary 5‑bit chunk – the decoder must reject it as illegal trailing data."
        },
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{}" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "0" },
            { "parameter": "context", "value": "Context{eof=true, modulus=2, lbitWorkArea=0x3FL, pos=0}" }
          ],
          "expected": "One decoded byte is written: (lbitWorkArea>>2) & 0xFF. Context.pos becomes 1.",
          "description": "Modulus 2 (10 bits) – after validation of the two low bits the decoder must output a single byte."
        },
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{}" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "0" },
            { "parameter": "context", "value": "Context{eof=true, modulus=3, lbitWorkArea=0x7FL, pos=0}" }
          ],
          "expected": "IllegalArgumentException thrown by validateTrailingCharacters().",
          "description": "Modulus 3 (15 bits) is not a valid trailing length; the decoder must treat it as an error."
        },
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{}" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "0" },
            { "parameter": "context", "value": "Context{eof=true, modulus=4, lbitWorkArea=0xFFFFL, pos=0}" }
          ],
          "expected": "Two bytes are emitted: first = (lbitWorkArea>>8)&0xFF, second = lbitWorkArea&0xFF. Context.pos becomes 2.",
          "description": "Modulus 4 (20 bits) – after validating the low 4 bits the decoder drops them and outputs two bytes."
        },
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{}" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "0" },
            { "parameter": "context", "value": "Context{eof=true, modulus=5, lbitWorkArea=0x1FFFFFL, pos=0}" }
          ],
          "expected": "Three bytes are emitted: (lbitWorkArea>>16)&0xFF, (lbitWorkArea>>8)&0xFF, lbitWorkArea&0xFF. Context.pos becomes 3.",
          "description": "Modulus 5 (25 bits) – after validating the single low bit the decoder drops it and writes three bytes."
        },
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{}" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "0" },
            { "parameter": "context", "value": "Context{eof=true, modulus=6, lbitWorkArea=0x3FFFFFL, pos=0}" }
          ],
          "expected": "IllegalArgumentException thrown by validateTrailingCharacters().",
          "description": "Modulus 6 (30 bits) is not a legal trailing length; the decoder must raise an exception."
        },
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{}" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "0" },
            { "parameter": "context", "value": "Context{eof=true, modulus=7, lbitWorkArea=0x7FFFFFL, pos=0}" }
          ],
          "expected": "Four bytes are emitted after validating the low 3 bits and shifting right 3: (lbitWorkArea>>24)&0xFF, (>>16)&0xFF, (>>8)&0xFF, (>>0)&0xFF. Context.pos becomes 4.",
          "description": "Modulus 7 (35 bits) – after validating the low 3 bits the decoder drops them and writes four bytes."
        },
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{}" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "0" },
            { "parameter": "context", "value": "Context{eof=true, modulus=8, lbitWorkArea=0xFFFFFFFFL, pos=0}" }
          ],
          "expected": "IllegalStateException with message \"Impossible modulus 8\".",
          "description": "Any modulus outside 0‑7 is impossible; the default clause must throw an IllegalStateException."
        }
      ]
    },
    {
      "group": "No‑trailing processing (modulus == 0 after EOF)",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "new byte[]{'A','B','C','D','E','F','G','H'}" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "8" },
            { "parameter": "context", "value": "Context{eof=true, modulus=0, lbitWorkArea=0, pos=0}" }
          ],
          "expected": "No additional bytes are written after the loop because modulus == 0; only the 5 bytes emitted inside the loop are present.",
          "description": "When EOF is true but the accumulated bits form a complete block (modulus 0) the decoder must skip the trailing‑character switch."
        }
      ]
    }
  ]
}