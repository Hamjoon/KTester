{
    "testCases": [
        {
            "group": "getSparkConf - Default Spark Configuration",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "submissionId",
                            "value": "sub-123"
                        },
                        {
                            "parameter": "request",
                            "value": {
                                "sparkConf": {
                                    "key1": "value1"
                                },
                                "applicationName": "TestApp"
                            }
                        },
                        {
                            "parameter": "appConfig",
                            "value": {
                                "defaultSparkConf": {
                                    "key2": "value2"
                                },
                                "fixedSparkConf": {}
                            }
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": {
                                "sparkConf": {}
                            }
                        }
                    ],
                    "expected": {
                        "key1": "value1",
                        "key2": "value2",
                        "spark.app.name": "TestApp"
                    },
                    "description": "Test case where defaultSparkConf is provided, request has sparkConf and applicationName."
                },
                {
                    "input": [
                        {
                            "parameter": "submissionId",
                            "value": "sub-456"
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
                                    "key3": "value3"
                                }
                            }
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": {
                                "sparkConf": {}
                            }
                        }
                    ],
                    "expected": {
                        "key3": "value3"
                    },
                    "description": "Test case where only fixedSparkConf is provided."
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
                            "value": "sub-789"
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
                                "defaultSparkConf": {},
                                "fixedSparkConf": {}
                            }
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": {
                                "sparkConf": {
                                    "key4": "value4"
                                }
                            }
                        }
                    ],
                    "expected": {
                        "key4": "value4"
                    },
                    "description": "Test case where only sparkCluster has sparkConf."
                },
                {
                    "input": [
                        {
                            "parameter": "submissionId",
                            "value": "sub-101"
                        },
                        {
                            "parameter": "request",
                            "value": {
                                "sparkConf": {
                                    "key5": "value5"
                                },
                                "applicationName": "AnotherTestApp"
                            }
                        },
                        {
                            "parameter": "appConfig",
                            "value": {
                                "defaultSparkConf": null,
                                "fixedSparkConf": {}
                            }
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": {
                                "sparkConf": {
                                    "key6": "value6"
                                }
                            }
                        }
                    ],
                    "expected": {
                        "key5": "value5",
                        "key6": "value6",
                        "spark.app.name": "AnotherTestApp"
                    },
                    "description": "Test case where request has sparkConf, sparkCluster has sparkConf and applicationName is provided."
                }
            ]
        },
        {
            "group": "getSparkConf - Edge Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "submissionId",
                            "value": "sub-202"
                        },
                        {
                            "parameter": "request",
                            "value": {
                                "sparkConf": null,
                                "applicationName": ""
                            }
                        },
                        {
                            "parameter": "appConfig",
                            "value": {
                                "defaultSparkConf": {},
                                "fixedSparkConf": {}
                            }
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": {
                                "sparkConf": {}
                            }
                        }
                    ],
                    "expected": {},
                    "description": "Test case where all configurations are empty or null."
                },
                {
                    "input": [
                        {
                            "parameter": "submissionId",
                            "value": "sub-303"
                        },
                        {
                            "parameter": "request",
                            "value": {
                                "sparkConf": {
                                    "key7": "value7"
                                },
                                "applicationName": null
                            }
                        },
                        {
                            "parameter": "appConfig",
                            "value": {
                                "defaultSparkConf": null,
                                "fixedSparkConf": {
                                    "key8": "value8"
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
                        "key7": "value7",
                        "key8": "value8"
                    },
                    "description": "Test case where request has sparkConf, fixedSparkConf is provided, but sparkCluster has no configurations."
                }
            ]
        }
    ]
}