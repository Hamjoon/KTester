{
    "group": "printContent",
    "cases": [
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "SAXTarget instance"
                },
                {
                    "parameter": "fstack",
                    "value": "FormatStack instance"
                },
                {
                    "parameter": "nstack",
                    "value": "NamespaceStack instance"
                },
                {
                    "parameter": "walker",
                    "value": "Walker instance with hasNext() returning false"
                }
            ],
            "expected": "No output or exception",
            "description": "Test with an empty Walker to ensure no processing is done."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "SAXTarget instance"
                },
                {
                    "parameter": "fstack",
                    "value": "FormatStack instance"
                },
                {
                    "parameter": "nstack",
                    "value": "NamespaceStack instance"
                },
                {
                    "parameter": "walker",
                    "value": "Walker instance with hasNext() returning true and next() returning null with isCDATA() true"
                }
            ],
            "expected": "printCDATA is called with the correct parameters",
            "description": "Test when Walker returns null and isCDATA() is true, ensuring printCDATA is called."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "SAXTarget instance"
                },
                {
                    "parameter": "fstack",
                    "value": "FormatStack instance"
                },
                {
                    "parameter": "nstack",
                    "value": "NamespaceStack instance"
                },
                {
                    "parameter": "walker",
                    "value": "Walker instance with hasNext() returning true and next() returning null with isCDATA() false"
                }
            ],
            "expected": "printText is called with the correct parameters",
            "description": "Test when Walker returns null and isCDATA() is false, ensuring printText is called."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "SAXTarget instance"
                },
                {
                    "parameter": "fstack",
                    "value": "FormatStack instance"
                },
                {
                    "parameter": "nstack",
                    "value": "NamespaceStack instance"
                },
                {
                    "parameter": "walker",
                    "value": "Walker instance with hasNext() returning true and next() returning a CDATA instance"
                }
            ],
            "expected": "printCDATA is called with the correct CDATA instance",
            "description": "Test when Walker returns a CDATA instance, ensuring printCDATA is called."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "SAXTarget instance"
                },
                {
                    "parameter": "fstack",
                    "value": "FormatStack instance"
                },
                {
                    "parameter": "nstack",
                    "value": "NamespaceStack instance"
                },
                {
                    "parameter": "walker",
                    "value": "Walker instance with hasNext() returning true and next() returning a Comment instance"
                }
            ],
            "expected": "printComment is called with the correct Comment instance",
            "description": "Test when Walker returns a Comment instance, ensuring printComment is called."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "SAXTarget instance"
                },
                {
                    "parameter": "fstack",
                    "value": "FormatStack instance"
                },
                {
                    "parameter": "nstack",
                    "value": "NamespaceStack instance"
                },
                {
                    "parameter": "walker",
                    "value": "Walker instance with hasNext() returning true and next() returning a DocType instance"
                }
            ],
            "expected": "No action taken for DocType instance",
            "description": "Test when Walker returns a DocType instance, ensuring no action is taken."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "SAXTarget instance"
                },
                {
                    "parameter": "fstack",
                    "value": "FormatStack instance"
                },
                {
                    "parameter": "nstack",
                    "value": "NamespaceStack instance"
                },
                {
                    "parameter": "walker",
                    "value": "Walker instance with hasNext() returning true and next() returning an Element instance"
                }
            ],
            "expected": "printElement is called with the correct Element instance",
            "description": "Test when Walker returns an Element instance, ensuring printElement is called."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "SAXTarget instance"
                },
                {
                    "parameter": "fstack",
                    "value": "FormatStack instance"
                },
                {
                    "parameter": "nstack",
                    "value": "NamespaceStack instance"
                },
                {
                    "parameter": "walker",
                    "value": "Walker instance with hasNext() returning true and next() returning an EntityRef instance"
                }
            ],
            "expected": "printEntityRef is called with the correct EntityRef instance",
            "description": "Test when Walker returns an EntityRef instance, ensuring printEntityRef is called."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "SAXTarget instance"
                },
                {
                    "parameter": "fstack",
                    "value": "FormatStack instance"
                },
                {
                    "parameter": "nstack",
                    "value": "NamespaceStack instance"
                },
                {
                    "parameter": "walker",
                    "value": "Walker instance with hasNext() returning true and next() returning a ProcessingInstruction instance"
                }
            ],
            "expected": "printProcessingInstruction is called with the correct ProcessingInstruction instance",
            "description": "Test when Walker returns a ProcessingInstruction instance, ensuring printProcessingInstruction is called."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "SAXTarget instance"
                },
                {
                    "parameter": "fstack",
                    "value": "FormatStack instance"
                },
                {
                    "parameter": "nstack",
                    "value": "NamespaceStack instance"
                },
                {
                    "parameter": "walker",
                    "value": "Walker instance with hasNext() returning true and next() returning a Text instance"
                }
            ],
            "expected": "printText is called with the correct Text instance",
            "description": "Test when Walker returns a Text instance, ensuring printText is called."
        }
    ]
}