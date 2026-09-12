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
                            "volumeMounts": [],
                            "serviceAccount": "serviceAccount1",
                            "cores": 4,
                            "coreLimit": "4000",
                            "memory": "4096Mi"
                        },
                        "volumes": []
                    }
                },
                {
                    "parameter": "appConfig",
                    "value": "appConfigInstance"
                },
                {
                    "parameter": "parentQueue",
                    "value": "parentQueue5"
                },
                {
                    "parameter": "sparkCluster",
                    "value": {
                        "driver": {
                            "volumeMounts": ["mount5", "mount6"]
                        },
                        "sparkServiceAccount": "sparkServiceAccount5"
                    }
                }
            ],
            "expected": "DriverSpec with normalized labels and adjusted resources",
            "description": "Test case where request has driver with labels but no volumes. Verifies label normalization and resource adjustment without additional volumes."
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
                            "serviceAccount": "serviceAccount2",
                            "cores": 0,
                            "coreLimit": "0",
                            "memory": "0Mi"
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
                    "value": "parentQueue6"
                },
                {
                    "parameter": "sparkCluster",
                    "value": {
                        "driver": {
                            "volumeMounts": ["mount7", "mount8"]
                        },
                        "sparkServiceAccount": "sparkServiceAccount6"
                    }
                }
            ],
            "expected": "DriverSpec with service account from request, zero cores and memory adjusted",
            "description": "Test case where request has a driver with zero cores and memory. Verifies handling of zero values and resource adjustments."
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
                            "volumeMounts": [],
                            "serviceAccount": "",
                            "cores": 2,
                            "coreLimit": null,
                            "memory": "2048Mi"
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
                    "value": "parentQueue7"
                },
                {
                    "parameter": "sparkCluster",
                    "value": {
                        "driver": {
                            "volumeMounts": ["mount9", "mount10"]
                        },
                        "sparkServiceAccount": "sparkServiceAccount7"
                    }
                }
            ],
            "expected": "DriverSpec with default service account and adjusted core limit/memory",
            "description": "Test case where request has a driver with empty service account and no core limit. Verifies default service account usage and resource adjustments."
        },
        {
            "input": [
                {
                    "parameter": "request",
                    "value": {
                        "driver": {
                            "labels": null,
                            "volumeMounts": ["mount1", "mount2"],
                            "serviceAccount": "serviceAccount3",
                            "cores": 3,
                            "coreLimit": "3000",
                            "memory": "3072Mi"
                        },
                        "volumes": ["volume2"]
                    }
                },
                {
                    "parameter": "appConfig",
                    "value": "appConfigInstance"
                },
                {
                    "parameter": "parentQueue",
                    "value": "parentQueue8"
                },
                {
                    "parameter": "sparkCluster",
                    "value": {
                        "driver": {
                            "volumeMounts": ["mount11", "mount12"]
                        },
                        "sparkServiceAccount": "sparkServiceAccount8"
                    }
                }
            ],
            "expected": "DriverSpec with specified service account and adjusted core limit/memory",
            "description": "Test case where request has driver with specified service account and volumes. Verifies correct usage of specified service account and resource adjustments."
        }
    ]
}