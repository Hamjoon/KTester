{
    "testCases": [
        {
            "group": "startElement with suppress flag set to true",
            "cases": [
                {
                    "input": [],
                    "expected": null,
                    "description": "Verify that the method returns immediately without doing anything when the suppress flag is true."
                }
            ]
        },
        {
            "group": "startElement with valid QName and localName",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "namespaceURI",
                            "value": "http://example.com"
                        },
                        {
                            "parameter": "localName",
                            "value": "element"
                        },
                        {
                            "parameter": "qName",
                            "value": "prefix:element"
                        },
                        {
                            "parameter": "atts",
                            "value": "mocked Attributes instance"
                        }
                    ],
                    "expected": "Element instance created and set as currentElement",
                    "description": "Test the scenario where a valid QName and localName are provided, ensuring that the currentElement is set correctly."
                }
            ]
        },
        {
            "group": "startElement with empty localName",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "namespaceURI",
                            "value": "http://example.com"
                        },
                        {
                            "parameter": "localName",
                            "value": ""
                        },
                        {
                            "parameter": "qName",
                            "value": "prefix:element"
                        },
                        {
                            "parameter": "atts",
                            "value": "mocked Attributes instance"
                        }
                    ],
                    "expected": "Element instance created with localName derived from qName",
                    "description": "Test the scenario where localName is empty, ensuring it is derived from the QName correctly."
                }
            ]
        },
        {
            "group": "startElement with no QName",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "namespaceURI",
                            "value": "http://example.com"
                        },
                        {
                            "parameter": "localName",
                            "value": null
                        },
                        {
                            "parameter": "qName",
                            "value": ""
                        },
                        {
                            "parameter": "atts",
                            "value": "mocked Attributes instance"
                        }
                    ],
                    "expected": "Element instance created with localName as default",
                    "description": "Test the scenario where both localName and QName are not provided, ensuring the method handles this case appropriately."
                }
            ]
        },
        {
            "group": "startElement with declared namespaces",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "namespaceURI",
                            "value": "http://example.com"
                        },
                        {
                            "parameter": "localName",
                            "value": "element"
                        },
                        {
                            "parameter": "qName",
                            "value": "prefix:element"
                        },
                        {
                            "parameter": "atts",
                            "value": "mocked Attributes instance with xmlns attributes"
                        }
                    ],
                    "expected": "Element instance created without xmlns attributes",
                    "description": "Test the scenario where declared namespaces are present, ensuring that xmlns attributes are skipped."
                }
            ]
        },
        {
            "group": "startElement at root",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "namespaceURI",
                            "value": "http://example.com"
                        },
                        {
                            "parameter": "localName",
                            "value": "rootElement"
                        },
                        {
                            "parameter": "qName",
                            "value": "prefix:rootElement"
                        },
                        {
                            "parameter": "atts",
                            "value": "mocked Attributes instance"
                        }
                    ],
                    "expected": "Root element set in the current document",
                    "description": "Test the scenario where the element is at the root level, ensuring it is set as the root of the current document."
                }
            ]
        },
        {
            "group": "startElement not at root",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "namespaceURI",
                            "value": "http://example.com"
                        },
                        {
                            "parameter": "localName",
                            "value": "childElement"
                        },
                        {
                            "parameter": "qName",
                            "value": "prefix:childElement"
                        },
                        {
                            "parameter": "atts",
                            "value": "mocked Attributes instance"
                        }
                    ],
                    "expected": "Child element added to the current element",
                    "description": "Test the scenario where the element is not at the root level, ensuring it is added as a child to the current element."
                }
            ]
        }
    ]
}