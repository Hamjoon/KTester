{
    "test cases": [
        {
            "group": "nextUnquotedValue Exception Test Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": ""
                        },
                        {
                            "parameter": "pos",
                            "value": 0
                        },
                        {
                            "parameter": "limit",
                            "value": 0
                        }
                    ],
                    "expected": "EOFException",
                    "description": "Test with an empty buffer which should trigger an EOFException when trying to read."
                },
                {
                    "input": [
                        {
                            "parameter": "buffer",
                            "value": "abc"
                        },
                        {
                            "parameter": "pos",
                            "value": 3
                        },
                        {
                            "parameter": "limit",
                            "value": 3
                        }
                    ],
                    "expected": "EOFException",
                    "description": "Test with position at the end of the buffer which should trigger an EOFException when trying to read."
                }
            ]
        }
    ]
}