{
    "testCases": [
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
                    "expected": "buffer containing the text position path",
                    "description": "Test getSingleStep with a Text instance to verify it generates the correct position path."
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
                    "expected": "buffer containing the comment position path",
                    "description": "Test getSingleStep with a Comment instance to verify it generates the correct position path."
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
                    "expected": "buffer containing the processing-instruction position path",
                    "description": "Test getSingleStep with a ProcessingInstruction instance to verify it generates the correct position path."
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
                    "expected": "buffer containing the element position path for no-namespace",
                    "description": "Test getSingleStep with an Element instance with NO_NAMESPACE to verify it generates the correct position path."
                },
                {
                    "input": [
                        {
                            "parameter": "nsa",
                            "value": "Element instance with Namespace"
                        },
                        {
                            "parameter": "buffer",
                            "value": "new StringBuilder()"
                        }
                    ],
                    "expected": "buffer containing the element position path with namespace",
                    "description": "Test getSingleStep with an Element instance with Namespace to verify it generates the correct position path."
                },
                {
                    "input": [
                        {
                            "parameter": "nsa",
                            "value": "Content instance with no parent"
                        },
                        {
                            "parameter": "buffer",
                            "value": "new StringBuilder()"
                        }
                    ],
                    "expected": "buffer containing the node position path",
                    "description": "Test getSingleStep with a Content instance that has no parent to verify it generates the node position path."
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
                    "expected": "buffer containing the attribute path with no namespace",
                    "description": "Test getSingleStep with an Attribute instance with NO_NAMESPACE to verify it generates the correct attribute path."
                },
                {
                    "input": [
                        {
                            "parameter": "nsa",
                            "value": "Attribute instance with Namespace"
                        },
                        {
                            "parameter": "buffer",
                            "value": "new StringBuilder()"
                        }
                    ],
                    "expected": "buffer containing the attribute path with namespace",
                    "description": "Test getSingleStep with an Attribute instance with Namespace to verify it generates the correct attribute path."
                }
            ]
        },
        {
            "group": "getSingleStep with unsupported types",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "nsa",
                            "value": "Some unsupported type instance"
                        },
                        {
                            "parameter": "buffer",
                            "value": "new StringBuilder()"
                        }
                    ],
                    "expected": "buffer unchanged",
                    "description": "Test getSingleStep with an unsupported type to verify that the buffer remains unchanged."
                }
            ]
        }
    ]
}