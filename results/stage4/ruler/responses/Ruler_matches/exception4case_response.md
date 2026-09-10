{
    "matches": [
        {
            "group": "matches - NUMERIC_RANGE case with invalid CIDR",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "val",
                            "value": "\"invalidCIDR\""
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
                    "description": "Test case for NUMERIC_RANGE with CIDR where the value is an invalid CIDR format."
                }
            ]
        },
        {
            "group": "matches - Unsupported Pattern case",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "val",
                            "value": "\"value\""
                        },
                        {
                            "parameter": "pattern",
                            "value": {
                                "type": "UNKNOWN"
                            }
                        }
                    ],
                    "expected": "RuntimeException",
                    "description": "Test case for unsupported pattern type which should throw a RuntimeException."
                }
            ]
        }
    ]
}