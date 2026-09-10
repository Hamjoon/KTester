{
    "testCases": [
        {
            "group": "nextRecord - Empty Input Handling",
            "cases": [
                {
                    "input": [],
                    "expected": "null",
                    "description": "Test nextRecord when there are no tokens available to ensure it returns null."
                }
            ]
        },
        {
            "group": "nextRecord - Multiple Tokens Handling",
            "cases": [
                {
                    "input": [],
                    "expected": "CSVRecord object with multiple values in recordList",
                    "description": "Test nextRecord when multiple valid tokens are present to ensure all values are captured in the recordList."
                }
            ]
        },
        {
            "group": "nextRecord - Mixed Token Handling",
            "cases": [
                {
                    "input": [],
                    "expected": "CSVRecord object with correct values and comments",
                    "description": "Test nextRecord when a mix of TOKEN, COMMENT, and EORECORD tokens are encountered to ensure proper parsing."
                }
            ]
        },
        {
            "group": "nextRecord - Large Input Handling",
            "cases": [
                {
                    "input": [],
                    "expected": "CSVRecord object with populated recordList and correct comments",
                    "description": "Test nextRecord with a large input to ensure it handles large records without issues."
                }
            ]
        },
        {
            "group": "nextRecord - Trailing Comments Handling",
            "cases": [
                {
                    "input": [],
                    "expected": "CSVRecord object with trailerComment containing trailing comments",
                    "description": "Test nextRecord when there are trailing comments after the EOF token to ensure they are captured correctly."
                }
            ]
        }
    ]
}