{
    "testCases": [
        {
            "group": "Test printDocument with omitted declaration",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mockedXMLEventConsumer"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mockedFormatStackWithOmitDeclaration"
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
                    "expected": "StartDocument event with no declaration",
                    "description": "Test case where the format stack omits the declaration, ensuring that the start document event is created without a declaration."
                }
            ]
        },
        {
            "group": "Test printDocument with omitted encoding",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mockedXMLEventConsumer"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mockedFormatStackWithOmitEncoding"
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
                    "expected": "StartDocument event with version 1.0 and no encoding",
                    "description": "Test case where the format stack omits the encoding, ensuring that the start document event is created with version 1.0 and no encoding."
                }
            ]
        },
        {
            "group": "Test printDocument with default encoding",
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
                            "value": "mockedDocumentWithRootElement"
                        }
                    ],
                    "expected": "StartDocument event with specified encoding and version 1.0",
                    "description": "Test case where the format stack provides a default encoding, ensuring that the start document event is created with the specified encoding."
                }
            ]
        },
        {
            "group": "Test printDocument with empty document",
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
                            "value": "mockedEmptyDocument"
                        }
                    ],
                    "expected": "Empty content list processed without errors",
                    "description": "Test case with an empty document, ensuring that the method handles the empty content list without throwing exceptions."
                }
            ]
        },
        {
            "group": "Test printDocument with various content types",
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
                            "value": "mockedDocumentWithMultipleContentTypes"
                        }
                    ],
                    "expected": "All content types processed correctly",
                    "description": "Test case with a document containing various content types (comments, elements, processing instructions), ensuring that each type is processed correctly."
                }
            ]
        }
    ]
}