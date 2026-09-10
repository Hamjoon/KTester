{
    "test cases": [
        {
            "group": "getSparkConf - Application Name Only",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "submissionId",
                            "value": "test-submission-id"
                        },
                        {
                            "parameter": "request",
                            "value": {
                                "sparkConf": null,
                                "applicationName": "OnlyAppName"
                            }
                        },
                        {
                            "parameter": "appConfig",
                            "value": {
                                "defaultSparkConf": null,
                                "fixedSparkConf": null
                            }
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": {
                                "sparkConf": null
                            }
                        }
                    ],
                    "expected": {
                        "spark.app.name": "OnlyAppName"
                    },
                    "description": "Test case where only applicationName is set in request, expecting the application name to be returned."
                }
            ]
        },
        {
            "group": "getSparkConf - Mixed Configurations",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "submissionId",
                            "value": "test-submission-id"
                        },
                        {
                            "parameter": "request",
                            "value": {
                                "sparkConf": {
                                    "key10": "value10"
                                },
                                "applicationName": "MixedApp"
                            }
                        },
                        {
                            "parameter": "appConfig",
                            "value": {
                                "defaultSparkConf": {
                                    "key11": "value11"
                                },
                                "fixedSparkConf": {
                                    "key12": "value12"
                                }
                            }
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": {
                                "sparkConf": {
                                    "key13": "value13"
                                }
                            }
                        }
                    ],
                    "expected": {
                        "key10": "value10",
                        "key11": "value11",
                        "key12": "value12",
                        "key13": "value13",
                        "spark.app.name": "MixedApp"
                    },
                    "description": "Test case where all types of configurations are mixed, expecting a merged map with all values included."
                }
            ]
        },
        {
            "group": "getSparkConf - Only Fixed Configuration",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "submissionId",
                            "value": "test-submission-id"
                        },
                        {
                            "parameter": "request",
                            "value": {
                                "sparkConf": null,
                                "applicationName": null
                            }
                        },
                        {
                            "parameter": "appConfig",
                            "value": {
                                "defaultSparkConf": null,
                                "fixedSparkConf": {
                                    "key14": "value14"
                                }
                            }
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": {
                                "sparkConf": null
                            }
                        }
                    ],
                    "expected": {
                        "key14": "value14"
                    },
                    "description": "Test case where only fixedSparkConf is provided, expecting the fixed configuration to be returned."
                }
            ]
        },
        {
            "group": "getSparkConf - Default and Fixed Configurations",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "submissionId",
                            "value": "test-submission-id"
                        },
                        {
                            "parameter": "request",
                            "value": {
                                "sparkConf": null,
                                "applicationName": null
                            }
                        },
                        {
                            "parameter": "appConfig",
                            "value": {
                                "defaultSparkConf": {
                                    "key15": "value15"
                                },
                                "fixedSparkConf": {
                                    "key16": "value16"
                                }
                            }
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": {
                                "sparkConf": null
                            }
                        }
                    ],
                    "expected": {
                        "key15": "value15",
                        "key16": "value16"
                    },
                    "description": "Test case where both defaultSparkConf and fixedSparkConf are provided, expecting both to be included in the result."
                }
            ]
        }
    ]
}