{
    "test cases": [
        {
            "group": "equals method tests",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "size",
                            "value": 3
                        },
                        {
                            "parameter": "key1",
                            "value": "keyA"
                        },
                        {
                            "parameter": "value1",
                            "value": "valueA"
                        },
                        {
                            "parameter": "key2",
                            "value": "keyB"
                        },
                        {
                            "parameter": "value2",
                            "value": "valueB"
                        },
                        {
                            "parameter": "key3",
                            "value": "keyC"
                        },
                        {
                            "parameter": "value3",
                            "value": "valueD"
                        }
                    ],
                    "expected": false,
                    "description": "Test equality with a map containing the same keys as Flat3Map but different value for key3."
                },
                {
                    "input": [
                        {
                            "parameter": "size",
                            "value": 2
                        },
                        {
                            "parameter": "key1",
                            "value": "keyA"
                        },
                        {
                            "parameter": "value1",
                            "value": "valueA"
                        },
                        {
                            "parameter": "key2",
                            "value": "keyB"
                        },
                        {
                            "parameter": "value2",
                            "value": "valueC"
                        }
                    ],
                    "expected": false,
                    "description": "Test equality with a map containing the same keys but different values for key2."
                },
                {
                    "input": [
                        {
                            "parameter": "size",
                            "value": 1
                        },
                        {
                            "parameter": "key1",
                            "value": "keyA"
                        },
                        {
                            "parameter": "value1",
                            "value": null
                        }
                    ],
                    "expected": false,
                    "description": "Test equality with a map containing a null value for the same key."
                },
                {
                    "input": [
                        {
                            "parameter": "size",
                            "value": 3
                        },
                        {
                            "parameter": "key1",
                            "value": "keyA"
                        },
                        {
                            "parameter": "value1",
                            "value": "valueA"
                        },
                        {
                            "parameter": "key2",
                            "value": "keyB"
                        },
                        {
                            "parameter": "value2",
                            "value": "valueB"
                        },
                        {
                            "parameter": "key3",
                            "value": "keyC"
                        },
                        {
                            "parameter": "value3",
                            "value": "valueB"
                        }
                    ],
                    "expected": false,
                    "description": "Test equality with a map where key3 has a different value than expected."
                },
                {
                    "input": [
                        {
                            "parameter": "size",
                            "value": 0
                        }
                    ],
                    "expected": false,
                    "description": "Test equality with a non-empty map when the Flat3Map is empty."
                }
            ]
        }
    ]
}