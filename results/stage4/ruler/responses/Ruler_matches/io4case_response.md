{
    "test_cases": [
        {
            "group": "matches - ANYTHING_BUT_SUFFIX case",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "val",
                            "value": "\"testSuffix\""
                        },
                        {
                            "parameter": "pattern",
                            "value": {
                                "type": "ANYTHING_BUT_SUFFIX",
                                "pattern": "\"Suffix\""
                            }
                        }
                    ],
                    "expected": false,
                    "description": "Test case for ANYTHING_BUT_SUFFIX pattern where the value ends with the suffix pattern."
                },
                {
                    "input": [
                        {
                            "parameter": "val",
                            "value": "\"testValue\""
                        },
                        {
                            "parameter": "pattern",
                            "value": {
                                "type": "ANYTHING_BUT_SUFFIX",
                                "pattern": "\"Suffix\""
                            }
                        }
                    ],
                    "expected": true,
                    "description": "Test case for ANYTHING_BUT_SUFFIX pattern where the value does not end with the suffix pattern."
                }
            ]
        },
        {
            "group": "matches - ANYTHING_BUT_PREFIX case",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "val",
                            "value": "\"testValue\""
                        },
                        {
                            "parameter": "pattern",
                            "value": {
                                "type": "ANYTHING_BUT_PREFIX",
                                "pattern": "\"test\""
                            }
                        }
                    ],
                    "expected": false,
                    "description": "Test case for ANYTHING_BUT_PREFIX pattern where the value starts with the prefix pattern."
                },
                {
                    "input": [
                        {
                            "parameter": "val",
                            "value": "\"exampleValue\""
                        },
                        {
                            "parameter": "pattern",
                            "value": {
                                "type": "ANYTHING_BUT_PREFIX",
                                "pattern": "\"test\""
                            }
                        }
                    ],
                    "expected": true,
                    "description": "Test case for ANYTHING_BUT_PREFIX pattern where the value does not start with the prefix pattern."
                }
            ]
        },
        {
            "group": "matches - NUMERIC_RANGE case with open boundaries",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "val",
                            "value": 1
                        },
                        {
                            "parameter": "pattern",
                            "value": {
                                "type": "NUMERIC_RANGE",
                                "bottom": 1,
                                "top": 10,
                                "openBottom": true,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": false,
                    "description": "Test case for NUMERIC_RANGE where the value is equal to the open bottom boundary."
                },
                {
                    "input": [
                        {
                            "parameter": "val",
                            "value": 10
                        },
                        {
                            "parameter": "pattern",
                            "value": {
                                "type": "NUMERIC_RANGE",
                                "bottom": 1,
                                "top": 10,
                                "openBottom": false,
                                "openTop": true
                            }
                        }
                    ],
                    "expected": false,
                    "description": "Test case for NUMERIC_RANGE where the value is equal to the open top boundary."
                }
            ]
        },
        {
            "group": "matches - CIDR case",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "val",
                            "value": "\"192.168.1.1\""
                        },
                        {
                            "parameter": "pattern",
                            "value": {
                                "type": "NUMERIC_RANGE",
                                "isCIDR": true,
                                "bottom": "192.168.1.0",
                                "top": "192.168.1.255",
                                "openBottom": false,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": true,
                    "description": "Test case for NUMERIC_RANGE with CIDR where the value is within the CIDR range."
                },
                {
                    "input": [
                        {
                            "parameter": "val",
                            "value": "\"192.169.1.1\""
                        },
                        {
                            "parameter": "pattern",
                            "value": {
                                "type": "NUMERIC_RANGE",
                                "isCIDR": true,
                                "bottom": "192.168.1.0",
                                "top": "192.168.1.255",
                                "openBottom": false,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": false,
                    "description": "Test case for NUMERIC_RANGE with CIDR where the value is outside the CIDR range."
                }
            ]
        }
    ]
}