{
    "result": [
        {
            "group": "processPrunableElement - Handling of Invalid XMLStreamReader",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "factory",
                            "value": "new DefaultJDOMFactory()"
                        },
                        {
                            "parameter": "reader",
                            "value": "mock XMLStreamReader that throws XMLStreamException"
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
                    "expected": "XMLStreamException",
                    "description": "Test the method when the XMLStreamReader throws an XMLStreamException during processing."
                }
            ]
        },
        {
            "group": "processPrunableElement - Handling of Prune Functions Returning Null",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "factory",
                            "value": "new DefaultJDOMFactory()"
                        },
                        {
                            "parameter": "reader",
                            "value": "mock XMLStreamReader that returns START_ELEMENT"
                        },
                        {
                            "parameter": "topdepth",
                            "value": 1
                        },
                        {
                            "parameter": "filter",
                            "value": "mock StAXFilter that returns null for pruneElement"
                        }
                    ],
                    "expected": "Element object with no added content",
                    "description": "Test the method when the filter's pruneElement function returns null, meaning the element is not pruned."
                }
            ]
        },
        {
            "group": "processPrunableElement - Handling of Prune Functions Returning Non-null Values",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "factory",
                            "value": "new DefaultJDOMFactory()"
                        },
                        {
                            "parameter": "reader",
                            "value": "mock XMLStreamReader that returns START_ELEMENT"
                        },
                        {
                            "parameter": "topdepth",
                            "value": 1
                        },
                        {
                            "parameter": "filter",
                            "value": "mock StAXFilter that returns non-null for pruneCDATA"
                        }
                    ],
                    "expected": "Element object with CDATA content",
                    "description": "Test the method when the filter's pruneCDATA function returns a non-null value, meaning CDATA content is added."
                }
            ]
        }
    ]
}