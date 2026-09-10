{
    "group": "copyFrom with invalid driver memory",
    "cases": [
        {
            "input": [
                {
                    "parameter": "sparkApplicationResource",
                    "value": {
                        "metadata": {
                            "name": "app12",
                            "labels": {
                                "PROXY_USER_LABEL": "user12"
                            }
                        },
                        "spec": {
                            "driver": {
                                "memory": "invalid_memory_string",
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
            "description": "Test copyFrom with an invalid driver memory string. Verify that the driver memory is set to 0 and an appropriate log message is generated."
        }
    ]
}