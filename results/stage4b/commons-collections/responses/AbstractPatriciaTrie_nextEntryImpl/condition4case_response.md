{
    "testCases": [
        {
            "group": "nextEntryImpl - Traversing Left",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "start",
                            "value": "TrieEntry(key='a', value='valueA', bitIndex=0)"
                        },
                        {
                            "parameter": "previous",
                            "value": "null"
                        },
                        {
                            "parameter": "tree",
                            "value": "TrieEntry(key='root', value='valueRoot', bitIndex=-1)"
                        }
                    ],
                    "expected": "TrieEntry(key='b', value='valueB', bitIndex=1)",
                    "description": "Test case where the left child is valid and should be returned."
                },
                {
                    "input": [
                        {
                            "parameter": "start",
                            "value": "TrieEntry(key='a', value='valueA', bitIndex=0)"
                        },
                        {
                            "parameter": "previous",
                            "value": "TrieEntry(key='b', value='valueB', bitIndex=1)"
                        },
                        {
                            "parameter": "tree",
                            "value": "TrieEntry(key='root', value='valueRoot', bitIndex=-1)"
                        }
                    ],
                    "expected": "null",
                    "description": "Test case where the previous entry is the left child and should not be returned."
                }
            ]
        },
        {
            "group": "nextEntryImpl - Handling Empty Node",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "start",
                            "value": "TrieEntry(key='a', value='valueA', bitIndex=0)"
                        },
                        {
                            "parameter": "previous",
                            "value": "TrieEntry(key='b', value='valueB', bitIndex=1)"
                        },
                        {
                            "parameter": "tree",
                            "value": "TrieEntry(key='root', value='valueRoot', bitIndex=-1)"
                        }
                    ],
                    "expected": "null",
                    "description": "Test case where current node is empty, should return null."
                }
            ]
        },
        {
            "group": "nextEntryImpl - Traversing Right",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "start",
                            "value": "TrieEntry(key='a', value='valueA', bitIndex=0)"
                        },
                        {
                            "parameter": "previous",
                            "value": "TrieEntry(key='c', value='valueC', bitIndex=2)"
                        },
                        {
                            "parameter": "tree",
                            "value": "TrieEntry(key='root', value='valueRoot', bitIndex=-1)"
                        }
                    ],
                    "expected": "TrieEntry(key='d', value='valueD', bitIndex=3)",
                    "description": "Test case where the right child is valid and should be returned."
                },
                {
                    "input": [
                        {
                            "parameter": "start",
                            "value": "TrieEntry(key='a', value='valueA', bitIndex=0)"
                        },
                        {
                            "parameter": "previous",
                            "value": "TrieEntry(key='d', value='valueD', bitIndex=3)"
                        },
                        {
                            "parameter": "tree",
                            "value": "TrieEntry(key='root', value='valueRoot', bitIndex=-1)"
                        }
                    ],
                    "expected": "null",
                    "description": "Test case where the previous entry is the right child and should not be returned."
                }
            ]
        },
        {
            "group": "nextEntryImpl - Parent Traversal",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "start",
                            "value": "TrieEntry(key='a', value='valueA', bitIndex=0)"
                        },
                        {
                            "parameter": "previous",
                            "value": "TrieEntry(key='b', value='valueB', bitIndex=1)"
                        },
                        {
                            "parameter": "tree",
                            "value": "TrieEntry(key='root', value='valueRoot', bitIndex=-1)"
                        }
                    ],
                    "expected": "TrieEntry(key='root', value='valueRoot', bitIndex=-1)",
                    "description": "Test case where we traverse up to find a valid parent entry."
                },
                {
                    "input": [
                        {
                            "parameter": "start",
                            "value": "TrieEntry(key='c', value='valueC', bitIndex=2)"
                        },
                        {
                            "parameter": "previous",
                            "value": "TrieEntry(key='root', value='valueRoot', bitIndex=-1)"
                        },
                        {
                            "parameter": "tree",
                            "value": "TrieEntry(key='root', value='valueRoot', bitIndex=-1)"
                        }
                    ],
                    "expected": "null",
                    "description": "Test case where we cannot find a valid entry while traversing up."
                }
            ]
        }
    ]
}