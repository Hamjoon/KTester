{
    "testCases": [
        {
            "group": "printContent with null content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mockedXMLEventConsumer"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mockedFormatStack"
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
                            "parameter": "walker",
                            "value": "mockedWalkerWithNullContent"
                        }
                    ],
                    "expected": "void",
                    "description": "Test when walker has next but returns null content, expecting to process as CDATA or Text based on walker.isCDATA()"
                }
            ]
        },
        {
            "group": "printContent with CDATA content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mockedXMLEventConsumer"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mockedFormatStack"
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
                            "parameter": "walker",
                            "value": "mockedWalkerWithCDATA"
                        }
                    ],
                    "expected": "void",
                    "description": "Test when walker returns CDATA content, expecting printCDATA to be called"
                }
            ]
        },
        {
            "group": "printContent with Comment content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mockedXMLEventConsumer"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mockedFormatStack"
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
                            "parameter": "walker",
                            "value": "mockedWalkerWithComment"
                        }
                    ],
                    "expected": "void",
                    "description": "Test when walker returns Comment content, expecting printComment to be called"
                }
            ]
        },
        {
            "group": "printContent with Element content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mockedXMLEventConsumer"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mockedFormatStack"
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
                            "parameter": "walker",
                            "value": "mockedWalkerWithElement"
                        }
                    ],
                    "expected": "void",
                    "description": "Test when walker returns Element content, expecting printElement to be called"
                }
            ]
        },
        {
            "group": "printContent with EntityRef content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mockedXMLEventConsumer"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mockedFormatStack"
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
                            "parameter": "walker",
                            "value": "mockedWalkerWithEntityRef"
                        }
                    ],
                    "expected": "void",
                    "description": "Test when walker returns EntityRef content, expecting printEntityRef to be called"
                }
            ]
        },
        {
            "group": "printContent with ProcessingInstruction content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mockedXMLEventConsumer"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mockedFormatStack"
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
                            "parameter": "walker",
                            "value": "mockedWalkerWithProcessingInstruction"
                        }
                    ],
                    "expected": "void",
                    "description": "Test when walker returns ProcessingInstruction content, expecting printProcessingInstruction to be called"
                }
            ]
        },
        {
            "group": "printContent with Text content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mockedXMLEventConsumer"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mockedFormatStack"
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
                            "parameter": "walker",
                            "value": "mockedWalkerWithText"
                        }
                    ],
                    "expected": "void",
                    "description": "Test when walker returns Text content, expecting printText to be called"
                }
            ]
        },
        {
            "group": "printContent with DocType content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mockedXMLEventConsumer"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mockedFormatStack"
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
                            "parameter": "walker",
                            "value": "mockedWalkerWithDocType"
                        }
                    ],
                    "expected": "void",
                    "description": "Test when walker returns DocType content, expecting printDocType to be called"
                }
            ]
        },
        {
            "group": "printContent with unexpected content type",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mockedXMLEventConsumer"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mockedFormatStack"
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
                            "parameter": "walker",
                            "value": "mockedWalkerWithUnexpectedContent"
                        }
                    ],
                    "expected": "IllegalStateException",
                    "description": "Test when walker returns unexpected content type, expecting IllegalStateException to be thrown"
                }
            ]
        }
    ]
}