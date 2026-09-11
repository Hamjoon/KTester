{
    "test cases": [
        {
            "group": "nextRecord - Valid Token Processing with Multiple Tokens",
            "cases": [
                {
                    "input": [],
                    "expected": "CSVRecord object with multiple populated values",
                    "description": "Test the nextRecord method with multiple valid tokens to ensure it processes and returns a CSVRecord with all values correctly populated."
                }
            ]
        },
        {
            "group": "nextRecord - Mixed Token Handling",
            "cases": [
                {
                    "input": [],
                    "expected": "CSVRecord object with mixed values and comments",
                    "description": "Test the nextRecord method when encountering a mix of TOKEN and COMMENT tokens to ensure it correctly handles and stores all values and comments."
                }
            ]
        },
        {
            "group": "nextRecord - Trailing Comments Handling",
            "cases": [
                {
                    "input": [],
                    "expected": "CSVRecord object with trailer comment and values",
                    "description": "Test the nextRecord method when encountering EOF token with ready state and trailing comments to ensure it adds both correctly."
                }
            ]
        },
        {
            "group": "nextRecord - Handling of Consecutive EORECORD",
            "cases": [
                {
                    "input": [],
                    "expected": "CSVRecord object with last record values for consecutive EORECORD",
                    "description": "Test the nextRecord method when encountering multiple consecutive EORECORD tokens to ensure it handles them correctly and returns the last record."
                }
            ]
        },
        {
            "group": "nextRecord - Handling of Empty CSV Input",
            "cases": [
                {
                    "input": [],
                    "expected": "null",
                    "description": "Test the nextRecord method when the CSV input is empty to ensure it returns null."
                }
            ]
        }
    ]
}