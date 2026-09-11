{
    "output": [
        {
            "group": "handleG method tests",
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
                        },
                        {
                            "parameter": "slavoGermanic",
                            "value": false
                        }
                    ],
                    "expected": "IndexOutOfBoundsException",
                    "description": "Test with an empty string. It should throw an IndexOutOfBoundsException when trying to access a character."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "GHOST"
                        },
                        {
                            "parameter": "result",
                            "value": "new DoubleMetaphoneResult(2)"
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
                    "expected": "IndexOutOfBoundsException",
                    "description": "Test with a result max length less than necessary. It should throw an IndexOutOfBoundsException when trying to append more characters than allowed."
                },
                {
                    "input": [
                        {
                            "parameter": "value",
                            "value": "GHOST"
                        },
                        {
                            "parameter": "result",
                            "value": "new DoubleMetaphoneResult(0)"
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
                    "expected": "IndexOutOfBoundsException",
                    "description": "Test with a result max length of 0. It should throw an IndexOutOfBoundsException when trying to append characters."
                }
            ]
        }
    ]
}