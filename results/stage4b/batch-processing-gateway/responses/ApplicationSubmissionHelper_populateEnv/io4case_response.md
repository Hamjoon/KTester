{
    "testCases": [
        {
            "group": "populateEnv - Spark Cluster Driver Environment with Empty Env",
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
                            "value": "new AppConfig.SparkCluster() with empty driver env"
                        }
                    ],
                    "expected": "sparkSpec.getDriver().getEnv() should be empty",
                    "description": "Test case where spark cluster driver has an empty environment variable list."
                }
            ]
        },
        {
            "group": "populateEnv - Submit Application Request Driver Environment with Empty Env",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "sparkSpec",
                            "value": "new SparkApplicationSpec()"
                        },
                        {
                            "parameter": "request",
                            "value": "new SubmitApplicationRequest() with driver having empty env"
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": "new AppConfig.SparkCluster()"
                        }
                    ],
                    "expected": "sparkSpec.getDriver().getEnv() should be empty",
                    "description": "Test case where the driver environment from the submit application request is empty."
                }
            ]
        },
        {
            "group": "populateEnv - Spark Cluster Executor Environment with Empty Env",
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
                            "value": "new AppConfig.SparkCluster() with executor having empty env"
                        }
                    ],
                    "expected": "sparkSpec.getExecutor().getEnv() should be empty",
                    "description": "Test case where spark cluster executor has an empty environment variable list."
                }
            ]
        },
        {
            "group": "populateEnv - Submit Application Request Executor Environment with Empty Env",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "sparkSpec",
                            "value": "new SparkApplicationSpec()"
                        },
                        {
                            "parameter": "request",
                            "value": "new SubmitApplicationRequest() with executor having empty env"
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": "new AppConfig.SparkCluster()"
                        }
                    ],
                    "expected": "sparkSpec.getExecutor().getEnv() should be empty",
                    "description": "Test case where the executor environment from the submit application request is empty."
                }
            ]
        },
        {
            "group": "populateEnv - Both Driver and Executor Environments Present",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "sparkSpec",
                            "value": "new SparkApplicationSpec()"
                        },
                        {
                            "parameter": "request",
                            "value": "new SubmitApplicationRequest() with driver and executor having env"
                        },
                        {
                            "parameter": "sparkCluster",
                            "value": "new AppConfig.SparkCluster() with driver and executor having env"
                        }
                    ],
                    "expected": "sparkSpec.getDriver().getEnv() should contain env from both request and sparkCluster",
                    "description": "Test case where both driver and executor environments from the request and spark cluster are copied to spark spec."
                }
            ]
        }
    ]
}