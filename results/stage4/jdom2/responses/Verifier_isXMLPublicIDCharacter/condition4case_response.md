{
    "test cases": [
        {
            "group": "isXMLPublicIDCharacter",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": "a"
                        }
                    ],
                    "expected": true,
                    "description": "Test with a lowercase letter 'a', which is a valid XML Public ID character."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": "Z"
                        }
                    ],
                    "expected": true,
                    "description": "Test with an uppercase letter 'Z', which is a valid XML Public ID character."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": " "
                        }
                    ],
                    "expected": true,
                    "description": "Test with a space character, which is a valid XML Public ID character."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": "!"
                        }
                    ],
                    "expected": true,
                    "description": "Test with an exclamation mark '!', which is a valid XML Public ID character."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": "#"
                        }
                    ],
                    "expected": true,
                    "description": "Test with a hash '#' character, which is a valid XML Public ID character."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": "$"
                        }
                    ],
                    "expected": true,
                    "description": "Test with a dollar '$' character, which is a valid XML Public ID character."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": "%"
                        }
                    ],
                    "expected": true,
                    "description": "Test with a percent '%' character, which is a valid XML Public ID character."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": "\n"
                        }
                    ],
                    "expected": true,
                    "description": "Test with a newline character, which is a valid XML Public ID character."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": "\r"
                        }
                    ],
                    "expected": true,
                    "description": "Test with a carriage return character, which is a valid XML Public ID character."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": "\t"
                        }
                    ],
                    "expected": true,
                    "description": "Test with a tab character, which is a valid XML Public ID character."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": "0"
                        }
                    ],
                    "expected": true,
                    "description": "Test with a digit '0', which is a valid XML Public ID character."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": "?"
                        }
                    ],
                    "expected": true,
                    "description": "Test with a question mark '?', which is a valid XML Public ID character."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": "'"
                        }
                    ],
                    "expected": true,
                    "description": "Test with a single quote ''', which is a valid XML Public ID character."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": "("
                        }
                    ],
                    "expected": true,
                    "description": "Test with an opening parenthesis '(', which is a valid XML Public ID character."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": ")"
                        }
                    ],
                    "expected": true,
                    "description": "Test with a closing parenthesis ')', which is a valid XML Public ID character."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": "x"
                        }
                    ],
                    "expected": true,
                    "description": "Test with a lowercase letter 'x', which is a valid XML Public ID character."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": ">"
                        }
                    ],
                    "expected": false,
                    "description": "Test with a greater than '>', which is not a valid XML Public ID character."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": "<"
                        }
                    ],
                    "expected": false,
                    "description": "Test with a less than '<', which is not a valid XML Public ID character."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": "&"
                        }
                    ],
                    "expected": false,
                    "description": "Test with an ampersand '&', which is not a valid XML Public ID character."
                },
                {
                    "input": [
                        {
                            "parameter": "c",
                            "value": "}"
                        }
                    ],
                    "expected": false,
                    "description": "Test with a closing brace '}', which is not a valid XML Public ID character."
                }
            ]
        }
    ]
}