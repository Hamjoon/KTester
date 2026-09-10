{
    "testCases": [
        {
            "group": "getExecutorSpec - Executor with empty labels",
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
                    "description": "Test case where executor is provided with empty labels."
                }
            ]
        },
        {
            "group": "getExecutorSpec - Executor with null memory",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "request",
                            "value": {
                                "executor": {
                                    "cores": 2,
                                    "memory": null,
                                    "labels": {
                                        "dagName": "testDag"
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
                        "memory": "0",
                        "labels": {
                            "dagName": "testDag"
                        }
                    },
                    "description": "Test case where executor is provided with null memory."
                }
            ]
        },
        {
            "group": "getExecutorSpec - No executor and volumes in request",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "request",
                            "value": {
                                "executor": null,
                                "volumes": ["volume1"]
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
                        "labels": {},
                        "volumeMounts": []
                    },
                    "description": "Test case where no executor is defined and volumes are specified in the request."
                }
            ]
        },
        {
            "group": "getExecutorSpec - Spot instance with defined cores",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "request",
                            "value": {
                                "executor": {
                                    "cores": 4,
                                    "memory": "8Gi",
                                    "spotInstance": true
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
                        "cores": 4,
                        "memory": "8Gi",
                        "labels": {},
                        "affinity": {"requiredDuringSchedulingIgnoredDuringExecution": {}}
                    },
                    "description": "Test case to verify settings when spot instances are defined with specific cores."
                }
            ]
        },
        {
            "group": "getExecutorSpec - Core limit adjustments with zero cores",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "request",
                            "value": {
                                "executor": {
                                    "cores": 0,
                                    "memory": "1Gi"
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
                        "cores": 0,
                        "coreLimit": "0m",
                        "memory": "1Gi"
                    },
                    "description": "Test case to verify core limit adjustments when cores are set to zero."
                }
            ]
        }
    ]
}