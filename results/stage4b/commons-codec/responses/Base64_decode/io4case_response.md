{
    "test cases": [
        {
            "group": "Base64.decode method tests",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": [65, 66, 67, 68]
                        },
                        {
                            "parameter": "inPos",
                            "value": 0
                        },
                        {
                            "parameter": "inAvail",
                            "value": 4
                        },
                        {
                            "parameter": "context",
                            "value": {}
                        }
                    ],
                    "expected": "context.eof is true and buffer contains [65, 66, 67]",
                    "description": "Test with valid Base64 input that does not end with padding and has a full block. The method should decode the input correctly and set context.eof to true."
                },
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": [65, 66, 67, 68, 69]
                        },
                        {
                            "parameter": "inPos",
                            "value": 0
                        },
                        {
                            "parameter": "inAvail",
                            "value": 5
                        },
                        {
                            "parameter": "context",
                            "value": {}
                        }
                    ],
                    "expected": "context.eof is true and buffer contains [65, 66, 67]",
                    "description": "Test with valid Base64 input that contains more than one block but ends with an invalid character. The method should decode the input correctly until the invalid character and set context.eof to true."
                },
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": [64]
                        },
                        {
                            "parameter": "inPos",
                            "value": 0
                        },
                        {
                            "parameter": "inAvail",
                            "value": 1
                        },
                        {
                            "parameter": "context",
                            "value": {}
                        }
                    ],
                    "expected": "context.eof is true and buffer contains []",
                    "description": "Test with a single invalid Base64 character. The method should set context.eof to true without adding any bytes to the buffer."
                },
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": [65, 66, 67, 64, 64]
                        },
                        {
                            "parameter": "inPos",
                            "value": 0
                        },
                        {
                            "parameter": "inAvail",
                            "value": 5
                        },
                        {
                            "parameter": "context",
                            "value": {}
                        }
                    ],
                    "expected": "context.eof is true and buffer contains [65, 66]",
                    "description": "Test with valid Base64 input that ends with two padding characters after valid data. The method should decode correctly and set context.eof to true."
                },
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": [65, 66, 67, 68, 69, 70]
                        },
                        {
                            "parameter": "inPos",
                            "value": 0
                        },
                        {
                            "parameter": "inAvail",
                            "value": 6
                        },
                        {
                            "parameter": "context",
                            "value": {}
                        }
                    ],
                    "expected": "context.modulus is 0 and buffer contains [65, 66, 67]",
                    "description": "Test with valid Base64 input that results in a complete block with no padding. The method should decode the input correctly and set context.modulus to 0."
                },
                {
                    "input": [
                        {
                            "parameter": "input",
                            "value": [65, 66, 67, 64, 64, 64]
                        },
                        {
                            "parameter": "inPos",
                            "value": 0
                        },
                        {
                            "parameter": "inAvail",
                            "value": 6
                        },
                        {
                            "parameter": "context",
                            "value": {}
                        }
                    ],
                    "expected": "context.eof is true and buffer contains [65, 66]",
                    "description": "Test with valid Base64 input that has multiple padding characters. The method should decode correctly and set context.eof to true."
                }
            ]
        }
    ]
}