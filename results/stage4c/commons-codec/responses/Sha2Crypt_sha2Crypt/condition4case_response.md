{
  "group": "sha2Crypt Method Tests",
  "cases": [
    {
      "input": [
        {
          "parameter": "keyBytes",
          "value": "new byte[] { 1, 2, 3 }"
        },
        {
          "parameter": "salt",
          "value": "null"
        },
        {
          "parameter": "saltPrefix",
          "value": "$5$"
        },
        {
          "parameter": "blocksize",
          "value": "32"
        },
        {
          "parameter": "algorithm",
          "value": "SHA-256"
        }
      ],
      "expected": "IllegalArgumentException",
      "description": "Test case where the salt is null, expecting an IllegalArgumentException to be thrown."
    },
    {
      "input": [
        {
          "parameter": "keyBytes",
          "value": "new byte[] { 1, 2, 3 }"
        },
        {
          "parameter": "salt",
          "value": "$5$rounds=1000$abcd"
        },
        {
          "parameter": "saltPrefix",
          "value": "$5$"
        },
        {
          "parameter": "blocksize",
          "value": "32"
        },
        {
          "parameter": "algorithm",
          "value": "SHA-256"
        }
      ],
      "expected": "Valid Hash",
      "description": "Test case with a valid salt format and rounds set to the minimum (1000), expecting a valid hash output."
    },
    {
      "input": [
        {
          "parameter": "keyBytes",
          "value": "new byte[] { 1, 2, 3 }"
        },
        {
          "parameter": "salt",
          "value": "$5$rounds=999999999$abcd"
        },
        {
          "parameter": "saltPrefix",
          "value": "$5$"
        },
        {
          "parameter": "blocksize",
          "value": "32"
        },
        {
          "parameter": "algorithm",
          "value": "SHA-256"
        }
      ],
      "expected": "Valid Hash",
      "description": "Test case with a valid salt format and rounds set to the maximum (999999999), expecting a valid hash output."
    },
    {
      "input": [
        {
          "parameter": "keyBytes",
          "value": "new byte[] { 1, 2, 3 }"
        },
        {
          "parameter": "salt",
          "value": "$5$rounds=5000$abcd"
        },
        {
          "parameter": "saltPrefix",
          "value": "$5$"
        },
        {
          "parameter": "blocksize",
          "value": "32"
        },
        {
          "parameter": "algorithm",
          "value": "SHA-256"
        }
      ],
      "expected": "Valid Hash",
      "description": "Test case with a valid salt format and default rounds (5000), expecting a valid hash output."
    },
    {
      "input": [
        {
          "parameter": "keyBytes",
          "value": "new byte[] { 1, 2, 3 }"
        },
        {
          "parameter": "salt",
          "value": "$5$abcd"
        },
        {
          "parameter": "saltPrefix",
          "value": "$5$"
        },
        {
          "parameter": "blocksize",
          "value": "32"
        },
        {
          "parameter": "algorithm",
          "value": "SHA-256"
        }
      ],
      "expected": "Valid Hash",
      "description": "Test case with a valid salt format without rounds specification, expecting a valid hash output with default rounds."
    },
    {
      "input": [
        {
          "parameter": "keyBytes",
          "value": "new byte[] { 1, 2, 3 }"
        },
        {
          "parameter": "salt",
          "value": "invalidSalt"
        },
        {
          "parameter": "saltPrefix",
          "value": "$5$"
        },
        {
          "parameter": "blocksize",
          "value": "32"
        },
        {
          "parameter": "algorithm",
          "value": "SHA-256"
        }
      ],
      "expected": "IllegalArgumentException",
      "description": "Test case with an invalid salt format, expecting an IllegalArgumentException to be thrown."
    }
  ]
}