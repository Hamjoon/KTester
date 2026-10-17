{
    "group": "Null and Empty Input",
    "cases": [
        {
            "input": [
                {
                    "parameter": "base64String",
                    "value": null
                }
            ],
            "expected": null,
            "description": "Passing a null string should return null according to the contract of Base64.decodeBase64(String)."
        },
        {
            "input": [
                {
                    "parameter": "base64String",
                    "value": ""
                }
            ],
            "expected": [],
            "description": "An empty string represents no data and must be decoded to an empty byte array."
        }
    ]
  ,
    "group": "Valid Base64 Decoding",
    "cases": [
        {
            "input": [
                {
                    "parameter": "base64String",
                    "value": "SGVsbG8="
                }
            ],
            "expected": [72, 101, 108, 108, 111],
            "description": "Standard Base64 string with correct padding – should decode to the ASCII bytes of \"Hello\"."
        },
        {
            "input": [
                {
                    "parameter": "base64String",
                    "value": "SGVsbG8"
                }
            ],
            "expected": [72, 101, 108, 108, 111],
            "description": "Base64 string without explicit padding – decoder must handle missing padding and still produce \"Hello\"."
        }
    ]
  ,
    "group": "Whitespace Handling",
    "cases": [
        {
            "input": [
                {
                    "parameter": "base64String",
                    "value": " S G V s b G 8 = "
                }
            ],
            "expected": [72, 101, 108, 108, 111],
            "description": "Base64 input containing spaces and line‑break characters should be ignored by the decoder and yield the same result as the compact form."
        },
        {
            "input": [
                {
                    "parameter": "base64String",
                    "value": "\nSGVs\nbG8=\r\n"
                }
            ],
            "expected": [72, 101, 108, 108, 111],
            "description": "New‑line and carriage‑return characters are also treated as whitespace and must be ignored."
        }
    ]
  ,
    "group": "Padding Variations",
    "cases": [
        {
            "input": [
                {
                    "parameter": "base64String",
                    "value": "SGVsbG8=="
                }
            ],
            "expected": [72, 101, 108, 108, 111],
            "description": "Double padding characters are tolerated; the decoder should still produce the correct byte sequence."
        },
        {
            "input": [
                {
                    "parameter": "base64String",
                    "value": "U29tZSBkYXRhIHdpdGggcGFkZGluZw"
                }
            ],
            "expected": [83, 111, 109, 101, 32, 100, 97, 116, 97, 32, 119, 105, 116, 104, 32, 112, 97, 100, 100, 105, 110, 103],
            "description": "A longer string that ends without padding – decoder must correctly handle the final quantum."
        }
    ]
  ,
    "group": "Invalid Characters",
    "cases": [
        {
            "input": [
                {
                    "parameter": "base64String",
                    "value": "??!!"
                }
            ],
            "expected": [],
            "description": "String containing only characters that are not part of the Base64 alphabet should be ignored, resulting in an empty byte array."
        },
        {
            "input": [
                {
                    "parameter": "base64String",
                    "value": "SGVsbG8$%="
                }
            ],
            "expected": [72, 101, 108, 108, 111],
            "description": "Non‑alphabet characters embedded in a valid Base64 sequence are ignored; the valid portion should still decode to \"Hello\"."
        }
    ]
}