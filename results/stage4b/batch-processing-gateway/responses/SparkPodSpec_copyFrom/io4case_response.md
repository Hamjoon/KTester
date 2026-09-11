{
    "test cases": [
        {
            "group": "copyFrom method tests",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "another",
                            "value": {
                                "cores": 0,
                                "coreRequest": "0",
                                "coreLimit": "0",
                                "memory": "0Gi",
                                "memoryOverhead": "0Gi",
                                "image": "empty:spark",
                                "env": [],
                                "labels": {},
                                "annotations": {},
                                "terminationGracePeriodSeconds": 0,
                                "serviceAccount": "default",
                                "volumeMounts": [],
                                "securityContext": null,
                                "affinity": null,
                                "javaOptions": ""
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test copying from another SparkPodSpec with all fields set to minimal values."
                },
                {
                    "input": [
                        {
                            "parameter": "another",
                            "value": {
                                "cores": 1,
                                "coreRequest": "1",
                                "coreLimit": "1",
                                "memory": "1Gi",
                                "memoryOverhead": "0.1Gi",
                                "image": "spark:1.0.0",
                                "env": null,
                                "labels": null,
                                "annotations": null,
                                "terminationGracePeriodSeconds": 15,
                                "serviceAccount": null,
                                "volumeMounts": null,
                                "securityContext": null,
                                "affinity": null,
                                "javaOptions": null
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test copying from another SparkPodSpec with minimal valid values for all fields except lists and maps."
                },
                {
                    "input": [
                        {
                            "parameter": "another",
                            "value": {
                                "cores": 2,
                                "coreRequest": "1",
                                "coreLimit": null,
                                "memory": "2Gi",
                                "memoryOverhead": "0.5Gi",
                                "image": "spark:latest",
                                "env": [],
                                "labels": {"key": "value"},
                                "annotations": null,
                                "terminationGracePeriodSeconds": 30,
                                "serviceAccount": null,
                                "volumeMounts": null,
                                "securityContext": null,
                                "affinity": null,
                                "javaOptions": null
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test copying from another SparkPodSpec with some fields set and others null, including a non-empty labels map."
                },
                {
                    "input": [
                        {
                            "parameter": "another",
                            "value": {
                                "cores": null,
                                "coreRequest": null,
                                "coreLimit": null,
                                "memory": null,
                                "memoryOverhead": null,
                                "image": null,
                                "env": null,
                                "labels": {},
                                "annotations": {},
                                "terminationGracePeriodSeconds": null,
                                "serviceAccount": null,
                                "volumeMounts": null,
                                "securityContext": null,
                                "affinity": null,
                                "javaOptions": null
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test copying from another SparkPodSpec with all fields set to null except for empty maps."
                },
                {
                    "input": [
                        {
                            "parameter": "another",
                            "value": {
                                "cores": 10,
                                "coreRequest": "5",
                                "coreLimit": "10",
                                "memory": "32Gi",
                                "memoryOverhead": "4Gi",
                                "image": "spark:3.0.0",
                                "env": null,
                                "labels": null,
                                "annotations": null,
                                "terminationGracePeriodSeconds": 120,
                                "serviceAccount": "admin",
                                "volumeMounts": null,
                                "securityContext": null,
                                "affinity": null,
                                "javaOptions": null
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test copying from another SparkPodSpec with maximum reasonable values for cores and memory."
                }
            ]
        }
    ]
}