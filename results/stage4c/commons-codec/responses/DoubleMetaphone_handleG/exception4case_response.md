{
    "group": "handleG method exception scenarios",
    "cases": [
        {
            "input": [
                {
                    "parameter": "value",
                    "value": null
                },
                {
                    "parameter": "result",
                    "value": "DoubleMetaphoneResult instance"
                },
                {
                    "parameter": "index",
                    "value": 0
                },
                {
                    "parameter": "slavoGermanic",
                    "value": false
                }
            ],
            "expected": "NullPointerException",
            "description": "Test when the input 'value' is null, should throw NullPointerException"
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "G"
                },
                {
                    "parameter": "result",
                    "value": null
                },
                {
                    "parameter": "index",
                    "value": 0
                },
                {
                    "parameter": "slavoGermanic",
                    "value": false
                }
            ],
            "expected": "NullPointerException",
            "description": "Test when the 'result' is null, should throw NullPointerException"
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "G"
                },
                {
                    "parameter": "result",
                    "value": "DoubleMetaphoneResult instance"
                },
                {
                    "parameter": "index",
                    "value": -1
                },
                {
                    "parameter": "slavoGermanic",
                    "value": false
                }
            ],
            "expected": "IndexOutOfBoundsException",
            "description": "Test when 'index' is negative, should throw IndexOutOfBoundsException"
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "G"
                },
                {
                    "parameter": "result",
                    "value": "DoubleMetaphoneResult instance"
                },
                {
                    "parameter": "index",
                    "value": 1000
                },
                {
                    "parameter": "slavoGermanic",
                    "value": false
                }
            ],
            "expected": "IndexOutOfBoundsException",
            "description": "Test when 'index' is out of bounds of the 'value' string length, should throw IndexOutOfBoundsException"
        }
    ]
}