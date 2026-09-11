{
    "testCases": [
        {
            "group": "printContent",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mocked XMLStreamWriter"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mocked FormatStack"
                        },
                        {
                            "parameter": "nstack",
                            "value": "mocked NamespaceStack"
                        },
                        {
                            "parameter": "walker",
                            "value": "mocked Walker with no content"
                        }
                    ],
                    "expected": "No content should be printed",
                    "description": "Test when the walker has no content. It should handle the case where walker.hasNext() is false."
                },
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mocked XMLStreamWriter"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mocked FormatStack"
                        },
                        {
                            "parameter": "nstack",
                            "value": "mocked NamespaceStack"
                        },
                        {
                            "parameter": "walker",
                            "value": "mocked Walker that returns CDATA"
                        }
                    ],
                    "expected": "printCDATA method should be called",
                    "description": "Test when walker.next() returns null and walker.isCDATA() returns true. It should call printCDATA."
                },
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mocked XMLStreamWriter"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mocked FormatStack"
                        },
                        {
                            "parameter": "nstack",
                            "value": "mocked NamespaceStack"
                        },
                        {
                            "parameter": "walker",
                            "value": "mocked Walker that returns Text"
                        }
                    ],
                    "expected": "printText method should be called",
                    "description": "Test when walker.next() returns null and walker.isCDATA() returns false. It should call printText."
                },
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mocked XMLStreamWriter"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mocked FormatStack"
                        },
                        {
                            "parameter": "nstack",
                            "value": "mocked NamespaceStack"
                        },
                        {
                            "parameter": "walker",
                            "value": "mocked Walker that returns CDATA content"
                        }
                    ],
                    "expected": "printCDATA method should be called for CDATA content",
                    "description": "Test when walker.next() returns a CDATA content. It should call printCDATA."
                },
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mocked XMLStreamWriter"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mocked FormatStack"
                        },
                        {
                            "parameter": "nstack",
                            "value": "mocked NamespaceStack"
                        },
                        {
                            "parameter": "walker",
                            "value": "mocked Walker that returns Comment content"
                        }
                    ],
                    "expected": "printComment method should be called for Comment content",
                    "description": "Test when walker.next() returns a Comment content. It should call printComment."
                },
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mocked XMLStreamWriter"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mocked FormatStack"
                        },
                        {
                            "parameter": "nstack",
                            "value": "mocked NamespaceStack"
                        },
                        {
                            "parameter": "walker",
                            "value": "mocked Walker that returns Element content"
                        }
                    ],
                    "expected": "printElement method should be called for Element content",
                    "description": "Test when walker.next() returns an Element content. It should call printElement."
                },
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mocked XMLStreamWriter"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mocked FormatStack"
                        },
                        {
                            "parameter": "nstack",
                            "value": "mocked NamespaceStack"
                        },
                        {
                            "parameter": "walker",
                            "value": "mocked Walker that returns EntityRef content"
                        }
                    ],
                    "expected": "printEntityRef method should be called for EntityRef content",
                    "description": "Test when walker.next() returns an EntityRef content. It should call printEntityRef."
                },
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mocked XMLStreamWriter"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mocked FormatStack"
                        },
                        {
                            "parameter": "nstack",
                            "value": "mocked NamespaceStack"
                        },
                        {
                            "parameter": "walker",
                            "value": "mocked Walker that returns ProcessingInstruction content"
                        }
                    ],
                    "expected": "printProcessingInstruction method should be called for ProcessingInstruction content",
                    "description": "Test when walker.next() returns a ProcessingInstruction content. It should call printProcessingInstruction."
                },
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mocked XMLStreamWriter"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mocked FormatStack"
                        },
                        {
                            "parameter": "nstack",
                            "value": "mocked NamespaceStack"
                        },
                        {
                            "parameter": "walker",
                            "value": "mocked Walker that returns Text content"
                        }
                    ],
                    "expected": "printText method should be called for Text content",
                    "description": "Test when walker.next() returns a Text content. It should call printText."
                },
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mocked XMLStreamWriter"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mocked FormatStack"
                        },
                        {
                            "parameter": "nstack",
                            "value": "mocked NamespaceStack"
                        },
                        {
                            "parameter": "walker",
                            "value": "mocked Walker that returns DocType content"
                        }
                    ],
                    "expected": "printDocType method should be called for DocType content",
                    "description": "Test when walker.next() returns a DocType content. It should call printDocType."
                },
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mocked XMLStreamWriter"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mocked FormatStack"
                        },
                        {
                            "parameter": "nstack",
                            "value": "mocked NamespaceStack"
                        },
                        {
                            "parameter": "walker",
                            "value": "mocked Walker that returns unexpected content"
                        }
                    ],
                    "expected": "An IllegalStateException should be thrown",
                    "description": "Test when walker.next() returns content of an unexpected CType. It should throw IllegalStateException."
                }
            ]
        }
    ]
}