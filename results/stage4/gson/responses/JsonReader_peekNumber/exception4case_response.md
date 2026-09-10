{
    "group": "peekNumber",
    "cases": [
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "1.2e"
                }
            ],
            "expected": "PEEKED_NONE",
            "description": "Test when a scientific notation number is incomplete (missing exponent), expecting PEEKED_NONE."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "1e+"
                }
            ],
            "expected": "PEEKED_NONE",
            "description": "Test when a scientific notation number is incomplete (missing exponent), expecting PEEKED_NONE."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "1e-"
                }
            ],
            "expected": "PEEKED_NONE",
            "description": "Test when a scientific notation number is incomplete (missing exponent), expecting PEEKED_NONE."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "++123"
                }
            ],
            "expected": "PEEKED_NONE",
            "description": "Test when there are multiple signs in front of a number, expecting PEEKED_NONE."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "--123"
                }
            ],
            "expected": "PEEKED_NONE",
            "description": "Test when there are multiple signs in front of a number, expecting PEEKED_NONE."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "123abc"
                }
            ],
            "expected": "PEEKED_NONE",
            "description": "Test when a valid number is followed by non-numeric characters, expecting PEEKED_NONE."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "1.2.3"
                }
            ],
            "expected": "PEEKED_NONE",
            "description": "Test when a valid decimal number is followed by another dot, expecting PEEKED_NONE."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "1e1e1"
                }
            ],
            "expected": "PEEKED_NONE",
            "description": "Test when a valid scientific notation number is followed by another 'e', expecting PEEKED_NONE."
        }
    ]
}