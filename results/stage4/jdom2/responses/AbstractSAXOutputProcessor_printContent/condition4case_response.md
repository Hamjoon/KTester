{
    "testCases": [
        {
            "group": "printContent with null Content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "SAXTarget",
                            "value": "mockSAXTarget"
                        },
                        {
                            "parameter": "FormatStack",
                            "value": "mockFormatStack"
                        },
                        {
                            "parameter": "NamespaceStack",
                            "value": "mockNamespaceStack"
                        },
                        {
                            "parameter": "Walker",
                            "value": "mockWalkerWithNullContent"
                        }
                    ],
                    "expected": "no exception and calls printText or printCDATA",
                    "description": "Test printContent when Walker has null Content. It should call printText or printCDATA based on the text type."
                }
            ]
        },
        {
            "group": "printContent with CDATA Content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "SAXTarget",
                            "value": "mockSAXTarget"
                        },
                        {
                            "parameter": "FormatStack",
                            "value": "mockFormatStack"
                        },
                        {
                            "parameter": "NamespaceStack",
                            "value": "mockNamespaceStack"
                        },
                        {
                            "parameter": "Walker",
                            "value": "mockWalkerWithCDATA"
                        }
                    ],
                    "expected": "calls printCDATA",
                    "description": "Test printContent when Walker has CDATA Content. It should call printCDATA method."
                }
            ]
        },
        {
            "group": "printContent with Comment Content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "SAXTarget",
                            "value": "mockSAXTarget"
                        },
                        {
                            "parameter": "FormatStack",
                            "value": "mockFormatStack"
                        },
                        {
                            "parameter": "NamespaceStack",
                            "value": "mockNamespaceStack"
                        },
                        {
                            "parameter": "Walker",
                            "value": "mockWalkerWithComment"
                        }
                    ],
                    "expected": "calls printComment",
                    "description": "Test printContent when Walker has Comment Content. It should call printComment method."
                }
            ]
        },
        {
            "group": "printContent with DocType Content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "SAXTarget",
                            "value": "mockSAXTarget"
                        },
                        {
                            "parameter": "FormatStack",
                            "value": "mockFormatStack"
                        },
                        {
                            "parameter": "NamespaceStack",
                            "value": "mockNamespaceStack"
                        },
                        {
                            "parameter": "Walker",
                            "value": "mockWalkerWithDocType"
                        }
                    ],
                    "expected": "no exception and does nothing",
                    "description": "Test printContent when Walker has DocType Content. It should do nothing as per the implementation."
                }
            ]
        },
        {
            "group": "printContent with Element Content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "SAXTarget",
                            "value": "mockSAXTarget"
                        },
                        {
                            "parameter": "FormatStack",
                            "value": "mockFormatStack"
                        },
                        {
                            "parameter": "NamespaceStack",
                            "value": "mockNamespaceStack"
                        },
                        {
                            "parameter": "Walker",
                            "value": "mockWalkerWithElement"
                        }
                    ],
                    "expected": "calls printElement",
                    "description": "Test printContent when Walker has Element Content. It should call printElement method."
                }
            ]
        },
        {
            "group": "printContent with EntityRef Content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "SAXTarget",
                            "value": "mockSAXTarget"
                        },
                        {
                            "parameter": "FormatStack",
                            "value": "mockFormatStack"
                        },
                        {
                            "parameter": "NamespaceStack",
                            "value": "mockNamespaceStack"
                        },
                        {
                            "parameter": "Walker",
                            "value": "mockWalkerWithEntityRef"
                        }
                    ],
                    "expected": "calls printEntityRef",
                    "description": "Test printContent when Walker has EntityRef Content. It should call printEntityRef method."
                }
            ]
        },
        {
            "group": "printContent with ProcessingInstruction Content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "SAXTarget",
                            "value": "mockSAXTarget"
                        },
                        {
                            "parameter": "FormatStack",
                            "value": "mockFormatStack"
                        },
                        {
                            "parameter": "NamespaceStack",
                            "value": "mockNamespaceStack"
                        },
                        {
                            "parameter": "Walker",
                            "value": "mockWalkerWithProcessingInstruction"
                        }
                    ],
                    "expected": "calls printProcessingInstruction",
                    "description": "Test printContent when Walker has ProcessingInstruction Content. It should call printProcessingInstruction method."
                }
            ]
        },
        {
            "group": "printContent with Text Content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "SAXTarget",
                            "value": "mockSAXTarget"
                        },
                        {
                            "parameter": "FormatStack",
                            "value": "mockFormatStack"
                        },
                        {
                            "parameter": "NamespaceStack",
                            "value": "mockNamespaceStack"
                        },
                        {
                            "parameter": "Walker",
                            "value": "mockWalkerWithText"
                        }
                    ],
                    "expected": "calls printText",
                    "description": "Test printContent when Walker has Text Content. It should call printText method."
                }
            ]
        }
    ]
}