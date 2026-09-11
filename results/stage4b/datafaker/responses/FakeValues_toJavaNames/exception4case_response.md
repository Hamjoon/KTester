{
    "group": "toJavaNames - Invalid Input Scenarios",
    "cases": [
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "test_string_example_"
                },
                {
                    "parameter": "isMethod",
                    "value": false
                }
            ],
            "expected": "TestStringExample",
            "description": "Test with a string that ends with an underscore, expecting the output to remove the trailing underscore and capitalize the first letter."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "test_string_example_"
                },
                {
                    "parameter": "isMethod",
                    "value": true
                }
            ],
            "expected": "testStringExample",
            "description": "Test with a string that ends with an underscore for a method, expecting the output to remove the trailing underscore and keep the first letter lowercase."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "1_invalid_start"
                },
                {
                    "parameter": "isMethod",
                    "value": false
                }
            ],
            "expected": "1InvalidStart",
            "description": "Test with a string that starts with a number, expecting the output to retain the number and capitalize the first letter after the underscore."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "1_invalid_start"
                },
                {
                    "parameter": "isMethod",
                    "value": true
                }
            ],
            "expected": "1_invalidStart",
            "description": "Test with a string that starts with a number for a method, expecting the output to retain the number and keep the first letter after the underscore lowercase."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "___multiple_leading_underscores"
                },
                {
                    "parameter": "isMethod",
                    "value": false
                }
            ],
            "expected": "MultipleLeadingUnderscores",
            "description": "Test with a string that has multiple leading underscores, expecting the output to remove the leading underscores and capitalize the first letter."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "___multiple_leading_underscores"
                },
                {
                    "parameter": "isMethod",
                    "value": true
                }
            ],
            "expected": "multipleLeadingUnderscores",
            "description": "Test with a string that has multiple leading underscores for a method, expecting the output to remove the leading underscores and keep the first letter lowercase."
        }
    ]
}