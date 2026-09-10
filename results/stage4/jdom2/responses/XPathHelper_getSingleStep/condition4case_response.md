{
    "test cases": [
        {
            "group": "getSingleStep with Content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "nsa",
                            "value": "Text instance"
                        },
                        {
                            "parameter": "buffer",
                            "value": "new StringBuilder()"
                        }
                    ],
                    "expected": "StringBuilder containing the position path for text()",
                    "description": "Test case for when nsa is a Text instance. It should return the position path for text nodes."
                },
                {
                    "input": [
                        {
                            "parameter": "nsa",
                            "value": "Comment instance"
                        },
                        {
                            "parameter": "buffer",
                            "value": "new StringBuilder()"
                        }
                    ],
                    "expected": "StringBuilder containing the position path for comment()",
                    "description": "Test case for when nsa is a Comment instance. It should return the position path for comment nodes."
                },
                {
                    "input": [
                        {
                            "parameter": "nsa",
                            "value": "ProcessingInstruction instance"
                        },
                        {
                            "parameter": "buffer",
                            "value": "new StringBuilder()"
                        }
                    ],
                    "expected": "StringBuilder containing the position path for processing-instruction()",
                    "description": "Test case for when nsa is a ProcessingInstruction instance. It should return the position path for processing instructions."
                },
                {
                    "input": [
                        {
                            "parameter": "nsa",
                            "value": "Element instance with NO_NAMESPACE"
                        },
                        {
                            "parameter": "buffer",
                            "value": "new StringBuilder()"
                        }
                    ],
                    "expected": "StringBuilder containing the position path for the element with no namespace",
                    "description": "Test case for when nsa is an Element with NO_NAMESPACE. It should return the position path for the element."
                },
                {
                    "input": [
                        {
                            "parameter": "nsa",
                            "value": "Element instance with a Namespace"
                        },
                        {
                            "parameter": "buffer",
                            "value": "new StringBuilder()"
                        }
                    ],
                    "expected": "StringBuilder containing the position path for the element with its namespace",
                    "description": "Test case for when nsa is an Element with a Namespace. It should return the position path for the element with its namespace."
                },
                {
                    "input": [
                        {
                            "parameter": "nsa",
                            "value": "other Content instance"
                        },
                        {
                            "parameter": "buffer",
                            "value": "new StringBuilder()"
                        }
                    ],
                    "expected": "StringBuilder containing the position path for node()",
                    "description": "Test case for when nsa is another type of Content. It should return the position path for a generic node."
                }
            ]
        },
        {
            "group": "getSingleStep with Attribute",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "nsa",
                            "value": "Attribute instance with NO_NAMESPACE"
                        },
                        {
                            "parameter": "buffer",
                            "value": "new StringBuilder()"
                        }
                    ],
                    "expected": "StringBuilder containing '@attributeName'",
                    "description": "Test case for when nsa is an Attribute with NO_NAMESPACE. The buffer should contain the attribute's name prefixed with '@'."
                },
                {
                    "input": [
                        {
                            "parameter": "nsa",
                            "value": "Attribute instance with a Namespace"
                        },
                        {
                            "parameter": "buffer",
                            "value": "new StringBuilder()"
                        }
                    ],
                    "expected": "StringBuilder containing '@*[local-name() = 'attributeName' and namespace-uri() = 'namespaceURI']'",
                    "description": "Test case for when nsa is an Attribute with a Namespace. The buffer should contain the attribute's name and its namespace URI."
                }
            ]
        },
        {
            "group": "getSingleStep with invalid input",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "nsa",
                            "value": "null"
                        },
                        {
                            "parameter": "buffer",
                            "value": "new StringBuilder()"
                        }
                    ],
                    "expected": "StringBuilder remains unchanged",
                    "description": "Test case for when nsa is null. The buffer should remain unchanged as there is no valid input."
                }
            ]
        }
    ]
}