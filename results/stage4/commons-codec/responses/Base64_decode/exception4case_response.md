{
    "group": "decode method",
    "cases": [
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
                    "value": {
                        "eof": false,
                        "modulus": 0,
                        "ibitWorkArea": 0,
                        "pos": 0
                    }
                }
            ],
            "expected": "context.ibitWorkArea is shifted right by 2, context.pos is incremented twice",
            "description": "Test decoding with context.modulus equal to 3, validating correct bit manipulation and buffer update."
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
                    "value": {
                        "eof": false,
                        "modulus": 1,
                        "ibitWorkArea": 0,
                        "pos": 0
                    }
                }
            ],
            "expected": "IllegalStateException is thrown with message 'Impossible modulus 1'",
            "description": "Test decoding with context.modulus equal to 1, ensuring an exception is thrown for impossible state."
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
                    "value": {
                        "eof": false,
                        "modulus": 2,
                        "ibitWorkArea": 0,
                        "pos": 0
                    }
                }
            ],
            "expected": "context.ibitWorkArea is shifted right by 4, context.pos is incremented",
            "description": "Test decoding with context.modulus equal to 2 and a valid character after padding, validating correct bit manipulation and buffer update."
        }
    ]
}