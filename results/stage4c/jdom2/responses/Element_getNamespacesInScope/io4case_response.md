{
    "group": "getNamespacesInScope",
    "cases": [
        {
            "input": [
                {
                    "parameter": "additionalNamespaces",
                    "value": "[Namespace.getNamespace(\"prefix3\", \"uri3\")]"
                },
                {
                    "parameter": "attributes",
                    "value": "[new Attribute(\"attr1\", \"value1\", Namespace.getNamespace(\"prefix3\", \"uri3\"))]"
                }
            ],
            "expected": "List containing XML_NAMESPACE, NO_NAMESPACE, and the additional namespace with the same prefix as the attribute",
            "description": "Test case where additional namespace and attribute namespace have the same prefix but different URIs. The list should include XML_NAMESPACE, NO_NAMESPACE, and the additional namespace."
        },
        {
            "input": [
                {
                    "parameter": "parentElement",
                    "value": "new Element()"
                },
                {
                    "parameter": "attributes",
                    "value": "[new Attribute(\"attr1\", \"value1\", Namespace.getNamespace(\"prefix4\", \"uri4\"))]"
                }
            ],
            "expected": "List containing XML_NAMESPACE, NO_NAMESPACE, the attribute's namespace, and inherited namespaces from parent",
            "description": "Test case where the element has an attribute namespace and a parent. The list should include XML_NAMESPACE, NO_NAMESPACE, the attribute's namespace, and any inherited namespaces from the parent."
        },
        {
            "input": [
                {
                    "parameter": "parentElement",
                    "value": "new Element()"
                },
                {
                    "parameter": "additionalNamespaces",
                    "value": "[Namespace.getNamespace(\"prefix5\", \"uri5\")]"
                },
                {
                    "parameter": "attributes",
                    "value": "[new Attribute(\"attr2\", \"value2\", Namespace.getNamespace(\"prefix6\", \"uri6\"))]"
                }
            ],
            "expected": "List containing XML_NAMESPACE, NO_NAMESPACE, the additional namespace, the attribute's namespace, and inherited namespaces from parent",
            "description": "Test case where the element has an additional namespace, an attribute namespace, and a parent. The list should include XML_NAMESPACE, NO_NAMESPACE, the additional namespace, the attribute's namespace, and any inherited namespaces from the parent."
        },
        {
            "input": [
                {
                    "parameter": "parentElement",
                    "value": "null"
                },
                {
                    "parameter": "additionalNamespaces",
                    "value": "[Namespace.getNamespace(\"prefix7\", \"uri7\")]"
                },
                {
                    "parameter": "attributes",
                    "value": "[new Attribute(\"attr3\", \"value3\", Namespace.getNamespace(\"prefix8\", \"uri8\"))]"
                }
            ],
            "expected": "List containing XML_NAMESPACE, NO_NAMESPACE, the additional namespace, and the attribute's namespace",
            "description": "Test case where the element is the root with an additional namespace and an attribute namespace. The list should include XML_NAMESPACE, NO_NAMESPACE, the additional namespace, and the attribute's namespace."
        }
    ]
}