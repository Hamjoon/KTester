{
    "group": "Base32.encode",
    "cases": [
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "byte[] with 10 valid bytes"
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
                    "value": "Context with eof=false, modulus=0, pos=0"
                }
            ],
            "expected": "buffer filled with encoded data for two full blocks, modulus reset to 0",
            "description": "Test normal processing with two full blocks of 5 bytes each"
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "byte[] with 6 valid bytes"
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
                    "value": "Context with eof=false, modulus=0, pos=0"
                }
            ],
            "expected": "buffer filled with encoded data for one full block and one partial block, modulus updated to 1",
            "description": "Test processing with one full block and one additional byte"
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "byte[] with 7 valid bytes"
                },
                {
                    "parameter": "inPos",
                    "value": 0
                },
                {
                    "parameter": "inAvail",
                    "value": 7
                },
                {
                    "parameter": "context",
                    "value": "Context with eof=false, modulus=0, pos=0"
                }
            ],
            "expected": "buffer filled with encoded data for one full block and two additional bytes, modulus updated to 2",
            "description": "Test processing with one full block and two additional bytes"
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "byte[] with 8 valid bytes"
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
                    "value": "Context with eof=false, modulus=0, pos=0"
                }
            ],
            "expected": "buffer filled with encoded data for one full block and three additional bytes, modulus updated to 3",
            "description": "Test processing with one full block and three additional bytes"
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "byte[] with 9 valid bytes"
                },
                {
                    "parameter": "inPos",
                    "value": 0
                },
                {
                    "parameter": "inAvail",
                    "value": 9
                },
                {
                    "parameter": "context",
                    "value": "Context with eof=false, modulus=0, pos=0"
                }
            ],
            "expected": "buffer filled with encoded data for one full block and four additional bytes, modulus updated to 4",
            "description": "Test processing with one full block and four additional bytes"
        }
    ]
}