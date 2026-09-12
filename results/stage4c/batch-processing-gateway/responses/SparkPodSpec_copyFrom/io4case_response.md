{
    "group": "copyFrom Method Additional Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "another",
                    "value": {
                        "cores": 0,
                        "coreLimit": "0m",
                        "memory": "0Gi",
                        "memoryOverhead": "0Mi",
                        "image": "",
                        "env": [],
                        "labels": {},
                        "annotations": {},
                        "terminationGracePeriodSeconds": 0,
                        "serviceAccount": "",
                        "volumeMounts": [],
                        "securityContext": null,
                        "affinity": null,
                        "coreRequest": "0m",
                        "javaOptions": ""
                    }
                }
            ],
            "expected": "All fields set to zero values or empty strings",
            "description": "Test copying from a SparkPodSpec object where fields are set to zero values or empty strings to verify handling of such edge cases."
        },
        {
            "input": [
                {
                    "parameter": "another",
                    "value": {
                        "cores": 2,
                        "coreLimit": "1500m",
                        "memory": "2Gi",
                        "memoryOverhead": "300Mi",
                        "image": "spark:v1",
                        "env": null,
                        "labels": null,
                        "annotations": null,
                        "terminationGracePeriodSeconds": 45,
                        "serviceAccount": null,
                        "volumeMounts": null,
                        "securityContext": null,
                        "affinity": null,
                        "coreRequest": "500m",
                        "javaOptions": null
                    }
                }
            ],
            "expected": "Non-null fields copied, null fields remain unchanged",
            "description": "Test copying from a SparkPodSpec object with a mix of populated fields and null fields to ensure proper copying behavior."
        },
        {
            "input": [
                {
                    "parameter": "another",
                    "value": {
                        "cores": 16,
                        "coreLimit": "4000m",
                        "memory": "16Gi",
                        "memoryOverhead": "2Gi",
                        "image": "spark:latest",
                        "env": [
                            {
                                "name": "ENV_VAR",
                                "value": "value"
                            },
                            {
                                "name": "ENV_VAR_EXTRA",
                                "value": "extra_value"
                            }
                        ],
                        "labels": {
                            "app": "spark",
                            "version": "v2"
                        },
                        "annotations": {
                            "annotation1": "value1",
                            "annotation3": "value3"
                        },
                        "terminationGracePeriodSeconds": 90,
                        "serviceAccount": "spark-sa-extra",
                        "volumeMounts": [
                            {
                                "name": "volume1",
                                "mountPath": "/data"
                            },
                            {
                                "name": "volume3",
                                "mountPath": "/extra"
                            }
                        ],
                        "securityContext": {
                            "runAsUser": 3000
                        },
                        "affinity": {
                            "nodeAffinity": {
                                "preferredDuringSchedulingIgnoredDuringExecution": {}
                            }
                        },
                        "coreRequest": "3000m",
                        "javaOptions": "-Xmx8g"
                    }
                }
            ],
            "expected": "All fields with complex structures copied correctly",
            "description": "Test copying from a SparkPodSpec object with complex structures in fields like env, labels, annotations, and volumeMounts to ensure deep copying."
        }
    ]
}