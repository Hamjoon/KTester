{
    "testCases": [
        {
            "group": "transcodeRemaining - Additional Vowel Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "prev",
                            "value": "B"
                        },
                        {
                            "parameter": "curr",
                            "value": "I"
                        },
                        {
                            "parameter": "next",
                            "value": "X"
                        },
                        {
                            "parameter": "aNext",
                            "value": "Y"
                        }
                    ],
                    "expected": "['A']",
                    "description": "Test case for vowel 'I' to ensure it returns ['A']."
                },
                {
                    "input": [
                        {
                            "parameter": "prev",
                            "value": "B"
                        },
                        {
                            "parameter": "curr",
                            "value": "O"
                        },
                        {
                            "parameter": "next",
                            "value": "X"
                        },
                        {
                            "parameter": "aNext",
                            "value": "Y"
                        }
                    ],
                    "expected": "['A']",
                    "description": "Test case for vowel 'O' to ensure it returns ['A']."
                },
                {
                    "input": [
                        {
                            "parameter": "prev",
                            "value": "B"
                        },
                        {
                            "parameter": "curr",
                            "value": "U"
                        },
                        {
                            "parameter": "next",
                            "value": "X"
                        },
                        {
                            "parameter": "aNext",
                            "value": "Y"
                        }
                    ],
                    "expected": "['A']",
                    "description": "Test case for vowel 'U' to ensure it returns ['A']."
                }
            ]
        },
        {
            "group": "transcodeRemaining - Additional Character Mapping",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "prev",
                            "value": "B"
                        },
                        {
                            "parameter": "curr",
                            "value": "X"
                        },
                        {
                            "parameter": "next",
                            "value": "Y"
                        },
                        {
                            "parameter": "aNext",
                            "value": "Z"
                        }
                    ],
                    "expected": "['X']",
                    "description": "Test case for character 'X' to ensure it returns ['X']."
                },
                {
                    "input": [
                        {
                            "parameter": "prev",
                            "value": "B"
                        },
                        {
                            "parameter": "curr",
                            "value": "J"
                        },
                        {
                            "parameter": "next",
                            "value": "K"
                        },
                        {
                            "parameter": "aNext",
                            "value": "L"
                        }
                    ],
                    "expected": "['J']",
                    "description": "Test case for character 'J' to ensure it returns ['J']."
                }
            ]
        },
        {
            "group": "transcodeRemaining - Additional Special Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "prev",
                            "value": "B"
                        },
                        {
                            "parameter": "curr",
                            "value": "S"
                        },
                        {
                            "parameter": "next",
                            "value": "H"
                        },
                        {
                            "parameter": "aNext",
                            "value": "Y"
                        }
                    ],
                    "expected": "['S']",
                    "description": "Test case for 'S' followed by 'H' to ensure it returns ['S']."
                },
                {
                    "input": [
                        {
                            "parameter": "prev",
                            "value": "A"
                        },
                        {
                            "parameter": "curr",
                            "value": "H"
                        },
                        {
                            "parameter": "next",
                            "value": "E"
                        },
                        {
                            "parameter": "aNext",
                            "value": "F"
                        }
                    ],
                    "expected": "['A']",
                    "description": "Test case for 'H' with vowel previous to ensure it returns ['A']."
                }
            ]
        }
    ]
}