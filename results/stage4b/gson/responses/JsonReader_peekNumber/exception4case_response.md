{
    "group": "Test peekNumber for invalid number formats",
    "cases": [
        {
            "input": [],
            "expected": "PEEKED_NONE",
            "description": "Test with a leading zero in a number (e.g., '0123') to ensure it returns PEEKED_NONE."
        },
        {
            "input": [],
            "expected": "PEEKED_NONE",
            "description": "Test with a non-numeric character (e.g., 'abc') to ensure it returns PEEKED_NONE."
        },
        {
            "input": [],
            "expected": "PEEKED_NONE",
            "description": "Test with a negative sign at an invalid position (e.g., '3-4') to ensure it returns PEEKED_NONE."
        },
        {
            "input": [],
            "expected": "PEEKED_NONE",
            "description": "Test with an exponent without a preceding digit (e.g., 'e10') to ensure it returns PEEKED_NONE."
        },
        {
            "input": [],
            "expected": "PEEKED_NONE",
            "description": "Test with an invalid character following a valid number (e.g., '123abc') to ensure it returns PEEKED_NONE."
        }
    ]
}