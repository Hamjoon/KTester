{
    "group": "Base64.encode(byte[], int, int, Context) Exception Tests",
    "cases": [
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
                    "value": "null"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test with null Context object, which should throw a NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "in",
                    "value": "null"
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
            "expected": "NullPointerException",
            "description": "Test with null input byte array, which should throw a NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "in",
                    "value": "new byte[]{0, 1, 2}"
                },
                {
                    "parameter": "inPos",
                    "value": -1
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
            "expected": "ArrayIndexOutOfBoundsException",
            "description": "Test with negative inPos, which should throw an ArrayIndexOutOfBoundsException."
        },
        {
            "input": [
                {
                    "parameter": "in",
                    "value": "new byte[]{0, 1, 2}"
                },
                {
                    "parameter": "inPos",
                    "value": 4
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
            "expected": "ArrayIndexOutOfBoundsException",
            "description": "Test with inPos greater than input array length, which should throw an ArrayIndexOutOfBoundsException."
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
                    "value": 4
                },
                {
                    "parameter": "context",
                    "value": "new Context()"
                }
            ],
            "expected": "ArrayIndexOutOfBoundsException",
            "description": "Test with inAvail greater than input array length from inPos, which should throw an ArrayIndexOutOfBoundsException."
        }
    ]
}