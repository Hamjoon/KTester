{
    "testCases": [
        {
            "group": "copyFrom - Cores",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "another",
                            "value": {
                                "cores": 4,
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
                                "coreRequest": null,
                                "javaOptions": null
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test copying from another SparkPodSpec with cores set to 4. The current instance should have cores set to 4."
                },
                {
                    "input": [
                        {
                            "parameter": "another",
                            "value": {
                                "cores": null,
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
                                "coreRequest": null,
                                "javaOptions": null
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test copying from another SparkPodSpec with cores set to null. The current instance should retain its existing cores value."
                }
            ]
        },
        {
            "group": "copyFrom - Core Limit",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "another",
                            "value": {
                                "cores": null,
                                "coreLimit": "2",
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
                                "coreRequest": null,
                                "javaOptions": null
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test copying from another SparkPodSpec with coreLimit set to '2'. The current instance should have coreLimit set to '2'."
                }
            ]
        },
        {
            "group": "copyFrom - Memory",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "another",
                            "value": {
                                "cores": null,
                                "coreLimit": null,
                                "memory": "512Mi",
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
                                "coreRequest": null,
                                "javaOptions": null
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test copying from another SparkPodSpec with memory set to '512Mi'. The current instance should have memory set to '512Mi'."
                }
            ]
        },
        {
            "group": "copyFrom - Environment Variables",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "another",
                            "value": {
                                "cores": null,
                                "coreLimit": null,
                                "memory": null,
                                "memoryOverhead": null,
                                "image": null,
                                "env": [ { "name": "ENV_VAR", "value": "value" } ],
                                "labels": null,
                                "annotations": null,
                                "terminationGracePeriodSeconds": null,
                                "serviceAccount": null,
                                "volumeMounts": null,
                                "securityContext": null,
                                "affinity": null,
                                "coreRequest": null,
                                "javaOptions": null
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test copying from another SparkPodSpec with env set. The current instance should have env set to a new list containing the copied environment variables."
                },
                {
                    "input": [
                        {
                            "parameter": "another",
                            "value": {
                                "cores": null,
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
                                "coreRequest": null,
                                "javaOptions": null
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test copying from another SparkPodSpec with env set to null. The current instance should have env set to null."
                }
            ]
        },
        {
            "group": "copyFrom - Labels and Annotations",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "another",
                            "value": {
                                "cores": null,
                                "coreLimit": null,
                                "memory": null,
                                "memoryOverhead": null,
                                "image": null,
                                "env": null,
                                "labels": { "key": "value" },
                                "annotations": { "annotationKey": "annotationValue" },
                                "terminationGracePeriodSeconds": null,
                                "serviceAccount": null,
                                "volumeMounts": null,
                                "securityContext": null,
                                "affinity": null,
                                "coreRequest": null,
                                "javaOptions": null
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test copying from another SparkPodSpec with labels and annotations set. The current instance should have labels and annotations copied correctly."
                },
                {
                    "input": [
                        {
                            "parameter": "another",
                            "value": {
                                "cores": null,
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
                                "coreRequest": null,
                                "javaOptions": null
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test copying from another SparkPodSpec with labels and annotations set to null. The current instance should have labels and annotations set to null."
                }
            ]
        },
        {
            "group": "copyFrom - Volume Mounts",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "another",
                            "value": {
                                "cores": null,
                                "coreLimit": null,
                                "memory": null,
                                "memoryOverhead": null,
                                "image": null,
                                "env": null,
                                "labels": null,
                                "annotations": null,
                                "terminationGracePeriodSeconds": null,
                                "serviceAccount": null,
                                "volumeMounts": [ { "mountPath": "/mnt", "name": "volume" } ],
                                "securityContext": null,
                                "affinity": null,
                                "coreRequest": null,
                                "javaOptions": null
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test copying from another SparkPodSpec with volumeMounts set. The current instance should have volumeMounts set to a new list containing the copied volume mounts."
                },
                {
                    "input": [
                        {
                            "parameter": "another",
                            "value": {
                                "cores": null,
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
                                "coreRequest": null,
                                "javaOptions": null
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test copying from another SparkPodSpec with volumeMounts set to null. The current instance should have volumeMounts set to null."
                }
            ]
        },
        {
            "group": "copyFrom - Other Fields",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "another",
                            "value": {
                                "cores": null,
                                "coreLimit": null,
                                "memory": null,
                                "memoryOverhead": null,
                                "image": "spark-image",
                                "env": null,
                                "labels": null,
                                "annotations": null,
                                "terminationGracePeriodSeconds": 30,
                                "serviceAccount": "default",
                                "volumeMounts": null,
                                "securityContext": null,
                                "affinity": null,
                                "coreRequest": null,
                                "javaOptions": "-Djava.security.egd=file:/dev/./urandom"
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test copying from another SparkPodSpec with image, terminationGracePeriodSeconds, serviceAccount, and javaOptions set. The current instance should have these fields copied correctly."
                }
            ]
        }
    ]
}