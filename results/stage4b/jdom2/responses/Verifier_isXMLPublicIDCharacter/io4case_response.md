{
    "test cases": [
        {
            "group": "isXMLPublicIDCharacter",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": "-"
                        }
                    ],
                    "expected": true,
                    "description": "Test with the character '-', which is a valid XML Public ID character."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": "("
                        }
                    ],
                    "expected": true,
                    "description": "Test with the character '(', which is a valid XML Public ID character."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": ")"
                        }
                    ],
                    "expected": true,
                    "description": "Test with the character ')', which is a valid XML Public ID character."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": "."
                        }
                    ],
                    "expected": true,
                    "description": "Test with the character '.', which is a valid XML Public ID character."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": "/"
                        }
                    ],
                    "expected": true,
                    "description": "Test with the character '/', which is a valid XML Public ID character."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": ":"
                        }
                    ],
                    "expected": true,
                    "description": "Test with the character ':', which is a valid XML Public ID character."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": ";"
                        }
                    ],
                    "expected": true,
                    "description": "Test with the character ';', which is a valid XML Public ID character."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": ">"
                        }
                    ],
                    "expected": false,
                    "description": "Test with the character '>', which is not a valid XML Public ID character."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": "&"
                        }
                    ],
                    "expected": false,
                    "description": "Test with the character '&', which is not a valid XML Public ID character."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": "}"
                        }
                    ],
                    "expected": false,
                    "description": "Test with the character '}', which is not a valid XML Public ID character."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": 128
                        }
                    ],
                    "expected": false,
                    "description": "Test with the character represented by the Unicode code point 128, which is not a valid XML Public ID character."
                }
            ]
        }
    ]
}