{"group": "getExecutorSpec Tests", "cases": [
    {
        "input": [
            {
                "parameter": "request",
                "value": {
                    "executor": {
                        "cores": 2,
                        "memory": "4G",
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
                "value": "default"
            },
            {
                "parameter": "sparkCluster",
                "value": {}
            }
        ],
        "expected": {
            "cores": 2,
            "memory": "4G",
            "labels": {
                "DAG_NAME_LABEL": "testDAG",
                "TASK_NAME_LABEL": "testTask"
            }
        },
        "description": "Test case where executor is specified in the request with valid labels."
    },
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
        "description": "Test case where executor is not specified in the request but is provided in the sparkCluster."
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
                "value": "default"
            },
            {
                "parameter": "sparkCluster",
                "value": {}
            }
        ],
        "expected": {
            "cores": 2,
            "memory": "4G",
            "labels": {}
        },
        "description": "Test case where executor is specified without labels."
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
                "value": {}
            }
        ],
        "expected": {
            "cores": 2,
            "memory": "4G",
            "labels": {
                "DAG_NAME_LABEL": "testDAG"
            }
        },
        "description": "Test case where executor has only DAG_NAME_LABEL specified."
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
                            "DAG_NAME_LABEL": "testDAG",
                            "TASK_NAME_LABEL": "testTask"
                        },
                        "volumeMounts": []
                    },
                    "volumes": [
                        {
                            "name": "volume1",
                            "mountPath": "/mnt/volume1"
                        }
                    ]
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
                "value": {}
            }
        ],
        "expected": {
            "cores": 2,
            "memory": "4G",
            "labels": {
                "DAG_NAME_LABEL": "testDAG",
                "TASK_NAME_LABEL": "testTask"
            },
            "volumeMounts": []
        },
        "description": "Test case where executor has volumes specified in request but no volume mounts."
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
                            "DAG_NAME_LABEL": "testDAG",
                            "TASK_NAME_LABEL": "testTask"
                        },
                        "volumeMounts": [
                            {
                                "name": "volume1",
                                "mountPath": "/mnt/volume1"
                            }
                        ]
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
                "value": {}
            }
        ],
        "expected": {
            "cores": 2,
            "memory": "4G",
            "labels": {
                "DAG_NAME_LABEL": "testDAG",
                "TASK_NAME_LABEL": "testTask"
            },
            "volumeMounts": [
                {
                    "name": "volume1",
                    "mountPath": "/mnt/volume1"
                }
            ]
        },
        "description": "Test case where executor has volume mounts specified in the request."
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
            "memory": "8G"
        },
        "description": "Test case where executor is not specified in the request but is provided in the sparkCluster."
    }
]}