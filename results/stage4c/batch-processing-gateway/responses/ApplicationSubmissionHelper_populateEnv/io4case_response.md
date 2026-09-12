{
    "group": "populateEnv",
    "cases": [
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
                            "env": []
                        },
                        "executor": {
                            "env": []
                        }
                    }
                },
                {
                    "parameter": "sparkCluster",
                    "value": {
                        "driver": {
                            "env": []
                        },
                        "executor": {
                            "env": []
                        }
                    }
                }
            ],
            "expected": {
                "driverEnv": [],
                "executorEnv": []
            },
            "description": "Test when request and sparkCluster have empty environment lists, verify sparkSpec remains unchanged."
        },
        {
            "input": [
                {
                    "parameter": "sparkSpec",
                    "value": {
                        "driver": {
                            "env": ["EXISTING=value"]
                        },
                        "executor": {
                            "env": []
                        }
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
                    "value": {
                        "driver": null,
                        "executor": {
                            "env": ["VAR3=value3"]
                        }
                    }
                }
            ],
            "expected": {
                "driverEnv": ["EXISTING=value", "VAR1=value1"],
                "executorEnv": ["VAR2=value2", "VAR3=value3"]
            },
            "description": "Test when sparkSpec driver has existing environments, and request and sparkCluster provide additional environments."
        },
        {
            "input": [
                {
                    "parameter": "sparkSpec",
                    "value": {
                        "driver": {
                            "env": ["EXISTING=value"]
                        },
                        "executor": {
                            "env": ["EXISTING=value"]
                        }
                    }
                },
                {
                    "parameter": "request",
                    "value": {
                        "driver": {
                            "env": []
                        },
                        "executor": {
                            "env": []
                        }
                    }
                },
                {
                    "parameter": "sparkCluster",
                    "value": {
                        "driver": {
                            "env": []
                        },
                        "executor": {
                            "env": []
                        }
                    }
                }
            ],
            "expected": {
                "driverEnv": ["EXISTING=value"],
                "executorEnv": ["EXISTING=value"]
            },
            "description": "Test when sparkSpec has existing environments and request and sparkCluster have empty environments, verify sparkSpec remains unchanged."
        }
    ]
}