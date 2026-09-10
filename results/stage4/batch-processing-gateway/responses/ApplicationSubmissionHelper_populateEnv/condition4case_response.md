{
    "testCases": [
        {
            "group": "Test populateEnv method with SparkCluster driver environment",
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
                                "driver": null,
                                "executor": null
                            }
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": {
                                "driver": {
                                    "env": ["VAR1=value1", "VAR2=value2"]
                                },
                                "executor": null
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test case where SparkCluster has a driver environment, and sparkSpec initially has no driver. The driver environment should be copied to sparkSpec."
                },
                {
                    "input": [
                        {
                            "parameter": "sparkSpec",
                            "value": {
                                "driver": {
                                    "env": []
                                },
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
                                "driver": {
                                    "env": ["VAR1=value1"]
                                },
                                "executor": null
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test case where SparkCluster has a driver environment, and sparkSpec driver already exists but is empty. The environment should be populated."
                }
            ]
        },
        {
            "group": "Test populateEnv method with SubmitApplicationRequest driver environment",
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
                                    "env": ["VAR3=value3"]
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
                    "description": "Test case where SubmitApplicationRequest has a driver environment, and sparkSpec initially has no driver. The driver environment should be copied to sparkSpec."
                },
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
                    "description": "Test case where SubmitApplicationRequest has a driver environment, and sparkSpec driver already exists. The environment should be merged."
                }
            ]
        },
        {
            "group": "Test populateEnv method with SparkCluster executor environment",
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
                                "driver": null,
                                "executor": null
                            }
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": {
                                "driver": null,
                                "executor": {
                                    "env": ["VAR4=value4"]
                                }
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test case where SparkCluster has an executor environment, and sparkSpec initially has no executor. The executor environment should be copied to sparkSpec."
                },
                {
                    "input": [
                        {
                            "parameter": "sparkSpec",
                            "value": {
                                "driver": null,
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
                                "driver": null,
                                "executor": {
                                    "env": ["VAR5=value5"]
                                }
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test case where SparkCluster has an executor environment, and sparkSpec executor already exists but is empty. The environment should be populated."
                }
            ]
        },
        {
            "group": "Test populateEnv method with SubmitApplicationRequest executor environment",
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
                                "driver": null,
                                "executor": {
                                    "env": ["VAR6=value6"]
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
                    "expected": null,
                    "description": "Test case where SubmitApplicationRequest has an executor environment, and sparkSpec initially has no executor. The executor environment should be copied to sparkSpec."
                },
                {
                    "input": [
                        {
                            "parameter": "sparkSpec",
                            "value": {
                                "driver": null,
                                "executor": {
                                    "env": ["VAR1=value1"]
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
                                "driver": null,
                                "executor": null
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test case where SubmitApplicationRequest has an executor environment, and sparkSpec executor already exists. The environment should be merged."
                }
            ]
        }
    ]
}