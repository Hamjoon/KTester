{
    "group": "getExecutorSpec",
    "cases": [
        {
            "input": [
                {
                    "parameter": "request",
                    "value": {
                        "executor": null,
                        "volumes": null,
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
                "affinity": null,
                "dnsConfig": {
                    "options": [
                        {
                            "name": "ndots",
                            "value": "5"
                        }
                    ]
                }
            },
            "description": "Test with null executor and volumes in request and sparkCluster. Expect default ExecutorSpec settings."
        },
        {
            "input": [
                {
                    "parameter": "request",
                    "value": {
                        "executor": {
                            "cores": 2,
                            "coreLimit": "2000m",
                            "memory": "2048Mi",
                            "labels": {
                                "dagName": "testDag",
                                "taskName": "testTask"
                            }
                        },
                        "volumes": [],
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
                "cores": 2,
                "coreLimit": "2000m",
                "memory": "2048Mi",
                "labels": {
                    "dagName": "testDag",
                    "taskName": "testTask"
                },
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
            "description": "Test with executor details in request and spotInstance set to true. Expect executor spec with spot node labels."
        },
        {
            "input": [
                {
                    "parameter": "request",
                    "value": {
                        "executor": {
                            "cores": 4,
                            "coreLimit": "",
                            "memory": "4096Mi",
                            "labels": {}
                        },
                        "volumes": [],
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
                                    "name": "testVolume",
                                    "mountPath": "/mnt/test"
                                }
                            ]
                        },
                        "volumes": [
                            {
                                "name": "testVolume",
                                "path": "/mnt/test"
                            }
                        ]
                    }
                }
            ],
            "expected": {
                "cores": 4,
                "coreLimit": "0m",
                "memory": "4096Mi",
                "volumeMounts": [
                    {
                        "name": "testVolume",
                        "mountPath": "/mnt/test"
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
            "description": "Test with executor coreLimit empty in request and volumes in sparkCluster. Expect executor spec with provided volumes and calculated coreLimit."
        }
    ]
}