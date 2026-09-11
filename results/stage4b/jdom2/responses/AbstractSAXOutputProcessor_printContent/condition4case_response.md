{
    "testCases": [
        {
            "group": "printContent with empty walker",
            "cases": [
                {
                    "input": [],
                    "expected": "No output generated, method completes successfully.",
                    "description": "Test the printContent method when the walker has no elements to process."
                }
            ]
        },
        {
            "group": "printContent with null content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "walker",
                            "value": "MockedWalker with hasNext returning true and next returning null"
                        }
                    ],
                    "expected": "printText or printCDATA is called with the text from walker.",
                    "description": "Test the printContent method when the walker returns null content, ensuring it processes formatted text or CDATA correctly."
                }
            ]
        },
        {
            "group": "printContent with CDATA content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "walker",
                            "value": "MockedWalker with hasNext returning true and next returning CDATA instance"
                        }
                    ],
                    "expected": "printCDATA is called with the CDATA content.",
                    "description": "Test the printContent method when the walker returns a CDATA instance, ensuring printCDATA is invoked."
                }
            ]
        },
        {
            "group": "printContent with Comment content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "walker",
                            "value": "MockedWalker with hasNext returning true and next returning Comment instance"
                        }
                    ],
                    "expected": "printComment is called with the Comment content.",
                    "description": "Test the printContent method when the walker returns a Comment instance, ensuring printComment is invoked."
                }
            ]
        },
        {
            "group": "printContent with DocType content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "walker",
                            "value": "MockedWalker with hasNext returning true and next returning DocType instance"
                        }
                    ],
                    "expected": "No action taken, method completes successfully.",
                    "description": "Test the printContent method when the walker returns a DocType instance, ensuring no action is taken."
                }
            ]
        },
        {
            "group": "printContent with Element content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "walker",
                            "value": "MockedWalker with hasNext returning true and next returning Element instance"
                        }
                    ],
                    "expected": "printElement is called with the Element content.",
                    "description": "Test the printContent method when the walker returns an Element instance, ensuring printElement is invoked."
                }
            ]
        },
        {
            "group": "printContent with EntityRef content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "walker",
                            "value": "MockedWalker with hasNext returning true and next returning EntityRef instance"
                        }
                    ],
                    "expected": "printEntityRef is called with the EntityRef content.",
                    "description": "Test the printContent method when the walker returns an EntityRef instance, ensuring printEntityRef is invoked."
                }
            ]
        },
        {
            "group": "printContent with ProcessingInstruction content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "walker",
                            "value": "MockedWalker with hasNext returning true and next returning ProcessingInstruction instance"
                        }
                    ],
                    "expected": "printProcessingInstruction is called with the ProcessingInstruction content.",
                    "description": "Test the printContent method when the walker returns a ProcessingInstruction instance, ensuring printProcessingInstruction is invoked."
                }
            ]
        },
        {
            "group": "printContent with Text content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "walker",
                            "value": "MockedWalker with hasNext returning true and next returning Text instance"
                        }
                    ],
                    "expected": "printText is called with the Text content.",
                    "description": "Test the printContent method when the walker returns a Text instance, ensuring printText is invoked."
                }
            ]
        }
    ]
}