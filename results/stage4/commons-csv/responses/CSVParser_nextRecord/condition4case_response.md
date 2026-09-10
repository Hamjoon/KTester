{
    "testCases": [
        {
            "group": "nextRecord - Valid Token Parsing",
            "cases": [
                {
                    "input": [],
                    "expected": "CSVRecord object with populated recordList and null comment",
                    "description": "Test nextRecord when there are valid tokens present to ensure a CSVRecord is created with the correct values."
                }
            ]
        },
        {
            "group": "nextRecord - End of Record Handling",
            "cases": [
                {
                    "input": [],
                    "expected": "CSVRecord object with populated recordList and null comment",
                    "description": "Test nextRecord when an end of record (EORECORD) token is encountered to ensure the record is properly added."
                }
            ]
        },
        {
            "group": "nextRecord - End of File Handling",
            "cases": [
                {
                    "input": [],
                    "expected": "CSVRecord object with trailerComment set correctly",
                    "description": "Test nextRecord when an EOF token is encountered and the reusable token is ready, ensuring the last record is added."
                },
                {
                    "input": [],
                    "expected": "trailerComment should contain the last comment read before EOF",
                    "description": "Test nextRecord when EOF is encountered without a ready token to ensure the trailerComment is set."
                }
            ]
        },
        {
            "group": "nextRecord - Invalid Token Handling",
            "cases": [
                {
                    "input": [],
                    "expected": "IOException with message indicating invalid parse sequence",
                    "description": "Test nextRecord when an INVALID token is encountered to ensure an IOException is thrown."
                }
            ]
        },
        {
            "group": "nextRecord - Unexpected Token Handling",
            "cases": [
                {
                    "input": [],
                    "expected": "IllegalStateException with message indicating unexpected token type",
                    "description": "Test nextRecord when an unexpected token type is encountered to ensure an IllegalStateException is thrown."
                }
            ]
        },
        {
            "group": "nextRecord - Comment Handling",
            "cases": [
                {
                    "input": [],
                    "expected": "CSVRecord object with comment containing the parsed comment",
                    "description": "Test nextRecord when a COMMENT token is encountered to ensure comments are appended correctly."
                },
                {
                    "input": [],
                    "expected": "CSVRecord object with null comment if no comments were read",
                    "description": "Test nextRecord when no COMMENT tokens are encountered to ensure the comment is null."
                }
            ]
        }
    ]
}