{
    "test_cases": [
        {
            "group": "Test printDocument with no root element",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mockedXMLEventConsumer"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mockedFormatStackWithDefaultEncoding"
                        },
                        {
                            "parameter": "nstack",
                            "value": "mockedNamespaceStack"
                        },
                        {
                            "parameter": "eventfactory",
                            "value": "mockedXMLEventFactory"
                        },
                        {
                            "parameter": "doc",
                            "value": "mockedDocumentWithoutRootElement"
                        }
                    ],
                    "expected": "Empty content list processed without errors",
                    "description": "Test case where the document has no root element, ensuring that the method handles the lack of a root element without throwing exceptions."
                }
            ]
        },
        {
            "group": "Test printDocument with line separator",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mockedXMLEventConsumer"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mockedFormatStackWithLineSeparator"
                        },
                        {
                            "parameter": "nstack",
                            "value": "mockedNamespaceStack"
                        },
                        {
                            "parameter": "eventfactory",
                            "value": "mockedXMLEventFactory"
                        },
                        {
                            "parameter": "doc",
                            "value": "mockedDocumentWithRootElement"
                        }
                    ],
                    "expected": "StartDocument event with specified encoding and version 1.0 followed by line separator",
                    "description": "Test case where the format stack specifies a line separator, ensuring that the start document event is created with the specified encoding and version, followed by the line separator."
                }
            ]
        },
        {
            "group": "Test printDocument with multiple root elements",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mockedXMLEventConsumer"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mockedFormatStackWithDefaultEncoding"
                        },
                        {
                            "parameter": "nstack",
                            "value": "mockedNamespaceStack"
                        },
                        {
                            "parameter": "eventfactory",
                            "value": "mockedXMLEventFactory"
                        },
                        {
                            "parameter": "doc",
                            "value": "mockedDocumentWithMultipleRootElements"
                        }
                    ],
                    "expected": "Exception thrown due to multiple root elements",
                    "description": "Test case where the document contains multiple root elements, ensuring that the method throws an exception as this is invalid for XML documents."
                }
            ]
        },
        {
            "group": "Test printDocument with only comments",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mockedXMLEventConsumer"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mockedFormatStackWithDefaultEncoding"
                        },
                        {
                            "parameter": "nstack",
                            "value": "mockedNamespaceStack"
                        },
                        {
                            "parameter": "eventfactory",
                            "value": "mockedXMLEventFactory"
                        },
                        {
                            "parameter": "doc",
                            "value": "mockedDocumentWithOnlyComments"
                        }
                    ],
                    "expected": "All comments processed correctly",
                    "description": "Test case where the document contains only comments, ensuring that the method processes comments correctly without any other content types."
                }
            ]
        },
        {
            "group": "Test printDocument with processing instructions",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mockedXMLEventConsumer"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mockedFormatStackWithDefaultEncoding"
                        },
                        {
                            "parameter": "nstack",
                            "value": "mockedNamespaceStack"
                        },
                        {
                            "parameter": "eventfactory",
                            "value": "mockedXMLEventFactory"
                        },
                        {
                            "parameter": "doc",
                            "value": "mockedDocumentWithProcessingInstructions"
                        }
                    ],
                    "expected": "All processing instructions processed correctly",
                    "description": "Test case where the document contains processing instructions, ensuring that the method processes them correctly."
                }
            ]
        }
    ]
}