{
    "testCases": [
        {
            "group": "printContent - Walker has next content",
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
                            "value": "mockWalkerWithContent"
                        }
                    ],
                    "expected": "no exception",
                    "description": "Test when walker has next content and processes various content types."
                }
            ]
        },
        {
            "group": "printContent - Walker has null content",
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
                            "value": "mockWalkerWithNullContentAndCDATA"
                        }
                    ],
                    "expected": "no exception",
                    "description": "Test when walker returns null content and is identified as CDATA."
                },
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
                            "value": "mockWalkerWithNullContentAndText"
                        }
                    ],
                    "expected": "no exception",
                    "description": "Test when walker returns null content and is identified as Text."
                }
            ]
        },
        {
            "group": "printContent - Switch case for Content types",
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
                            "value": "mockWalkerWithContentTypeCDATA"
                        }
                    ],
                    "expected": "no exception",
                    "description": "Test case for processing CDATA content type."
                },
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
                            "value": "mockWalkerWithContentTypeComment"
                        }
                    ],
                    "expected": "no exception",
                    "description": "Test case for processing Comment content type."
                },
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
                            "value": "mockWalkerWithContentTypeElement"
                        }
                    ],
                    "expected": "no exception",
                    "description": "Test case for processing Element content type."
                },
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
                            "value": "mockWalkerWithContentTypeEntityRef"
                        }
                    ],
                    "expected": "no exception",
                    "description": "Test case for processing EntityRef content type."
                },
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
                            "value": "mockWalkerWithContentTypeProcessingInstruction"
                        }
                    ],
                    "expected": "no exception",
                    "description": "Test case for processing ProcessingInstruction content type."
                },
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
                            "value": "mockWalkerWithContentTypeText"
                        }
                    ],
                    "expected": "no exception",
                    "description": "Test case for processing Text content type."
                },
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
                            "value": "mockWalkerWithContentTypeDocType"
                        }
                    ],
                    "expected": "no exception",
                    "description": "Test case for processing DocType content type."
                }
            ]
        },
        {
            "group": "printContent - Unexpected Content",
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
                            "value": "mockWalkerWithUnexpectedContent"
                        }
                    ],
                    "expected": "IllegalStateException with message 'Unexpected Content'",
                    "description": "Test case for handling unexpected content types."
                }
            ]
        }
    ]
}