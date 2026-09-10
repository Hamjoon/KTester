{
    "testCases": [
        {
            "group": "Test printDocument with omitDeclaration true",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mockXMLEventConsumer"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mockFormatStackOmitDeclaration"
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
                            "parameter": "doc",
                            "value": "mockDocumentWithRoot"
                        }
                    ],
                    "expected": "Start document with no encoding",
                    "description": "Test when FormatStack omits declaration, expect start document to be created without encoding."
                }
            ]
        },
        {
            "group": "Test printDocument with omitEncoding true",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mockXMLEventConsumer"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mockFormatStackOmitEncoding"
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
                            "parameter": "doc",
                            "value": "mockDocumentWithRoot"
                        }
                    ],
                    "expected": "Start document with version 1.0 and no encoding",
                    "description": "Test when FormatStack omits encoding, expect start document to be created with version 1.0 and no encoding."
                }
            ]
        },
        {
            "group": "Test printDocument with normal encoding",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mockXMLEventConsumer"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mockFormatStackWithEncoding"
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
                            "parameter": "doc",
                            "value": "mockDocumentWithRoot"
                        }
                    ],
                    "expected": "Start document with specified encoding",
                    "description": "Test when FormatStack has normal encoding, expect start document to be created with specified encoding."
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
                            "value": "mockXMLEventConsumer"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mockFormatStackWithEncoding"
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
                            "parameter": "doc",
                            "value": "mockEmptyDocument"
                        }
                    ],
                    "expected": "No content processed, end document created",
                    "description": "Test when document has no root element, expect no content to be processed, and end document to be created."
                }
            ]
        },
        {
            "group": "Test printDocument with different content types",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mockXMLEventConsumer"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mockFormatStackWithEncoding"
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
                            "parameter": "doc",
                            "value": "mockDocumentWithVariousContent"
                        }
                    ],
                    "expected": "All content types processed correctly",
                    "description": "Test when document contains various content types (Comment, DocType, Element, ProcessingInstruction), expect all to be processed correctly."
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
                            "value": "mockXMLEventConsumer"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mockFormatStackWithLineSeparator"
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
                            "parameter": "doc",
                            "value": "mockDocumentWithRoot"
                        }
                    ],
                    "expected": "Line separator added before end document",
                    "description": "Test when FormatStack has line separator, expect line separator to be added before the end document."
                }
            ]
        }
    ]
}