{
    "group": "copyFrom - Exception Handling",
    "cases": [
        {
            "input": [
                {
                    "parameter": "sparkApplicationResource",
                    "value": {
                        "metadata": {
                            "name": "app9",
                            "labels": {
                                "proxyUser": "user7",
                                "queue": "default"
                            }
                        },
                        "spec": {
                            "driver": {
                                "labels": {
                                    "dagName": "DAG6"
                                },
                                "memory": "invalid_memory_format",
                                "cores": 2
                            },
                            "executor": {
                                "cores": 2,
                                "instances": 3,
                                "memory": "4g"
                            },
                            "sparkVersion": "3.0.0",
                            "arguments": ["arg1", "arg2"],
                            "sparkConf": {
                                "spark.app.name": "MyApp"
                            }
                        }
                    }
                }
            ],
            "expected": null,
            "description": "Test the copyFrom method with invalid driver memory format to check exception handling."
        },
        {
            "input": [
                {
                    "parameter": "sparkApplicationResource",
                    "value": {
                        "metadata": {
                            "name": "app10",
                            "labels": {
                                "proxyUser": "user8",
                                "queue": "default"
                            }
                        },
                        "spec": {
                            "driver": {
                                "labels": {
                                    "dagName": "DAG7"
                                },
                                "memory": null,
                                "cores": 2
                            },
                            "executor": {
                                "cores": 2,
                                "instances": 3,
                                "memory": "4g"
                            },
                            "sparkVersion": "3.0.0",
                            "arguments": ["arg1", "arg2"],
                            "sparkConf": {
                                "spark.app.name": "MyApp"
                            }
                        }
                    }
                }
            ],
            "expected": null,
            "description": "Test the copyFrom method when driver memory is null to verify handling of missing values."
        },
        {
            "input": [
                {
                    "parameter": "sparkApplicationResource",
                    "value": {
                        "metadata": {
                            "name": "app11",
                            "labels": {
                                "proxyUser": "user9",
                                "queue": "default"
                            }
                        },
                        "spec": {
                            "driver": {
                                "labels": {
                                    "dagName": "DAG8"
                                },
                                "memory": "2g",
                                "cores": null
                            },
                            "executor": {
                                "cores": null,
                                "instances": 3,
                                "memory": "4g"
                            },
                            "sparkVersion": "3.0.0",
                            "arguments": ["arg1", "arg2"],
                            "sparkConf": {
                                "spark.app.name": "MyApp"
                            }
                        }
                    }
                }
            ],
            "expected": null,
            "description": "Test the copyFrom method when driver cores are null to verify handling of missing values."
        },
        {
            "input": [
                {
                    "parameter": "sparkApplicationResource",
                    "value": {
                        "metadata": {
                            "name": "app12",
                            "labels": {
                                "proxyUser": "user10",
                                "queue": "default"
                            }
                        },
                        "spec": {
                            "driver": {
                                "labels": {
                                    "dagName": "DAG9"
                                },
                                "memory": "2g",
                                "cores": 2
                            },
                            "executor": {
                                "cores": 2,
                                "instances": null,
                                "memory": "4g"
                            },
                            "sparkVersion": "3.0.0",
                            "arguments": ["arg1", "arg2"],
                            "sparkConf": {
                                "spark.app.name": "MyApp"
                            }
                        }
                    }
                }
            ],
            "expected": null,
            "description": "Test the copyFrom method when executor instances are null to verify handling of missing values."
        },
        {
            "input": [
                {
                    "parameter": "sparkApplicationResource",
                    "value": {
                        "metadata": {
                            "name": "app13",
                            "labels": {
                                "proxyUser": "user11",
                                "queue": "default"
                            }
                        },
                        "spec": {
                            "driver": {
                                "labels": {
                                    "dagName": "DAG10"
                                },
                                "memory": "2g",
                                "cores": 2
                            },
                            "executor": {
                                "cores": 2,
                                "instances": 3,
                                "memory": null
                            },
                            "sparkVersion": "3.0.0",
                            "arguments": ["arg1", "arg2"],
                            "sparkConf": {
                                "spark.app.name": "MyApp"
                            }
                        }
                    }
                }
            ],
            "expected": null,
            "description": "Test the copyFrom method when executor memory is null to verify handling of missing values."
        }
    ]
}