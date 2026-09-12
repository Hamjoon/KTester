{
    "group": "getExecutorSpec",
    "cases": [
        {
            "input": [
                {
                    "parameter": "request",
                    "value": {
                        "executor": {
                            "cores": 2,
                            "coreLimit": "1000m",
                            "memory": "1024Mi",
                            "labels": {
                                "dagName": "testDag",
                                "taskName": "testTask"
                            }
                        },
                        "volumes": [
                            {
                                "name": "volume1",
                                "path": "/mnt/volume1"
                            }
                        ],
                        "spotInstance": false
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
                            "volumeMounts": [
                                {
                                    "name": "volume2",
                                    "mountPath": "/mnt/volume2"
                                }
                            ]
                        },
                        "volumes": [
                            {
                                "name": "volume2",
                                "path": "/mnt/volume2"
                            }
                        ]
                    }
                }
            ],
            "expected": {
                "cores": 2,
                "coreLimit": "1000m",
                "memory": "1024Mi",
                "labels": {
                    "dagName": "testDag",
                    "taskName": "testTask"
                },
                "volumeMounts": [
                    {
                        "name": "volume1",
                        "mountPath": "/mnt/volume1"
                    }
                ],
                "annotations": {
                    "kubeClusterAutoscalerScaleIn": "false"
                },
                "affinity": {
                    "nodeAffinity": {
                        "requiredDuringSchedulingIgnoredDuringExecution": {
                            "nodeSelectorTerms": [
                                {
                                    "matchExpressions": [
                                        {
                                            "key": "nodeLabelKey",
                                            "operator": "In",
                                            "values": [
                                                "value1",
                                                "value2"
                                            ]
                                        }
                                    ]
                                }
                            ]
                        }
                    }
                },
                "dnsConfig": {
                    "options": [
                        {
                            "name": "ndots",
                            "value": "5"
                        }
                    ]
                }
            },
            "description": "Test with executor details in request, volumes in both request and sparkCluster, and spotInstance set to false. Expect executor spec with request volumes taking precedence."
        },
        {
            "input": [
                {
                    "parameter": "request",
                    "value": {
                        "executor": {
                            "cores": 0,
                            "coreLimit": null,
                            "memory": "0Mi",
                            "labels": {}
                        },
                        "volumes": null,
                        "spotInstance": true
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
                        "executor": null,
                        "volumes": null
                    }
                }
            ],
            "expected": {
                "cores": 0,
                "coreLimit": "0m",
                "memory": "0Mi",
                "annotations": {
                    "kubeClusterAutoscalerScaleIn": "false"
                },
                "affinity": {
                    "nodeAffinity": {
                        "requiredDuringSchedulingIgnoredDuringExecution": {
                            "nodeSelectorTerms": [
                                {
                                    "matchExpressions": [
                                        {
                                            "key": "nodeLabelKey",
                                            "operator": "In",
                                            "values": [
                                                "value1",
                                                "value2"
                                            ]
                                        }
                                    ]
                                }
                            ]
                        }
                    }
                },
                "dnsConfig": {
                    "options": [
                        {
                            "name": "ndots",
                            "value": "5"
                        }
                    ]
                }
            },
            "description": "Test with null coreLimit and memory in request, and spotInstance set to true. Expect executor spec with default settings and spot node labels."
        }
    ]
}