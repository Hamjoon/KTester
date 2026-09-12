{
    "group": "Base32.encode",
    "cases": [
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "null"
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
                    "value": "Context with eof=false, modulus=0, pos=0"
                }
            ],
            "expected": "context.eof=true, no additional processing",
            "description": "Test EOF condition with no leftovers and no chunking"
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "null"
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
                    "value": "Context with eof=false, modulus=1, pos=0"
                }
            ],
            "expected": "context.eof=true, buffer filled with 1 octet encoding and padding",
            "description": "Test EOF condition with modulus 1"
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "null"
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
                    "value": "Context with eof=false, modulus=2, pos=0"
                }
            ],
            "expected": "context.eof=true, buffer filled with 2 octets encoding and padding",
            "description": "Test EOF condition with modulus 2"
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "null"
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
                    "value": "Context with eof=false, modulus=3, pos=0"
                }
            ],
            "expected": "context.eof=true, buffer filled with 3 octets encoding and padding",
            "description": "Test EOF condition with modulus 3"
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "null"
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
                    "value": "Context with eof=false, modulus=4, pos=0"
                }
            ],
            "expected": "context.eof=true, buffer filled with 4 octets encoding and padding",
            "description": "Test EOF condition with modulus 4"
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "byte[] with 5 valid bytes"
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
                    "value": "Context with eof=false, modulus=0, pos=0"
                }
            ],
            "expected": "buffer filled with encoded data, modulus reset to 0",
            "description": "Test normal processing with full block of 5 bytes"
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "byte[] with less than 5 bytes"
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
                    "value": "Context with eof=false, modulus=0, pos=0"
                }
            ],
            "expected": "buffer partially filled, modulus updated to 3",
            "description": "Test processing with partial block of bytes"
        }
    ]
}