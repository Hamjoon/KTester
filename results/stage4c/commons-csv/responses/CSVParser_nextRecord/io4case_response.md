{
    "group": "CSVParser.nextRecord() Additional Tests",
    "cases": [
        {
            "input": [],
            "expected": "CSVRecord",
            "description": "Test case where multiple consecutive comments are encountered before a valid record, expecting a CSVRecord object with the concatenated comments included."
        },
        {
            "input": [],
            "expected": "null",
            "description": "Test case where the file contains only comments and then reaches EOF, expecting null as the output."
        },
        {
            "input": [],
            "expected": "CSVRecord",
            "description": "Test case where an EOF is reached immediately after a comment, expecting a CSVRecord object with the comment included."
        },
        {
            "input": [],
            "expected": "null",
            "description": "Test case where the lexer provides no tokens (empty input), expecting null as the output."
        },
        {
            "input": [],
            "expected": "IOException",
            "description": "Test case where a sequence of invalid tokens is encountered without any valid records, expecting an IOException to be thrown."
        }
    ]
}