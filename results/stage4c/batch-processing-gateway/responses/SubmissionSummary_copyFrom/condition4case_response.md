{
    "group": "SubmissionSummary.copyFrom(SparkApplication)",
    "cases": [
        {
            "input": [
                {
                    "parameter": "sparkApplicationResource",
                    "value": "SparkApplication with non-null metadata and labels"
                }
            ],
            "expected": "All fields set correctly based on the input SparkApplication",
            "description": "Test when SparkApplication has all necessary metadata and label fields populated."
        },
        {
            "input": [
                {
                    "parameter": "sparkApplicationResource",
                    "value": "SparkApplication with null metadata"
                }
            ],
            "expected": "No fields set, method handles null metadata gracefully",
            "description": "Test when SparkApplication has null metadata to ensure the method handles it gracefully."
        },
        {
            "input": [
                {
                    "parameter": "sparkApplicationResource",
                    "value": "SparkApplication with metadata but null labels"
                }
            ],
            "expected": "SubmissionId is set, other fields related to labels remain default",
            "description": "Test when SparkApplication has metadata but labels are null to check correct behavior."
        },
        {
            "input": [
                {
                    "parameter": "sparkApplicationResource",
                    "value": "SparkApplication with labels but missing specific keys"
                }
            ],
            "expected": "Fields related to missing labels remain default, others set correctly",
            "description": "Test when SparkApplication has labels but specific keys like QUEUE_LABEL are missing."
        },
        {
            "input": [
                {
                    "parameter": "sparkApplicationResource",
                    "value": "SparkApplication with driver and executor memory not convertible"
                }
            ],
            "expected": "Driver and executor memory fields set to 0 with log message",
            "description": "Test when memory strings for driver and executor are not convertible to GB, check logging and default values."
        },
        {
            "input": [
                {
                    "parameter": "sparkApplicationResource",
                    "value": "SparkApplication with null driver and executor specs"
                }
            ],
            "expected": "Driver and executor related fields remain default",
            "description": "Test when driver and executor specs in SparkApplication are null, ensure defaults are used."
        },
        {
            "input": [
                {
                    "parameter": "sparkApplicationResource",
                    "value": "SparkApplication with valid spec but null sparkConf"
                }
            ],
            "expected": "SparkVersion and arguments set, application name remains default",
            "description": "Test when SparkApplication spec is valid but sparkConf is null, check correct field assignments."
        },
        {
            "input": [
                {
                    "parameter": "sparkApplicationResource",
                    "value": "SparkApplication with valid sparkConf but missing SPARK_APP_NAME_CONFIG"
                }
            ],
            "expected": "Application name remains default, other fields set correctly",
            "description": "Test when SparkConf is present but does not contain SPARK_APP_NAME_CONFIG."
        }
    ]
}