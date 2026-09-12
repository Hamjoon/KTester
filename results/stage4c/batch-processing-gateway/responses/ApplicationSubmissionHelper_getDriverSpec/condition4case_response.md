{
    "group": "getDriverSpec",
    "cases": [
        {
            "input": [
                {
                    "parameter": "request",
                    "value": {
                        "driver": {
                            "labels": {
                                "DAG_NAME_LABEL": "dagName",
                                "TASK_NAME_LABEL": "taskName"
                            },
                            "volumeMounts": ["mount1", "mount2"],
                            "serviceAccount": "serviceAccount1",
                            "cores": 4,
                            "coreLimit": "4000",
                            "memory": "4096Mi"
                        },
                        "volumes": ["volume1"]
                    }
                },
                {
                    "parameter": "appConfig",
                    "value": "appConfigInstance"
                },
                {
                    "parameter": "parentQueue",
                    "value": "parentQueue1"
                },
                {
                    "parameter": "sparkCluster",
                    "value": {
                        "driver": {
                            "volumeMounts": ["mount3", "mount4"]
                        },
                        "sparkServiceAccount": "sparkServiceAccount1"
                    }
                }
            ],
            "expected": "DriverSpec with normalized labels and updated resources",
            "description": "Test case where request has driver with labels, volumes, and service account. Verifies label normalization and resource adjustment."
        },
        {
            "input": [
                {
                    "parameter": "request",
                    "value": {
                        "driver": null,
                        "volumes": []
                    }
                },
                {
                    "parameter": "appConfig",
                    "value": "appConfigInstance"
                },
                {
                    "parameter": "parentQueue",
                    "value": "parentQueue1"
                },
                {
                    "parameter": "sparkCluster",
                    "value": {
                        "driver": {
                            "volumeMounts": ["mount3", "mount4"]
                        },
                        "sparkServiceAccount": "sparkServiceAccount1"
                    }
                }
            ],
            "expected": "DriverSpec with default values from sparkCluster",
            "description": "Test case where request has no driver and no volumes. Verifies that defaults from sparkCluster are used."
        },
        {
            "input": [
                {
                    "parameter": "request",
                    "value": {
                        "driver": {
                            "labels": {
                                "DAG_NAME_LABEL": "dagName"
                            },
                            "volumeMounts": ["mount1"],
                            "serviceAccount": null,
                            "cores": 2,
                            "coreLimit": null,
                            "memory": "2048Mi"
                        },
                        "volumes": ["volume1", "volume2"]
                    }
                },
                {
                    "parameter": "appConfig",
                    "value": "appConfigInstance"
                },
                {
                    "parameter": "parentQueue",
                    "value": "parentQueue2"
                },
                {
                    "parameter": "sparkCluster",
                    "value": {
                        "driver": {
                            "volumeMounts": ["mount3", "mount4"]
                        },
                        "sparkServiceAccount": "sparkServiceAccount2"
                    }
                }
            ],
            "expected": "DriverSpec with service account from sparkCluster and adjusted cores/memory",
            "description": "Test case where request has a driver with missing service account and core limit. Verifies service account from sparkCluster and resource adjustments."
        },
        {
            "input": [
                {
                    "parameter": "request",
                    "value": {
                        "driver": {
                            "labels": null,
                            "volumeMounts": [],
                            "serviceAccount": "",
                            "cores": 1,
                            "coreLimit": "1000",
                            "memory": "1024Mi"
                        },
                        "volumes": null
                    }
                },
                {
                    "parameter": "appConfig",
                    "value": "appConfigInstance"
                },
                {
                    "parameter": "parentQueue",
                    "value": "parentQueue3"
                },
                {
                    "parameter": "sparkCluster",
                    "value": {
                        "driver": {
                            "volumeMounts": ["mount3", "mount4"]
                        },
                        "sparkServiceAccount": "sparkServiceAccount3"
                    }
                }
            ],
            "expected": "DriverSpec with default service account and adjusted core limit/memory",
            "description": "Test case where request has a driver with empty service account and volumes, checking default service account and resource adjustments."
        },
        {
            "input": [
                {
                    "parameter": "request",
                    "value": {
                        "driver": {
                            "labels": {
                                "TASK_NAME_LABEL": "taskName"
                            },
                            "volumeMounts": ["mount1"],
                            "serviceAccount": "serviceAccount4",
                            "cores": 3,
                            "coreLimit": "3000",
                            "memory": "3072Mi"
                        },
                        "volumes": ["volume3"]
                    }
                },
                {
                    "parameter": "appConfig",
                    "value": "appConfigInstance"
                },
                {
                    "parameter": "parentQueue",
                    "value": "parentQueue4"
                },
                {
                    "parameter": "sparkCluster",
                    "value": {
                        "driver": {
                            "volumeMounts": ["mount5", "mount6"]
                        },
                        "sparkServiceAccount": "sparkServiceAccount4"
                    }
                }
            ],
            "expected": "DriverSpec with normalized labels and adjusted core limit/memory",
            "description": "Test case where request has driver with TASK_NAME_LABEL and volumes, checking label normalization and resource adjustments."
        }
    ]
}