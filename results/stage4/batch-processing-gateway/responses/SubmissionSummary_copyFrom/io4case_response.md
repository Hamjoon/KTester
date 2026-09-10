{
    "testCases": [
        {
            "group": "copyFrom with empty labels",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "sparkApplicationResource",
                            "value": {
                                "metadata": {
                                    "name": "app7",
                                    "labels": {}
                                },
                                "spec": {
                                    "driver": {
                                        "memory": "1g",
                                        "cores": 1
                                    },
                                    "executor": {
                                        "cores": 1,
                                        "instances": 1,
                                        "memory": "1g"
                                    }
                                }
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test copyFrom with a SparkApplication that has empty labels. Verify that user, queue, and dagName are not set."
                }
            ]
        },
        {
            "group": "copyFrom with extreme values for driver and executor memory",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "sparkApplicationResource",
                            "value": {
                                "metadata": {
                                    "name": "app8",
                                    "labels": {
                                        "PROXY_USER_LABEL": "user8",
                                        "QUEUE_LABEL": "queue8"
                                    }
                                },
                                "spec": {
                                    "driver": {
                                        "memory": "100g",
                                        "cores": 4
                                    },
                                    "executor": {
                                        "cores": 4,
                                        "instances": 10,
                                        "memory": "50g"
                                    }
                                }
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test copyFrom with extreme values for driver and executor memory. Verify that total memory and total cores are calculated correctly."
                }
            ]
        },
        {
            "group": "copyFrom with null executor instances",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "sparkApplicationResource",
                            "value": {
                                "metadata": {
                                    "name": "app9",
                                    "labels": {
                                        "PROXY_USER_LABEL": "user9"
                                    }
                                },
                                "spec": {
                                    "driver": {
                                        "memory": "2g",
                                        "cores": 2
                                    },
                                    "executor": {
                                        "cores": 2,
                                        "instances": null,
                                        "memory": "2g"
                                    }
                                }
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test copyFrom with a SparkApplication that has null executor instances. Verify that default value is used for executor instances."
                }
            ]
        },
        {
            "group": "copyFrom with multiple labels set",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "sparkApplicationResource",
                            "value": {
                                "metadata": {
                                    "name": "app10",
                                    "labels": {
                                        "PROXY_USER_LABEL": "user10",
                                        "QUEUE_LABEL": "queue10",
                                        "DAG_NAME_LABEL": "dag10"
                                    }
                                },
                                "spec": {
                                    "driver": {
                                        "memory": "3g",
                                        "cores": 3
                                    },
                                    "executor": {
                                        "cores": 3,
                                        "instances": 3,
                                        "memory": "3g"
                                    }
                                }
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test copyFrom with a SparkApplication that has multiple labels set. Verify that all labels are correctly assigned to the properties."
                }
            ]
        },
        {
            "group": "copyFrom with invalid executor memory",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "sparkApplicationResource",
                            "value": {
                                "metadata": {
                                    "name": "app11",
                                    "labels": {
                                        "PROXY_USER_LABEL": "user11"
                                    }
                                },
                                "spec": {
                                    "driver": {
                                        "memory": "1g",
                                        "cores": 1
                                    },
                                    "executor": {
                                        "cores": 1,
                                        "instances": 1,
                                        "memory": "invalid_memory"
                                    }
                                }
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test copyFrom with an invalid executor memory string. Verify that the executor memory is set to 0 and an appropriate log message is generated."
                }
            ]
        }
    ]
}