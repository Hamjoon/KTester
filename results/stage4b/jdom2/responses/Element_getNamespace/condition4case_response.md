{
    "testCases": [
        {
            "group": "getNamespace",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "prefix",
                            "value": null
                        }
                    ],
                    "expected": null,
                    "description": "Test with null prefix, expecting a return value of null."
                },
                {
                    "input": [
                        {
                            "parameter": "prefix",
                            "value": "xml"
                        }
                    ],
                    "expected": "Namespace.XML_NAMESPACE",
                    "description": "Test with prefix 'xml', expecting the XML namespace."
                },
                {
                    "input": [
                        {
                            "parameter": "prefix",
                            "value": "elementPrefix"
                        }
                    ],
                    "expected": "elementNamespace",
                    "description": "Test with a prefix that matches the element's own namespace prefix, expecting the element's namespace."
                },
                {
                    "input": [
                        {
                            "parameter": "prefix",
                            "value": "additionalNamespacePrefix"
                        }
                    ],
                    "expected": "additionalNamespace",
                    "description": "Test with a prefix that matches one of the additional namespaces, expecting the corresponding namespace."
                },
                {
                    "input": [
                        {
                            "parameter": "prefix",
                            "value": "attributeNamespacePrefix"
                        }
                    ],
                    "expected": "attributeNamespace",
                    "description": "Test with a prefix that matches an attribute's namespace prefix, expecting the corresponding namespace."
                },
                {
                    "input": [
                        {
                            "parameter": "prefix",
                            "value": "parentElementPrefix"
                        }
                    ],
                    "expected": "parentNamespace",
                    "description": "Test with a prefix that does not match the current element but matches a parent element's namespace, expecting the parent's namespace."
                },
                {
                    "input": [
                        {
                            "parameter": "prefix",
                            "value": "nonExistentPrefix"
                        }
                    ],
                    "expected": null,
                    "description": "Test with a prefix that does not match any namespace, expecting a return value of null."
                }
            ]
        }
    ]
}