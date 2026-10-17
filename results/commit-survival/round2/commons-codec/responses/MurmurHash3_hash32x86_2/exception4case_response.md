{
    "group": "Null data – expect NullPointerException",
    "cases": [
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "null"
                },
                {
                    "parameter": "offset",
                    "value": "0"
                },
                {
                    "parameter": "length",
                    "value": "0"
                },
                {
                    "parameter": "seed",
                    "value": "0"
                }
            ],
            "expected": "java.lang.NullPointerException",
            "description": "Passing a null byte array should cause a NullPointerException before any processing occurs."
        }
    ]
}