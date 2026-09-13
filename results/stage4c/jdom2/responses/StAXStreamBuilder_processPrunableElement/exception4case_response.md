{
    "group": "processPrunableElement",
    "cases": [
        {
            "input": [
                {
                    "parameter": "factory",
                    "value": "MockJDOMFactory"
                },
                {
                    "parameter": "reader",
                    "value": "MockXMLStreamReader with START_ELEMENT and END_DOCUMENT events"
                },
                {
                    "parameter": "topdepth",
                    "value": 0
                },
                {
                    "parameter": "filter",
                    "value": "MockStAXFilter"
                }
            ],
            "expected": "Element without further processing beyond END_DOCUMENT",
            "description": "Test case where the XMLStreamReader encounters an END_DOCUMENT event unexpectedly, ensuring no further processing occurs."
        },
        {
            "input": [
                {
                    "parameter": "factory",
                    "value": "MockJDOMFactory"
                },
                {
                    "parameter": "reader",
                    "value": "MockXMLStreamReader with START_ELEMENT and invalid XMLStream event"
                },
                {
                    "parameter": "topdepth",
                    "value": 0
                },
                {
                    "parameter": "filter",
                    "value": "MockStAXFilter"
                }
            ],
            "expected": "JDOMException with message 'Unexpected XMLStream event X'",
            "description": "Test case where an invalid XMLStream event is encountered, expecting an exception to be thrown."
        }
    ]
}