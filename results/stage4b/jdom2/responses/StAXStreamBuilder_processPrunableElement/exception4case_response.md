{
    "output": [
        {
            "group": "processPrunableElement - Depth Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "factory",
                            "value": "DefaultJDOMFactory instance"
                        },
                        {
                            "parameter": "reader",
                            "value": "XMLStreamReader at START_ELEMENT with deeply nested elements"
                        },
                        {
                            "parameter": "topdepth",
                            "value": 0
                        },
                        {
                            "parameter": "filter",
                            "value": "StAXFilter that allows all elements"
                        }
                    ],
                    "expected": "Element with deep nested structure",
                    "description": "Test case to verify that elements are processed correctly when there are deeply nested elements."
                },
                {
                    "input": [
                        {
                            "parameter": "factory",
                            "value": "DefaultJDOMFactory instance"
                        },
                        {
                            "parameter": "reader",
                            "value": "XMLStreamReader at START_ELEMENT with elements causing depth to exceed limits"
                        },
                        {
                            "parameter": "topdepth",
                            "value": 0
                        },
                        {
                            "parameter": "filter",
                            "value": "StAXFilter that allows all elements"
                        }
                    ],
                    "expected": "JDOMException",
                    "description": "Test case to verify that a JDOMException is thrown when the depth exceeds the expected limits."
                }
            ]
        },
        {
            "group": "processPrunableElement - Filter Behavior",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "factory",
                            "value": "DefaultJDOMFactory instance"
                        },
                        {
                            "parameter": "reader",
                            "value": "XMLStreamReader at START_ELEMENT with elements to prune"
                        },
                        {
                            "parameter": "topdepth",
                            "value": 0
                        },
                        {
                            "parameter": "filter",
                            "value": "StAXFilter that prunes all elements"
                        }
                    ],
                    "expected": "Empty Element",
                    "description": "Test case to verify that when all elements are pruned by the filter, the result is an empty element."
                }
            ]
        }
    ]
}