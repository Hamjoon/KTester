{
    "test cases": [
        {
            "group": "populateEnv - Spark Cluster Driver Environment",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "sparkSpec",
                            "value": "new SparkApplicationSpec()"
                        },
                        {
                            "parameter": "request",
                            "value": "new SubmitApplicationRequest()"
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": "new AppConfig.SparkCluster()"
                        }
                    ],
                    "expected": "sparkSpec.getDriver().getEnv() should contain the environment variables from sparkCluster.getDriver().getEnv()",
                    "description": "Test case where the driver environment from the spark cluster is copied to spark spec."
                },
                {
                    "input": [
                        {
                            "parameter": "sparkSpec",
                            "value": "new SparkApplicationSpec()"
                        },
                        {
                            "parameter": "request",
                            "value": "new SubmitApplicationRequest()"
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": "new AppConfig.SparkCluster() with no driver"
                        }
                    ],
                    "expected": "sparkSpec.getDriver() should be null",
                    "description": "Test case where spark cluster driver is null, ensuring no environment variables are copied."
                }
            ]
        },
        {
            "group": "populateEnv - Submit Application Request Driver Environment",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "sparkSpec",
                            "value": "new SparkApplicationSpec()"
                        },
                        {
                            "parameter": "request",
                            "value": "new SubmitApplicationRequest() with driver having env"
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": "new AppConfig.SparkCluster()"
                        }
                    ],
                    "expected": "sparkSpec.getDriver().getEnv() should contain the environment variables from request.getDriver().getEnv()",
                    "description": "Test case where the driver environment from the submit application request is copied to spark spec."
                },
                {
                    "input": [
                        {
                            "parameter": "sparkSpec",
                            "value": "new SparkApplicationSpec()"
                        },
                        {
                            "parameter": "request",
                            "value": "new SubmitApplicationRequest() with no driver"
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": "new AppConfig.SparkCluster()"
                        }
                    ],
                    "expected": "sparkSpec.getDriver() should be null",
                    "description": "Test case where request driver is null, ensuring no environment variables are copied."
                }
            ]
        },
        {
            "group": "populateEnv - Spark Cluster Executor Environment",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "sparkSpec",
                            "value": "new SparkApplicationSpec()"
                        },
                        {
                            "parameter": "request",
                            "value": "new SubmitApplicationRequest()"
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": "new AppConfig.SparkCluster() with executor having env"
                        }
                    ],
                    "expected": "sparkSpec.getExecutor().getEnv() should contain the environment variables from sparkCluster.getExecutor().getEnv()",
                    "description": "Test case where the executor environment from the spark cluster is copied to spark spec."
                },
                {
                    "input": [
                        {
                            "parameter": "sparkSpec",
                            "value": "new SparkApplicationSpec()"
                        },
                        {
                            "parameter": "request",
                            "value": "new SubmitApplicationRequest()"
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": "new AppConfig.SparkCluster() with no executor"
                        }
                    ],
                    "expected": "sparkSpec.getExecutor() should be null",
                    "description": "Test case where spark cluster executor is null, ensuring no environment variables are copied."
                }
            ]
        },
        {
            "group": "populateEnv - Submit Application Request Executor Environment",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "sparkSpec",
                            "value": "new SparkApplicationSpec()"
                        },
                        {
                            "parameter": "request",
                            "value": "new SubmitApplicationRequest() with executor having env"
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": "new AppConfig.SparkCluster()"
                        }
                    ],
                    "expected": "sparkSpec.getExecutor().getEnv() should contain the environment variables from request.getExecutor().getEnv()",
                    "description": "Test case where the executor environment from the submit application request is copied to spark spec."
                },
                {
                    "input": [
                        {
                            "parameter": "sparkSpec",
                            "value": "new SparkApplicationSpec()"
                        },
                        {
                            "parameter": "request",
                            "value": "new SubmitApplicationRequest() with no executor"
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": "new AppConfig.SparkCluster()"
                        }
                    ],
                    "expected": "sparkSpec.getExecutor() should be null",
                    "description": "Test case where request executor is null, ensuring no environment variables are copied."
                }
            ]
        }
    ]
}