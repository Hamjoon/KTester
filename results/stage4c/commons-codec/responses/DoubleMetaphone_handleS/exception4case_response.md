{
    "group": "handleS exception test cases",
    "cases": [
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
                },
                {
                    "parameter": "slavoGermanic",
                    "value": false
                }
            ],
            "expected": "NullPointerException",
            "description": "Test case for a null input string to handleS method which should throw NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "TEST"
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
            "description": "Test case for a null result object to handleS method which should throw NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "TEST"
                },
                {
                    "parameter": "result",
                    "value": "new DoubleMetaphoneResult(10)"
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
            "description": "Test case for a negative index which should throw IndexOutOfBoundsException."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "TEST"
                },
                {
                    "parameter": "result",
                    "value": "new DoubleMetaphoneResult(10)"
                },
                {
                    "parameter": "index",
                    "value": 5
                },
                {
                    "parameter": "slavoGermanic",
                    "value": false
                }
            ],
            "expected": "IndexOutOfBoundsException",
            "description": "Test case for an index greater than the string length which should throw IndexOutOfBoundsException."
        }
    ]
}