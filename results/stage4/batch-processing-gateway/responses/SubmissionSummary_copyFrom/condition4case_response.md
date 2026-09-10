{
    "testCases": [
        {
            "group": "copyFrom with valid SparkApplication",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "sparkApplicationResource",
                            "value": {
                                "metadata": {
                                    "name": "app1",
                                    "labels": {
                                        "PROXY_USER_LABEL": "user1",
                                        "QUEUE_LABEL": "queue1",
                                        "DAG_NAME_LABEL": "dag1"
                                    }
                                },
                                "spec": {
                                    "driver": {
                                        "labels": {
                                            "DAG_NAME_LABEL": "dag1"
                                        },
                                        "memory": "2g",
                                        "cores": 2
                                    },
                                    "executor": {
                                        "cores": 2,
                                        "instances": 3,
                                        "memory": "4g"
                                    }
                                }
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test copyFrom with a valid SparkApplication that has all fields set. Verify that all properties are set correctly."
                }
            ]
        },
        {
            "group": "copyFrom with missing labels",
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
                                        "memory": "1g",
                                        "cores": 1
                                    },
                                    "executor": {
                                        "cores": 1,
                                        "instances": 1,
                                        "memory": "1g"
                                    }
                                }
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test copyFrom with a SparkApplication that has null labels. Verify that user, queue, and dagName are not set."
                }
            ]
        },
        {
            "group": "copyFrom with invalid driver memory",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "sparkApplicationResource",
                            "value": {
                                "metadata": {
                                    "name": "app3",
                                    "labels": {
                                        "PROXY_USER_LABEL": "user3"
                                    }
                                },
                                "spec": {
                                    "driver": {
                                        "memory": "invalid_memory",
                                        "cores": 1
                                    },
                                    "executor": {
                                        "cores": 1,
                                        "instances": 1,
                                        "memory": "1g"
                                    }
                                }
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test copyFrom with an invalid driver memory string. Verify that the driver memory is set to 0 and an appropriate log message is generated."
                }
            ]
        },
        {
            "group": "copyFrom with missing SparkApplication spec",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "sparkApplicationResource",
                            "value": {
                                "metadata": {
                                    "name": "app4",
                                    "labels": {
                                        "PROXY_USER_LABEL": "user4"
                                    }
                                },
                                "spec": null
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test copyFrom with a SparkApplication that has a null spec. Verify that no properties related to spec are set."
                }
            ]
        },
        {
            "group": "copyFrom with missing executor fields",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "sparkApplicationResource",
                            "value": {
                                "metadata": {
                                    "name": "app5",
                                    "labels": {
                                        "PROXY_USER_LABEL": "user5"
                                    }
                                },
                                "spec": {
                                    "driver": {
                                        "memory": "2g",
                                        "cores": 1
                                    },
                                    "executor": {
                                        "cores": null,
                                        "instances": null,
                                        "memory": null
                                    }
                                }
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test copyFrom with a SparkApplication that has null fields in executor. Verify that default values are used for executor fields."
                }
            ]
        },
        {
            "group": "copyFrom with valid app name config",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "sparkApplicationResource",
                            "value": {
                                "metadata": {
                                    "name": "app6",
                                    "labels": {
                                        "PROXY_USER_LABEL": "user6"
                                    }
                                },
                                "spec": {
                                    "driver": {
                                        "memory": "1g",
                                        "cores": 1
                                    },
                                    "executor": {
                                        "cores": 1,
                                        "instances": 1,
                                        "memory": "1g"
                                    },
                                    "sparkConf": {
                                        "SPARK_APP_NAME_CONFIG": "ApplicationName"
                                    }
                                }
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test copyFrom with a valid app name in SparkConf. Verify that the application name is set correctly."
                }
            ]
        }
    ]
}