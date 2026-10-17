{
    "testCases": [
        {
            "group": "Null Input",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": null
                        }
                    ],
                    "expected": null,
                    "description": "When the input byte array is null, encodeBase64String should return null (the underlying encodeBase64 returns null and newStringUsAscii therefore returns null)."
                }
            ]
        },
        {
            "group": "Empty Input",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": []
                        }
                    ],
                    "expected": "",
                    "description": "An empty byte array should produce an empty Base64 string – no characters, no padding."
                }
            ]
        },
        {
            "group": "Single‑byte Input (padding = \"==\")",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": [0]
                        }
                    ],
                    "expected": "AA==",
                    "description": "One byte (0x00) is encoded to \"AA==\" – tests the branch where input length % 3 == 1 causing two padding characters."
                }
            ]
        },
        {
            "group": "Two‑byte Input (padding = \"=\")",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": [0, 1]
                        }
                    ],
                    "expected": "AAE=",
                    "description": "Two bytes (0x00,0x01) are encoded to \"AAE=\" – tests the branch where input length % 3 == 2 causing one padding character."
                }
            ]
        },
        {
            "group": "Three‑byte Input (no padding)",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": [0, 1, 2]
                        }
                    ],
                    "expected": "AAEC",
                    "description": "Three bytes (0x00,0x01,0x02) fill a full Base64 quantum, producing \"AAEC\" with no padding."
                }
            ]
        },
        {
            "group": "Typical ASCII String",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": [72, 101, 108, 108, 111]     }
                    ],
                    "expected": "SGVsbG8=",
                    "description": "Encodes the ASCII string \"Hello\" (bytes 72,101,108,108,111) to its standard Base64 representation. Verifies normal path without chunking."
                }
            ]
        },
        {
            "group": "Large Input (multiple quanta)",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19]
                        }
                    ],
                    "expected": "AAECAwQFBgcICQoLDA0ODw==",
                    "description": "A 20‑byte array spans several full Base64 quanta (6 groups) and ends with padding. Ensures internal loops handling multiple iterations work correctly."
                }
            ]
        }
    ]
}