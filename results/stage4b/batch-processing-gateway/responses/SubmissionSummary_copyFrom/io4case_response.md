{
    "testCases": [
        {
            "group": "copyFrom - Valid Input with All Fields",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "sparkApplicationResource",
                            "value": {
                                "metadata": {
                                    "name": "app5",
                                    "labels": {
                                        "proxyUser": "user3",
                                        "queue": "high-priority"
                                    }
                                },
                                "spec": {
                                    "driver": {
                                        "labels": {
                                            "dagName": "DAG3"
                                        },
                                        "memory": "3g",
                                        "cores": 4
                                    },
                                    "executor": {
                                        "cores": 4,
                                        "instances": 2,
                                        "memory": "5g"
                                    },
                                    "sparkVersion": "3.1.0",
                                    "arguments": ["arg3", "arg4"],
                                    "sparkConf": {
                                        "spark.app.name": "AnotherApp"
                                    }
                                }
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test the copyFrom method with valid SparkApplication input including all fields."
                }
            ]
        },
        {
            "group": "copyFrom - Edge Case for Memory and Cores",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "sparkApplicationResource",
                            "value": {
                                "metadata": {
                                    "name": "app6",
                                    "labels": {
                                        "proxyUser": "user4",
                                        "queue": "default"
                                    }
                                },
                                "spec": {
                                    "driver": {
                                        "labels": {
                                            "dagName": "DAG4"
                                        },
                                        "memory": "0g",
                                        "cores": 0
                                    },
                                    "executor": {
                                        "cores": 0,
                                        "instances": 0,
                                        "memory": "0g"
                                    },
                                    "sparkVersion": "3.1.0",
                                    "arguments": [],
                                    "sparkConf": {
                                        "spark.app.name": "ZeroApp"
                                    }
                                }
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test the copyFrom method with zero values for memory and cores for driver and executor."
                }
            ]
        },
        {
            "group": "copyFrom - Handling Missing DAG Name",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "sparkApplicationResource",
                            "value": {
                                "metadata": {
                                    "name": "app7",
                                    "labels": {
                                        "proxyUser": "user5",
                                        "queue": "default"
                                    }
                                },
                                "spec": {
                                    "driver": {
                                        "labels": {
                                            "dagName": null
                                        },
                                        "memory": "2g",
                                        "cores": 2
                                    },
                                    "executor": {
                                        "cores": 2,
                                        "instances": 3,
                                        "memory": "4g"
                                    },
                                    "sparkVersion": "3.1.0",
                                    "arguments": ["arg5"],
                                    "sparkConf": {
                                        "spark.app.name": "AppWithNoDAG"
                                    }
                                }
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test the copyFrom method with a null DAG name to verify handling of missing labels."
                }
            ]
        },
        {
            "group": "copyFrom - Handling Large Input Values",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "sparkApplicationResource",
                            "value": {
                                "metadata": {
                                    "name": "app8",
                                    "labels": {
                                        "proxyUser": "user6",
                                        "queue": "default"
                                    }
                                },
                                "spec": {
                                    "driver": {
                                        "labels": {
                                            "dagName": "DAG5"
                                        },
                                        "memory": "100g",
                                        "cores": 32
                                    },
                                    "executor": {
                                        "cores": 32,
                                        "instances": 10,
                                        "memory": "64g"
                                    },
                                    "sparkVersion": "3.1.0",
                                    "arguments": ["arg6", "arg7"],
                                    "sparkConf": {
                                        "spark.app.name": "LargeResourceApp"
                                    }
                                }
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test the copyFrom method with large values for memory and cores to verify system handling."
                }
            ]
        }
    ]
}