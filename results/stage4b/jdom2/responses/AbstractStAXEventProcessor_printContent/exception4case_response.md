{
    "group": "printContent - Walker with null content and unexpected CType",
    "cases": [
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "mockXMLEventConsumer"
                },
                {
                    "parameter": "fstack",
                    "value": "mockFormatStack"
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
                    "parameter": "walker",
                    "value": "mockWalkerWithNullContentAndUnexpectedCType"
                }
            ],
            "expected": "IllegalStateException with message 'Unexpected Content'",
            "description": "Test when walker returns null content but has an unexpected CType."
        }
    ]
}