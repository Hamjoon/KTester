{
    "testCases": [
        {
            "group": "printContent - Null Content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "Writer",
                            "value": "mocked Writer instance"
                        },
                        {
                            "parameter": "FormatStack",
                            "value": "mocked FormatStack instance"
                        },
                        {
                            "parameter": "NamespaceStack",
                            "value": "mocked NamespaceStack instance"
                        },
                        {
                            "parameter": "Walker",
                            "value": "mocked Walker with hasNext() returning true and next() returning null"
                        }
                    ],
                    "expected": "no exception thrown",
                    "description": "Test when Walker returns null content, should call textRaw with the text value."
                }
            ]
        },
        {
            "group": "printContent - CDATA Case",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "Writer",
                            "value": "mocked Writer instance"
                        },
                        {
                            "parameter": "FormatStack",
                            "value": "mocked FormatStack instance"
                        },
                        {
                            "parameter": "NamespaceStack",
                            "value": "mocked NamespaceStack instance"
                        },
                        {
                            "parameter": "Walker",
                            "value": "mocked Walker with hasNext() returning true, next() returning CDATA instance"
                        }
                    ],
                    "expected": "printCDATA called with CDATA instance",
                    "description": "Test when Walker returns a CDATA content, should call printCDATA method."
                }
            ]
        },
        {
            "group": "printContent - Comment Case",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "Writer",
                            "value": "mocked Writer instance"
                        },
                        {
                            "parameter": "FormatStack",
                            "value": "mocked FormatStack instance"
                        },
                        {
                            "parameter": "NamespaceStack",
                            "value": "mocked NamespaceStack instance"
                        },
                        {
                            "parameter": "Walker",
                            "value": "mocked Walker with hasNext() returning true, next() returning Comment instance"
                        }
                    ],
                    "expected": "printComment called with Comment instance",
                    "description": "Test when Walker returns a Comment content, should call printComment method."
                }
            ]
        },
        {
            "group": "printContent - DocType Case",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "Writer",
                            "value": "mocked Writer instance"
                        },
                        {
                            "parameter": "FormatStack",
                            "value": "mocked FormatStack instance"
                        },
                        {
                            "parameter": "NamespaceStack",
                            "value": "mocked NamespaceStack instance"
                        },
                        {
                            "parameter": "Walker",
                            "value": "mocked Walker with hasNext() returning true, next() returning DocType instance"
                        }
                    ],
                    "expected": "printDocType called with DocType instance",
                    "description": "Test when Walker returns a DocType content, should call printDocType method."
                }
            ]
        },
        {
            "group": "printContent - Element Case",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "Writer",
                            "value": "mocked Writer instance"
                        },
                        {
                            "parameter": "FormatStack",
                            "value": "mocked FormatStack instance"
                        },
                        {
                            "parameter": "NamespaceStack",
                            "value": "mocked NamespaceStack instance"
                        },
                        {
                            "parameter": "Walker",
                            "value": "mocked Walker with hasNext() returning true, next() returning Element instance"
                        }
                    ],
                    "expected": "printElement called with Element instance",
                    "description": "Test when Walker returns an Element content, should call printElement method."
                }
            ]
        },
        {
            "group": "printContent - EntityRef Case",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "Writer",
                            "value": "mocked Writer instance"
                        },
                        {
                            "parameter": "FormatStack",
                            "value": "mocked FormatStack instance"
                        },
                        {
                            "parameter": "NamespaceStack",
                            "value": "mocked NamespaceStack instance"
                        },
                        {
                            "parameter": "Walker",
                            "value": "mocked Walker with hasNext() returning true, next() returning EntityRef instance"
                        }
                    ],
                    "expected": "printEntityRef called with EntityRef instance",
                    "description": "Test when Walker returns an EntityRef content, should call printEntityRef method."
                }
            ]
        },
        {
            "group": "printContent - ProcessingInstruction Case",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "Writer",
                            "value": "mocked Writer instance"
                        },
                        {
                            "parameter": "FormatStack",
                            "value": "mocked FormatStack instance"
                        },
                        {
                            "parameter": "NamespaceStack",
                            "value": "mocked NamespaceStack instance"
                        },
                        {
                            "parameter": "Walker",
                            "value": "mocked Walker with hasNext() returning true, next() returning ProcessingInstruction instance"
                        }
                    ],
                    "expected": "printProcessingInstruction called with ProcessingInstruction instance",
                    "description": "Test when Walker returns a ProcessingInstruction content, should call printProcessingInstruction method."
                }
            ]
        },
        {
            "group": "printContent - Text Case",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "Writer",
                            "value": "mocked Writer instance"
                        },
                        {
                            "parameter": "FormatStack",
                            "value": "mocked FormatStack instance"
                        },
                        {
                            "parameter": "NamespaceStack",
                            "value": "mocked NamespaceStack instance"
                        },
                        {
                            "parameter": "Walker",
                            "value": "mocked Walker with hasNext() returning true, next() returning Text instance"
                        }
                    ],
                    "expected": "printText called with Text instance",
                    "description": "Test when Walker returns a Text content, should call printText method."
                }
            ]
        }
    ]
}