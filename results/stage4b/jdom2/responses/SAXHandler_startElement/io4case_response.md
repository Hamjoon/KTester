{
    "testCases": [
        {
            "group": "startElement with empty namespaceURI",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "namespaceURI",
                            "value": ""
                        },
                        {
                            "parameter": "localName",
                            "value": "example"
                        },
                        {
                            "parameter": "qName",
                            "value": "ex:example"
                        },
                        {
                            "parameter": "atts",
                            "value": "null"
                        }
                    ],
                    "expected": "Element with local name 'example' and empty namespace should be created",
                    "description": "Test startElement with an empty namespaceURI. The element should still be created with the specified localName."
                }
            ]
        },
        {
            "group": "startElement with null localName and valid QName",
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
                            "value": "ex:example"
                        },
                        {
                            "parameter": "atts",
                            "value": "null"
                        }
                    ],
                    "expected": "Element with local name 'example' and namespace 'http://example.com' should be created",
                    "description": "Test startElement with null localName and a valid QName. The localName should be derived from QName."
                }
            ]
        },
        {
            "group": "startElement with attributes having empty values",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "namespaceURI",
                            "value": "http://example.com"
                        },
                        {
                            "parameter": "localName",
                            "value": "example"
                        },
                        {
                            "parameter": "qName",
                            "value": "ex:example"
                        },
                        {
                            "parameter": "atts",
                            "value": "mockAttributesWithEmptyValues()"
                        }
                    ],
                    "expected": "Element should have attributes set with empty values",
                    "description": "Test startElement with attributes that have empty values. Ensure the attributes are still added to the element."
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
                            "value": "example"
                        },
                        {
                            "parameter": "qName",
                            "value": "ex:example"
                        },
                        {
                            "parameter": "atts",
                            "value": "mockAttributesWithMultipleValidValues()"
                        }
                    ],
                    "expected": "Element should have multiple attributes set correctly",
                    "description": "Test startElement with multiple valid attributes. Ensure all attributes are added to the element correctly."
                }
            ]
        },
        {
            "group": "startElement with suppress false",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "namespaceURI",
                            "value": "http://example.com"
                        },
                        {
                            "parameter": "localName",
                            "value": "example"
                        },
                        {
                            "parameter": "qName",
                            "value": "ex:example"
                        },
                        {
                            "parameter": "atts",
                            "value": "null"
                        }
                    ],
                    "expected": "Element with local name 'example' and namespace 'http://example.com' should be created",
                    "description": "Test startElement when suppress is false. Ensure that elements are processed and created."
                }
            ]
        },
        {
            "group": "startElement with invalid QName",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "namespaceURI",
                            "value": "http://example.com"
                        },
                        {
                            "parameter": "localName",
                            "value": "example"
                        },
                        {
                            "parameter": "qName",
                            "value": "invalidQName"
                        },
                        {
                            "parameter": "atts",
                            "value": "null"
                        }
                    ],
                    "expected": "Element with local name 'example' and namespace 'http://example.com' should be created despite invalid QName",
                    "description": "Test startElement with an invalid QName format. The element should still be created with the specified localName."
                }
            ]
        }
    ]
}