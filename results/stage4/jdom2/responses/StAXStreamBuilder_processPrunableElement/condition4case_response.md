{
    "test_cases": [
        {
            "group": "processPrunableElement - Valid START_ELEMENT",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "factory",
                            "value": "new DefaultJDOMFactory()"
                        },
                        {
                            "parameter": "reader",
                            "value": "mock XMLStreamReader at START_ELEMENT with valid XML"
                        },
                        {
                            "parameter": "topdepth",
                            "value": 0
                        },
                        {
                            "parameter": "filter",
                            "value": "mock StAXFilter that allows elements"
                        }
                    ],
                    "expected": "Element object representing the processed XML",
                    "description": "Test the method with a valid START_ELEMENT state and a filter that allows elements."
                }
            ]
        },
        {
            "group": "processPrunableElement - Invalid START_ELEMENT State",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "factory",
                            "value": "new DefaultJDOMFactory()"
                        },
                        {
                            "parameter": "reader",
                            "value": "mock XMLStreamReader at event type other than START_ELEMENT"
                        },
                        {
                            "parameter": "topdepth",
                            "value": 0
                        },
                        {
                            "parameter": "filter",
                            "value": "mock StAXFilter"
                        }
                    ],
                    "expected": "JDOMException",
                    "description": "Test the method when the XMLStreamReader is not at START_ELEMENT, expecting a JDOMException."
                }
            ]
        },
        {
            "group": "processPrunableElement - Handling of END_ELEMENT",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "factory",
                            "value": "new DefaultJDOMFactory()"
                        },
                        {
                            "parameter": "reader",
                            "value": "mock XMLStreamReader that transitions to END_ELEMENT correctly"
                        },
                        {
                            "parameter": "topdepth",
                            "value": 0
                        },
                        {
                            "parameter": "filter",
                            "value": "mock StAXFilter"
                        }
                    ],
                    "expected": "Element object with correct parent structure",
                    "description": "Test the method's ability to handle END_ELEMENT transitions correctly."
                }
            ]
        },
        {
            "group": "processPrunableElement - Handling of CDATA",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "factory",
                            "value": "new DefaultJDOMFactory()"
                        },
                        {
                            "parameter": "reader",
                            "value": "mock XMLStreamReader that returns CDATA"
                        },
                        {
                            "parameter": "topdepth",
                            "value": 1
                        },
                        {
                            "parameter": "filter",
                            "value": "mock StAXFilter that allows CDATA"
                        }
                    ],
                    "expected": "Element object with CDATA content",
                    "description": "Test the method's ability to process CDATA sections correctly."
                }
            ]
        },
        {
            "group": "processPrunableElement - Handling of SPACE and CHARACTERS",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "factory",
                            "value": "new DefaultJDOMFactory()"
                        },
                        {
                            "parameter": "reader",
                            "value": "mock XMLStreamReader that returns SPACE and CHARACTERS"
                        },
                        {
                            "parameter": "topdepth",
                            "value": 1
                        },
                        {
                            "parameter": "filter",
                            "value": "mock StAXFilter that allows SPACE and CHARACTERS"
                        }
                    ],
                    "expected": "Element object with text content",
                    "description": "Test the method's ability to handle SPACE and CHARACTERS correctly."
                }
            ]
        },
        {
            "group": "processPrunableElement - Handling of COMMENT",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "factory",
                            "value": "new DefaultJDOMFactory()"
                        },
                        {
                            "parameter": "reader",
                            "value": "mock XMLStreamReader that returns COMMENT"
                        },
                        {
                            "parameter": "topdepth",
                            "value": 1
                        },
                        {
                            "parameter": "filter",
                            "value": "mock StAXFilter that allows COMMENT"
                        }
                    ],
                    "expected": "Element object with comment content",
                    "description": "Test the method's ability to process COMMENT sections correctly."
                }
            ]
        },
        {
            "group": "processPrunableElement - Handling of ENTITY_REFERENCE",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "factory",
                            "value": "new DefaultJDOMFactory()"
                        },
                        {
                            "parameter": "reader",
                            "value": "mock XMLStreamReader that returns ENTITY_REFERENCE"
                        },
                        {
                            "parameter": "topdepth",
                            "value": 1
                        },
                        {
                            "parameter": "filter",
                            "value": "mock StAXFilter that allows ENTITY_REFERENCE"
                        }
                    ],
                    "expected": "Element object with entity reference content",
                    "description": "Test the method's ability to handle ENTITY_REFERENCE correctly."
                }
            ]
        },
        {
            "group": "processPrunableElement - Handling of PROCESSING_INSTRUCTION",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "factory",
                            "value": "new DefaultJDOMFactory()"
                        },
                        {
                            "parameter": "reader",
                            "value": "mock XMLStreamReader that returns PROCESSING_INSTRUCTION"
                        },
                        {
                            "parameter": "topdepth",
                            "value": 1
                        },
                        {
                            "parameter": "filter",
                            "value": "mock StAXFilter that allows PROCESSING_INSTRUCTION"
                        }
                    ],
                    "expected": "Element object with processing instruction content",
                    "description": "Test the method's ability to process PROCESSING_INSTRUCTION correctly."
                }
            ]
        },
        {
            "group": "processPrunableElement - Handling of unexpected XMLStream events",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "factory",
                            "value": "new DefaultJDOMFactory()"
                        },
                        {
                            "parameter": "reader",
                            "value": "mock XMLStreamReader that returns unexpected event"
                        },
                        {
                            "parameter": "topdepth",
                            "value": 0
                        },
                        {
                            "parameter": "filter",
                            "value": "mock StAXFilter"
                        }
                    ],
                    "expected": "JDOMException with message 'Unexpected XMLStream event'",
                    "description": "Test the method's behavior when encountering an unexpected XMLStream event."
                }
            ]
        }
    ]
}