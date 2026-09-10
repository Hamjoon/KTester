{
    "testCases": [
        {
            "group": "startElement with null namespaceURI",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "namespaceURI",
                            "value": null
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
                    "expected": "Element instance created with null namespace",
                    "description": "Test the scenario where namespaceURI is null, ensuring that the element is created correctly with a null namespace."
                }
            ]
        },
        {
            "group": "startElement with empty qName",
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
                            "value": ""
                        },
                        {
                            "parameter": "atts",
                            "value": "mocked Attributes instance"
                        }
                    ],
                    "expected": "Element instance created with localName as 'element'",
                    "description": "Test the scenario where qName is empty, ensuring the element is created with the provided localName."
                }
            ]
        },
        {
            "group": "startElement with attributes containing empty values",
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
                            "value": "mocked Attributes instance with empty values"
                        }
                    ],
                    "expected": "Element instance created with attributes set to empty values",
                    "description": "Test the scenario where attributes contain empty values, ensuring that they are handled correctly."
                }
            ]
        },
        {
            "group": "startElement with multiple attributes",
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
                            "value": "mocked Attributes instance with multiple attributes"
                        }
                    ],
                    "expected": "Element instance created with all attributes set correctly",
                    "description": "Test the scenario where multiple attributes are provided, ensuring they are all set on the element."
                }
            ]
        },
        {
            "group": "startElement with invalid QName format",
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
                            "value": "invalidQNameFormat"
                        },
                        {
                            "parameter": "atts",
                            "value": "mocked Attributes instance"
                        }
                    ],
                    "expected": "Element instance created with localName as 'element'",
                    "description": "Test the scenario where qName does not follow the expected format, ensuring that the localName is still set correctly."
                }
            ]
        },
        {
            "group": "startElement with suppress flag set to false",
            "cases": [
                {
                    "input": [],
                    "expected": "Method processes normally",
                    "description": "Verify that the method processes normally when the suppress flag is false."
                }
            ]
        }
    ]
}