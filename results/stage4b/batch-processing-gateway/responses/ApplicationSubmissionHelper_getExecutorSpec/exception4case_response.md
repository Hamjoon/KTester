{
    "group": "getExecutorSpec Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "request",
                    "value": {
                        "executor": {
                            "cores": 2,
                            "memory": "4G",
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
                    "value": "default"
                },
                {
                    "parameter": "sparkCluster",
                    "value": {
                        "executor": {
                            "cores": 4,
                            "memory": "8G",
                            "volumeMounts": []
                        },
                        "volumes": []
                    }
                }
            ],
            "expected": {
                "cores": 4,
                "memory": "8G"
            },
            "description": "Test case where executor has null labels in the request, should fallback to sparkCluster."
        },
        {
            "input": [
                {
                    "parameter": "request",
                    "value": {
                        "executor": {
                            "cores": 2,
                            "memory": "4G",
                            "labels": {
                                "DAG_NAME_LABEL": "testDAG"
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
                    "value": "default"
                },
                {
                    "parameter": "sparkCluster",
                    "value": {
                        "executor": {
                            "cores": 4,
                            "memory": "8G",
                            "volumeMounts": []
                        },
                        "volumes": []
                    }
                }
            ],
            "expected": {
                "cores": 4,
                "memory": "8G",
                "labels": {
                    "DAG_NAME_LABEL": "testDAG"
                }
            },
            "description": "Test case where executor has partial labels in the request, should fallback to sparkCluster."
        },
        {
            "input": [
                {
                    "parameter": "request",
                    "value": {
                        "executor": {
                            "cores": -1,
                            "memory": "4G",
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
                    "value": "default"
                },
                {
                    "parameter": "sparkCluster",
                    "value": {
                        "executor": {
                            "cores": 4,
                            "memory": "8G",
                            "volumeMounts": []
                        },
                        "volumes": []
                    }
                }
            ],
            "expected": {
                "cores": 4,
                "memory": "8G"
            },
            "description": "Test case where executor has negative cores specified in the request, should fallback to sparkCluster."
        },
        {
            "input": [
                {
                    "parameter": "request",
                    "value": {
                        "executor": {
                            "cores": 2,
                            "memory": "4G",
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
                    "value": "invalidQueue"
                },
                {
                    "parameter": "sparkCluster",
                    "value": {
                        "executor": {
                            "cores": 4,
                            "memory": "8G",
                            "volumeMounts": []
                        },
                        "volumes": []
                    }
                }
            ],
            "expected": {
                "cores": 4,
                "memory": "8G"
            },
            "description": "Test case where an invalid queue is specified in the parentQueue, should fallback to sparkCluster."
        }
    ]
}