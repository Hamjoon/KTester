{
    "group": "getSparkConf Exception Scenarios",
    "cases": [
        {
            "input": [
                {
                    "parameter": "submissionId",
                    "value": "sub-001"
                },
                {
                    "parameter": "request",
                    "value": null
                },
                {
                    "parameter": "appConfig",
                    "value": {
                        "defaultSparkConf": {
                            "spark.executor.memory": "1g"
                        },
                        "fixedSparkConf": {
                            "spark.driver.cores": "1"
                        }
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
            "expected": "NullPointerException",
            "description": "Test with null SubmitApplicationRequest to check handling of null request object."
        },
        {
            "input": [
                {
                    "parameter": "submissionId",
                    "value": "sub-002"
                },
                {
                    "parameter": "request",
                    "value": {
                        "sparkConf": {
                            "spark.executor.memory": "2g"
                        },
                        "applicationName": "TestApp"
                    }
                },
                {
                    "parameter": "appConfig",
                    "value": null
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
            "expected": "NullPointerException",
            "description": "Test with null AppConfig to check handling of null appConfig object."
        },
        {
            "input": [
                {
                    "parameter": "submissionId",
                    "value": "sub-003"
                },
                {
                    "parameter": "request",
                    "value": {
                        "sparkConf": {
                            "spark.executor.memory": "2g"
                        },
                        "applicationName": "TestApp"
                    }
                },
                {
                    "parameter": "appConfig",
                    "value": {
                        "defaultSparkConf": {
                            "spark.executor.memory": "1g"
                        },
                        "fixedSparkConf": {
                            "spark.driver.cores": "1"
                        }
                    }
                },
                {
                    "parameter": "sparkCluster",
                    "value": null
                }
            ],
            "expected": "NullPointerException",
            "description": "Test with null SparkCluster to check handling of null sparkCluster object."
        }
    ]
}