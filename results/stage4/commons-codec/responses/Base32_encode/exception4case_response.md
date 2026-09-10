{
    "group": "encode method - exception handling",
    "cases": [
        {
            "input": [
                {
                    "parameter": "input",
                    "value": [0, 0, 0, 0, 0]
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
                        "modulus": 5,
                        "pos": 0,
                        "lbitWorkArea": 0,
                        "currentLinePos": 0
                    }
                }
            ],
            "expected": "IllegalStateException is thrown for impossible modulus",
            "description": "Test that an IllegalStateException is thrown when an impossible modulus (5) is encountered."
        }
    ]
}