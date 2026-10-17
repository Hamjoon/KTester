{
    "group": "Base64.isBase64(byte) - additional cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": 43
                }
            ],
            "expected": true,
            "description": "Octet is '+' (ASCII 43), a valid Base64 character mapping to 62. Should return true."
        },
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": 45
                }
            ],
            "expected": true,
            "description": "Octet is '-' (ASCII 45), a valid Base64 character (URL‑safe variant) mapping to 62. Should return true."
        },
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": 47
                }
            ],
            "expected": true,
            "description": "Octet is '/' (ASCII 47), a valid Base64 character mapping to 63. Should return true."
        },
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": 95
                }
            ],
            "expected": true,
            "description": "Octet is '_' (ASCII 95), a valid URL‑safe Base64 character mapping to 63. Should return true."
        },
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": 48
                }
            ],
            "expected": true,
            "description": "Octet is '0' (ASCII 48), a valid Base64 digit mapping to 52. Should return true."
        },
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": 57
                }
            ],
            "expected": true,
            "description": "Octet is '9' (ASCII 57), a valid Base64 digit mapping to 61. Should return true."
        },
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": 64
                }
            ],
            "expected": false,
            "description": "Octet is '@' (ASCII 64), not part of Base64 alphabet. Should return false."
        },
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": 91
                }
            ],
            "expected": false,
            "description": "Octet is '[' (ASCII 91), not part of Base64 alphabet. Should return false."
        },
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": 93
                }
            ],
            "expected": false,
            "description": "Octet is ']' (ASCII 93), not part of Base64 alphabet. Should return false."
        },
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": 124
                }
            ],
            "expected": false,
            "description": "Octet is '|' (ASCII 124), not part of Base64 alphabet. Should return false."
        },
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": -128
                }
            ],
            "expected": false,
            "description": "Octet is the minimum byte value (-128). Negative values cause the first part of the second condition to fail, returning false."
        },
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": 126
                }
            ],
            "expected": false,
            "description": "Octet is '~' (ASCII 126), not a Base64 character. Should return false."
        },
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": 63
                }
            ],
            "expected": false,
            "description": "Octet is '?' (ASCII 63), not a Base64 character. Should return false."
        },
        {
            "input": [
                {
                    "parameter": "octet",
                    "value": 123
                }
            ],
            "expected": false,
            "description": "Octet is '{' (ASCII 123), not a Base64 character. Should return false."
        }
    ]
}