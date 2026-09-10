{
    "group": "getDriverSpec - Missing Driver CPU Buffer Ratio",
    "cases": [
        {
            "input": [
                {
                    "parameter": "request",
                    "value": {
                        "driver": {
                            "cores": 4,
                            "memory": "4G"
                        },
                        "volumes": []
                    }
                },
                {
                    "parameter": "appConfig",
                    "value": {}
                },
                {
                    "parameter": "parentQueue",
                    "value": "testQueue"
                },
                {
                    "parameter": "sparkCluster",
                    "value": {
                        "sparkServiceAccount": "default"
                    }
                }
            ],
            "expected": {
                "cores": 4,
                "memory": "4G"
            },
            "description": "Test case where driver CPU buffer ratio is missing in appConfig, expecting default behavior."
        }
    ]
  }