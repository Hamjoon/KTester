{
    "output": [
        {
            "group": "decode method - modulus cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "new byte[]{0, 1, 2, 3, 4, 5, 6, 7}"
                        },
                        {
                            "parameter": "inPos",
                            "value": "0"
                        },
                        {
                            "parameter": "inAvail",
                            "value": "8"
                        },
                        {
                            "parameter": "context",
                            "value": "new Context()"
                        }
                    ],
                    "expected": "Exception",
                    "description": "Test when modulus is 1, should raise an exception due to invalid trailing characters."
                },
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "new byte[]{0, 1, 2, 3, 4, 5, 6, 7}"
                        },
                        {
                            "parameter": "inPos",
                            "value": "0"
                        },
                        {
                            "parameter": "inAvail",
                            "value": "8"
                        },
                        {
                            "parameter": "context",
                            "value": "new Context()"
                        }
                    ],
                    "expected": "Exception",
                    "description": "Test when modulus is 3, should raise an exception due to invalid trailing characters."
                },
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "new byte[]{0, 1, 2, 3, 4, 5, 6, 7}"
                        },
                        {
                            "parameter": "inPos",
                            "value": "0"
                        },
                        {
                            "parameter": "inAvail",
                            "value": "8"
                        },
                        {
                            "parameter": "context",
                            "value": "new Context()"
                        }
                    ],
                    "expected": "Exception",
                    "description": "Test when modulus is 6, should raise an exception due to invalid trailing characters."
                }
            ]
        },
        {
            "group": "decode method - invalid input handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "new byte[]{-1, -2, -3, -4, -5}"
                        },
                        {
                            "parameter": "inPos",
                            "value": "0"
                        },
                        {
                            "parameter": "inAvail",
                            "value": "5"
                        },
                        {
                            "parameter": "context",
                            "value": "new Context()"
                        }
                    ],
                    "expected": "Exception",
                    "description": "Test with negative byte values, should raise an exception due to invalid input."
                },
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": "new byte[]{100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115}"
                        },
                        {
                            "parameter": "inPos",
                            "value": "0"
                        },
                        {
                            "parameter": "inAvail",
                            "value": "16"
                        },
                        {
                            "parameter": "context",
                            "value": "new Context()"
                        }
                    ],
                    "expected": "Exception",
                    "description": "Test with input exceeding valid Base32 byte range, should raise an exception."
                }
            ]
        }
    ]
}