{
    "test_cases": [
        {
            "group": "printContent",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "MockWriter"
                        },
                        {
                            "parameter": "fstack",
                            "value": "MockFormatStack"
                        },
                        {
                            "parameter": "nstack",
                            "value": "MockNamespaceStack"
                        },
                        {
                            "parameter": "walker",
                            "value": "MockWalkerWithMixedContent"
                        }
                    ],
                    "expected": "printElement, printText, printComment called in sequence",
                    "description": "Test case where Walker returns a sequence of different content types, expecting appropriate print methods called in sequence."
                },
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "MockWriter"
                        },
                        {
                            "parameter": "fstack",
                            "value": "MockFormatStack"
                        },
                        {
                            "parameter": "nstack",
                            "value": "MockNamespaceStack"
                        },
                        {
                            "parameter": "walker",
                            "value": "MockWalkerWithEmptyContent"
                        }
                    ],
                    "expected": "no method calls, no output",
                    "description": "Test case where Walker is empty, expecting no method calls and no output."
                },
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "MockWriter"
                        },
                        {
                            "parameter": "fstack",
                            "value": "MockFormatStack"
                        },
                        {
                            "parameter": "nstack",
                            "value": "MockNamespaceStack"
                        },
                        {
                            "parameter": "walker",
                            "value": "MockWalkerWithInvalidContent"
                        }
                    ],
                    "expected": "IllegalDataException thrown",
                    "description": "Test case where Walker returns invalid content, expecting IllegalDataException to be thrown."
                }
            ]
        }
    ]
}