{
    "group": "copyFrom - Exception Scenarios",
    "cases": [
        {
            "input": [
                {
                    "parameter": "another",
                    "value": null
                }
            ],
            "expected": "NullPointerException",
            "description": "Test copying from a null SparkPodSpec. The method should throw a NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "another",
                    "value": {
                        "cores": 2,
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
            "description": "Test copying from another SparkPodSpec where coreLimit is null. The method should not throw an exception and should handle the null value gracefully."
        }
    ]
}