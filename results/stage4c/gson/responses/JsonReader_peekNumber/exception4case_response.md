{
    "group": "peekNumber Method Exception Test Cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": ""
                }
            ],
            "expected": "PEEKED_NONE",
            "description": "Test with an empty buffer to ensure it is not recognized as a valid number."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "-"
                }
            ],
            "expected": "PEEKED_NONE",
            "description": "Test with a single negative sign to ensure it is not recognized as a valid number."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "+"
                }
            ],
            "expected": "PEEKED_NONE",
            "description": "Test with a single positive sign to ensure it is not recognized as a valid number."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "e"
                }
            ],
            "expected": "PEEKED_NONE",
            "description": "Test with a single 'e' character to ensure it is not recognized as a valid number."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "."
                }
            ],
            "expected": "PEEKED_NONE",
            "description": "Test with a single decimal point to ensure it is not recognized as a valid number."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "9e-"
                }
            ],
            "expected": "PEEKED_NONE",
            "description": "Test with a number in scientific notation with an incomplete negative exponent to ensure it is not recognized as a valid number."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "9e+"
                }
            ],
            "expected": "PEEKED_NONE",
            "description": "Test with a number in scientific notation with an incomplete positive exponent to ensure it is not recognized as a valid number."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "9223372036854775808"
                }
            ],
            "expected": "PEEKED_NONE",
            "description": "Test with a number just over Long.MAX_VALUE to ensure it is not recognized as a valid long."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "-9223372036854775809"
                }
            ],
            "expected": "PEEKED_NONE",
            "description": "Test with a number just below Long.MIN_VALUE to ensure it is not recognized as a valid long."
        }
    ]
}