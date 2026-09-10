{
    "group": "printContent",
    "cases": [
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "mock XMLStreamWriter that throws XMLStreamException"
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
            "expected": "Throws XMLStreamException",
            "description": "Test case where printCDATA throws XMLStreamException when called."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "mock XMLStreamWriter that throws XMLStreamException"
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
            "expected": "Throws XMLStreamException",
            "description": "Test case where printComment throws XMLStreamException when called."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "mock XMLStreamWriter that throws XMLStreamException"
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
            "expected": "Throws XMLStreamException",
            "description": "Test case where printElement throws XMLStreamException when called."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "mock XMLStreamWriter that throws XMLStreamException"
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
            "expected": "Throws XMLStreamException",
            "description": "Test case where printEntityRef throws XMLStreamException when called."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "mock XMLStreamWriter that throws XMLStreamException"
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
            "expected": "Throws XMLStreamException",
            "description": "Test case where printProcessingInstruction throws XMLStreamException when called."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "mock XMLStreamWriter that throws XMLStreamException"
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
            "expected": "Throws XMLStreamException",
            "description": "Test case where printText throws XMLStreamException when called."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "mock XMLStreamWriter that throws XMLStreamException"
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
            "expected": "Throws XMLStreamException",
            "description": "Test case where printDocType throws XMLStreamException when called."
        }
    ]
}