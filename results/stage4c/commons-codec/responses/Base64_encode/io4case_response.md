{
    "group": "Base64.encode(byte[], int, int, Context) Additional Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "in",
                    "value": "new byte[]{0, 1, 2, 3, 4, 5}"
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
                    "value": "new Context()"
                }
            ],
            "expected": "context.pos = 8; context.currentLinePos = 8",
            "description": "Test encoding of a 6-byte input, which should produce 8 encoded bytes, with no padding required."
        },
        {
            "input": [
                {
                    "parameter": "in",
                    "value": "new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}"
                },
                {
                    "parameter": "inPos",
                    "value": 0
                },
                {
                    "parameter": "inAvail",
                    "value": 10
                },
                {
                    "parameter": "context",
                    "value": "new Context()"
                }
            ],
            "expected": "context.pos = 16; context.currentLinePos = 16",
            "description": "Test encoding of a 10-byte input, which should produce 16 encoded bytes, ensuring correct chunking and line length handling."
        },
        {
            "input": [
                {
                    "parameter": "in",
                    "value": "new byte[]{127, -128, 0, 1, -1, 2, -2, 3}"
                },
                {
                    "parameter": "inPos",
                    "value": 0
                },
                {
                    "parameter": "inAvail",
                    "value": 8
                },
                {
                    "parameter": "context",
                    "value": "new Context()"
                }
            ],
            "expected": "context.pos = 12; context.currentLinePos = 12",
            "description": "Test encoding with a mix of positive and negative byte values, ensuring proper conversion and encoding."
        },
        {
            "input": [
                {
                    "parameter": "in",
                    "value": "new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}"
                },
                {
                    "parameter": "inPos",
                    "value": 0
                },
                {
                    "parameter": "inAvail",
                    "value": 16
                },
                {
                    "parameter": "context",
                    "value": "new Context()"
                }
            ],
            "expected": "context.pos = 24; context.currentLinePos = 24",
            "description": "Test encoding of a 16-byte input, which should produce 24 encoded bytes, ensuring correct handling of larger inputs."
        },
        {
            "input": [
                {
                    "parameter": "in",
                    "value": "new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31}"
                },
                {
                    "parameter": "inPos",
                    "value": 0
                },
                {
                    "parameter": "inAvail",
                    "value": 32
                },
                {
                    "parameter": "context",
                    "value": "new Context()"
                }
            ],
            "expected": "context.pos = 44; context.currentLinePos = 44",
            "description": "Test encoding of a 32-byte input, which should produce 44 encoded bytes, checking for performance and correctness with large data."
        }
    ]
}