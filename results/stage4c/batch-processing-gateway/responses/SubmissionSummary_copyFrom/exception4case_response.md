{
    "group": "SubmissionSummary.copyFrom(SparkApplication)",
    "cases": [
        {
            "input": [
                {
                    "parameter": "sparkApplicationResource",
                    "value": "SparkApplication with null spec"
                }
            ],
            "expected": "No exception, fields related to spec remain default",
            "description": "Test when SparkApplication has null spec to ensure method handles it gracefully."
        },
        {
            "input": [
                {
                    "parameter": "sparkApplicationResource",
                    "value": "SparkApplication with null driver and executor memory"
                }
            ],
            "expected": "Driver and executor memory fields remain default, no exceptions",
            "description": "Test when driver and executor memory are null to ensure method handles it without exceptions."
        },
        {
            "input": [
                {
                    "parameter": "sparkApplicationResource",
                    "value": "SparkApplication with null driver and executor cores"
                }
            ],
            "expected": "Driver and executor core fields remain default, no exceptions",
            "description": "Test when driver and executor cores are null to ensure method handles it without exceptions."
        }
    ]
}