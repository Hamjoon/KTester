{
    "testCases": [
        {
            "group": "getDriverSpec - Driver with additional labels",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "request",
                            "value": {
                                "driver": {
                                    "labels": {
                                        "DAG_NAME_LABEL": "additionalDAG",
                                        "TASK_NAME_LABEL": "additionalTask"
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
                            "DAG_NAME_LABEL": "additionalDAG",
                            "TASK_NAME_LABEL": "additionalTask"
                        },
                        "serviceAccount": "default"
                    },
                    "description": "Test case where driver has additional labels set and should be reflected in the DriverSpec."
                }
            ]
        },
        {
            "group": "getDriverSpec - Adjusted CPU and Memory with Buffer Ratios",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "request",
                            "value": {
                                "driver": {
                                    "cores": 4,
                                    "memory": "4G"
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
                                "sparkServiceAccount": "default"
                            }
                        }
                    ],
                    "expected": {
                        "cores": 4,  "memory": "4G"
                    },
                    "description": "Test case to verify core and memory settings when driver is defined with specific values and buffer ratios are applied."
                }
            ]
        },
        {
            "group": "getDriverSpec - Driver without service account",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "request",
                            "value": {
                                "driver": {
                                    "serviceAccount": null
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
                                "sparkServiceAccount": "default"
                            }
                        }
                    ],
                    "expected": {
                        "serviceAccount": "default"
                    },
                    "description": "Test case where driver has no service account specified, and it should take the service account from sparkCluster."
                }
            ]
        },
        {
            "group": "getDriverSpec - Driver with no volumes and empty sparkCluster",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "request",
                            "value": {
                                "driver": {
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
                        "volumeMounts": []
                    },
                    "description": "Test case where driver has no volumes and sparkCluster is also empty, expecting empty volume mounts."
                }
            ]
        },
        {
            "group": "getDriverSpec - Driver with large core and memory values",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "request",
                            "value": {
                                "driver": {
                                    "cores": 100,
                                    "memory": "100G"
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
                                "sparkServiceAccount": "default"
                            }
                        }
                    ],
                    "expected": {
                        "cores": 100, "memory": "100G"
                    },
                    "description": "Test case to check if large core and memory values are handled correctly."
                }
            ]
        }
    ]
}