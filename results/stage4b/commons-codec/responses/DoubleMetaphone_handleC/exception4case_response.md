{
    "group": "handleC method tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "value",
                    "value": ""
                },
                {
                    "parameter": "result",
                    "value": "new DoubleMetaphoneResult(10)"
                },
                {
                    "parameter": "index",
                    "value": 0
                }
            ],
            "expected": "IndexOutOfBoundsException",
            "description": "Test when the input value is an empty string at index 0, should throw IndexOutOfBoundsException when trying to access characters."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": null
                },
                {
                    "parameter": "result",
                    "value": "new DoubleMetaphoneResult(10)"
                },
                {
                    "parameter": "index",
                    "value": 0
                }
            ],
            "expected": "NullPointerException",
            "description": "Test when the input value is null at index 0, should throw NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "C"
                },
                {
                    "parameter": "result",
                    "value": "new DoubleMetaphoneResult(0)"
                },
                {
                    "parameter": "index",
                    "value": 0
                }
            ],
            "expected": "StringIndexOutOfBoundsException",
            "description": "Test when the input value is 'C' and max length of result is 0 at index 0, should throw StringIndexOutOfBoundsException when trying to append."
        }
    ]
}