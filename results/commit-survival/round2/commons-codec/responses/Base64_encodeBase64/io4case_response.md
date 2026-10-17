{
  "testCases": [
    {
      "group": "Minimal Input (padding)",
      "cases": [
        {
          "input": [
            {
              "parameter": "binaryData",
              "value": [0]
            }
          ],
          "expected": [65, 65, 61, 61],
          "description": "Single zero byte should be encoded to \"AA==\". Verifies two‑character padding path."
        },
        {
          "input": [
            {
              "parameter": "binaryData",
              "value": [255]
            }
          ],
          "expected": [47, 119, 61, 61],
          "description": "Single 0xFF byte should be encoded to \"/w==\". Tests handling of high‑order unsigned values with padding."
        },
        {
          "input": [
            {
              "parameter": "binaryData",
              "value": [0, 1]
            }
          ],
          "expected": [65, 65, 69, 61],
          "description": "Two‑byte array should be encoded to \"AAE=\" (one padding character). Checks the single‑padding branch."
        }
      ]
    },
    {
      "group": "Non‑multiple‑of‑3 Longer Input",
      "cases": [
        {
          "input": [
            {
              "parameter": "binaryData",
              "value": [1, 2, 3, 4]
            }
          ],
          "expected": [65, 81, 73, 68, 66, 65, 61, 61],
          "description": "Four bytes produce \"AQIDBA==\". Validates correct handling of a leftover single byte after processing full triples."
        },
        {
          "input": [
            {
              "parameter": "binaryData",
              "value": [1, 2, 3, 4, 5]
            }
          ],
          "expected": [65, 81, 73, 68, 66, 65, 85, 61],
          "description": "Five bytes produce \"AQIDBAU=\". Checks the branch where a leftover two‑byte group yields one padding character."
        }
      ]
    },
    {
      "group": "Large Input (no chunking)",
      "cases": [
        {
          "input": [
            {
              "parameter": "binaryData",
              "value": [
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
                10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
                20, 21, 22, 23, 24, 25, 26, 27, 28, 29,
                30, 31, 32, 33, 34, 35, 36, 37, 38, 39,
                40, 41, 42, 43, 44, 45, 46, 47, 48, 49,
                50, 51, 52, 53, 54, 55, 56, 57, 58, 59,
                60, 61, 62, 63, 64, 65, 66, 67, 68, 69,
                70, 71, 72, 73, 74, 75, 76, 77, 78, 79,
                80, 81, 82, 83, 84, 85, 86, 87, 88, 89,
                90, 91, 92, 93, 94, 95, 96, 97, 98, 99
              ]
            }
          ],
          "expected": "byte array of length 136 with no CR/LF characters",
          "description": "Encoding 100 sequential bytes should produce a 136‑byte Base64 result without any line breaks (isChunked = false). Confirms internal buffer growth and line‑separator suppression."
        }
      ]
    },
    {
      "group": "Immutability and Reference Checks",
      "cases": [
        {
          "input": [
            {
              "parameter": "binaryData",
              "value": [97, 98, 99]  ,
              "note": "original array will be mutated after encoding"
            }
          ],
          "expected": {
            "outputUnchangedAfterInputMutation": true,
            "differentInstance": true
          },
          "description": "After calling encodeBase64, mutate the original array (e.g., change 97→0). The returned byte array must remain equal to the encoding of the original data and must not be the same object as the input."
        }
      ]
    }
  ]
}