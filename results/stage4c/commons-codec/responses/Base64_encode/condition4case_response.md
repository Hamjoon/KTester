{
    "group": "Base64.encode(byte[], int, int, Context) Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "in",
                    "value": "new byte[]{}"
                },
                {
                    "parameter": "inPos",
                    "value": 0
                },
                {
                    "parameter": "inAvail",
                    "value": -1
                },
                {
                    "parameter": "context",
                    "value": "new Context()"
                }
            ],
            "expected": "context.eof = true; context.pos = 0",
            "description": "Test when input is empty and EOF is reached immediately."
        },
        {
            "input": [
                {
                    "parameter": "in",
                    "value": "new byte[]{0, 1, 2}"
                },
                {
                    "parameter": "inPos",
                    "value": 0
                },
                {
                    "parameter": "inAvail",
                    "value": 3
                },
                {
                    "parameter": "context",
                    "value": "new Context()"
                }
            ],
            "expected": "context.pos = 4; context.currentLinePos = 4",
            "description": "Test encoding of a 3-byte input, which should produce 4 encoded bytes."
        },
        {
            "input": [
                {
                    "parameter": "in",
                    "value": "new byte[]{0, 1}"
                },
                {
                    "parameter": "inPos",
                    "value": 0
                },
                {
                    "parameter": "inAvail",
                    "value": 2
                },
                {
                    "parameter": "context",
                    "value": "new Context()"
                }
            ],
            "expected": "context.pos = 3; context.currentLinePos = 3",
            "description": "Test encoding of a 2-byte input, which should produce 3 encoded bytes with padding if standard table is used."
        },
        {
            "input": [
                {
                    "parameter": "in",
                    "value": "new byte[]{0}"
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
                    "value": "new Context()"
                }
            ],
            "expected": "context.pos = 2; context.currentLinePos = 2",
            "description": "Test encoding of a 1-byte input, which should produce 2 encoded bytes with padding if standard table is used."
        },
        {
            "input": [
                {
                    "parameter": "in",
                    "value": "new byte[]{0, 1, 2, 3}"
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
                    "value": "new Context()"
                }
            ],
            "expected": "context.pos = 8; context.currentLinePos = 8",
            "description": "Test encoding of a 4-byte input, which should produce 8 encoded bytes."
        },
        {
            "input": [
                {
                    "parameter": "in",
                    "value": "new byte[]{-1, -2, -3}"
                },
                {
                    "parameter": "inPos",
                    "value": 0
                },
                {
                    "parameter": "inAvail",
                    "value": 3
                },
                {
                    "parameter": "context",
                    "value": "new Context()"
                }
            ],
            "expected": "context.pos = 4; context.currentLinePos = 4",
            "description": "Test encoding with negative byte values, which should be handled correctly by converting them to positive values."
        },
        {
            "input": [
                {
                    "parameter": "in",
                    "value": "new byte[]{0, 1, 2}"
                },
                {
                    "parameter": "inPos",
                    "value": 0
                },
                {
                    "parameter": "inAvail",
                    "value": -1
                },
                {
                    "parameter": "context",
                    "value": "new Context()"
                }
            ],
            "expected": "context.eof = true; context.pos = 4",
            "description": "Test EOF condition with leftover bytes to process, should encode them and set eof to true."
        }
    ]
}