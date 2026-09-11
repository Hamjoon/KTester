{
    "testCases": [
        {
            "group": "copyFrom - Basic Functionality",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "sparkApplicationResource",
                            "value": {
                                "metadata": {
                                    "name": "app1",
                                    "labels": {
                                        "proxyUser": "user1",
                                        "queue": "default"
                                    }
                                },
                                "spec": {
                                    "driver": {
                                        "labels": {
                                            "dagName": "DAG1"
                                        },
                                        "memory": "2g",
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
                    "description": "Test the copyFrom method with complete and valid SparkApplication input."
                }
            ]
        },
        {
            "group": "copyFrom - Null Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "sparkApplicationResource",
                            "value": {
                                "metadata": {
                                    "name": "app2",
                                    "labels": null
                                },
                                "spec": {
                                    "driver": {
                                        "labels": null,
                                        "memory": null,
                                        "cores": null
                                    },
                                    "executor": {
                                        "cores": null,
                                        "instances": null,
                                        "memory": null
                                    },
                                    "sparkVersion": null,
                                    "arguments": null,
                                    "sparkConf": null
                                }
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test the copyFrom method when labels and all spec fields are null."
                }
            ]
        },
        {
            "group": "copyFrom - Exception Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "sparkApplicationResource",
                            "value": {
                                "metadata": {
                                    "name": "app3",
                                    "labels": {
                                        "proxyUser": "user2",
                                        "queue": "default"
                                    }
                                },
                                "spec": {
                                    "driver": {
                                        "labels": {
                                            "dagName": "DAG2"
                                        },
                                        "memory": "invalid_memory",
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
                }
            ]
        },
        {
            "group": "copyFrom - Conditional Branches",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "sparkApplicationResource",
                            "value": {
                                "metadata": {
                                    "name": "app4",
                                    "labels": {
                                        "proxyUser": null,
                                        "queue": null
                                    }
                                },
                                "spec": {
                                    "driver": {
                                        "labels": {
                                            "dagName": null
                                        },
                                        "memory": "1g",
                                        "cores": 1
                                    },
                                    "executor": {
                                        "cores": 1,
                                        "instances": 1,
                                        "memory": "1g"
                                    },
                                    "sparkVersion": null,
                                    "arguments": null,
                                    "sparkConf": {
                                        "spark.app.name": null
                                    }
                                }
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test the copyFrom method with null user, queue, dagName, and app name to verify conditional branches."
                }
            ]
        }
    ]
}