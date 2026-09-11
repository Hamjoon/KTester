{
    "testCases": [
        {
            "group": "printContent - Walker with empty content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mockXMLEventConsumer"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mockFormatStack"
                        },
                        {
                            "parameter": "nstack",
                            "value": "mockNamespaceStack"
                        },
                        {
                            "parameter": "eventfactory",
                            "value": "mockXMLEventFactory"
                        },
                        {
                            "parameter": "walker",
                            "value": "mockWalkerWithEmptyContent"
                        }
                    ],
                    "expected": "no exception",
                    "description": "Test when walker has no content to process."
                }
            ]
        },
        {
            "group": "printContent - Walker with multiple null contents",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mockXMLEventConsumer"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mockFormatStack"
                        },
                        {
                            "parameter": "nstack",
                            "value": "mockNamespaceStack"
                        },
                        {
                            "parameter": "eventfactory",
                            "value": "mockXMLEventFactory"
                        },
                        {
                            "parameter": "walker",
                            "value": "mockWalkerWithMultipleNullContents"
                        }
                    ],
                    "expected": "no exception",
                    "description": "Test when walker returns multiple null contents."
                }
            ]
        },
        {
            "group": "printContent - Walker with mixed content types",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mockXMLEventConsumer"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mockFormatStack"
                        },
                        {
                            "parameter": "nstack",
                            "value": "mockNamespaceStack"
                        },
                        {
                            "parameter": "eventfactory",
                            "value": "mockXMLEventFactory"
                        },
                        {
                            "parameter": "walker",
                            "value": "mockWalkerWithMixedContentTypes"
                        }
                    ],
                    "expected": "no exception",
                    "description": "Test when walker processes a mix of CDATA, Comment, and Text content types."
                }
            ]
        },
        {
            "group": "printContent - Walker with invalid content type",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mockXMLEventConsumer"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mockFormatStack"
                        },
                        {
                            "parameter": "nstack",
                            "value": "mockNamespaceStack"
                        },
                        {
                            "parameter": "eventfactory",
                            "value": "mockXMLEventFactory"
                        },
                        {
                            "parameter": "walker",
                            "value": "mockWalkerWithInvalidContentType"
                        }
                    ],
                    "expected": "IllegalStateException with message 'Unexpected Content'",
                    "description": "Test when walker returns a content type that is not recognized."
                }
            ]
        }
    ]
}