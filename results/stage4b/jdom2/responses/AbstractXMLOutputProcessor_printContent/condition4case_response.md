{
    "test cases": [
        {
            "group": "printContent",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mock Writer"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mock FormatStack"
                        },
                        {
                            "parameter": "nstack",
                            "value": "mock NamespaceStack"
                        },
                        {
                            "parameter": "walker",
                            "value": "mock Walker with hasNext() returning true and next() returning null"
                        }
                    ],
                    "expected": "textRaw method is called with appropriate text value",
                    "description": "Test case where walker has next content as null, expecting raw text handling."
                },
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mock Writer"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mock FormatStack"
                        },
                        {
                            "parameter": "nstack",
                            "value": "mock NamespaceStack"
                        },
                        {
                            "parameter": "walker",
                            "value": "mock Walker with hasNext() returning true and next() returning a CDATA object"
                        }
                    ],
                    "expected": "printCDATA method is called",
                    "description": "Test case where walker returns a CDATA object, expecting printCDATA to be invoked."
                },
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mock Writer"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mock FormatStack"
                        },
                        {
                            "parameter": "nstack",
                            "value": "mock NamespaceStack"
                        },
                        {
                            "parameter": "walker",
                            "value": "mock Walker with hasNext() returning true and next() returning a Comment object"
                        }
                    ],
                    "expected": "printComment method is called",
                    "description": "Test case where walker returns a Comment object, expecting printComment to be invoked."
                },
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mock Writer"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mock FormatStack"
                        },
                        {
                            "parameter": "nstack",
                            "value": "mock NamespaceStack"
                        },
                        {
                            "parameter": "walker",
                            "value": "mock Walker with hasNext() returning true and next() returning a DocType object"
                        }
                    ],
                    "expected": "printDocType method is called",
                    "description": "Test case where walker returns a DocType object, expecting printDocType to be invoked."
                },
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mock Writer"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mock FormatStack"
                        },
                        {
                            "parameter": "nstack",
                            "value": "mock NamespaceStack"
                        },
                        {
                            "parameter": "walker",
                            "value": "mock Walker with hasNext() returning true and next() returning an Element object"
                        }
                    ],
                    "expected": "printElement method is called",
                    "description": "Test case where walker returns an Element object, expecting printElement to be invoked."
                },
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mock Writer"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mock FormatStack"
                        },
                        {
                            "parameter": "nstack",
                            "value": "mock NamespaceStack"
                        },
                        {
                            "parameter": "walker",
                            "value": "mock Walker with hasNext() returning true and next() returning an EntityRef object"
                        }
                    ],
                    "expected": "printEntityRef method is called",
                    "description": "Test case where walker returns an EntityRef object, expecting printEntityRef to be invoked."
                },
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mock Writer"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mock FormatStack"
                        },
                        {
                            "parameter": "nstack",
                            "value": "mock NamespaceStack"
                        },
                        {
                            "parameter": "walker",
                            "value": "mock Walker with hasNext() returning true and next() returning a ProcessingInstruction object"
                        }
                    ],
                    "expected": "printProcessingInstruction method is called",
                    "description": "Test case where walker returns a ProcessingInstruction object, expecting printProcessingInstruction to be invoked."
                },
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mock Writer"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mock FormatStack"
                        },
                        {
                            "parameter": "nstack",
                            "value": "mock NamespaceStack"
                        },
                        {
                            "parameter": "walker",
                            "value": "mock Walker with hasNext() returning true and next() returning a Text object"
                        }
                    ],
                    "expected": "printText method is called",
                    "description": "Test case where walker returns a Text object, expecting printText to be invoked."
                },
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mock Writer"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mock FormatStack"
                        },
                        {
                            "parameter": "nstack",
                            "value": "mock NamespaceStack"
                        },
                        {
                            "parameter": "walker",
                            "value": "mock Walker with hasNext() returning false"
                        }
                    ],
                    "expected": "no content is processed, method completes without exceptions",
                    "description": "Test case where walker has no next content, expecting the method to complete without processing."
                }
            ]
        }
    ]
}