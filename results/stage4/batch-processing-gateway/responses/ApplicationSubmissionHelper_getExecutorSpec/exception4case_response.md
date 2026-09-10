{
    "output": [
        {
            "group": "getExecutorSpec - Executor labels null",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "request",
                            "value": {
                                "executor": {
                                    "cores": 2,
                                    "memory": "4Gi",
                                    "labels": null,
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
                        "labels": {}
                    },
                    "description": "Test case where executor labels are null."
                }
            ]
        },
        {
            "group": "getExecutorSpec - Executor memory format invalid",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "request",
                            "value": {
                                "executor": {
                                    "cores": 2,
                                    "memory": "invalidMemoryFormat",
                                    "labels": {},
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
                        "memory": "0",
                        "labels": {}
                    },
                    "description": "Test case where executor memory format is invalid."
                }
            ]
        },
        {
            "group": "getExecutorSpec - Executor cores negative",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "request",
                            "value": {
                                "executor": {
                                    "cores": -1,
                                    "memory": "2Gi",
                                    "labels": {},
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
                        "cores": 0,
                        "memory": "2Gi",
                        "labels": {}
                    },
                    "description": "Test case where executor cores are set to a negative value."
                }
            ]
        },
        {
            "group": "getExecutorSpec - Executor core limit invalid",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "request",
                            "value": {
                                "executor": {
                                    "cores": 2,
                                    "memory": "4Gi",
                                    "coreLimit": "invalidLimit",
                                    "labels": {},
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
                        "coreLimit": "0m",
                        "memory": "4Gi",
                        "labels": {}
                    },
                    "description": "Test case where executor core limit is invalid."
                }
            ]
        },
        {
            "group": "getExecutorSpec - AppConfig null",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "request",
                            "value": {
                                "executor": {
                                    "cores": 2,
                                    "memory": "4Gi",
                                    "labels": {},
                                    "volumeMounts": []
                                },
                                "volumes": []
                            }
                        },
                        {
                            "parameter": "appConfig",
                            "value": null
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
                        "labels": {}
                    },
                    "description": "Test case where appConfig is null."
                }
            ]
        }
    ]
}