{
    "test cases": [
        {
            "group": "colognePhonetic",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": null
                        }
                    ],
                    "expected": null,
                    "description": "Test case where input is null. The method should return null."
                },
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": "ABCD"
                        }
                    ],
                    "expected": "1002",
                    "description": "Test case with a simple input containing letters 'A', 'B', 'C', 'D'. Expected output should be '1002'."
                },
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": "HELLO"
                        }
                    ],
                    "expected": "500",
                    "description": "Test case with input 'HELLO'. Expected output should be '500'."
                },
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": "SCHOOL"
                        }
                    ],
                    "expected": "848",
                    "description": "Test case with input 'SCHOOL'. Expected output should be '848'."
                },
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": "XENON"
                        }
                    ],
                    "expected": "48",
                    "description": "Test case with input 'XENON'. Expected output should be '48'."
                },
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": "PHONETIC"
                        }
                    ],
                    "expected": "1320",
                    "description": "Test case with input 'PHONETIC'. Expected output should be '1320'."
                },
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": "ZEBRA"
                        }
                    ],
                    "expected": "80",
                    "description": "Test case with input 'ZEBRA'. Expected output should be '80'."
                },
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": "CIPHER"
                        }
                    ],
                    "expected": "48",
                    "description": "Test case with input 'CIPHER'. Expected output should be '48'."
                },
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": "RANDOM"
                        }
                    ],
                    "expected": "67",
                    "description": "Test case with input 'RANDOM'. Expected output should be '67'."
                },
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": "12345"
                        }
                    ],
                    "expected": "",
                    "description": "Test case with input '12345'. The method should ignore non-alphabetic characters and return an empty string."
                },
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": "A-B-C"
                        }
                    ],
                    "expected": "0",
                    "description": "Test case with input 'A-B-C'. Expected output should be '0', as only 'A' is considered."
                }
            ]
        }
    ]
}