{
    "testCases": [
        {
            "group": "startElement with suppress true",
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
                    "expected": "no elements should be added",
                    "description": "Test startElement when suppress is true. No elements should be processed."
                }
            ]
        },
        {
            "group": "startElement with valid QName",
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
                    "description": "Test startElement with valid QName and null localName. The localName should be derived from QName."
                }
            ]
        },
        {
            "group": "startElement without QName",
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
                            "value": ""
                        },
                        {
                            "parameter": "atts",
                            "value": "null"
                        }
                    ],
                    "expected": "Element with local name 'example' and namespace 'http://example.com' should be created",
                    "description": "Test startElement with empty QName. The localName should be used as is."
                }
            ]
        },
        {
            "group": "startElement with attributes",
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
                            "value": "mockAttributesWithValidValues()"
                        }
                    ],
                    "expected": "Element should have attributes set correctly",
                    "description": "Test startElement with valid attributes. Ensure attributes are added to the element."
                }
            ]
        },
        {
            "group": "startElement with xmlns attributes",
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
                            "value": "xmlns:ex"
                        },
                        {
                            "parameter": "atts",
                            "value": "mockAttributesWithXmlns()"
                        }
                    ],
                    "expected": "Element should not have xmlns attributes added",
                    "description": "Test startElement with xmlns attributes. Ensure xmlns attributes are ignored."
                }
            ]
        },
        {
            "group": "startElement with default namespace",
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
                            "value": "example"
                        },
                        {
                            "parameter": "atts",
                            "value": "mockAttributesWithDefaultNamespace()"
                        }
                    ],
                    "expected": "Element should have attributes set with a default namespace",
                    "description": "Test startElement with default namespace and attributes. Ensure attributes are added correctly."
                }
            ]
        }
    ]
}