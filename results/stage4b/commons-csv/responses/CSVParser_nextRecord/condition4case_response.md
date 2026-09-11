{
    "testCases": [
        {
            "group": "nextRecord - Valid Token Processing",
            "cases": [
                {
                    "input": [],
                    "expected": "CSVRecord object with populated values",
                    "description": "Test the nextRecord method with valid tokens to ensure it processes and returns a CSVRecord correctly."
                }
            ]
        },
        {
            "group": "nextRecord - End of Record Handling",
            "cases": [
                {
                    "input": [],
                    "expected": "CSVRecord object with last record values",
                    "description": "Test the nextRecord method when encountering an end of record (EORecord) token to ensure it adds the last record value correctly."
                }
            ]
        },
        {
            "group": "nextRecord - End of File Handling",
            "cases": [
                {
                    "input": [],
                    "expected": "CSVRecord object with trailer comment",
                    "description": "Test the nextRecord method when encountering an EOF token with ready state to ensure it adds a trailer comment correctly."
                },
                {
                    "input": [],
                    "expected": "null",
                    "description": "Test the nextRecord method when encountering an EOF token without ready state to ensure it returns null."
                }
            ]
        },
        {
            "group": "nextRecord - Invalid Token Handling",
            "cases": [
                {
                    "input": [],
                    "expected": "IOException with message indicating invalid parse sequence",
                    "description": "Test the nextRecord method when encountering an INVALID token to ensure it throws an IOException."
                }
            ]
        },
        {
            "group": "nextRecord - Comment Handling",
            "cases": [
                {
                    "input": [],
                    "expected": "CSVRecord object with comment",
                    "description": "Test the nextRecord method when encountering COMMENT tokens to ensure it correctly handles and stores comments."
                }
            ]
        },
        {
            "group": "nextRecord - Unexpected Token Handling",
            "cases": [
                {
                    "input": [],
                    "expected": "IllegalStateException with message indicating unexpected token type",
                    "description": "Test the nextRecord method when encountering an unexpected token type to ensure it throws an IllegalStateException."
                }
            ]
        },
        {
            "group": "nextRecord - Empty Record List Handling",
            "cases": [
                {
                    "input": [],
                    "expected": "null",
                    "description": "Test the nextRecord method when the recordList is empty to ensure it returns null."
                }
            ]
        }
    ]
}