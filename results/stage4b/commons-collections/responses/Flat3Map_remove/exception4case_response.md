{
    "result": [
        {
            "group": "remove with valid keys when size is 3 - key3 not null",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key3"
                        }
                    ],
                    "expected": "value3",
                    "description": "Test removing key3 when size is 3, should return value3."
                }
            ]
        },
        {
            "group": "remove with valid keys when size is 3 - key2 not null",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key2"
                        }
                    ],
                    "expected": "value2",
                    "description": "Test removing key2 when size is 3, should return value2."
                }
            ]
        },
        {
            "group": "remove with valid keys when size is 3 - key1 not null",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key1"
                        }
                    ],
                    "expected": "value1",
                    "description": "Test removing key1 when size is 3, should return value1."
                }
            ]
        },
        {
            "group": "remove with valid keys when size is 2 - key2 not null",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key2"
                        }
                    ],
                    "expected": "value2",
                    "description": "Test removing key2 when size is 2, should return value2."
                }
            ]
        },
        {
            "group": "remove with valid keys when size is 2 - key1 not null",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key1"
                        }
                    ],
                    "expected": "value1",
                    "description": "Test removing key1 when size is 2, should return value1."
                }
            ]
        },
        {
            "group": "remove with valid key when size is 1 - valid key",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key1"
                        }
                    ],
                    "expected": "value1",
                    "description": "Test removing key1 when size is 1, should return value1."
                }
            ]
        },
        {
            "group": "remove non-existing key when size is 2",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "nonExistingKey"
                        }
                    ],
                    "expected": "null",
                    "description": "Test removing a non-existing key when size is 2, should return null."
                }
            ]
        },
        {
            "group": "remove non-existing key when size is 3",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "nonExistingKey"
                        }
                    ],
                    "expected": "null",
                    "description": "Test removing a non-existing key when size is 3, should return null."
                }
            ]
        }
    ]
}