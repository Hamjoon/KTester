{
    "group": "peekNumber Method Test Cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "123"
                }
            ],
            "expected": "PEEKED_LONG",
            "description": "Test with a simple integer number to ensure it is recognized as a long."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "-123"
                }
            ],
            "expected": "PEEKED_LONG",
            "description": "Test with a negative integer number to ensure it is recognized as a long."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "123.456"
                }
            ],
            "expected": "PEEKED_NUMBER",
            "description": "Test with a decimal number to ensure it is recognized as a number."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "1e10"
                }
            ],
            "expected": "PEEKED_NUMBER",
            "description": "Test with a number in scientific notation to ensure it is recognized as a number."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "1.2e-3"
                }
            ],
            "expected": "PEEKED_NUMBER",
            "description": "Test with a number in scientific notation with a negative exponent to ensure it is recognized as a number."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "0123"
                }
            ],
            "expected": "PEEKED_NONE",
            "description": "Test with a number with leading zero to ensure it is not recognized as a valid number."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "-0"
                }
            ],
            "expected": "PEEKED_LONG",
            "description": "Test with -0 to ensure it is recognized as a long, not a double."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "123e"
                }
            ],
            "expected": "PEEKED_NONE",
            "description": "Test with an incomplete scientific notation number to ensure it is not recognized as a valid number."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "123."
                }
            ],
            "expected": "PEEKED_NONE",
            "description": "Test with a number ending with a decimal point to ensure it is not recognized as a valid number."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "123e+"
                }
            ],
            "expected": "PEEKED_NONE",
            "description": "Test with a number in scientific notation with an incomplete exponent to ensure it is not recognized as a valid number."
        }
    ]
}