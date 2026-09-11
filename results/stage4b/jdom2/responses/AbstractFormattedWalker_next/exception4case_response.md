{
    "group": "next() method tests",
    "cases": [
        {
            "input": [],
            "expected": "NoSuchElementException",
            "description": "Test when hasnext is false, should throw NoSuchElementException."
        },
        {
            "input": [],
            "expected": "null",
            "description": "Test when pending is null and there is no more content, should return null."
        },
        {
            "input": [],
            "expected": "Content object",
            "description": "Test when pending is not null and there is more content, should return the next content object."
        },
        {
            "input": [],
            "expected": "null",
            "description": "Test when the next content is non-text and there is more content, should return the non-text content."
        },
        {
            "input": [],
            "expected": "Content object",
            "description": "Test when the next content is text-like, should return the text-like content."
        },
        {
            "input": [],
            "expected": "Content object",
            "description": "Test when pending is whitespace and newlineindent is not null, should return the newline indent."
        },
        {
            "input": [],
            "expected": "Content object",
            "description": "Test when pendingmt is not null and needs to be processed, should return the processed multi-text content."
        },
        {
            "input": [],
            "expected": "null",
            "description": "Test when the last content was text-like and now there is no more text-like content, should return null."
        },
        {
            "input": [],
            "expected": "NoSuchElementException",
            "description": "Test when multitext is not null and mtpos exceeds mtsize, should throw NoSuchElementException."
        },
        {
            "input": [],
            "expected": "NoSuchElementException",
            "description": "Test when the escape strategy changes while processing pendingmt, should throw NoSuchElementException."
        }
    ]
}