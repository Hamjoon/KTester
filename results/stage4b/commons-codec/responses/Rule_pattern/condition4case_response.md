{
    "test cases": [
        {
            "group": "Testing pattern method for exact match",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "regex",
                            "value": "^hello$"
                        }
                    ],
                    "expected": true,
                    "description": "Test for an exact match with the string 'hello'. Should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "regex",
                            "value": "^world$"
                        }
                    ],
                    "expected": false,
                    "description": "Test for an exact match with the string 'hello'. Should return false."
                },
                {
                    "input": [
                        {
                            "parameter": "regex",
                            "value": "^$"
                        }
                    ],
                    "expected": true,
                    "description": "Test for an exact match with an empty string. Should return true."
                }
            ]
        },
        {
            "group": "Testing pattern method for prefix match",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "regex",
                            "value": "^hello"
                        }
                    ],
                    "expected": true,
                    "description": "Test for prefix match with the string 'hello world'. Should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "regex",
                            "value": "^world"
                        }
                    ],
                    "expected": false,
                    "description": "Test for prefix match with the string 'hello world'. Should return false."
                }
            ]
        },
        {
            "group": "Testing pattern method for suffix match",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "regex",
                            "value": "world$"
                        }
                    ],
                    "expected": true,
                    "description": "Test for suffix match with the string 'hello world'. Should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "regex",
                            "value": "hello$"
                        }
                    ],
                    "expected": false,
                    "description": "Test for suffix match with the string 'hello world'. Should return false."
                }
            ]
        },
        {
            "group": "Testing pattern method for empty content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "regex",
                            "value": "^$"
                        }
                    ],
                    "expected": true,
                    "description": "Test for a regex that matches any string including empty. Should return true for empty input."
                },
                {
                    "input": [
                        {
                            "parameter": "regex",
                            "value": "^hello$"
                        }
                    ],
                    "expected": false,
                    "description": "Test for a regex that does not match empty input. Should return false for empty input."
                }
            ]
        },
        {
            "group": "Testing pattern method with character classes",
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
        }
    ]
}