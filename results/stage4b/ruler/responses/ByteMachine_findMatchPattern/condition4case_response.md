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
                    "expected": "NameState object corresponding to the pattern",
                    "description": "Test case where the input characters match the pattern exactly."
                },
                {
                    "input": [
                        {
                            "parameter": "characters",
                            "value": ["A", "B", "C"]
                        },
                        {
                            "parameter": "pattern",
                            "value": "SUFFIX"
                        }
                    ],
                    "expected": "null",
                    "description": "Test case where the input characters do not match the pattern type SUFFIX."
                },
                {
                    "input": [
                        {
                            "parameter": "characters",
                            "value": ["X", "Y", "Z"]
                        },
                        {
                            "parameter": "pattern",
                            "value": "ANYTHING_BUT_SUFFIX"
                        }
                    ],
                    "expected": "NameState object or null based on transition logic",
                    "description": "Test case where the input characters do not match the suffix and should return either a NameState or null based on internal transitions."
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
                    "description": "Test case where no input characters are provided, expecting null as output."
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
                    "expected": "NameState object corresponding to the pattern",
                    "description": "Test case where a single character matches the EXISTS pattern."
                },
                {
                    "input": [
                        {
                            "parameter": "characters",
                            "value": ["A", "B"]
                        },
                        {
                            "parameter": "pattern",
                            "value": "EXACT"
                        }
                    ],
                    "expected": "null",
                    "description": "Test case where the input characters do not match the exact pattern."
                }
            ]
        }
    ]
}