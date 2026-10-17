{
    "testCases": [
        {
            "group": "Null and Empty Input",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": null
                        }
                    ],
                    "expected": null,
                    "description": "When the input byte array is null the method should return null without throwing an exception."
                },
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": []
                        }
                    ],
                    "expected": [],
                    "description": "When the input byte array is empty the method should return an empty byte array (no padding, no line‑breaks)."
                }
            ]
        },
        {
            "group": "Standard Encoding (no chunking, no URL‑safe)",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": [97, 98, 99]                      
                        }
                    ],
                    "expected": [89, 87, 74, 106],                    
                    "description": "Encode the ASCII string \"abc\". Expected Base64 output is the byte representation of \"YWJj\". The method is called with the default non‑chunked flag."
                },
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": [102, 111, 111, 98, 97, 114]                    
                        }
                    ],
                    "expected": [102, 111, 111, 98, 97, 114, 61, 61],                    
                    "description": "Encode the ASCII string \"foobar\". Expected Base64 output is \"Zm9vYmFy\" (bytes [122, 109, 57, 118, 89, 109, 70, 121]). This verifies correct handling of a multiple‑of‑3 length input (no padding)."
                }
            ]
        },
        {
            "group": "Padding Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": [97, 110, 121]                    
                        }
                    ],
                    "expected": [89, 87, 53, 121, 73, 71, 61, 61],                    
                    "description": "Encode the ASCII string \"any\" (3 bytes). The Base64 result should be \"YW55\" followed by two padding characters \"==\". This tests the branch that adds padding when the input length is not a multiple of 3."
                },
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": [112, 108, 101, 97, 115, 117, 114, 101]                    
                        }
                    ],
                    "expected": [112, 108, 101, 97, 115, 117, 114, 101, 61],                    
                    "description": "Encode the ASCII string \"pleasure\" (8 bytes). The Base64 result is \"cGxlYXN1cmU=\" which ends with a single padding character. This verifies the single‑padding branch."
                }
            ]
        },
        {
            "group": "Non‑ASCII / Binary Data",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": [0, -1, 127, -128, 34, 56]                    
                        }
                    ],
                    "expected": [65, 80, 47, 47, 87, 119, 54, 115, 77, 61],
                    "description": "Encode a byte array containing negative (signed) values and zero. Expected Base64 string is \"AP//Vw6sM=\" (bytes [65,80,47,47,87,119,54,115,77,61]). This ensures the method correctly treats bytes as unsigned values."
                }
            ]
        }
    ]
}