{
    "result": [
        {
            "group": "getSingleStep with Content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "nsa",
                            "value": "Content instance with null parent"
                        },
                        {
                            "parameter": "buffer",
                            "value": "new StringBuilder()"
                        }
                    ],
                    "expected": "buffer containing the node position path",
                    "description": "Test getSingleStep with a Content instance that has a null parent to verify it generates the node position path."
                },
                {
                    "input": [
                        {
                            "parameter": "nsa",
                            "value": "Element instance with null namespace"
                        },
                        {
                            "parameter": "buffer",
                            "value": "new StringBuilder()"
                        }
                    ],
                    "expected": "buffer containing the element position path for an element with null namespace",
                    "description": "Test getSingleStep with an Element instance that has null namespace to verify it generates the correct position path."
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
                            "value": "Attribute instance with null namespace"
                        },
                        {
                            "parameter": "buffer",
                            "value": "new StringBuilder()"
                        }
                    ],
                    "expected": "buffer containing the attribute path with null namespace",
                    "description": "Test getSingleStep with an Attribute instance that has null namespace to verify it generates the correct attribute path."
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
                },
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