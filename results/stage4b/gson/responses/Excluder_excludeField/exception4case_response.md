{
    "output": [
        {
            "group": "excludeField - Version Check with Invalid Version",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "field",
                            "value": "Field with Since annotation and Until annotation that are not valid"
                        },
                        {
                            "parameter": "serialize",
                            "value": "true"
                        }
                    ],
                    "expected": "True",
                    "description": "Test case where the field has both Since and Until annotations that do not validate against the Excluder's version. The method should return true."
                }
            ]
        },
        {
            "group": "excludeField - Serialization Strategies Check",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "field",
                            "value": "Field that matches serialization strategy"
                        },
                        {
                            "parameter": "serialize",
                            "value": "true"
                        }
                    ],
                    "expected": "True",
                    "description": "Test case where the field matches a serialization exclusion strategy. The method should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "field",
                            "value": "Field that does not match any serialization strategy"
                        },
                        {
                            "parameter": "serialize",
                            "value": "true"
                        }
                    ],
                    "expected": "False",
                    "description": "Test case where the field does not match any serialization exclusion strategy. The method should return false."
                }
            ]
        },
        {
            "group": "excludeField - Deserialization Strategies Check",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "field",
                            "value": "Field that matches deserialization strategy"
                        },
                        {
                            "parameter": "serialize",
                            "value": "false"
                        }
                    ],
                    "expected": "True",
                    "description": "Test case where the field matches a deserialization exclusion strategy. The method should return true."
                },
                {
                    "input": [
                        {
                            "parameter": "field",
                            "value": "Field that does not match any deserialization strategy"
                        },
                        {
                            "parameter": "serialize",
                            "value": "false"
                        }
                    ],
                    "expected": "False",
                    "description": "Test case where the field does not match any deserialization exclusion strategy. The method should return false."
                }
            ]
        }
    ]
}