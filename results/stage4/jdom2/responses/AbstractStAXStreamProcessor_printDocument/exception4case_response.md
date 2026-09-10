{
    "output": [
        {
            "group": "printDocument - XMLStreamException on writeStartDocument",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "XMLStreamWriter",
                            "value": "mocked XMLStreamWriter that throws XMLStreamException on writeStartDocument"
                        },
                        {
                            "parameter": "FormatStack",
                            "value": "mocked FormatStack with encoding and line separator set"
                        },
                        {
                            "parameter": "NamespaceStack",
                            "value": "mocked NamespaceStack"
                        },
                        {
                            "parameter": "Document",
                            "value": "mocked Document with a root element"
                        }
                    ],
                    "expected": "XMLStreamException",
                    "description": "Test when writeStartDocument fails due to XMLStreamException, ensuring the exception is thrown."
                }
            ]
        },
        {
            "group": "printDocument - XMLStreamException on writeCharacters",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "XMLStreamWriter",
                            "value": "mocked XMLStreamWriter that throws XMLStreamException on writeCharacters"
                        },
                        {
                            "parameter": "FormatStack",
                            "value": "mocked FormatStack with encoding and line separator set"
                        },
                        {
                            "parameter": "NamespaceStack",
                            "value": "mocked NamespaceStack"
                        },
                        {
                            "parameter": "Document",
                            "value": "mocked Document with a root element and whitespace content"
                        }
                    ],
                    "expected": "XMLStreamException",
                    "description": "Test when writeCharacters fails due to XMLStreamException, ensuring the exception is thrown."
                }
            ]
        },
        {
            "group": "printDocument - XMLStreamException on writeEndDocument",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "XMLStreamWriter",
                            "value": "mocked XMLStreamWriter that throws XMLStreamException on writeEndDocument"
                        },
                        {
                            "parameter": "FormatStack",
                            "value": "mocked FormatStack with encoding and line separator set"
                        },
                        {
                            "parameter": "NamespaceStack",
                            "value": "mocked NamespaceStack"
                        },
                        {
                            "parameter": "Document",
                            "value": "mocked Document with a root element"
                        }
                    ],
                    "expected": "XMLStreamException",
                    "description": "Test when writeEndDocument fails due to XMLStreamException, ensuring the exception is thrown."
                }
            ]
        }
    ]
}