{
    "group": "printContent",
    "cases": [
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "mocked XMLStreamWriter that throws XMLStreamException"
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
            "expected": "XMLStreamException should be thrown",
            "description": "Test when printElement is called and XMLStreamWriter throws an XMLStreamException."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "mocked XMLStreamWriter that throws XMLStreamException"
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
            "expected": "XMLStreamException should be thrown",
            "description": "Test when printComment is called and XMLStreamWriter throws an XMLStreamException."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "mocked XMLStreamWriter that throws XMLStreamException"
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
            "expected": "XMLStreamException should be thrown",
            "description": "Test when printCDATA is called and XMLStreamWriter throws an XMLStreamException."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "mocked XMLStreamWriter that throws XMLStreamException"
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
            "expected": "XMLStreamException should be thrown",
            "description": "Test when printProcessingInstruction is called and XMLStreamWriter throws an XMLStreamException."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "mocked XMLStreamWriter that throws XMLStreamException"
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
            "expected": "XMLStreamException should be thrown",
            "description": "Test when printEntityRef is called and XMLStreamWriter throws an XMLStreamException."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "mocked XMLStreamWriter that throws XMLStreamException"
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
            "expected": "XMLStreamException should be thrown",
            "description": "Test when printDocType is called and XMLStreamWriter throws an XMLStreamException."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "mocked XMLStreamWriter that throws XMLStreamException"
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
            "expected": "XMLStreamException should be thrown",
            "description": "Test when printText is called and XMLStreamWriter throws an XMLStreamException."
        }
    ]
}