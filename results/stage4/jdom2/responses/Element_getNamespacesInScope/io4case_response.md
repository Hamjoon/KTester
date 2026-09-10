{
    "test cases": [
        {
            "group": "getNamespacesInScope",
            "cases": [
                {
                    "input": [],
                    "expected": "List containing the XML namespace and the default namespace",
                    "description": "Test case for an Element without additional namespaces and no attributes, ensuring it returns the XML and default namespaces."
                },
                {
                    "input": [
                        {
                            "parameter": "namespace",
                            "value": "Namespace with prefix 'ns1'"
                        }
                    ],
                    "expected": "List containing the XML namespace and the additional namespace 'ns1'",
                    "description": "Test case for an Element with one additional namespace, ensuring it returns the XML namespace and the additional namespace."
                },
                {
                    "input": [
                        {
                            "parameter": "attributes",
                            "value": "Attribute with namespace 'ns2'"
                        }
                    ],
                    "expected": "List containing the XML namespace, additional namespace 'ns1', and attribute's namespace 'ns2'",
                    "description": "Test case for an Element with one attribute that has a namespace, ensuring it returns the XML namespace, additional namespace, and attribute's namespace."
                },
                {
                    "input": [
                        {
                            "parameter": "parentElement",
                            "value": "Parent Element with additional namespace 'ns3'"
                        }
                    ],
                    "expected": "List containing the XML namespace, additional namespace 'ns1', attribute's namespace 'ns2', and parent namespace 'ns3'",
                    "description": "Test case for an Element that has a parent Element with an additional namespace, ensuring it returns all namespaces from the child and parent."
                },
                {
                    "input": [],
                    "expected": "List containing the XML namespace and the NO_NAMESPACE",
                    "description": "Test case for a root Element with no default namespace, ensuring it returns the XML namespace and NO_NAMESPACE."
                },
                {
                    "input": [
                        {
                            "parameter": "additionalNamespaces",
                            "value": "Two additional namespaces: 'ns1' and 'ns2'"
                        }
                    ],
                    "expected": "List containing the XML namespace, the additional namespaces 'ns1' and 'ns2'",
                    "description": "Test case for an Element with multiple additional namespaces, ensuring it returns the XML namespace along with all additional namespaces."
                },
                {
                    "input": [
                        {
                            "parameter": "attributes",
                            "value": "Two attributes with different namespaces: 'attr1' in 'ns1' and 'attr2' in 'ns2'"
                        }
                    ],
                    "expected": "List containing the XML namespace, additional namespaces 'ns1', 'ns2', and attribute namespaces from 'attr1' and 'attr2'",
                    "description": "Test case for an Element with multiple attributes each having different namespaces, ensuring it returns all relevant namespaces."
                },
                {
                    "input": [
                        {
                            "parameter": "parentElement",
                            "value": "Parent Element with no additional namespaces"
                        }
                    ],
                    "expected": "List containing the XML namespace and the default namespace, as the parent has no additional namespaces",
                    "description": "Test case for an Element that has a parent Element with no additional namespaces, ensuring it returns the XML namespace and default namespace."
                },
                {
                    "input": [
                        {
                            "parameter": "parentElement",
                            "value": "Parent Element with conflicting namespace prefix 'ns1'"
                        }
                    ],
                    "expected": "List containing the XML namespace and the additional namespace 'ns1' from the child, ignoring the parent's 'ns1'",
                    "description": "Test case for an Element that has a parent Element with a conflicting namespace prefix, ensuring it returns the child's namespace."
                }
            ]
        }
    ]
}