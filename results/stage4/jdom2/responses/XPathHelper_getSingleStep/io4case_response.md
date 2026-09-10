{
    "testCases": [
        {
            "group": "getSingleStep with Content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "nsa",
                            "value": "Element instance with NO_NAMESPACE and no siblings"
                        },
                        {
                            "parameter": "buffer",
                            "value": "new StringBuilder()"
                        }
                    ],
                    "expected": "StringBuilder containing the position path for node()",
                    "description": "Test case for when nsa is an Element with NO_NAMESPACE and no siblings. It should return the position path for a generic node."
                },
                {
                    "input": [
                        {
                            "parameter": "nsa",
                            "value": "Element instance with a Namespace and no siblings"
                        },
                        {
                            "parameter": "buffer",
                            "value": "new StringBuilder()"
                        }
                    ],
                    "expected": "StringBuilder containing the position path for node()",
                    "description": "Test case for when nsa is an Element with a Namespace and no siblings. It should return the position path for a generic node."
                },
                {
                    "input": [
                        {
                            "parameter": "nsa",
                            "value": "Text instance with a parent that has no siblings"
                        },
                        {
                            "parameter": "buffer",
                            "value": "new StringBuilder()"
                        }
                    ],
                    "expected": "StringBuilder containing the position path for text()",
                    "description": "Test case for when nsa is a Text instance with a parent that has no siblings. It should return the position path for text nodes."
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
                            "value": "Attribute instance with empty name"
                        },
                        {
                            "parameter": "buffer",
                            "value": "new StringBuilder()"
                        }
                    ],
                    "expected": "StringBuilder containing '@'",
                    "description": "Test case for when nsa is an Attribute with an empty name. The buffer should contain just '@'."
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
                            "value": "String instead of NamespaceAware"
                        },
                        {
                            "parameter": "buffer",
                            "value": "new StringBuilder()"
                        }
                    ],
                    "expected": "StringBuilder remains unchanged",
                    "description": "Test case for when nsa is a String instead of a valid NamespaceAware instance. The buffer should remain unchanged as there is no valid input."
                }
            ]
        }
    ]
}