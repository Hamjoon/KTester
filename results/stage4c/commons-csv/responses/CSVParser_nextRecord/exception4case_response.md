{
    "group": "CSVParser.nextRecord() Method Tests",
    "cases": [
        {
            "input": [],
            "expected": "CSVRecord",
            "description": "Test case where a valid CSV record is parsed, expecting a CSVRecord object as output."
        },
        {
            "input": [],
            "expected": "IOException",
            "description": "Test case where an INVALID token is encountered, expecting an IOException to be thrown."
        },
        {
            "input": [],
            "expected": "null",
            "description": "Test case where the end of file (EOF) is reached without a ready token, expecting null as the output."
        },
        {
            "input": [],
            "expected": "CSVRecord",
            "description": "Test case where a comment is encountered followed by a valid record, expecting a CSVRecord object with the comment included."
        },
        {
            "input": [],
            "expected": "IllegalStateException",
            "description": "Test case where an unexpected token type is encountered, expecting an IllegalStateException to be thrown."
        },
        {
            "input": [],
            "expected": "CSVRecord",
            "description": "Test case where an end of record (EORECORD) token is encountered, expecting a CSVRecord object as output."
        },
        {
            "input": [],
            "expected": "CSVRecord",
            "description": "Test case where the EOF is reached with a ready token, expecting a CSVRecord object as output."
        }
    ]
}