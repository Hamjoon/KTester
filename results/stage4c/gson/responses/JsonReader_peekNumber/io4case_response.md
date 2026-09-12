{
    "group": "peekNumber Method Additional Test Cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "9223372036854775807"
                }
            ],
            "expected": "PEEKED_LONG",
            "description": "Test with Long.MAX_VALUE to ensure it is recognized as a long."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "-9223372036854775808"
                }
            ],
            "expected": "PEEKED_LONG",
            "description": "Test with Long.MIN_VALUE to ensure it is recognized as a long."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "1.7976931348623157e308"
                }
            ],
            "expected": "PEEKED_NUMBER",
            "description": "Test with Double.MAX_VALUE to ensure it is recognized as a number."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "-1.7976931348623157e308"
                }
            ],
            "expected": "PEEKED_NUMBER",
            "description": "Test with negative Double.MAX_VALUE to ensure it is recognized as a number."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "1e-308"
                }
            ],
            "expected": "PEEKED_NUMBER",
            "description": "Test with a very small number in scientific notation to ensure it is recognized as a number."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "-1e-308"
                }
            ],
            "expected": "PEEKED_NUMBER",
            "description": "Test with a very small negative number in scientific notation to ensure it is recognized as a number."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "0.000000000000000000000000000000000000000000001"
                }
            ],
            "expected": "PEEKED_NUMBER",
            "description": "Test with a very small decimal number to ensure it is recognized as a number."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "123456789012345678901234567890"
                }
            ],
            "expected": "PEEKED_NONE",
            "description": "Test with a number exceeding Long.MAX_VALUE to ensure it is not recognized as a valid long."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "123abc"
                }
            ],
            "expected": "PEEKED_NONE",
            "description": "Test with a number followed by letters to ensure it is not recognized as a valid number."
        },
        {
            "input": [
                {
                    "parameter": "buffer",
                    "value": "-.5"
                }
            ],
            "expected": "PEEKED_NONE",
            "description": "Test with a number starting with a negative decimal point to ensure it is not recognized as a valid number."
        }
    ]
}