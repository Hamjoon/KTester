{
    "output": [
        {
            "group": "matches method - NUMERIC_RANGE pattern type",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "val",
                            "value": "invalid_number"
                        },
                        {
                            "parameter": "pattern",
                            "value": {
                                "type": "NUMERIC_RANGE",
                                "bottom": 20,
                                "top": 30,
                                "isCIDR": false,
                                "openBottom": false,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": false,
                    "description": "Test case for NUMERIC_RANGE pattern where the value is not a valid number, should return false."
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
                            "value": "25"
                        },
                        {
                            "parameter": "pattern",
                            "value": {
                                "type": "NUMERIC_RANGE",
                                "bottom": "invalid_bottom",
                                "top": 30,
                                "isCIDR": false,
                                "openBottom": false,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": false,
                    "description": "Test case for NUMERIC_RANGE pattern where the bottom boundary is not a valid number, should return false."
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
                            "value": "25"
                        },
                        {
                            "parameter": "pattern",
                            "value": {
                                "type": "NUMERIC_RANGE",
                                "bottom": 20,
                                "top": "invalid_top",
                                "isCIDR": false,
                                "openBottom": false,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": false,
                    "description": "Test case for NUMERIC_RANGE pattern where the top boundary is not a valid number, should return false."
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
                            "value": "25"
                        },
                        {
                            "parameter": "pattern",
                            "value": {
                                "type": "NUMERIC_RANGE",
                                "bottom": 20,
                                "top": 30,
                                "isCIDR": true,
                                "openBottom": false,
                                "openTop": false
                            }
                        }
                    ],
                    "expected": false,
                    "description": "Test case for NUMERIC_RANGE pattern where isCIDR is true but the value is not a valid CIDR, should return false."
                }
            ]
        },
        {
            "group": "matches method - Unsupported Pattern type",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "val",
                            "value": "\"test\""
                        },
                        {
                            "parameter": "pattern",
                            "value": {
                                "type": "UNKNOWN_PATTERN_TYPE"
                            }
                        }
                    ],
                    "expected": "RuntimeException",
                    "description": "Test case for unsupported pattern type, should throw RuntimeException."
                }
            ]
        }
    ]
}