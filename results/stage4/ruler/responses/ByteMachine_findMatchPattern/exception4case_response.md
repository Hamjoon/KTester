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
                    "value": "NON_EXISTENT_PATTERN"
                }
            ],
            "expected": "null",
            "description": "Test case where the input characters do not match any defined pattern, expecting a null return."
        },
        {
            "input": [
                {
                    "parameter": "characters",
                    "value": ["A", "B", "C"]
                },
                {
                    "parameter": "pattern",
                    "value": null
                }
            ],
            "expected": "null",
            "description": "Test case where the pattern is null, expecting a null return."
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
            "expected": "expected NameState object matching the suffix pattern",
            "description": "Test case where the input characters match a suffix pattern."
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
        }
    ]
}