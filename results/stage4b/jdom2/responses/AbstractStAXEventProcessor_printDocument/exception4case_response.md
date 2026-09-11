{
    "group": "Test printDocument exception scenarios",
    "cases": [
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "null"
                },
                {
                    "parameter": "fstack",
                    "value": "mockedFormatStackWithDefaultEncoding"
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
                    "parameter": "doc",
                    "value": "mockedDocumentWithRootElement"
                }
            ],
            "expected": "XMLStreamException due to null XMLEventConsumer",
            "description": "Test case where the XMLEventConsumer is null, ensuring that the method throws an XMLStreamException."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "mockedXMLEventConsumer"
                },
                {
                    "parameter": "fstack",
                    "value": "mockedFormatStackWithDefaultEncoding"
                },
                {
                    "parameter": "nstack",
                    "value": "mockedNamespaceStack"
                },
                {
                    "parameter": "eventfactory",
                    "value": "null"
                },
                {
                    "parameter": "doc",
                    "value": "mockedDocumentWithRootElement"
                }
            ],
            "expected": "XMLStreamException due to null XMLEventFactory",
            "description": "Test case where the XMLEventFactory is null, ensuring that the method throws an XMLStreamException."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "mockedXMLEventConsumer"
                },
                {
                    "parameter": "fstack",
                    "value": "mockedFormatStackWithDefaultEncoding"
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
                    "parameter": "doc",
                    "value": "mockedDocumentWithInvalidContent"
                }
            ],
            "expected": "XMLStreamException due to invalid content in Document",
            "description": "Test case where the Document contains invalid content that cannot be processed, ensuring that the method throws an XMLStreamException."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "mockedXMLEventConsumer"
                },
                {
                    "parameter": "fstack",
                    "value": "mockedFormatStackWithDefaultEncoding"
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
                    "parameter": "doc",
                    "value": "mockedDocumentWithNullRootElement"
                }
            ],
            "expected": "XMLStreamException due to null root element",
            "description": "Test case where the Document has a null root element, ensuring that the method throws an XMLStreamException."
        }
    ]
}