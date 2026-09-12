{
    "group": "Base64 decode method",
    "cases": [
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "[]"
                },
                {
                    "parameter": "inPos",
                    "value": "0"
                },
                {
                    "parameter": "inAvail",
                    "value": "-1"
                },
                {
                    "parameter": "context",
                    "value": "new Context()"
                }
            ],
            "expected": "context.eof is true",
            "description": "Test case where inAvail is negative, expecting context.eof to be set to true."
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "[0, 1, 2, 3]"
                },
                {
                    "parameter": "inPos",
                    "value": "0"
                },
                {
                    "parameter": "inAvail",
                    "value": "4"
                },
                {
                    "parameter": "context",
                    "value": "new Context()"
                }
            ],
            "expected": "context.eof is false and buffer is filled correctly",
            "description": "Test case with valid input data, expecting buffer to be filled correctly without setting eof."
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "[61]"  						
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
            "expected": "context.eof is true",
            "description": "Test case where input contains the padding character '=', expecting context.eof to be set to true."
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "[5, 6, 7, 8]"
                },
                {
                    "parameter": "inPos",
                    "value": "0"
                },
                {
                    "parameter": "inAvail",
                    "value": "4"
                },
                {
                    "parameter": "context",
                    "value": "new Context()"
                }
            ],
            "expected": "context.modulus == 0 and buffer is filled correctly",
            "description": "Test case with valid input data filling the buffer completely, expecting modulus to be 0."
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "[5, 6]"
                },
                {
                    "parameter": "inPos",
                    "value": "0"
                },
                {
                    "parameter": "inAvail",
                    "value": "2"
                },
                {
                    "parameter": "context",
                    "value": "new Context()"
                }
            ],
            "expected": "validateCharacter is called with MASK_4BITS",
            "description": "Test case with 2 bytes of input, expecting validateCharacter to be called due to modulus 2."
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "[5, 6, 7]"
                },
                {
                    "parameter": "inPos",
                    "value": "0"
                },
                {
                    "parameter": "inAvail",
                    "value": "3"
                },
                {
                    "parameter": "context",
                    "value": "new Context()"
                }
            ],
            "expected": "validateCharacter is called with MASK_2BITS",
            "description": "Test case with 3 bytes of input, expecting validateCharacter to be called due to modulus 3."
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "[5]"
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
            "expected": "validateTrailingCharacter is called",
            "description": "Test case with 1 byte of input, expecting validateTrailingCharacter to be called due to modulus 1."
        }
    ]
}