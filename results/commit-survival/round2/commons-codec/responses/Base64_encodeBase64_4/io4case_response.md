{
  "testCases": [
    {
      "group": "Standard encoding without chunking",
      "cases": [
        {
          "input": [
            { "parameter": "binaryData", "value": [97, 110, 121] },
            { "parameter": "isChunked", "value": false },
            { "parameter": "urlSafe", "value": false },
            { "parameter": "maxResultSize", "value": 10 }
          ],
          "expected": "YW55",
          "description": "Encode a short ASCII payload without chunking or URL‑safe mode; the result should be the standard Base64 string \"YW55\"."
        }
      ]
    },
    {
      "group": "Chunked encoding with line separators",
      "cases": [
        {
          "input": [
            { "parameter": "binaryData", "value": [97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97] },
            { "parameter": "isChunked", "value": true },
            { "parameter": "urlSafe", "value": false },
            { "parameter": "maxResultSize", "value": 2000 }
          ],
          "expected": "contains CRLF at position 76 (i.e., line separator \"\\r\\n\" is inserted)",
          "description": "Encode 57 bytes of the character 'a' with chunking enabled. Because the encoded output reaches the default line length of 76 characters, a CRLF line separator should be inserted, increasing the total length by 2."
        }
      ]
    },
    {
      "group": "URL‑safe encoding",
      "cases": [
        {
          "input": [
            { "parameter": "binaryData", "value": [251, 255, 254] },
            { "parameter": "isChunked", "value": false },
            { "parameter": "urlSafe", "value": true },
            { "parameter": "maxResultSize", "value": 10 }
          ],
          "expected": "-__-",
          "description": "Encode three non‑ASCII bytes with URL‑safe mode; '+' should become '-', '/' should become '_' resulting in the string \"-__-\"."
        }
      ]
    },
    {
      "group": "Maximum size limit satisfied",
      "cases": [
        {
          "input": [
            { "parameter": "binaryData", "value": [1, 2, 3, 4] },
            { "parameter": "isChunked", "value": false },
            { "parameter": "urlSafe", "value": false },
            { "parameter": "maxResultSize", "value": 8 }
          ],
          "expected": "AQIDBA==",
          "description": "The encoded length of a 4‑byte array is 8 bytes; providing maxResultSize equal to 8 allows successful encoding."
        }
      ]
    },
    {
      "group": "Maximum size limit exceeded",
      "cases": [
        {
          "input": [
            { "parameter": "binaryData", "value": [1, 2, 3, 4] },
            { "parameter": "isChunked", "value": false },
            { "parameter": "urlSafe", "value": false },
            { "parameter": "maxResultSize", "value": 7 }
          ],
          "expected": "IllegalArgumentException",
          "description": "When maxResultSize (7) is smaller than the required encoded length (8), the method must throw IllegalArgumentException."
        }
      ]
    },
    {
      "group": "Null input handling",
      "cases": [
        {
          "input": [
            { "parameter": "binaryData", "value": null },
            { "parameter": "isChunked", "value": false },
            { "parameter": "urlSafe", "value": false },
            { "parameter": "maxResultSize", "value": 10 }
          ],
          "expected": null,
          "description": "A null byte array is considered empty; the method should return null without performing any encoding."
        }
      ]
    }
  ]
}