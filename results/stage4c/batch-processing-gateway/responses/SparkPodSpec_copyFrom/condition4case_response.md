{
    "group": "copyFrom Method Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "another",
                    "value": {
                        "cores": 4,
                        "coreLimit": "2000m",
                        "memory": "4Gi",
                        "memoryOverhead": "500Mi",
                        "image": "spark:latest",
                        "env": [{"name": "ENV_VAR", "value": "value"}],
                        "labels": {"app": "spark"},
                        "annotations": {"annotation1": "value1"},
                        "terminationGracePeriodSeconds": 30,
                        "serviceAccount": "spark-sa",
                        "volumeMounts": [{"name": "volume1", "mountPath": "/data"}],
                        "securityContext": {"runAsUser": 1000},
                        "affinity": {"nodeAffinity": {"requiredDuringSchedulingIgnoredDuringExecution": {}}},
                        "coreRequest": "1000m",
                        "javaOptions": "-Xmx2g"
                    }
                }
            ],
            "expected": "All fields copied correctly",
            "description": "Test copying from a fully populated SparkPodSpec object to a new instance."
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
            "expected": "All fields set to null or default",
            "description": "Test copying from a SparkPodSpec object where all fields are null."
        },
        {
            "input": [
                {
                    "parameter": "another",
                    "value": {
                        "cores": 4,
                        "coreLimit": null,
                        "memory": "4Gi",
                        "memoryOverhead": null,
                        "image": "spark:latest",
                        "env": [],
                        "labels": {},
                        "annotations": {},
                        "terminationGracePeriodSeconds": null,
                        "serviceAccount": null,
                        "volumeMounts": [],
                        "securityContext": null,
                        "affinity": null,
                        "coreRequest": null,
                        "javaOptions": null
                    }
                }
            ],
            "expected": "Partial fields copied, null fields remain unchanged",
            "description": "Test copying from a SparkPodSpec object with a mix of populated and null fields."
        },
        {
            "input": [
                {
                    "parameter": "another",
                    "value": {
                        "cores": 8,
                        "coreLimit": "3000m",
                        "memory": "8Gi",
                        "memoryOverhead": "1Gi",
                        "image": "spark:v2",
                        "env": [{"name": "ENV_VAR_NEW", "value": "new_value"}],
                        "labels": {"app": "spark-new"},
                        "annotations": {"annotation2": "value2"},
                        "terminationGracePeriodSeconds": 60,
                        "serviceAccount": "spark-sa-new",
                        "volumeMounts": [{"name": "volume2", "mountPath": "/newdata"}],
                        "securityContext": {"runAsUser": 2000},
                        "affinity": {"podAffinity": {"requiredDuringSchedulingIgnoredDuringExecution": {}}},
                        "coreRequest": "2000m",
                        "javaOptions": "-Xmx4g"
                    }
                }
            ],
            "expected": "All fields updated correctly",
            "description": "Test copying from a fully populated SparkPodSpec object with different values to ensure all fields are updated."
        }
    ]
}