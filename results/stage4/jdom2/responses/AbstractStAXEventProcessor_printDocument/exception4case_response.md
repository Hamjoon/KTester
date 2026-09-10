{
    "group": "Test printDocument with XMLStreamException",
    "cases": [
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "mockXMLEventConsumerThatThrowsException"
                },
                {
                    "parameter": "fstack",
                    "value": "mockFormatStackWithEncoding"
                },
                {
                    "parameter": "nstack",
                    "value": "mockNamespaceStack"
                },
                {
                    "parameter": "eventfactory",
                    "value": "mockXMLEventFactory"
                },
                {
                    "parameter": "doc",
                    "value": "mockDocumentWithRoot"
                }
            ],
            "expected": "XMLStreamException is thrown",
            "description": "Test when the XMLEventConsumer throws an XMLStreamException during processing, expect an XMLStreamException to be thrown."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "mockXMLEventConsumer"
                },
                {
                    "parameter": "fstack",
                    "value": "mockFormatStackWithEncoding"
                },
                {
                    "parameter": "nstack",
                    "value": "mockNamespaceStack"
                },
                {
                    "parameter": "eventfactory",
                    "value": "mockXMLEventFactoryThatThrowsException"
                },
                {
                    "parameter": "doc",
                    "value": "mockDocumentWithRoot"
                }
            ],
            "expected": "XMLStreamException is thrown",
            "description": "Test when the XMLEventFactory throws an XMLStreamException during processing, expect an XMLStreamException to be thrown."
        }
    ]
}