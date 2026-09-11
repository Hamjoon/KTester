{
    "result": [
        {
            "group": "handleS - Exception Scenarios",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": null
                        },
                        {
                            "parameter": "result",
                            "value": "new DoubleMetaphoneResult(5)"
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
                    "description": "Test with null value for 'value', should throw NullPointerException."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "test"
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
                    "description": "Test with null result, should throw NullPointerException."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "test"
                        },
                        {
                            "parameter": "result",
                            "value": "new DoubleMetaphoneResult(5)"
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
                    "description": "Test with negative index, should throw IndexOutOfBoundsException."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "test"
                        },
                        {
                            "parameter": "result",
                            "value": "new DoubleMetaphoneResult(5)"
                        },
                        {
                            "parameter": "index",
                            "value": 10
                        },
                        {
                            "parameter": "slavoGermanic",
                            "value": false
                        }
                    ],
                    "expected": "IndexOutOfBoundsException",
                    "description": "Test with index greater than the length of the string, should throw IndexOutOfBoundsException."
                }
            ]
        }
    ]
}