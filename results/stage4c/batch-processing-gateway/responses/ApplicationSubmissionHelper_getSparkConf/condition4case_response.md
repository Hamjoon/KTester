{
  "group": "getSparkConf Method Tests",
  "cases": [
    {
      "input": [
        {
          "parameter": "submissionId",
          "value": "sub-123"
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
              "spark.executor.memory": "1g",
              "spark.driver.memory": "1g"
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
      "expected": {
        "spark.executor.memory": "2g",
        "spark.driver.memory": "1g",
        "spark.executor.cores": "2",
        "spark.driver.cores": "1",
        "spark.app.name": "TestApp"
      },
      "description": "Test with all configurations provided to ensure priority order is followed and all branches are covered."
    },
    {
      "input": [
        {
          "parameter": "submissionId",
          "value": "sub-456"
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
      "expected": null,
      "description": "Test with all configurations set to null to ensure method handles empty inputs gracefully."
    },
    {
      "input": [
        {
          "parameter": "submissionId",
          "value": "sub-789"
        },
        {
          "parameter": "request",
          "value": {
            "sparkConf": {},
            "applicationName": "AppWithoutConf"
          }
        },
        {
          "parameter": "appConfig",
          "value": {
            "defaultSparkConf": {
              "spark.executor.memory": "4g"
            },
            "fixedSparkConf": {
              "spark.executor.cores": "4"
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
        "spark.executor.memory": "4g",
        "spark.executor.cores": "4",
        "spark.app.name": "AppWithoutConf"
      },
      "description": "Test with no sparkConf in request and sparkCluster but with default and fixed configurations in appConfig."
    },
    {
      "input": [
        {
          "parameter": "submissionId",
          "value": "sub-101"
        },
        {
          "parameter": "request",
          "value": {
            "sparkConf": {
              "spark.executor.memory": "3g"
            },
            "applicationName": ""
          }
        },
        {
          "parameter": "appConfig",
          "value": {
            "defaultSparkConf": {
              "spark.executor.memory": "2g"
            },
            "fixedSparkConf": {
              "spark.driver.memory": "1g"
            }
          }
        },
        {
          "parameter": "sparkCluster",
          "value": {
            "sparkConf": {
              "spark.executor.cores": "3"
            }
          }
        }
      ],
      "expected": {
        "spark.executor.memory": "3g",
        "spark.driver.memory": "1g",
        "spark.executor.cores": "3"
      },
      "description": "Test with empty application name to ensure it does not get added to the configuration."
    }
  ]
}