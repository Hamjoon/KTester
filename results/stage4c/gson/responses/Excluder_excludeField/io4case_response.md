{
    "group": "excludeField Additional Test Cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "field",
                    "value": "exposeFieldWithDeserializeFalse"
                },
                {
                    "parameter": "serialize",
                    "value": false
                }
            ],
            "expected": true,
            "description": "Test case where the field has an Expose annotation with deserialize set to false, which should be excluded when serialize is false."
        },
        {
            "input": [
                {
                    "parameter": "field",
                    "value": "nonExposeFieldWithRequireExposeFalse"
                },
                {
                    "parameter": "serialize",
                    "value": true
                }
            ],
            "expected": false,
            "description": "Test case where the field does not have an Expose annotation and requireExpose is false, which should not be excluded."
        },
        {
            "input": [
                {
                    "parameter": "field",
                    "value": "innerClassFieldWithSerializationEnabled"
                },
                {
                    "parameter": "serialize",
                    "value": true
                }
            ],
            "expected": false,
            "description": "Test case where the field is an inner class and serializeInnerClasses is true, which should not be excluded."
        },
        {
            "input": [
                {
                    "parameter": "field",
                    "value": "fieldWithoutAnyAnnotationsOrModifiers"
                },
                {
                    "parameter": "serialize",
                    "value": false
                }
            ],
            "expected": false,
            "description": "Test case where the field does not have any annotations or modifiers and does not match any exclusion criteria, which should not be excluded."
        },
        {
            "input": [
                {
                    "parameter": "field",
                    "value": "classLevelExclusionStrategyField"
                },
                {
                    "parameter": "serialize",
                    "value": false
                }
            ],
            "expected": true,
            "description": "Test case where the field matches a class-level exclusion strategy, which should be excluded."
        }
    ]
}