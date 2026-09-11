{
    "test cases": [
        {
            "group": "Test peekNumber for valid long number",
            "cases": [
                {
                    "input": [],
                    "expected": "PEEKED_LONG",
                    "description": "Test with a valid long number to ensure it is correctly identified as PEEKED_LONG."
                }
            ]
        },
        {
            "group": "Test peekNumber for valid double number",
            "cases": [
                {
                    "input": [],
                    "expected": "PEEKED_NUMBER",
                    "description": "Test with a valid double number to ensure it is correctly identified as PEEKED_NUMBER."
                }
            ]
        },
        {
            "group": "Test peekNumber for invalid number formats",
            "cases": [
                {
                    "input": [],
                    "expected": "PEEKED_NONE",
                    "description": "Test with an invalid number format (e.g., leading zero) to ensure it returns PEEKED_NONE."
                },
                {
                    "input": [],
                    "expected": "PEEKED_NONE",
                    "description": "Test with a non-numeric character to ensure it returns PEEKED_NONE."
                },
                {
                    "input": [],
                    "expected": "PEEKED_NONE",
                    "description": "Test with a negative sign at an invalid position to ensure it returns PEEKED_NONE."
                },
                {
                    "input": [],
                    "expected": "PEEKED_NONE",
                    "description": "Test with an exponent without a preceding digit to ensure it returns PEEKED_NONE."
                },
                {
                    "input": [],
                    "expected": "PEEKED_NONE",
                    "description": "Test with an invalid character following a valid number to ensure it returns PEEKED_NONE."
                }
            ]
        },
        {
            "group": "Test peekNumber for buffer filling scenarios",
            "cases": [
                {
                    "input": [],
                    "expected": "PEEKED_NONE",
                    "description": "Test when the buffer is filled and number is too long to ensure it returns PEEKED_NONE."
                },
                {
                    "input": [],
                    "expected": "PEEKED_NONE",
                    "description": "Test with an empty buffer to ensure it returns PEEKED_NONE when no valid number can be read."
                }
            ]
        },
        {
            "group": "Test peekNumber for edge cases",
            "cases": [
                {
                    "input": [],
                    "expected": "PEEKED_LONG",
                    "description": "Test with Long.MIN_VALUE to ensure it is correctly identified as PEEKED_LONG."
                },
                {
                    "input": [],
                    "expected": "PEEKED_NUMBER",
                    "description": "Test with a number that is very large but still fits in a double to ensure it returns PEEKED_NUMBER."
                },
                {
                    "input": [],
                    "expected": "PEEKED_NONE",
                    "description": "Test with a number that exceeds the maximum limit for long to ensure it returns PEEKED_NONE."
                }
            ]
        }
    ]
}