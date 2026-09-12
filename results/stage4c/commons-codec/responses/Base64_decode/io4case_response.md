{
    "group": "Base64 decode method",
    "cases": [
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "[0, 1, 2, 3, 4, 5, 6, 7]"
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
            "expected": "context.modulus == 0 and buffer is filled correctly",
            "description": "Test case with 8 bytes of input, expecting complete blocks to be decoded and modulus to be 0."
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "[5, 6, 7, 8, 9]"
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
            "expected": "validateCharacter is called with MASK_4BITS",
            "description": "Test case with 5 bytes of input, expecting validateCharacter to be called due to modulus 1 after processing 4 bytes."
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "[5, 6, 7, 8, 9, 10]"
                },
                {
                    "parameter": "inPos",
                    "value": "0"
                },
                {
                    "parameter": "inAvail",
                    "value": "6"
                },
                {
                    "parameter": "context",
                    "value": "new Context()"
                }
            ],
            "expected": "validateCharacter is called with MASK_2BITS",
            "description": "Test case with 6 bytes of input, expecting validateCharacter to be called due to modulus 2 after processing 4 bytes."
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "[0, 1, 2, 3, 61, 61]"
                },
                {
                    "parameter": "inPos",
                    "value": "0"
                },
                {
                    "parameter": "inAvail",
                    "value": "6"
                },
                {
                    "parameter": "context",
                    "value": "new Context()"
                }
            ],
            "expected": "context.eof is true after processing padding",
            "description": "Test case with valid input followed by padding characters '=', expecting context.eof to be set to true."
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "[127]"
                },
                {
                    "parameter": "inPos",
                    "value": "0"
                },
                {
                    "parameter": "inAvail",
                    "value": "1"
                },
                {
                    "parameter": "context",
                    "value": "new Context()"
                }
            ],
            "expected": "context.eof is false and no valid data is processed",
            "description": "Test case with invalid byte outside decodeTable range, expecting no valid data to be processed."
        }
    ]
}