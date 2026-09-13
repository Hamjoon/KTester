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
                    "value": "MockXMLStreamReader with START_ELEMENT event"
                },
                {
                    "parameter": "topdepth",
                    "value": 0
                },
                {
                    "parameter": "filter",
                    "value": "MockStAXFilter that returns false for all prune methods"
                }
            ],
            "expected": "Element with nested structure based on XMLStreamReader input",
            "description": "Test case where reader starts with START_ELEMENT and filter does not prune any elements, ensuring a complete nested structure is built."
        },
        {
            "input": [
                {
                    "parameter": "factory",
                    "value": "MockJDOMFactory"
                },
                {
                    "parameter": "reader",
                    "value": "MockXMLStreamReader not at START_ELEMENT event"
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
            "expected": "JDOMException with message 'JDOM requires that the XMLStreamReader is at the START_ELEMENT state when retrieving an Element Fragment.'",
            "description": "Test case where the reader is not at the START_ELEMENT event, expecting an exception to be thrown."
        },
        {
            "input": [
                {
                    "parameter": "factory",
                    "value": "MockJDOMFactory"
                },
                {
                    "parameter": "reader",
                    "value": "MockXMLStreamReader with START_ELEMENT and subsequent END_ELEMENT events"
                },
                {
                    "parameter": "topdepth",
                    "value": 0
                },
                {
                    "parameter": "filter",
                    "value": "MockStAXFilter that prunes all elements"
                }
            ],
            "expected": "Element with no children",
            "description": "Test case where all elements are pruned by the filter, ensuring no children are added to the root element."
        },
        {
            "input": [
                {
                    "parameter": "factory",
                    "value": "MockJDOMFactory"
                },
                {
                    "parameter": "reader",
                    "value": "MockXMLStreamReader with START_ELEMENT, CDATA, and CHARACTERS events"
                },
                {
                    "parameter": "topdepth",
                    "value": 0
                },
                {
                    "parameter": "filter",
                    "value": "MockStAXFilter that prunes CDATA but not CHARACTERS"
                }
            ],
            "expected": "Element with text content from CHARACTERS event only",
            "description": "Test case where CDATA sections are pruned but CHARACTERS are not, verifying only CHARACTERS are added to the element."
        },
        {
            "input": [
                {
                    "parameter": "factory",
                    "value": "MockJDOMFactory"
                },
                {
                    "parameter": "reader",
                    "value": "MockXMLStreamReader with START_ELEMENT and ENTITY_REFERENCE events"
                },
                {
                    "parameter": "topdepth",
                    "value": 0
                },
                {
                    "parameter": "filter",
                    "value": "MockStAXFilter that prunes ENTITY_REFERENCE"
                }
            ],
            "expected": "Element without entity references",
            "description": "Test case where entity references are pruned, ensuring they are not added to the element content."
        },
        {
            "input": [
                {
                    "parameter": "factory",
                    "value": "MockJDOMFactory"
                },
                {
                    "parameter": "reader",
                    "value": "MockXMLStreamReader with START_ELEMENT and PROCESSING_INSTRUCTION events"
                },
                {
                    "parameter": "topdepth",
                    "value": 0
                },
                {
                    "parameter": "filter",
                    "value": "MockStAXFilter that does not prune PROCESSING_INSTRUCTION"
                }
            ],
            "expected": "Element with processing instructions",
            "description": "Test case where processing instructions are not pruned, ensuring they are added to the element content."
        },
        {
            "input": [
                {
                    "parameter": "factory",
                    "value": "MockJDOMFactory"
                },
                {
                    "parameter": "reader",
                    "value": "MockXMLStreamReader with START_ELEMENT and COMMENT events"
                },
                {
                    "parameter": "topdepth",
                    "value": 0
                },
                {
                    "parameter": "filter",
                    "value": "MockStAXFilter that prunes COMMENT"
                }
            ],
            "expected": "Element without comments",
            "description": "Test case where comments are pruned, ensuring they are not added to the element content."
        },
        {
            "input": [
                {
                    "parameter": "factory",
                    "value": "MockJDOMFactory"
                },
                {
                    "parameter": "reader",
                    "value": "MockXMLStreamReader with START_ELEMENT and unsupported event type"
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
            "description": "Test case where an unsupported XMLStream event occurs, expecting an exception to be thrown."
        }
    ]
}