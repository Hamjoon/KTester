{
    "testCases": [
        {
            "group": "getSingleStep with Content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "nsa",
                            "value": "Element instance with empty children"
                        },
                        {
                            "parameter": "buffer",
                            "value": "new StringBuilder()"
                        }
                    ],
                    "expected": "buffer containing the node position path for an element with no children",
                    "description": "Test getSingleStep with an Element instance that has no children to verify it generates the node position path."
                },
                {
                    "input": [
                        {
                            "parameter": "nsa",
                            "value": "Content instance with multiple siblings"
                        },
                        {
                            "parameter": "buffer",
                            "value": "new StringBuilder()"
                        }
                    ],
                    "expected": "buffer containing the node position path with multiple siblings",
                    "description": "Test getSingleStep with a Content instance that has multiple siblings to verify it generates the correct position path."
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
                            "value": "Attribute instance with special characters in name"
                        },
                        {
                            "parameter": "buffer",
                            "value": "new StringBuilder()"
                        }
                    ],
                    "expected": "buffer containing the attribute path with special characters",
                    "description": "Test getSingleStep with an Attribute instance that has special characters in its name to verify it generates the correct attribute path."
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
                            "value": "Null instance"
                        },
                        {
                            "parameter": "buffer",
                            "value": "new StringBuilder()"
                        }
                    ],
                    "expected": "buffer unchanged",
                    "description": "Test getSingleStep with a null input for nsa to verify that the buffer remains unchanged."
                }
            ]
        }
    ]
}