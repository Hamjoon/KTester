{
    "testCases": [
        {
            "group": "findMatchPattern",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "characters",
                            "value": ["A", "B", "C"]
                        },
                        {
                            "parameter": "pattern",
                            "value": "EXACT"
                        }
                    ],
                    "expected": "expected NameState object matching the pattern",
                    "description": "Test case where the input characters match the exact pattern."
                },
                {
                    "input": [
                        {
                            "parameter": "characters",
                            "value": ["A", "B", "C"]
                        },
                        {
                            "parameter": "pattern",
                            "value": "ANYTHING_BUT_SUFFIX"
                        }
                    ],
                    "expected": "expected NameState object matching the pattern",
                    "description": "Test case where the input characters match the pattern but should not match a suffix."
                },
                {
                    "input": [
                        {
                            "parameter": "characters",
                            "value": ["X", "Y", "Z"]
                        },
                        {
                            "parameter": "pattern",
                            "value": "SUFFIX"
                        }
                    ],
                    "expected": "null",
                    "description": "Test case where the input characters do not match the pattern, expecting a null return."
                },
                {
                    "input": [
                        {
                            "parameter": "characters",
                            "value": []
                        },
                        {
                            "parameter": "pattern",
                            "value": "EXACT"
                        }
                    ],
                    "expected": "null",
                    "description": "Test case with empty character array, expecting a null return."
                },
                {
                    "input": [
                        {
                            "parameter": "characters",
                            "value": ["A"]
                        },
                        {
                            "parameter": "pattern",
                            "value": "EXISTS"
                        }
                    ],
                    "expected": "expected NameState object matching the pattern",
                    "description": "Test case where the input contains a single character that matches the EXISTS pattern."
                },
                {
                    "input": [
                        {
                            "parameter": "characters",
                            "value": ["A", "B", "C", "D"]
                        },
                        {
                            "parameter": "pattern",
                            "value": "EXACT"
                        }
                    ],
                    "expected": "expected NameState object matching the pattern or null",
                    "description": "Test case where the input characters may match a longer pattern; expect a NameState or null based on the pattern."
                }
            ]
        }
    ]
}