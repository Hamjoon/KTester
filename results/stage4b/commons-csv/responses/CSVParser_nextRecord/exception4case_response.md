{
    "output": [
        {
            "group": "nextRecord - Lexer Not Initialized",
            "cases": [
                {
                    "input": [],
                    "expected": "IOException with message indicating lexer not initialized",
                    "description": "Test the nextRecord method when the lexer is not initialized to ensure it throws an IOException."
                }
            ]
        },
        {
            "group": "nextRecord - Lexer Throws IOException",
            "cases": [
                {
                    "input": [],
                    "expected": "IOException with message from lexer",
                    "description": "Test the nextRecord method when the lexer throws an IOException during token retrieval to ensure it propagates the exception."
                }
            ]
        }
    ]
}