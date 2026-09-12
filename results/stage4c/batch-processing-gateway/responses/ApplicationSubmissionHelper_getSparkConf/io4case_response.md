{
    "group": "getSparkConf Additional Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "submissionId",
                    "value": "sub-202"
                },
                {
                    "parameter": "request",
                    "value": {
                        "sparkConf": {
                            "spark.executor.memory": "2g"
                        },
                        "applicationName": "AppWithOnlyRequestConf"
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
                "spark.executor.memory": "2g",
                "spark.app.name": "AppWithOnlyRequestConf"
            },
            "description": "Test with only request sparkConf and applicationName provided to ensure these are correctly applied when others are null."
        },
        {
            "input": [
                {
                    "parameter": "submissionId",
                    "value": "sub-303"
                },
                {
                    "parameter": "request",
                    "value": {
                        "sparkConf": {},
                        "applicationName": "AppWithOnlyFixedConf"
                    }
                },
                {
                    "parameter": "appConfig",
                    "value": {
                        "defaultSparkConf": null,
                        "fixedSparkConf": {
                            "spark.driver.memory": "2g"
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
                "spark.driver.memory": "2g",
                "spark.app.name": "AppWithOnlyFixedConf"
            },
            "description": "Test with only fixed sparkConf provided to ensure it is correctly applied when others are null."
        },
        {
            "input": [
                {
                    "parameter": "submissionId",
                    "value": "sub-404"
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
                            "spark.driver.memory": "3g"
                        },
                        "fixedSparkConf": null
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
                "spark.driver.memory": "3g"
            },
            "description": "Test with only default sparkConf provided to ensure it is correctly applied when others are null."
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
                        "sparkConf": {
                            "spark.driver.memory": "4g"
                        },
                        "applicationName": "AppWithRequestAndClusterConf"
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
                        "sparkConf": {
                            "spark.executor.cores": "2"
                        }
                    }
                }
            ],
            "expected": {
                "spark.driver.memory": "4g",
                "spark.executor.cores": "2",
                "spark.app.name": "AppWithRequestAndClusterConf"
            },
            "description": "Test with sparkConf in both request and sparkCluster to ensure both are applied correctly."
        }
    ]
}