{
    "test cases": [
        {
            "group": "peekNumber",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "1.0"
                        }
                    ],
                    "expected": "PEEKED_NUMBER",
                    "description": "Test when a valid decimal number with one digit after the decimal point is in the buffer, expecting PEEKED_NUMBER."
                },
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "-1.0"
                        }
                    ],
                    "expected": "PEEKED_NUMBER",
                    "description": "Test when a valid negative decimal number with one digit after the decimal point is in the buffer, expecting PEEKED_NUMBER."
                },
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "1e+10"
                        }
                    ],
                    "expected": "PEEKED_NUMBER",
                    "description": "Test when a valid scientific notation number with a positive exponent is in the buffer, expecting PEEKED_NUMBER."
                },
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "1e-10"
                        }
                    ],
                    "expected": "PEEKED_NUMBER",
                    "description": "Test when a valid scientific notation number with a negative exponent is in the buffer, expecting PEEKED_NUMBER."
                },
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "1.2e-3"
                        }
                    ],
                    "expected": "PEEKED_NUMBER",
                    "description": "Test when a valid decimal scientific notation number is in the buffer, expecting PEEKED_NUMBER."
                },
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "1.2e3.4"
                        }
                    ],
                    "expected": "PEEKED_NONE",
                    "description": "Test when an invalid scientific notation format is in the buffer, expecting PEEKED_NONE."
                },
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "9999999999999999999"
                        }
                    ],
                    "expected": "PEEKED_NONE",
                    "description": "Test when a number that exceeds the range of long is in the buffer, expecting PEEKED_NONE."
                },
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "-9999999999999999999"
                        }
                    ],
                    "expected": "PEEKED_NONE",
                    "description": "Test when a negative number that exceeds the range of long is in the buffer, expecting PEEKED_NONE."
                },
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "0.0"
                        }
                    ],
                    "expected": "PEEKED_NUMBER",
                    "description": "Test when a valid decimal number with zero is in the buffer, expecting PEEKED_NUMBER."
                },
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "1.234567890123456789"
                        }
                    ],
                    "expected": "PEEKED_NUMBER",
                    "description": "Test when a valid large decimal number is in the buffer, expecting PEEKED_NUMBER."
                },
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "1.2.3"
                        }
                    ],
                    "expected": "PEEKED_NONE",
                    "description": "Test when an invalid decimal number format with multiple dots is in the buffer, expecting PEEKED_NONE."
                }
            ]
        }
    ]
}