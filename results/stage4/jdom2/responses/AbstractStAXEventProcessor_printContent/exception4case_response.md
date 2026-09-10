{
    "group": "printContent with XMLStreamException",
    "cases": [
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "mockedXMLEventConsumer"
                },
                {
                    "parameter": "fstack",
                    "value": "mockedFormatStack"
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
                    "parameter": "walker",
                    "value": "mockedWalkerWithXMLStreamException"
                }
            ],
            "expected": "XMLStreamException",
            "description": "Test when walker throws an XMLStreamException during content processing, expecting XMLStreamException to be thrown."
        }
    ]
}