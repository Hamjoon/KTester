{
    "test cases": [
        {
            "group": "Testing pattern method for box content matches",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "regex",
                            "value": "^[aeiou]$"
                        }
                    ],
                    "expected": true,
                    "description": "Test for a regex that matches a single vowel character. Should return true for 'a'."
                },
                {
                    "input": [
                        {
                            "parameter": "regex",
                            "value": "^[aeiou]$"
                        }
                    ],
                    "expected": false,
                    "description": "Test for a regex that matches a single vowel character. Should return false for 'b'."
                },
                {
                    "input": [
                        {
                            "parameter": "regex",
                            "value": "^[^aeiou]$"
                        }
                    ],
                    "expected": true,
                    "description": "Test for a regex that matches a single consonant character. Should return true for 'b'."
                },
                {
                    "input": [
                        {
                            "parameter": "regex",
                            "value": "^[^aeiou]$"
                        }
                    ],
                    "expected": false,
                    "description": "Test for a regex that matches a single consonant character. Should return false for 'a'."
                }
            ]
        },
        {
            "group": "Testing pattern method for matches with mixed content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "regex",
                            "value": "^[a-zA-Z]+$"
                        }
                    ],
                    "expected": true,
                    "description": "Test for a regex that matches any string of alphabetic characters. Should return true for 'hello'."
                },
                {
                    "input": [
                        {
                            "parameter": "regex",
                            "value": "^[a-zA-Z]+$"
                        }
                    ],
                    "expected": false,
                    "description": "Test for a regex that matches any string of alphabetic characters. Should return false for 'hello123'."
                },
                {
                    "input": [
                        {
                            "parameter": "regex",
                            "value": "^[0-9]*$"
                        }
                    ],
                    "expected": true,
                    "description": "Test for a regex that matches any string of digits. Should return true for '12345'."
                },
                {
                    "input": [
                        {
                            "parameter": "regex",
                            "value": "^[0-9]*$"
                        }
                    ],
                    "expected": false,
                    "description": "Test for a regex that matches any string of digits. Should return false for '123abc'."
                }
            ]
        },
        {
            "group": "Testing pattern method for edge cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "regex",
                            "value": "^.*$"
                        }
                    ],
                    "expected": true,
                    "description": "Test for a regex that matches any string including empty. Should return true for an empty string."
                },
                {
                    "input": [
                        {
                            "parameter": "regex",
                            "value": "^$"
                        }
                    ],
                    "expected": true,
                    "description": "Test for a regex that matches the empty string. Should return true for an empty string."
                },
                {
                    "input": [
                        {
                            "parameter": "regex",
                            "value": "^.*$"
                        }
                    ],
                    "expected": true,
                    "description": "Test for a regex that matches any string including empty. Should return true for 'any string'."
                },
                {
                    "input": [
                        {
                            "parameter": "regex",
                            "value": "^[^]*$"
                        }
                    ],
                    "expected": true,
                    "description": "Test for a regex that matches any character including newline. Should return true for 'any string'."
                }
            ]
        },
        {
            "group": "Testing pattern method for negated character classes",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "regex",
                            "value": "^[^aeiou].*$"
                        }
                    ],
                    "expected": true,
                    "description": "Test for a regex that matches a string starting with a consonant. Should return true for 'bacon'."
                },
                {
                    "input": [
                        {
                            "parameter": "regex",
                            "value": "^[^aeiou].*$"
                        }
                    ],
                    "expected": false,
                    "description": "Test for a regex that matches a string starting with a consonant. Should return false for 'apple'."
                }
            ]
        }
    ]
}