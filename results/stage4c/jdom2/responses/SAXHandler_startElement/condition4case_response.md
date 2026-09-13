{
    "group": "startElement Method Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "namespaceURI",
                    "value": ""
                },
                {
                    "parameter": "localName",
                    "value": ""
                },
                {
                    "parameter": "qName",
                    "value": ""
                },
                {
                    "parameter": "atts",
                    "value": "mockAttributes()"
                }
            ],
            "expected": "No action since suppress is true",
            "description": "Test when suppress is true, the method should return immediately without processing."
        },
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
                    "value": "mockAttributes()"
                }
            ],
            "expected": "Element created with prefix and local name derived from qName",
            "description": "Test when qName is provided, prefix and local name should be derived from qName."
        },
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
                    "value": "mockAttributes()"
                }
            ],
            "expected": "Element created with provided local name and namespace",
            "description": "Test when qName is empty, element should be created with provided local name and namespace."
        },
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
                    "value": "mockAttributesWithXMLNS()"
                }
            ],
            "expected": "XMLNS attributes are ignored",
            "description": "Test when attributes contain XMLNS, they should be ignored."
        },
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
                    "value": "mockAttributesWithSpecified()"
                }
            ],
            "expected": "Attributes are processed correctly with specified flag",
            "description": "Test when attributes have specified flag, they should be processed accordingly."
        },
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
                    "value": "mockAttributesWithNamespace()"
                }
            ],
            "expected": "Correct namespace prefix is assigned to attributes",
            "description": "Test when attributes have a namespace URI but no prefix, the correct prefix should be assigned."
        },
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
                    "value": "mockAttributesWithDefaultNamespace()"
                }
            ],
            "expected": "Default prefix is assigned when no existing prefix matches the namespace",
            "description": "Test when no existing prefix matches the attribute namespace, a default prefix should be assigned."
        }
    ]
}