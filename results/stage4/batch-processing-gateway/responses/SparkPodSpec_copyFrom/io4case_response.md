{
    "test cases": [
        {
            "group": "copyFrom - Memory Overhead",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "another",
                            "value": {
                                "cores": null,
                                "coreLimit": null,
                                "memory": null,
                                "memoryOverhead": "256Mi",
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
                    "description": "Test copying from another SparkPodSpec with memoryOverhead set to '256Mi'. The current instance should have memoryOverhead set to '256Mi'."
                }
            ]
        },
        {
            "group": "copyFrom - Core Request",
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
                                "volumeMounts": null,
                                "securityContext": null,
                                "affinity": null,
                                "coreRequest": "1",
                                "javaOptions": null
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test copying from another SparkPodSpec with coreRequest set to '1'. The current instance should have coreRequest set to '1'."
                }
            ]
        },
        {
            "group": "copyFrom - Service Account",
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
                                "serviceAccount": "custom-account",
                                "volumeMounts": null,
                                "securityContext": null,
                                "affinity": null,
                                "coreRequest": null,
                                "javaOptions": null
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test copying from another SparkPodSpec with serviceAccount set to 'custom-account'. The current instance should have serviceAccount set to 'custom-account'."
                }
            ]
        },
        {
            "group": "copyFrom - Security Context",
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
                                "volumeMounts": null,
                                "securityContext": {"runAsUser": 1000, "fsGroup": 2000},
                                "affinity": null,
                                "coreRequest": null,
                                "javaOptions": null
                            }
                        }
                    ],
                    "expected": null,
                    "description": "Test copying from another SparkPodSpec with securityContext set. The current instance should have securityContext set to the copied value."
                }
            ]
        },
        {
            "group": "copyFrom - Affinity",
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
                                "volumeMounts": null,
                                "securityContext": null,
                                "affinity": {"nodeAffinity": {"requiredDuringSchedulingIgnoredDuringExecution": {"nodeSelectorTerms": [{"matchExpressions": [{"key": "kubernetes.io/e2e-az-name", "operator": "In", "values": ["e2e-az1"]}]}]}}}},
                                "coreRequest": null,
                                "javaOptions": null
                            }
                        ]
                    },
                    "expected"  , null,
                    "description"  , "Test copying from another SparkPodSpec with affinity set. The current instance should have affinity set to the copied value."
                ]
            }
        ]
    }