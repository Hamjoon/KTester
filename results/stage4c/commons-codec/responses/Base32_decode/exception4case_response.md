{
    "group": "Base32 decode method additional exception tests",
    "cases": [
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
            "expected": "IndexOutOfBoundsException",
            "description": "Tests handling of input byte value out of decode table range, expecting an IndexOutOfBoundsException."
        },
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "[32, 32, 32, 32, 32, 32, 32, 32]"
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
            "expected": "IllegalStateException",
            "description": "Tests handling of input causing an impossible modulus state, expecting an IllegalStateException."
        }
    ]
}