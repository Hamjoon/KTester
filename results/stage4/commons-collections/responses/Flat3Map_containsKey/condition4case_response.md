{
    "test cases": [
        {
            "group": "containsKey - Delegate Map Present",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "someKey"
                        }
                    ],
                    "expected": true,
                    "description": "Test if the method returns true when the delegateMap contains the key."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "nonExistentKey"
                        }
                    ],
                    "expected": false,
                    "description": "Test if the method returns false when the delegateMap does not contain the key."
                }
            ]
        },
        {
            "group": "containsKey - Null Key Handling",
            "cases": [
                {
                    "input": [],
                    "expected": true,
                    "description": "Test if the method returns true when the key is null and key3 is also null."
                },
                {
                    "input": [],
                    "expected": true,
                    "description": "Test if the method returns true when the key is null and key2 is null."
                },
                {
                    "input": [],
                    "expected": true,
                    "description": "Test if the method returns true when the key is null and key1 is null."
                },
                {
                    "input": [],
                    "expected": false,
                    "description": "Test if the method returns false when the key is null and all keys are non-null."
                }
            ]
        },
        {
            "group": "containsKey - Non-Null Key Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key1"
                        }
                    ],
                    "expected": true,
                    "description": "Test if the method returns true when the key matches key1."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key2"
                        }
                    ],
                    "expected": true,
                    "description": "Test if the method returns true when the key matches key2."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "key3"
                        }
                    ],
                    "expected": true,
                    "description": "Test if the method returns true when the key matches key3."
                },
                {
                    "input": [
                        {
                            "parameter": "key",
                            "value": "differentKey"
                        }
                    ],
                    "expected": false,
                    "description": "Test if the method returns false when the key does not match any of the keys."
                }
            ]
        }
    ]
}