{
    "group": "printDocument - XMLStreamException Handling",
    "cases": [
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "mock XMLStreamWriter that throws XMLStreamException"
                },
                {
                    "parameter": "fstack",
                    "value": "FormatStack with default settings"
                },
                {
                    "parameter": "nstack",
                    "value": "NamespaceStack"
                },
                {
                    "parameter": "doc",
                    "value": "Document with a root element and content"
                }
            ],
            "expected": "XMLStreamException is thrown",
            "description": "Test printDocument when XMLStreamWriter throws an XMLStreamException, ensuring the exception is propagated."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "mock XMLStreamWriter that throws XMLStreamException during writeStartDocument"
                },
                {
                    "parameter": "fstack",
                    "value": "FormatStack with encoding set to UTF-8"
                },
                {
                    "parameter": "nstack",
                    "value": "NamespaceStack"
                },
                {
                    "parameter": "doc",
                    "value": "Document with a root element"
                }
            ],
            "expected": "XMLStreamException is thrown during writeStartDocument",
            "description": "Test printDocument when an exception is thrown during writeStartDocument, ensuring it is handled correctly."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "mock XMLStreamWriter that throws XMLStreamException during writeEndDocument"
                },
                {
                    "parameter": "fstack",
                    "value": "FormatStack with default settings"
                },
                {
                    "parameter": "nstack",
                    "value": "NamespaceStack"
                },
                {
                    "parameter": "doc",
                    "value": "Document with a root element and content"
                }
            ],
            "expected": "XMLStreamException is thrown during writeEndDocument",
            "description": "Test printDocument when an exception is thrown during writeEndDocument, ensuring it is handled correctly."
        }
    ]
}