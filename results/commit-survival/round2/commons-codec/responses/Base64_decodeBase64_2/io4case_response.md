{
  "testCases": [
    {
      "group": "Standard Decoding",
      "cases": [
        {
          "input": [
            {
              "parameter": "base64String",
              "value": "TWFu"
            }
          ],
          "expected": [77, 97, 110],
          "description": "Simple, non‑padded Base64 string representing \"Man\"."
        },
        {
          "input": [
            {
              "parameter": "base64String",
              "value": "SGVsbG8gV29ybGQh"
            }
          ],
          "expected": [72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100, 33],
          "description": "Standard Base64 string with no line breaks, decoding to \"Hello World!\"."
        }
      ]
    },
    {
      "group": "Padding Variations",
      "cases": [
        {
          "input": [
            {
              "parameter": "base64String",
              "value": "TQ=="
            }
          ],
          "expected": [77],
          "description": "Two padding characters – decodes to single byte \"M\"."
        },
        {
          "input": [
            {
              "parameter": "base64String",
              "value": "TWE="
            }
          ],
          "expected": [77, 97],
          "description": "One padding character – decodes to two bytes \"Ma\"."
        },
        {
          "input": [
            {
              "parameter": "base64String",
              "value": "TQ"
            }
          ],
          "expected": [77],
          "description": "Missing padding – Apache Commons Base64 tolerates the omission and still decodes to \"M\"."
        }
      ]
    },
    {
      "group": "Whitespace Handling",
      "cases": [
        {
          "input": [
            {
              "parameter": "base64String",
              "value": " T W F u "
            }
          ],
          "expected": [77, 97, 110],
          "description": "Spaces between characters are ignored; result identical to \"TWFu\"."
        },
        {
          "input": [
            {
              "parameter": "base64String",
              "value": "TWFu\r\nIGlz\r\nIGRpc3Rpbmd1aXNoZWQ="
            }
          ],
          "expected": [77, 97, 110, 32, 105, 115, 32, 100, 105, 115, 116, 105, 110, 103, 117, 105, 115, 104, 101, 100],
          "description": "CR/LF line breaks are ignored; decodes to \"Man is distinguished\"."
        }
      ]
    },
    {
      "group": "Empty and Null Input",
      "cases": [
        {
          "input": [
            {
              "parameter": "base64String",
              "value": ""
            }
          ],
          "expected": [],
          "description": "Empty string should produce an empty byte array."
        },
        {
          "input": [
            {
              "parameter": "base64String",
              "value": null
            }
          ],
          "expected": null,
          "description": "Null argument returns null (as defined by Apache Commons Codec)."
        }
      ]
    },
    {
      "group": "Invalid Length / Insufficient Data",
      "cases": [
        {
          "input": [
            {
              "parameter": "base64String",
              "value": "A"
            }
          ],
          "expected": [],
          "description": "Single Base64 character cannot form a complete byte; decoder ignores it and returns an empty array."
        }
      ]
    }
  ]
}