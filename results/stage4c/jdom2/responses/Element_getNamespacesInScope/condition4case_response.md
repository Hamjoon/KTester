{
    "group": "getNamespacesInScope",
    "cases": [
        {
            "input": [],
            "expected": "List containing XML_NAMESPACE and NO_NAMESPACE",
            "description": "Test case where the element is the root and there are no additional namespaces or attributes. The root element should have the XML_NAMESPACE and NO_NAMESPACE."
        },
        {
            "input": [
                {
                    "parameter": "additionalNamespaces",
                    "value": "[Namespace.getNamespace(\"prefix1\", \"uri1\")]"
                }
            ],
            "expected": "List containing XML_NAMESPACE, NO_NAMESPACE, and the additional namespace",
            "description": "Test case with one additional namespace. The list should include XML_NAMESPACE, NO_NAMESPACE, and the additional namespace."
        },
        {
            "input": [
                {
                    "parameter": "attributes",
                    "value": "[new Attribute(\"attr1\", \"value1\", Namespace.getNamespace(\"prefix2\", \"uri2\"))]"
                }
            ],
            "expected": "List containing XML_NAMESPACE, NO_NAMESPACE, and the attribute's namespace",
            "description": "Test case with one attribute having a namespace. The list should include XML_NAMESPACE, NO_NAMESPACE, and the attribute's namespace."
        },
        {
            "input": [
                {
                    "parameter": "additionalNamespaces",
                    "value": "[Namespace.getNamespace(\"prefix1\", \"uri1\")]"
                },
                {
                    "parameter": "attributes",
                    "value": "[new Attribute(\"attr1\", \"value1\", Namespace.getNamespace(\"prefix2\", \"uri2\"))]"
                }
            ],
            "expected": "List containing XML_NAMESPACE, NO_NAMESPACE, the additional namespace, and the attribute's namespace",
            "description": "Test case with both an additional namespace and an attribute namespace. The list should include XML_NAMESPACE, NO_NAMESPACE, the additional namespace, and the attribute's namespace."
        },
        {
            "input": [
                {
                    "parameter": "parentElement",
                    "value": "new Element()"
                }
            ],
            "expected": "List containing XML_NAMESPACE, NO_NAMESPACE, and inherited namespaces from parent",
            "description": "Test case where the element has a parent. The list should include XML_NAMESPACE, NO_NAMESPACE, and any namespaces inherited from the parent."
        },
        {
            "input": [
                {
                    "parameter": "parentElement",
                    "value": "new Element()"
                },
                {
                    "parameter": "additionalNamespaces",
                    "value": "[Namespace.getNamespace(\"prefix1\", \"uri1\")]"
                }
            ],
            "expected": "List containing XML_NAMESPACE, NO_NAMESPACE, the additional namespace, and inherited namespaces from parent",
            "description": "Test case where the element has both an additional namespace and a parent. The list should include XML_NAMESPACE, NO_NAMESPACE, the additional namespace, and any inherited namespaces from the parent."
        }
    ]
}