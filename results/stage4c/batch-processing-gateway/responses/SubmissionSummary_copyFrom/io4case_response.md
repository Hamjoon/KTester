{
    "group": "SubmissionSummary.copyFrom(SparkApplication)",
    "cases": [
        {
            "input": [
                {
                    "parameter": "sparkApplicationResource",
                    "value": "SparkApplication with empty labels and driver specs"
                }
            ],
            "expected": "SubmissionId is set, other fields related to labels and driver specs remain default",
            "description": "Test when SparkApplication has empty labels and driver specs to ensure defaults are used where applicable."
        },
        {
            "input": [
                {
                    "parameter": "sparkApplicationResource",
                    "value": "SparkApplication with empty executor specs"
                }
            ],
            "expected": "Executor related fields remain default, other fields set correctly",
            "description": "Test when SparkApplication has empty executor specs to ensure defaults are used for executor fields."
        },
        {
            "input": [
                {
                    "parameter": "sparkApplicationResource",
                    "value": "SparkApplication with valid metadata and labels but null spec"
                }
            ],
            "expected": "SubmissionId and user set, other fields related to spec remain default",
            "description": "Test when SparkApplication has valid metadata and labels but the spec is null to ensure correct handling."
        },
        {
            "input": [
                {
                    "parameter": "sparkApplicationResource",
                    "value": "SparkApplication with driver memory and executor memory set to zero"
                }
            ],
            "expected": "Driver and executor memory fields set to 0, total memory calculated correctly",
            "description": "Test when driver and executor memory are explicitly set to zero to ensure correct total memory calculation."
        },
        {
            "input": [
                {
                    "parameter": "sparkApplicationResource",
                    "value": "SparkApplication with null and empty strings in labels"
                }
            ],
            "expected": "Fields related to labels set to defaults or empty strings, no exceptions",
            "description": "Test when labels contain null and empty strings to ensure method handles this gracefully."
        }
    ]
}