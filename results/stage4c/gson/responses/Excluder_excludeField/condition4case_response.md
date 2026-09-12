{
    "group": "excludeField Test Cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "field",
                    "value": "transientField"
                },
                {
                    "parameter": "serialize",
                    "value": true
                }
            ],
            "expected": true,
            "description": "Test case where the field has a transient modifier, which should be excluded."
        },
        {
            "input": [
                {
                    "parameter": "field",
                    "value": "staticField"
                },
                {
                    "parameter": "serialize",
                    "value": true
                }
            ],
            "expected": true,
            "description": "Test case where the field has a static modifier, which should be excluded."
        },
        {
            "input": [
                {
                    "parameter": "field",
                    "value": "versionedField"
                },
                {
                    "parameter": "serialize",
                    "value": true
                }
            ],
            "expected": true,
            "description": "Test case where the field has a Since/Until annotation that does not match the current version, which should be excluded."
        },
        {
            "input": [
                {
                    "parameter": "field",
                    "value": "syntheticField"
                },
                {
                    "parameter": "serialize",
                    "value": true
                }
            ],
            "expected": true,
            "description": "Test case where the field is synthetic, which should be excluded."
        },
        {
            "input": [
                {
                    "parameter": "field",
                    "value": "exposeField"
                },
                {
                    "parameter": "serialize",
                    "value": true
                }
            ],
            "expected": false,
            "description": "Test case where the field has an Expose annotation with serialize set to true, which should not be excluded."
        },
        {
            "input": [
                {
                    "parameter": "field",
                    "value": "nonExposeField"
                },
                {
                    "parameter": "serialize",
                    "value": true
                }
            ],
            "expected": true,
            "description": "Test case where the field does not have an Expose annotation and requireExpose is true, which should be excluded."
        },
        {
            "input": [
                {
                    "parameter": "field",
                    "value": "innerClassField"
                },
                {
                    "parameter": "serialize",
                    "value": true
                }
            ],
            "expected": true,
            "description": "Test case where the field is an inner class and serializeInnerClasses is false, which should be excluded."
        },
        {
            "input": [
                {
                    "parameter": "field",
                    "value": "anonymousClassField"
                },
                {
                    "parameter": "serialize",
                    "value": true
                }
            ],
            "expected": true,
            "description": "Test case where the field is an anonymous or non-static local class, which should be excluded."
        },
        {
            "input": [
                {
                    "parameter": "field",
                    "value": "strategyField"
                },
                {
                    "parameter": "serialize",
                    "value": true
                }
            ],
            "expected": true,
            "description": "Test case where the field matches an exclusion strategy, which should be excluded."
        },
        {
            "input": [
                {
                    "parameter": "field",
                    "value": "nonExcludedField"
                },
                {
                    "parameter": "serialize",
                    "value": true
                }
            ],
            "expected": false,
            "description": "Test case where the field does not match any exclusion criteria, which should not be excluded."
        }
    ]
}