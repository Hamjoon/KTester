{
    "test cases": [
        {
            "group": "matches method - ANYTHING_BUT pattern type",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "val",
                            "value": "\"example\""
                        },
                        {
                            "parameter": "pattern",
                            "value": {
                                "type": "ANYTHING_BUT",
                                "values": ["\"test\"", "\"example\""]
                            }
                        }
                    ],
                    "expected": false,
                    "description": "Test case for ANYTHING_BUT pattern where the value is in the excluded list."
                }
            ]
        },
        {
            "group": "matches method - NUMERIC_RANGE pattern type",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "val",
                            "value": "30"
                        },
                        {
                            "parameter": "pattern",
                            "value": {
                                "type": "NUMERIC_RANGE",
                                "bottom": 20,
                                "top": 30,
                                "isCIDR": false,
                                "openBottom": false,
                                "openTop": true
                            }
                        }
                    ],
                    "expected": true,
                    "description": "Test case for NUMERIC_RANGE pattern where the value is equal to the top boundary but open."
                },
                {
                    "input": [
                        {
                            "parameter": "val",
                            "value": "30"
                        },
                        {
                            "parameter": "pattern",
                            "value": {
                                "type": "NUMERIC_RANGE",
                                "bottom": 20,
                                "top": 30,
                                "isCIDR": false,
                                "openBottom": true,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": false,
                    "description": "Test case for NUMERIC_RANGE pattern where the value is equal to the top boundary but closed."
                }
            ]
        },
        {
            "group": "matches method - ANYTHING_BUT_IGNORE_CASE pattern type",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "val",
                            "value": "\"TEST\""
                        },
                        {
                            "parameter": "pattern",
                            "value": {
                                "type": "ANYTHING_BUT_IGNORE_CASE",
                                "values": ["\"test\"", "\"example\""]
                            }
                        }
                    ],
                    "expected": false,
                    "description": "Test case for ANYTHING_BUT_IGNORE_CASE pattern where the value is in the excluded list ignoring case."
                },
                {
                    "input": [
                        {
                            "parameter": "val",
                            "value": "\"NotExcluded\""
                        },
                        {
                            "parameter": "pattern",
                            "value": {
                                "type": "ANYTHING_BUT_IGNORE_CASE",
                                "values": ["\"test\"", "\"example\""]
                            }
                        }
                    ],
                    "expected": true,
                    "description": "Test case for ANYTHING_BUT_IGNORE_CASE pattern where the value is not in the excluded list ignoring case."
                }
            ]
        },
        {
            "group": "matches method - ANYTHING_BUT_SUFFIX pattern type",
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
                                "type": "ANYTHING_BUT_SUFFIX",
                                "pattern": "\"Value\""
                            }
                        }
                    ],
                    "expected": false,
                    "description": "Test case for ANYTHING_BUT_SUFFIX pattern where the value ends with the excluded suffix."
                },
                {
                    "input": [
                        {
                            "parameter": "val",
                            "value": "\"test\""
                        },
                        {
                            "parameter": "pattern",
                            "value": {
                                "type": "ANYTHING_BUT_SUFFIX",
                                "pattern": "\"Value\""
                            }
                        }
                    ],
                    "expected": true,
                    "description": "Test case for ANYTHING_BUT_SUFFIX pattern where the value does not end with the excluded suffix."
                }
            ]
        }
    ]
}