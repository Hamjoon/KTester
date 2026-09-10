{
    "testCases": [
        {
            "group": "findMatchPattern",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "characters",
                            "value": ["A", "B", "C", "D", "E"]
                        },
                        {
                            "parameter": "pattern",
                            "value": "EXACT"
                        }
                    ],
                    "expected": "expected NameState object matching the pattern",
                    "description": "Test case where the input characters match a longer exact pattern."
                },
                {
                    "input": [
                        {
                            "parameter": "characters",
                            "value": ["D", "E", "F"]
                        },
                        {
                            "parameter": "pattern",
                            "value": "SUFFIX"
                        }
                    ],
                    "expected": "expected NameState object matching the suffix pattern",
                    "description": "Test case where the input characters match a suffix pattern."
                },
                {
                    "input": [
                        {
                            "parameter": "characters",
                            "value": ["A", "B", "C", "D"]
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
                            "value": ["A", "B", "C"]
                        },
                        {
                            "parameter": "pattern",
                            "value": "EXISTS"
                        }
                    ],
                    "expected": "expected NameState object matching the pattern",
                    "description": "Test case where the input characters match the EXISTS pattern."
                },
                {
                    "input": [
                        {
                            "parameter": "characters",
                            "value": ["1", "2", "3"]
                        },
                        {
                            "parameter": "pattern",
                            "value": "EXACT"
                        }
                    ],
                    "expected": "null",
                    "description": "Test case with numeric characters that do not match the EXACT pattern, expecting a null return."
                },
                {
                    "input": [
                        {
                            "parameter": "characters",
                            "value": ["A", "B"]
                        },
                        {
                            "parameter": "pattern",
                            "value": "ANYTHING_BUT_SUFFIX"
                        }
                    ],
                    "expected": "expected NameState object matching the pattern",
                    "description": "Test case where the input characters match the pattern but should not match a suffix."
                }
            ]
        }
    ]
}