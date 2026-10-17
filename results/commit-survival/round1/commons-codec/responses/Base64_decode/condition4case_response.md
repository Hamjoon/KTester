Invalid control character at: line 33 column 84 (char 1435)
{
  "tests": [
    {
      "group": "Early EOF handling",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "[]" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "0" },
            { "parameter": "context", "value": "{ \"eof\": true, \"modulus\": 0, \"pos\": 0, \"ibitWorkArea\": 0 }" }
          ],
          "expected": "Method returns immediately; context remains unchanged (eof = true, pos = 0, no data written).",
          "description": "When the context already signals EOF, decode should exit without touching the input or the internal buffer."
        },
        {
          "input": [
            { "parameter": "input", "value": "[]" },
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "-1" },
            { "parameter": "context", "value": "{ \"eof\": false, \"modulus\": 0, \"pos\": 0, \"ibitWorkArea\": 0 }" }
          ],
          "expected": "Context.eof becomes true after the call; no data is written to the buffer.",
          "description": "A negative inAvail signals an actual EOF; the decoder must set the EOF flag but must not attempt to decode any data."
        }
      ]
    },
    {
      "group": "Padding character handling",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "[84, 87, 70, 117, 61]" },           " // \"TWFu=\" (Base64 for \"Man\" with padding)",
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "5" },
            { "parameter": "context", "value": "{ \"eof\": false, \"modulus\": 0, \"pos\": 0, \"ibitWorkArea\": 0 }" }
          ],
          "expected": "Decoded bytes = [77, 97, 110] (\"Man\"); context.eof = true after the pad is seen.",
          "description": "When the pad character ('=') is encountered the decoder must stop processing, set EOF and output the bytes that were already fully decoded."
        }
      ]
    },
    {
      "group": "Invalid characters (not in Base64 alphabet)",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "[33]" },                     " // '!' (not a Base64 character)",
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "1" },
            { "parameter": "context", "value": "{ \"eof\": false, \"modulus\": 0, \"pos\": 0, \"ibitWorkArea\": 0 }" }
          ],
          "expected": "No bytes are written; context.modulus remains 0; decoder silently skips the character.",
          "description": "Characters that map to a negative value in decodeTable are ignored by the decoder."
        }
      ]
    },
    {
      "group": "Normal full‑block decoding (no explicit EOF)",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "[84, 87, 70, 117]" },        " // \"TWFu\"",
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "4" },
            { "parameter": "context", "value": "{ \"eof\": false, \"modulus\": 0, \"pos\": 0, \"ibitWorkArea\": 0 }" }
          ],
          "expected": "Decoded bytes = [77, 97, 110] (\"Man\"); context.eof stays false because no pad or -1 EOF was seen.",
          "description": "A complete 4‑character Base64 block should produce three output bytes immediately when modulus reaches 0."
        }
      ]
    },
    {
      "group": "Trailing‑bits handling after EOF",
      "cases": [
        {
          "input": [
            { "parameter": "input", "value": "[84]" },                    " // single valid char \"T\"",
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "1" },
            { "parameter": "context", "value": "{ \"eof\": false, \"modulus\": 0, \"pos\": 0, \"ibitWorkArea\": 0 }" }
          ],
          "expected": "IllegalArgumentException thrown by validateTrailingCharacter() (modulus == 1).",
          "description": "After processing one Base64 character the decoder has 6 leftover bits; when EOF is later signalled the decoder must treat this as an error (case 1). The test invokes decode twice: first with the single character, then with inAvail = -1 to trigger EOF."
        },
        {
          "input": [
            { "parameter": "input", "value": "[84, 81]" },                " // \"TQ\" (2 chars)",
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "2" },
            { "parameter": "context", "value": "{ \"eof\": false, \"modulus\": 0, \"pos\": 0, \"ibitWorkArea\": 0 }" }
          ],
          "expected": "Decoded bytes = [77] (single byte); no exception.",
          "description": "Two Base64 characters leave 12 bits (8 useful + 4 padding). After EOF the decoder should validate the 4 spare bits, discard them, and emit one byte (case 2)."
        },
        {
          "input": [
            { "parameter": "input", "value": "[84, 87, 69]" },            " // \"TWE\" (3 chars)",
            { "parameter": "inPos", "value": "0" },
            { "parameter": "inAvail", "value": "3" },
            { "parameter": "context", "value": "{ \"eof\": false, \"modulus\": 0, \"pos\": 0, \"ibitWorkArea\": 0 }" }
          ],
          "expected": "Decoded bytes = [77, 97] (\"Ma\"); no exception.",
          "description": "Three Base64 characters leave 18 bits (two full bytes + 2 spare bits). After EOF the decoder validates the 2 spare bits, discards them, and emits the two bytes (case 3)."
        }
      ]
    }
  ]
}