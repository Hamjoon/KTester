{
    "testCases": [
        {
            "group": "getSparkConf - Additional Valid Configurations",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "submissionId",
                            "value": "sub-404"
                        },
                        {
                            "parameter": "request",
                            "value": {
                                "sparkConf": {
                                    "key9": "value9"
                                },
                                "applicationName": "NewTestApp"
                            }
                        },
                        {
                            "parameter": "appConfig",
                            "value": {
                                "defaultSparkConf": {
                                    "key10": "value10"
                                },
                                "fixedSparkConf": {}
                            }
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": {
                                "sparkConf": {
                                    "key11": "value11"
                                }
                            }
                        }
                    ],
                    "expected": {
                        "key9": "value9",
                        "key10": "value10",
                        "key11": "value11",
                        "spark.app.name": "NewTestApp"
                    },
                    "description": "Test case where all types of configurations (request, default, sparkCluster) are provided."
                },
                {
                    "input": [
                        {
                            "parameter": "submissionId",
                            "value": "sub-505"
                        },
                        {
                            "parameter": "request",
                            "value": {
                                "sparkConf": {},
                                "applicationName": "OnlyAppName"
                            }
                        },
                        {
                            "parameter": "appConfig",
                            "value": {
                                "defaultSparkConf": {
                                    "key12": "value12"
                                },
                                "fixedSparkConf": {
                                    "key13": "value13"
                                }
                            }
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": {
                                "sparkConf": {}
                            }
                        }
                    ],
                    "expected": {
                        "key12": "value12",
                        "key13": "value13",
                        "spark.app.name": "OnlyAppName"
                    },
                    "description": "Test case where request has applicationName, appConfig has default and fixed configurations."
                }
            ]
        },
        {
            "group": "getSparkConf - Edge Cases with Null Values",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "submissionId",
                            "value": "sub-606"
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
                    "expected": {},
                    "description": "Test case where all configurations are null."
                },
                {
                    "input": [
                        {
                            "parameter": "submissionId",
                            "value": "sub-707"
                        },
                        {
                            "parameter": "request",
                            "value": {
                                "sparkConf": {},
                                "applicationName": ""
                            }
                        },
                        {
                            "parameter": "appConfig",
                            "value": {
                                "defaultSparkConf": {},
                                "fixedSparkConf": {}
                            }
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": {
                                "sparkConf": {}
                            }
                        }
                    ],
                    "expected": {},
                    "description": "Test case where all configurations are empty."
                }
            ]
        }
    ]
}