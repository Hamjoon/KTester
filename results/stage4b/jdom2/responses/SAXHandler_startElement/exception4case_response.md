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
                    "expected": "Element with local name 'example' and no namespace should be created",
                    "description": "Test startElement with null namespaceURI. The element should still be created with the specified localName."
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
                            "value": "ex:example"
                        },
                        {
                            "parameter": "atts",
                            "value": "null"
                        }
                    ],
                    "expected": "Element with local name 'example' and namespace 'http://example.com' should be created",
                    "description": "Test startElement with an empty localName. The localName should be derived from QName."
                }
            ]
        },
        {
            "group": "startElement with attributes having null values",
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
                            "value": "mockAttributesWithNullValues()"
                        }
                    ],
                    "expected": "Element should have attributes set with null values",
                    "description": "Test startElement with attributes that have null values. Ensure the attributes are still added to the element."
                }
            ]
        },
        {
            "group": "startElement with invalid attribute QName",
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
                            "value": "mockAttributesWithInvalidQName()"
                        }
                    ],
                    "expected": "Element should be created with attributes despite invalid attribute QName",
                    "description": "Test startElement with attributes having invalid QName format. Ensure the element is created and attributes are processed."
                }
            ]
        }
    ]
}