{
    "testCases": [
        {
            "group": "getDriverSpec Tests",
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
                                    "volumeMounts": ["mount3"]
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
                        "volumeMounts": ["mount3"],
                        "serviceAccount": "default"
                    },
                    "description": "Test with driver having labels but no volumeMounts, should use sparkCluster's driver volumeMounts."
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
                                    "volumeMounts": []
                                },
                                "sparkServiceAccount": "default"
                            }
                        }
                    ],
                    "expected": {
                        "volumeMounts": [],
                        "serviceAccount": "default"
                    },
                    "description": "Test when driver is null and volumes are specified, should not affect volumeMounts."
                },
                {
                    "input": [
                        {
                            "parameter": "request",
                            "value": {
                                "driver": {
                                    "labels": {
                                        "DAG_NAME_LABEL": "testDAG"
                                    },
                                    "volumeMounts": ["mount1"]
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
                                    "volumeMounts": ["mount3"]
                                },
                                "sparkServiceAccount": "default"
                            }
                        }
                    ],
                    "expected": {
                        "labels": {
                            "DAG_NAME_LABEL": "testDAG"
                        },
                        "volumeMounts": ["mount1"],
                        "serviceAccount": "default"
                    },
                    "description": "Test with driver having labels and volumeMounts specified, should prioritize driver's volumeMounts."
                },
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
                                    "volumeMounts": ["mount3"]
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
                        "volumeMounts": ["mount1"],
                        "serviceAccount": "default"
                    },
                    "description": "Test with driver having labels and volumeMounts, should use driver's volumeMounts."
                }
            ]
        }
    ]
}