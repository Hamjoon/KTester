{
    "testCases": [
        {
            "group": "Test containsKey with empty Flat3Map",
            "cases": [
                {
                    "input": [],
                    "expected": false,
                    "description": "Test when the Flat3Map is empty (size is 0)."
                }
            ]
        },
        {
            "group": "Test containsKey with size 1 and null key",
            "cases": [
                {
                    "input": [],
                    "expected": true,
                    "description": "Test when size is 1 and key1 is null."
                }
            ]
        },
        {
            "group": "Test containsKey with size 2 and non-null keys",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key1"
                        }
                    ],
                    "expected": true,
                    "description": "Test when size is 2, hash1 matches and key1 equals the provided key."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key2"
                        }
                    ],
                    "expected": true,
                    "description": "Test when size is 2, hash2 matches and key2 equals the provided key."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key1"
                        }
                    ],
                    "expected": false,
                    "description": "Test when size is 2, hash1 does not match or key1 does not equal the provided key."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key2"
                        }
                    ],
                    "expected": false,
                    "description": "Test when size is 2, hash2 does not match or key2 does not equal the provided key."
                }
            ]
        },
        {
            "group": "Test containsKey with size 3 and non-null keys",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key1"
                        }
                    ],
                    "expected": true,
                    "description": "Test when size is 3, hash1 matches and key1 equals the provided key."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key2"
                        }
                    ],
                    "expected": true,
                    "description": "Test when size is 3, hash2 matches and key2 equals the provided key."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key3"
                        }
                    ],
                    "expected": true,
                    "description": "Test when size is 3, hash3 matches and key3 equals the provided key."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key1"
                        }
                    ],
                    "expected": false,
                    "description": "Test when size is 3, hash1 does not match or key1 does not equal the provided key."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key2"
                        }
                    ],
                    "expected": false,
                    "description": "Test when size is 3, hash2 does not match or key2 does not equal the provided key."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key3"
                        }
                    ],
                    "expected": false,
                    "description": "Test when size is 3, hash3 does not match or key3 does not equal the provided key."
                }
            ]
        }
    ]
}