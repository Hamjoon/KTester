{
    "testCases": [
        {
            "group": "getExecutorSpec - Executor not null",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "request",
                            "value": {
                                "executor": {
                                    "cores": 2,
                                    "memory": "4Gi",
                                    "labels": {
                                        "dagName": "testDag",
                                        "taskName": "testTask"
                                    },
                                    "volumeMounts": []
                                },
                                "volumes": []
                            }
                        },
                        {
                            "parameter": "appConfig",
                            "value": {}
                        },
                        {
                            "parameter": "parentQueue",
                            "value": "parentQueue"
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": {}
                        }
                    ],
                    "expected": {
                        "cores": 2,
                        "memory": "4Gi",
                        "labels": {
                            "dagName": "testDag",
                            "taskName": "testTask"
                        }
                    },
                    "description": "Test case where executor is provided in the request."
                }
            ]
        },
        {
            "group": "getExecutorSpec - Executor null",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "request",
                            "value": {
                                "executor": null,
                                "volumes": []
                            }
                        },
                        {
                            "parameter": "appConfig",
                            "value": {}
                        },
                        {
                            "parameter": "parentQueue",
                            "value": "parentQueue"
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": {}
                        }
                    ],
                    "expected": {
                        "cores": 0,
                        "memory": null,
                        "labels": {}
                    },
                    "description": "Test case where executor is null in the request."
                }
            ]
        },
        {
            "group": "getExecutorSpec - Volumes specified in request",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "request",
                            "value": {
                                "executor": {
                                    "volumeMounts": []
                                },
                                "volumes": [ "volume1", "volume2" ]
                            }
                        },
                        {
                            "parameter": "appConfig",
                            "value": {}
                        },
                        {
                            "parameter": "parentQueue",
                            "value": "parentQueue"
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": {
                                "executor": {
                                    "volumeMounts": []
                                },
                                "volumes": []
                            }
                        }
                    ],
                    "expected": {
                        "volumeMounts": []
                    },
                    "description": "Test case where volumes are specified in the request."
                }
            ]
        },
        {
            "group": "getExecutorSpec - Volumes specified in SparkCluster",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "request",
                            "value": {
                                "executor": null,
                                "volumes": []
                            }
                        },
                        {
                            "parameter": "appConfig",
                            "value": {}
                        },
                        {
                            "parameter": "parentQueue",
                            "value": "parentQueue"
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": {
                                "executor": {
                                    "volumeMounts": []
                                },
                                "volumes": [ "volume3" ]
                            }
                        }
                    ],
                    "expected": {
                        "volumeMounts": []
                    },
                    "description": "Test case where volumes are specified in SparkCluster."
                }
            ]
        },
        {
            "group": "getExecutorSpec - Core limits and memory adjustments",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "request",
                            "value": {
                                "executor": {
                                    "cores": 3,
                                    "memory": "2Gi"
                                }
                            }
                        },
                        {
                            "parameter": "appConfig",
                            "value": {}
                        },
                        {
                            "parameter": "parentQueue",
                            "value": "parentQueue"
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": {}
                        }
                    ],
                    "expected": {
                        "cores": 3,
                        "coreLimit": "3m",
                        "memory": "2Gi"
                    },
                    "description": "Test case to verify core limits and memory adjustments."
                }
            ]
        },
        {
            "group": "getExecutorSpec - Affinity settings",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "request",
                            "value": {
                                "executor": {
                                    "spotInstance": true
                                }
                            }
                        },
                        {
                            "parameter": "appConfig",
                            "value": {}
                        },
                        {
                            "parameter": "parentQueue",
                            "value": "parentQueue"
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": {}
                        }
                    ],
                    "expected": {
                        "affinity": {
                            "requiredDuringSchedulingIgnoredDuringExecution": {}
                        }
                    },
                    "description": "Test case to verify affinity settings when spot instances are requested."
                }
            ]
        },
        {
            "group": "getExecutorSpec - DNS Config",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "request",
                            "value": {
                                "executor": null
                            }
                        },
                        {
                            "parameter": "appConfig",
                            "value": {}
                        },
                        {
                            "parameter": "parentQueue",
                            "value": "parentQueue"
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": {}
                        }
                    ],
                    "expected": {
                        "dnsConfig": {
                            "options": [
                                {
                                    "name": "ndots",
                                    "value": "5"
                                }
                            ]
                        }
                    },
                    "description": "Test case to verify DNS configuration is set correctly."
                }
            ]
        }
    ]
}