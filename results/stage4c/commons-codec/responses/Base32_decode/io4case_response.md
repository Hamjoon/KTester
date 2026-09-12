{
    "group": "Base32 decode method additional tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "[32, 32, 32, 32, 32, 32, 32, 61]"
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
            "expected": "context.eof = true, 5 bytes decoded and stored in buffer",
            "description": "Tests handling of valid input with trailing padding, ensuring correct decoding and EOF setting."
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "[32, 33, 34, 35, 36, 37, 38, 39]"
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
            "expected": "context.eof = true, exception or partial decoding",
            "description": "Tests handling of invalid characters within the input, expecting exception or partial decoding."
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "[32, 32, 61, 61, 61, 61, 61, 61]"
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
            "expected": "context.eof = true, validateTrailingCharacters() called",
            "description": "Tests input with multiple padding characters to ensure correct EOF and validation handling."
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "[32, 32, 32, 32, 32, 32, 32, 32, 32]"
                },
                {
                    "parameter": "inPos",
                    "value": "0"
                },
                {
                    "parameter": "inAvail",
                    "value": "9"
                },
                {
                    "parameter": "context",
                    "value": "new Context()"
                }
            ],
            "expected": "5 bytes decoded and stored in buffer, additional byte ignored",
            "description": "Tests handling of input exceeding a full block size, ensuring only valid blocks are decoded."
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "[32, 32, 32, 32, 32, 32, 32, 32, 61]"
                },
                {
                    "parameter": "inPos",
                    "value": "0"
                },
                {
                    "parameter": "inAvail",
                    "value": "9"
                },
                {
                    "parameter": "context",
                    "value": "new Context()"
                }
            ],
            "expected": "5 bytes decoded and stored in buffer, context.eof = true",
            "description": "Tests handling of valid input block followed by padding, ensuring correct decoding and EOF setting."
        }
    ]
}