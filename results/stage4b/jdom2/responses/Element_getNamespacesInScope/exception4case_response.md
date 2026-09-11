{
    "group": "getNamespacesInScope",
    "cases": [
        {
            "input": [
                {
                    "parameter": "additionalNamespaces",
                    "value": []
                },
                {
                    "parameter": "attributes",
                    "value": []
                },
                {
                    "parameter": "parentElement",
                    "value": null
                }
            ],
            "expected": "List containing the XML namespace and the default namespace",
            "description": "Test when the Element has no additional namespaces or attributes and is a root element, should return a list with the XML namespace and the default namespace."
        },
        {
            "input": [
                {
                    "parameter": "additionalNamespaces",
                    "value": []
                },
                {
                    "parameter": "attributes",
                    "value": []
                },
                {
                    "parameter": "parentElement",
                    "value": "not null"
                }
            ],
            "expected": "List containing the XML namespace and namespaces from the parent element",
            "description": "Test when the Element has no additional namespaces or attributes but has a parent element, should return a list with the XML namespace and the namespaces from the parent."
        },
        {
            "input": [
                {
                    "parameter": "additionalNamespaces",
                    "value": null
                },
                {
                    "parameter": "attributes",
                    "value": null
                },
                {
                    "parameter": "parentElement",
                    "value": null
                }
            ],
            "expected": "List containing only the XML namespace",
            "description": "Test when the Element has no additional namespaces, attributes, or parent element, should return a list containing only the XML namespace."
        }
    ]
}