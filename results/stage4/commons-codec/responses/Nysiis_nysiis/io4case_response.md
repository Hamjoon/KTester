{
    "test cases": [
        {
            "group": "nysiis method",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "John"
                        }
                    ],
                    "expected": "JON",
                    "description": "Test with a normal name that does not trigger any specific rules."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "Philipson"
                        }
                    ],
                    "expected": "FILSON",
                    "description": "Test with input starting with 'PH' to verify that it is replaced with 'FF' and processes remaining characters correctly."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "MacArthur"
                        }
                    ],
                    "expected": "MCCAR",
                    "description": "Test with input starting with 'MAC' to verify that it is replaced with 'MCC' and applies other rules."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "Samantha"
                        }
                    ],
                    "expected": "SAMANT",
                    "description": "Re-testing with input where last character is 'S', to verify that 'S' is removed."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "Katherine"
                        }
                    ],
                    "expected": "CATHR",
                    "description": "Test with input starting with 'K' to verify that it is replaced with 'C' and processes remaining characters correctly."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "Schmidt"
                        }
                    ],
                    "expected": "SSMID",
                    "description": "Test with input starting with 'SCH' to verify that it is replaced with 'SSS' and applies ending rules."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "Doe"
                        }
                    ],
                    "expected": "D",
                    "description": "Re-testing with input ending with 'E' to verify that it is replaced with 'Y'."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "Knightly"
                        }
                    ],
                    "expected": "NIGHTL",
                    "description": "Test with input ending with 'LY' to verify that it processes correctly without triggering ending rules."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "A"
                        }
                    ],
                    "expected": "",
                    "description": "Test with a single character 'A' to verify that it is removed."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "AA"
                        }
                    ],
                    "expected": "",
                    "description": "Test with input 'AA' to verify that both 'A's are removed."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "Bobby"
                        }
                    ],
                    "expected": "BOBY",
                    "description": "Test with a normal name to verify that it correctly processes and retains unique characters."
                }
            ]
        }
    ]
}