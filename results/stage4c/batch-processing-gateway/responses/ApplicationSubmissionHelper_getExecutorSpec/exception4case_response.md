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
                            "coreLimit": "invalid",
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
            "expected": "NumberFormatException",
            "description": "Test with invalid coreLimit format in executor details. Expect NumberFormatException when parsing coreLimit."
        },
        {
            "input": [
                {
                    "parameter": "request",
                    "value": {
                        "executor": {
                            "cores": 2,
                            "coreLimit": "1000m",
                            "memory": "invalid",
                            "labels": {
                                "dagName": "testDag",
                                "taskName": "testTask"
                            }
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
                        "executor": null,
                        "volumes": null
                    }
                }
            ],
            "expected": "NumberFormatException",
            "description": "Test with invalid memory format in executor details. Expect NumberFormatException when parsing memory."
        },
        {
            "input": [
                {
                    "parameter": "request",
                    "value": {
                        "executor": {
                            "cores": 2,
                            "coreLimit": "1000m",
                            "memory": "2048Mi",
                            "labels": {
                                "dagName": null,
                                "taskName": "testTask"
                            }
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
                        "executor": null,
                        "volumes": null
                    }
                }
            ],
            "expected": "NullPointerException",
            "description": "Test with null dagName in executor labels. Expect NullPointerException when normalizing label value."
        },
        {
            "input": [
                {
                    "parameter": "request",
                    "value": {
                        "executor": {
                            "cores": 2,
                            "coreLimit": "1000m",
                            "memory": "2048Mi",
                            "labels": {
                                "dagName": "testDag",
                                "taskName": null
                            }
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
                        "executor": null,
                        "volumes": null
                    }
                }
            ],
            "expected": "NullPointerException",
            "description": "Test with null taskName in executor labels. Expect NullPointerException when normalizing label value."
        }
    ]
}