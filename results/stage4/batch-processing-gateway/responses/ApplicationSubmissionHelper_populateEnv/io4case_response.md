{
    "testCases": [
        {
            "group": "Test populateEnv method with both SparkCluster and SubmitApplicationRequest driver environment",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "sparkSpec",
                            "value": {
                                "driver": {
                                    "env": ["VAR1=value1"]
                                },
                                "executor": null
                            }
                        },
                        {
                            "parameter": "request",
                            "value": {
                                "driver": {
                                    "env": ["VAR2=value2"]
                                },
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
                    "expected": null,
                    "description": "Test case where both SparkCluster and SubmitApplicationRequest have driver environments, and sparkSpec driver already exists. The environment should be merged."
                }
            ]
        },
        {
            "group": "Test populateEnv method with empty environments",
            "cases": [
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
                    "expected": null,
                    "description": "Test case where all environments (sparkSpec, request, sparkCluster) are empty. No changes should occur to sparkSpec."
                }
            ]
        },
        {
            "group": "Test populateEnv method with mixed environments",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "sparkSpec",
                            "value": {
                                "driver": {
                                    "env": ["VAR1=value1"]
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
                                "executor": null
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test case where SparkCluster driver has an environment, request executor has an environment, and sparkSpec driver already has an environment. The environments should be merged appropriately."
                }
            ]
        },
        {
            "group": "Test populateEnv method with null values",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "sparkSpec",
                            "value": null
                        },
                        {
                            "parameter": "request",
                            "value": null
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": null
                        }
                    ],
                    "expected": null,
                    "description": "Test case where all parameters are null. The method should handle null values gracefully."
                }
            ]
        }
    ]
}