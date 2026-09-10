{
    "test cases": [
        {
            "group": "nysiis method",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": null
                        }
                    ],
                    "expected": null,
                    "description": "Test with null input to verify that the method returns null."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": ""
                        }
                    ],
                    "expected": "",
                    "description": "Test with an empty string to verify that the method returns an empty string."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "MACDonald"
                        }
                    ],
                    "expected": "MCCD",
                    "description": "Test with input starting with 'MAC' to verify that it is replaced with 'MCC'."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "KNight"
                        }
                    ],
                    "expected": "NIGHT",
                    "description": "Test with input starting with 'KN' to verify that it is replaced with 'NN'."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "Kite"
                        }
                    ],
                    "expected": "CITE",
                    "description": "Test with input starting with 'K' to verify that it is replaced with 'C'."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "Philip"
                        }
                    ],
                    "expected": "FILI",
                    "description": "Test with input starting with 'PH' to verify that it is replaced with 'FF'."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "SCHOOL"
                        }
                    ],
                    "expected": "SSCOL",
                    "description": "Test with input starting with 'SCH' to verify that it is replaced with 'SSS'."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "Doe"
                        }
                    ],
                    "expected": "D",
                    "description": "Test with input ending with 'E' to verify that it is replaced with 'Y'."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "Knight"
                        }
                    ],
                    "expected": "NIGHT",
                    "description": "Test with input ending with 'HT' to verify that it is replaced with 'D'."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "Samantha"
                        }
                    ],
                    "expected": "SAMANT",
                    "description": "Test with input where last character is 'S', to verify that 'S' is removed."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "Ray"
                        }
                    ],
                    "expected": "R",
                    "description": "Test with input where last characters are 'AY', to verify that 'AY' is replaced with 'Y'."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "Sara"
                        }
                    ],
                    "expected": "S",
                    "description": "Test with input where last character is 'A', to verify that 'A' is removed."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "Alexander"
                        }
                    ],
                    "expected": "ALXAND",
                    "description": "Test with input to verify that the method correctly transcodes remaining characters and applies all rules."
                }
            ]
        }
    ]
}