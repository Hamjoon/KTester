{
    "test cases": [
        {
            "group": "getSparkConf - Default Spark Configuration",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "submissionId",
                            "value": "test-submission-id"
                        },
                        {
                            "parameter": "request",
                            "value": {
                                "sparkConf": null,
                                "applicationName": "TestApp"
                            }
                        },
                        {
                            "parameter": "appConfig",
                            "value": {
                                "defaultSparkConf": {
                                    "key1": "value1",
                                    "key2": "value2"
                                },
                                "fixedSparkConf": null
                            }
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": {
                                "sparkConf": null
                            }
                        }
                    ],
                    "expected": {
                        "key1": "value1",
                        "key2": "value2",
                        "spark.app.name": "TestApp"
                    },
                    "description": "Test case where defaultSparkConf is provided, and applicationName is set in request."
                }
            ]
        },
        {
            "group": "getSparkConf - Spark Cluster Configuration",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "submissionId",
                            "value": "test-submission-id"
                        },
                        {
                            "parameter": "request",
                            "value": {
                                "sparkConf": null,
                                "applicationName": null
                            }
                        },
                        {
                            "parameter": "appConfig",
                            "value": {
                                "defaultSparkConf": null,
                                "fixedSparkConf": null
                            }
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": {
                                "sparkConf": {
                                    "key3": "value3"
                                }
                            }
                        }
                    ],
                    "expected": {
                        "key3": "value3"
                    },
                    "description": "Test case where only sparkCluster's sparkConf is provided."
                }
            ]
        },
        {
            "group": "getSparkConf - Request Spark Configuration",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "submissionId",
                            "value": "test-submission-id"
                        },
                        {
                            "parameter": "request",
                            "value": {
                                "sparkConf": {
                                    "key4": "value4"
                                },
                                "applicationName": null
                            }
                        },
                        {
                            "parameter": "appConfig",
                            "value": {
                                "defaultSparkConf": null,
                                "fixedSparkConf": null
                            }
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": {
                                "sparkConf": null
                            }
                        }
                    ],
                    "expected": {
                        "key4": "value4"
                    },
                    "description": "Test case where only request's sparkConf is provided."
                }
            ]
        },
        {
            "group": "getSparkConf - Fixed Spark Configuration",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "submissionId",
                            "value": "test-submission-id"
                        },
                        {
                            "parameter": "request",
                            "value": {
                                "sparkConf": null,
                                "applicationName": null
                            }
                        },
                        {
                            "parameter": "appConfig",
                            "value": {
                                "defaultSparkConf": null,
                                "fixedSparkConf": {
                                    "key5": "value5"
                                }
                            }
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": {
                                "sparkConf": null
                            }
                        }
                    ],
                    "expected": {
                        "key5": "value5"
                    },
                    "description": "Test case where fixedSparkConf is provided."
                }
            ]
        },
        {
            "group": "getSparkConf - Empty Configurations",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "submissionId",
                            "value": "test-submission-id"
                        },
                        {
                            "parameter": "request",
                            "value": {
                                "sparkConf": null,
                                "applicationName": null
                            }
                        },
                        {
                            "parameter": "appConfig",
                            "value": {
                                "defaultSparkConf": null,
                                "fixedSparkConf": null
                            }
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": {
                                "sparkConf": null
                            }
                        }
                    ],
                    "expected": {},
                    "description": "Test case where all configurations are empty, expecting an empty map."
                }
            ]
        },
        {
            "group": "getSparkConf - Multiple Configurations",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "submissionId",
                            "value": "test-submission-id"
                        },
                        {
                            "parameter": "request",
                            "value": {
                                "sparkConf": {
                                    "key6": "value6"
                                },
                                "applicationName": "TestApp2"
                            }
                        },
                        {
                            "parameter": "appConfig",
                            "value": {
                                "defaultSparkConf": {
                                    "key7": "value7"
                                },
                                "fixedSparkConf": {
                                    "key8": "value8"
                                }
                            }
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": {
                                "sparkConf": {
                                    "key9": "value9"
                                }
                            }
                        }
                    ],
                    "expected": {
                        "key6": "value6",
                        "key7": "value7",
                        "key8": "value8",
                        "key9": "value9",
                        "spark.app.name": "TestApp2"
                    },
                    "description": "Test case where all types of configurations are provided, expecting a merged map."
                }
            ]
        }
    ]
}