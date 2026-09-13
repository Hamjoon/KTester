{
    "group": "startElement Additional Tests",
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
                    "value": ":element"
                },
                {
                    "parameter": "atts",
                    "value": "mockAttributes()"
                }
            ],
            "expected": "Element created with empty prefix and correct local name",
            "description": "Test when qName starts with a colon, indicating an empty prefix."
        },
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
                    "value": "prefix:"
                },
                {
                    "parameter": "atts",
                    "value": "mockAttributes()"
                }
            ],
            "expected": "Element created with prefix and empty local name",
            "description": "Test when qName ends with a colon, indicating an empty local name."
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
                    "value": "mockAttributesWithEmptyURI()"
                }
            ],
            "expected": "Attribute with empty URI handled",
            "description": "Test when attribute has an empty URI, ensuring it is processed without errors."
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
                    "value": "mockAttributesWithEmptyValue()"
                }
            ],
            "expected": "Attribute with empty value handled",
            "description": "Test when attribute has an empty value, ensuring it is processed without errors."
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
                    "value": "mockAttributesWithMultipleNamespaces()"
                }
            ],
            "expected": "Multiple namespaces handled correctly",
            "description": "Test when attributes belong to multiple namespaces, ensuring correct namespace assignment."
        }
    ]
}