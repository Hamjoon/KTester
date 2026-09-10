{
    "testCases": [
        {
            "group": "peekNumber",
            "cases": [
                {
                    "input": [],
                    "expected": "PEEKED_NONE",
                    "description": "Test when the buffer is empty, expecting PEEKED_NONE as no number can be read."
                },
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "-123"
                        }
                    ],
                    "expected": "PEEKED_LONG with value -123",
                    "description": "Test when a valid negative long number is in the buffer, expecting PEEKED_LONG with value -123."
                },
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "456"
                        }
                    ],
                    "expected": "PEEKED_LONG with value 456",
                    "description": "Test when a valid positive long number is in the buffer, expecting PEEKED_LONG with value 456."
                },
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "0.123"
                        }
                    ],
                    "expected": "PEEKED_NUMBER",
                    "description": "Test when a valid decimal number is in the buffer, expecting PEEKED_NUMBER."
                },
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "1e10"
                        }
                    ],
                    "expected": "PEEKED_NUMBER",
                    "description": "Test when a valid scientific notation number is in the buffer, expecting PEEKED_NUMBER."
                },
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "1.5e-2"
                        }
                    ],
                    "expected": "PEEKED_NUMBER",
                    "description": "Test when a valid decimal scientific notation number is in the buffer, expecting PEEKED_NUMBER."
                },
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "0123"
                        }
                    ],
                    "expected": "PEEKED_NONE",
                    "description": "Test when a number with leading zeros is in the buffer, expecting PEEKED_NONE due to invalid format."
                },
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "abc"
                        }
                    ],
                    "expected": "PEEKED_NONE",
                    "description": "Test when a non-numeric string is in the buffer, expecting PEEKED_NONE as it cannot be interpreted as a number."
                },
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "12.34.56"
                        }
                    ],
                    "expected": "PEEKED_NONE",
                    "description": "Test when an invalid decimal format with multiple dots is in the buffer, expecting PEEKED_NONE."
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
                            "value": "-"
                        }
                    ],
                    "expected": "PEEKED_NONE",
                    "description": "Test when only a negative sign is in the buffer, expecting PEEKED_NONE as it's incomplete."
                }
            ]
        }
    ]
}