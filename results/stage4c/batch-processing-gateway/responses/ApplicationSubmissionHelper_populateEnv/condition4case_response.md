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
            "expected": {
                "driverEnv": ["VAR3=value3", "VAR1=value1"],
                "executorEnv": ["VAR4=value4", "VAR2=value2"]
            },
            "description": "Test with both driver and executor environments present in request and sparkCluster, verify they are copied to sparkSpec."
        },
        {
            "input": [
                {
                    "parameter": "sparkSpec",
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
                    "parameter": "request",
                    "value": {
                        "driver": null,
                        "executor": null
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
            "expected": {
                "driverEnv": ["VAR3=value3"],
                "executorEnv": ["VAR4=value4"]
            },
            "description": "Test when only sparkCluster has driver and executor environments, verify they are copied to sparkSpec."
        },
        {
            "input": [
                {
                    "parameter": "sparkSpec",
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
                        "executor": null
                    }
                }
            ],
            "expected": {
                "driverEnv": ["VAR1=value1"],
                "executorEnv": ["VAR2=value2"]
            },
            "description": "Test when only request has driver and executor environments, verify they are copied to sparkSpec."
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
            "expected": {
                "driverEnv": ["EXISTING=value", "VAR3=value3", "VAR1=value1"],
                "executorEnv": ["EXISTING=value", "VAR4=value4", "VAR2=value2"]
            },
            "description": "Test when sparkSpec already has existing environments, verify new environments are appended."
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
                        "driver": null,
                        "executor": null
                    }
                },
                {
                    "parameter": "sparkCluster",
                    "value": {
                        "driver": null,
                        "executor": null
                    }
                }
            ],
            "expected": {
                "driverEnv": [],
                "executorEnv": []
            },
            "description": "Test when neither request nor sparkCluster has environments, verify sparkSpec remains unchanged."
        }
    ]
}