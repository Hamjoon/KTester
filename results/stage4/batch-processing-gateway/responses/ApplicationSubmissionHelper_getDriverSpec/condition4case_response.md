{
    "test cases": [
        {
            "group": "getDriverSpec - Driver is not null",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "request",
                            "value": {
                                "driver": {
                                    "labels": {
                                        "DAG_NAME_LABEL": "testDAG",
                                        "TASK_NAME_LABEL": "testTask"
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
                            "value": "testQueue"
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": {
                                "driver": {
                                    "volumeMounts": []
                                },
                                "sparkServiceAccount": "default"
                            }
                        }
                    ],
                    "expected": {
                        "labels": {
                            "DAG_NAME_LABEL": "testDAG",
                            "TASK_NAME_LABEL": "testTask"
                        },
                        "serviceAccount": "default"
                    },
                    "description": "Test case where driver is specified in the request and labels are set correctly."
                }
            ]
        },
        {
            "group": "getDriverSpec - Driver is null",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "request",
                            "value": {
                                "driver": null,
                                "volumes": null
                            }
                        },
                        {
                            "parameter": "appConfig",
                            "value": {}
                        },
                        {
                            "parameter": "parentQueue",
                            "value": "testQueue"
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": {
                                "driver": {
                                    "volumeMounts": []
                                },
                                "sparkServiceAccount": "default"
                            }
                        }
                    ],
                    "expected": {
                        "serviceAccount": "default"
                    },
                    "description": "Test case where driver is null in the request and service account is taken from sparkCluster."
                }
            ]
        },
        {
            "group": "getDriverSpec - Driver volumes and volume mounts",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "request",
                            "value": {
                                "driver": {
                                    "volumeMounts": ["mount1"]
                                },
                                "volumes": ["volume1"]
                            }
                        },
                        {
                            "parameter": "appConfig",
                            "value": {}
                        },
                        {
                            "parameter": "parentQueue",
                            "value": "testQueue"
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": {
                                "driver": {
                                    "volumeMounts": ["mount2"]
                                }
                            }
                        }
                    ],
                    "expected": {
                        "volumeMounts": ["mount1"]
                    },
                    "description": "Test case where driver volumes in request have higher priority than sparkCluster."
                },
                {
                    "input": [
                        {
                            "parameter": "request",
                            "value": {
                                "driver": null,
                                "volumes": ["volume1"]
                            }
                        },
                        {
                            "parameter": "appConfig",
                            "value": {}
                        },
                        {
                            "parameter": "parentQueue",
                            "value": "testQueue"
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": {
                                "driver": {
                                    "volumeMounts": ["mount2"]
                                }
                            }
                        }
                    ],
                    "expected": {
                        "volumeMounts": ["mount2"]
                    },
                    "description": "Test case where sparkCluster volumes are used when driver is null."
                }
            ]
        },
        {
            "group": "getDriverSpec - Core and Memory Adjustments",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "request",
                            "value": {
                                "driver": {
                                    "cores": 2,
                                    "memory": "2G"
                                }
                            }
                        },
                        {
                            "parameter": "appConfig",
                            "value": {}
                        },
                        {
                            "parameter": "parentQueue",
                            "value": "testQueue"
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": {
                                "sparkServiceAccount": "default"
                            }
                        }
                    ],
                    "expected": {
                        "cores": 2,
                        "memory": "2G"
                    },
                    "description": "Test case to verify core and memory settings when driver is defined."
                }
            ]
        },
        {
            "group": "getDriverSpec - Affinity Settings",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "request",
                            "value": {
                                "driver": {}
                            }
                        },
                        {
                            "parameter": "appConfig",
                            "value": {}
                        },
                        {
                            "parameter": "parentQueue",
                            "value": "testQueue"
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": {
                                "sparkServiceAccount": "default"
                            }
                        }
                    ],
                    "expected": {
                        "affinity": {
                            "nodeAffinity": {
                                "requiredDuringSchedulingIgnoredDuringExecution": {
                                    "nodeSelectorTerms": [
                                        {
                                            "matchExpressions": [
                                                {
                                                    "key": "nodeLabelKey",
                                                    "operator": "In",
                                                    "values": ["value1", "value2"]
                                                }
                                            ]
                                        }
                                    ]
                                }
                            }
                        }
                    },
                    "description": "Test case to verify affinity settings when driver affinity is not set."
                }
            ]
        }
    ]
}