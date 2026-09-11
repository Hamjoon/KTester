{
    "test cases": [
        {
            "group": "nysiis method tests",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": null
                        }
                    ],
                    "expected": null,
                    "description": "Test with null input; should return null."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": ""
                        }
                    ],
                    "expected": "",
                    "description": "Test with empty string input; should return empty string."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "MAC"
                        }
                    ],
                    "expected": "MCC",
                    "description": "Test with input 'MAC'; should translate to 'MCC'."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "KNIGHT"
                        }
                    ],
                    "expected": "NIGHT",
                    "description": "Test with input 'KNIGHT'; should translate 'KN' to 'NN'."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "KITE"
                        }
                    ],
                    "expected": "CITE",
                    "description": "Test with input 'KITE'; should translate 'K' to 'C'."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "PHILIP"
                        }
                    ],
                    "expected": "FFILIP",
                    "description": "Test with input 'PHILIP'; should translate 'PH' to 'FF'."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "SCHOOL"
                        }
                    ],
                    "expected": "SSCHOOL",
                    "description": "Test with input 'SCHOOL'; should translate 'SCH' to 'SSS'."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "JOHN"
                        }
                    ],
                    "expected": "JOHN",
                    "description": "Test with input 'JOHN'; should remain unchanged as no patterns match."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "HENRY"
                        }
                    ],
                    "expected": "HENRY",
                    "description": "Test with input 'HENRY'; should remain unchanged as no patterns match."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "TOMMY"
                        }
                    ],
                    "expected": "TOMMY",
                    "description": "Test with input 'TOMMY'; should remain unchanged as no patterns match."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "SANDY"
                        }
                    ],
                    "expected": "SANDY",
                    "description": "Test with input 'SANDY'; should remain unchanged and not remove 'S' as it's not the last character."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "S"
                        }
                    ],
                    "expected": "",
                    "description": "Test with input 'S'; should remove last character 'S' resulting in empty string."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "RAY"
                        }
                    ],
                    "expected": "R",
                    "description": "Test with input 'RAY'; should remove last 'A' resulting in 'R'."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "HAY"
                        }
                    ],
                    "expected": "H",
                    "description": "Test with input 'HAY'; should remove last 'A' resulting in 'H'."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "AAAY"
                        }
                    ],
                    "expected": "Y",
                    "description": "Test with input 'AAAY'; should remove last 'A' and 'AY' resulting in 'Y'."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "TESTING"
                        }
                    ],
                    "expected": "TESTING",
                    "description": "Test with input 'TESTING'; should remain unchanged as no patterns match."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "SOMETHING"
                        }
                    ],
                    "expected": "SOMETHING",
                    "description": "Test with input 'SOMETHING'; should remain unchanged as no patterns match."
                }
            ]
        }
    ]
}