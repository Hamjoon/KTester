{
  "group": "encodeQuotedPrintable",
  "cases": [
    {
      "input": [
        {
          "parameter": "printable",
          "value": null
        },
        {
          "parameter": "bytes",
          "value": null
        },
        {
          "parameter": "strict",
          "value": false
        }
      ],
      "expected": null,
      "description": "Test with null bytes array. Expecting null return value."
    },
    {
      "input": [
        {
          "parameter": "printable",
          "value": null
        },
        {
          "parameter": "bytes",
          "value": [65, 66, 67]
        },
        {
          "parameter": "strict",
          "value": true
        }
      ],
      "expected": null,
      "description": "Test with bytes array length less than MIN_BYTES in strict mode. Expecting null return value."
    },
    {
      "input": [
        {
          "parameter": "printable",
          "value": null
        },
        {
          "parameter": "bytes",
          "value": [65, 66, 67, 68, 69, 70]
        },
        {
          "parameter": "strict",
          "value": true
        }
      ],
      "expected": [65, 66, 67, 61, 13, 10, 68, 69, 70],
      "description": "Test with bytes array length more than MIN_BYTES in strict mode. Expecting encoded bytes with soft line break."
    },
    {
      "input": [
        {
          "parameter": "printable",
          "value": null
        },
        {
          "parameter": "bytes",
          "value": [65, 66, 61, 68]
        },
        {
          "parameter": "strict",
          "value": true
        }
      ],
      "expected": [65, 66, 61, 61, 68],
      "description": "Test with '=' character in input which is not at the end or second to last. Expecting encoded '=' character."
    },
    {
      "input": [
        {
          "parameter": "printable",
          "value": null
        },
        {
          "parameter": "bytes",
          "value": [32, 65, 66, 67, 32]
        },
        {
          "parameter": "strict",
          "value": true
        }
      ],
      "expected": [32, 65, 66, 67, 61, 32],
      "description": "Test with whitespace at the end of the line in strict mode. Expecting encoded whitespace."
    },
    {
      "input": [
        {
          "parameter": "printable",
          "value": null
        },
        {
          "parameter": "bytes",
          "value": [65, 66, 67]
        },
        {
          "parameter": "strict",
          "value": false
        }
      ],
      "expected": [65, 66, 67],
      "description": "Test with printable bytes in non-strict mode. Expecting same bytes as input."
    },
    {
      "input": [
        {
          "parameter": "printable",
          "value": null
        },
        {
          "parameter": "bytes",
          "value": [10, 13, 61]
        },
        {
          "parameter": "strict",
          "value": false
        }
      ],
      "expected": [61, 30, 61, 0, 61, 3],
      "description": "Test with non-printable bytes in non-strict mode. Expecting encoded bytes."
    }
  ]
}