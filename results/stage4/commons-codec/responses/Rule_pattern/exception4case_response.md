{
    "output": [
        {
            "group": "test pattern with invalid regex",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "regex",
                            "value": "["
                        }
                    ],
                    "expected": "PatternSyntaxException",
                    "description": "Test invalid regex pattern. Input '[' should throw a PatternSyntaxException."
                },
                {
                    "input": [
                        {
                            "parameter": "regex",
                            "value": "abc["
                        }
                    ],
                    "expected": "PatternSyntaxException",
                    "description": "Test invalid regex pattern. Input 'abc[' should throw a PatternSyntaxException."
                },
                {
                    "input": [
                        {
                            "parameter": "regex",
                            "value": "abc\\"
                        }
                    ],
                    "expected": "PatternSyntaxException",
                    "description": "Test invalid regex pattern. Input 'abc\\' should throw a PatternSyntaxException."
                },
                {
                    "input": [
                        {
                            "parameter": "regex",
                            "value": "(abc"
                        }
                    ],
                    "expected": "PatternSyntaxException",
                    "description": "Test invalid regex pattern. Input '(abc' should throw a PatternSyntaxException."
                }
            ]
        },
        {
            "group": "test pattern with empty regex",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "regex",
                            "value": ""
                        }
                    ],
                    "expected": true,
                    "description": "Test empty regex. Should match any input, including empty string."
                }
            ]
        },
        {
            "group": "test pattern with negated character class",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "regex",
                            "value": "[^xyz]"
                        }
                    ],
                    "expected": true,
                    "description": "Test negated character class. Input 'a' should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "regex",
                            "value": "[^xyz]"
                        }
                    ],
                    "expected": false,
                    "description": "Test negated character class. Input 'x' should return false."
                }
            ]
        },
        {
            "group": "test pattern with character class and boundary conditions",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "regex",
                            "value": "[0-9]"
                        }
                    ],
                    "expected": true,
                    "description": "Test character class for digits. Input '5' should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "regex",
                            "value": "[0-9]"
                        }
                    ],
                    "expected": false,
                    "description": "Test character class for digits. Input 'a' should return false."
                }
            ]
        },
        {
            "group": "test pattern with complex regex",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "regex",
                            "value": "^(abc|def)$"
                        }
                    ],
                    "expected": true,
                    "description": "Test complex regex with alternation. Input 'abc' should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "regex",
                            "value": "^(abc|def)$"
                        }
                    ],
                    "expected": false,
                    "description": "Test complex regex with alternation. Input 'xyz' should return false."
                }
            ]
        },
        {
            "group": "test pattern with mixed conditions",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "regex",
                            "value": "^[abc][xyz]$"
                        }
                    ],
                    "expected": true,
                    "description": "Test mixed conditions. Input 'ay' should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "regex",
                            "value": "^[abc][xyz]$"
                        }
                    ],
                    "expected": false,
                    "description": "Test mixed conditions. Input 'bz' should return false."
                },
                {
                    "input": [
                        {
                            "parameter": "regex",
                            "value": "^[^a][^b]$"
                        }
                    ],
                    "expected": true,
                    "description": "Test mixed negated conditions. Input 'cd' should return true."
                }
            ]
        }
    ]
}