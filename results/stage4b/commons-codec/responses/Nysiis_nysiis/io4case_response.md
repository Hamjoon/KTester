{
    "test cases": [
        {
            "group": "nysiis method tests",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "PHILIPSON"
                        }
                    ],
                    "expected": "FFILIPSON",
                    "description": "Test with input 'PHILIPSON'; should translate 'PH' to 'FF'."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "SCHOLARS"
                        }
                    ],
                    "expected": "SSCHOLARS",
                    "description": "Test with input 'SCHOLARS'; should translate 'SCH' to 'SSS'."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "KNOCK"
                        }
                    ],
                    "expected": "NOC",
                    "description": "Test with input 'KNOCK'; should translate 'KN' to 'NN' and 'K' to 'C'."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "HENRYSON"
                        }
                    ],
                    "expected": "HENRYSON",
                    "description": "Test with input 'HENRYSON'; should remain unchanged as no patterns match."
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
                            "value": "AAAYY"
                        }
                    ],
                    "expected": "Y",
                    "description": "Test with input 'AAAYY'; should remove last 'A' and 'AY' resulting in 'Y'."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "DANIEL"
                        }
                    ],
                    "expected": "DANIEL",
                    "description": "Test with input 'DANIEL'; should remain unchanged as no patterns match."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "KAY"
                        }
                    ],
                    "expected": "K",
                    "description": "Test with input 'KAY'; should remove last 'A' resulting in 'K'."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "RAYMOND"
                        }
                    ],
                    "expected": "RAYMOND",
                    "description": "Test with input 'RAYMOND'; should remain unchanged as no patterns match."
                },
                {
                    "input": [
                        {
                            "parameter": "str",
                            "value": "PHOEBE"
                        }
                    ],
                    "expected": "FFOEBE",
                    "description": "Test with input 'PHOEBE'; should translate 'PH' to 'FF'."
                }
            ]
        }
    ]
}