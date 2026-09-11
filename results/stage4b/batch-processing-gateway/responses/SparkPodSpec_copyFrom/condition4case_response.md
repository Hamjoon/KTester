{
    "testCases": [
        {
            "group": "copyFrom method tests",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "another",
                            "value": {
                                "cores": 4,
                                "coreRequest": "2",
                                "coreLimit": "4",
                                "memory": "8Gi",
                                "memoryOverhead": "1Gi",
                                "image": "spark:latest",
                                "env": [],
                                "labels": {},
                                "annotations": {},
                                "terminationGracePeriodSeconds": 30,
                                "serviceAccount": "default",
                                "volumeMounts": [],
                                "securityContext": null,
                                "affinity": null,
                                "javaOptions": "-Dconfig.file=application.conf"
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test copying from another SparkPodSpec with all fields populated."
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
                                "labels": null,
                                "annotations": null,
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
                    "description": "Test copying from another SparkPodSpec with all fields set to null."
                },
                {
                    "input": [
                        {
                            "parameter": "another",
                            "value": {
                                "cores": 2,
                                "coreRequest": null,
                                "coreLimit": null,
                                "memory": "4Gi",
                                "memoryOverhead": null,
                                "image": null,
                                "env": null,
                                "labels": null,
                                "annotations": null,
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
                    "description": "Test copying from another SparkPodSpec with only cores and memory set."
                },
                {
                    "input": [
                        {
                            "parameter": "another",
                            "value": {
                                "cores": 3,
                                "coreRequest": "1",
                                "coreLimit": "3",
                                "memory": "2Gi",
                                "memoryOverhead": "0.5Gi",
                                "image": "spark:2.4.0",
                                "env": null,
                                "labels": null,
                                "annotations": null,
                                "terminationGracePeriodSeconds": 60,
                                "serviceAccount": null,
                                "volumeMounts": null,
                                "securityContext": null,
                                "affinity": null,
                                "javaOptions": null
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test copying from another SparkPodSpec with partial fields set."
                },
                {
                    "input": [
                        {
                            "parameter": "another",
                            "value": {
                                "cores": 5,
                                "coreRequest": "2",
                                "coreLimit": "5",
                                "memory": "16Gi",
                                "memoryOverhead": "2Gi",
                                "image": null,
                                "env": null,
                                "labels": null,
                                "annotations": null,
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
                    "description": "Test copying from another SparkPodSpec with some fields null and others set."
                }
            ]
        }
    ]
}