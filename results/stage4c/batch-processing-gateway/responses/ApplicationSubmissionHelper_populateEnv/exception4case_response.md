{
    "group": "populateEnv",
    "cases": [
        {
            "input": [
                {
                    "parameter": "sparkSpec",
                    "value": null
                },
                {
                    "parameter": "request",
                    "value": {
                        "driver": {
                            "env": ["VAR1=value1"]
                        },
                        "executor": {
                            "env": ["VAR2=value2"]
                        }
                    }
                },
                {
                    "parameter": "sparkCluster",
                    "value": {
                        "driver": {
                            "env": ["VAR3=value3"]
                        },
                        "executor": {
                            "env": ["VAR4=value4"]
                        }
                    }
                }
            ],
            "expected": "NullPointerException",
            "description": "Test when sparkSpec is null, expecting a NullPointerException due to method attempting to access its properties."
        },
        {
            "input": [
                {
                    "parameter": "sparkSpec",
                    "value": {
                        "driver": null,
                        "executor": null
                    }
                },
                {
                    "parameter": "request",
                    "value": null
                },
                {
                    "parameter": "sparkCluster",
                    "value": {
                        "driver": {
                            "env": ["VAR3=value3"]
                        },
                        "executor": {
                            "env": ["VAR4=value4"]
                        }
                    }
                }
            ],
            "expected": "NullPointerException",
            "description": "Test when request is null, expecting a NullPointerException due to method attempting to access its properties."
        },
        {
            "input": [
                {
                    "parameter": "sparkSpec",
                    "value": {
                        "driver": null,
                        "executor": null
                    }
                },
                {
                    "parameter": "request",
                    "value": {
                        "driver": {
                            "env": ["VAR1=value1"]
                        },
                        "executor": {
                            "env": ["VAR2=value2"]
                        }
                    }
                },
                {
                    "parameter": "sparkCluster",
                    "value": null
                }
            ],
            "expected": "NullPointerException",
            "description": "Test when sparkCluster is null, expecting a NullPointerException due to method attempting to access its properties."
        }
    ]
}