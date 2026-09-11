{
    "testCases": [
        {
            "group": "getNamespacesInScope",
            "cases": [
                {
                    "input": [],
                    "expected": "List containing the XML namespace and the default namespace if applicable",
                    "description": "Test when the Element has no additional namespaces or attributes, should return a list with only the XML namespace."
                },
                {
                    "input": [
                        {
                            "parameter": "additionalNamespaces",
                            "value": [ "ns1", "ns2" ]
                        }
                    ],
                    "expected": "List containing the XML namespace, ns1, and ns2",
                    "description": "Test when the Element has additional namespaces, should return a list including the XML namespace and the additional namespaces."
                },
                {
                    "input": [
                        {
                            "parameter": "attributes",
                            "value": [ "attr1", "attr2" ]
                        }
                    ],
                    "expected": "List containing the XML namespace and the namespaces of attr1 and attr2",
                    "description": "Test when the Element has attributes with their own namespaces, should return a list including the XML namespace and the namespaces of the attributes."
                },
                {
                    "input": [],
                    "expected": "List containing the XML namespace and the default namespace when the element is a root element",
                    "description": "Test when the Element is a root element and there are no additional namespaces, should return a list with the XML namespace and the default namespace."
                },
                {
                    "input": [
                        {
                            "parameter": "parentElement",
                            "value": "not null"
                        }
                    ],
                    "expected": "List containing the XML namespace and namespaces from the parent element",
                    "description": "Test when the Element has a parent element which has namespaces, should return a list including the XML namespace and the namespaces from the parent."
                },
                {
                    "input": [
                        {
                            "parameter": "additionalNamespaces",
                            "value": [ "ns1" ]
                        },
                        {
                            "parameter": "attributes",
                            "value": [ "attr1" ]
                        },
                        {
                            "parameter": "parentElement",
                            "value": "null"
                        }
                    ],
                    "expected": "List containing the XML namespace and the default namespace",
                    "description": "Test when the Element has additional namespaces and attributes but is not a root element, should return a list with the XML namespace and the default namespace."
                },
                {
                    "input": [
                        {
                            "parameter": "additionalNamespaces",
                            "value": [ "ns1", "ns1" ]
                        }
                    ],
                    "expected": "List containing the XML namespace and ns1 only once",
                    "description": "Test when the Element has duplicate additional namespaces, should return a list with the XML namespace and ns1 only once."
                }
            ]
        }
    ]
}