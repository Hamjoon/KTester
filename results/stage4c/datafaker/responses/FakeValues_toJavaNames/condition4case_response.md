{
    "group": "toJavaNames method tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "string",
                    "value": null
                },
                {
                    "parameter": "isMethod",
                    "value": true
                }
            ],
            "expected": null,
            "description": "Test with null string input, should return null regardless of isMethod value."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": ""
                },
                {
                    "parameter": "isMethod",
                    "value": false
                }
            ],
            "expected": "",
            "description": "Test with empty string input, should return empty string regardless of isMethod value."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "SimpleTest"
                },
                {
                    "parameter": "isMethod",
                    "value": false
                }
            ],
            "expected": "SimpleTest",
            "description": "Test with string having no underscores and isMethod false, should return the string unchanged."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "simpleTest"
                },
                {
                    "parameter": "isMethod",
                    "value": true
                }
            ],
            "expected": "simpleTest",
            "description": "Test with string having no underscores and isMethod true, should return the string unchanged."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "simple_test_case"
                },
                {
                    "parameter": "isMethod",
                    "value": true
                }
            ],
            "expected": "simpleTestCase",
            "description": "Test with string having underscores and isMethod true, should convert to camelCase with lowercase first letter."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "Simple_Test_Case"
                },
                {
                    "parameter": "isMethod",
                    "value": false
                }
            ],
            "expected": "SimpleTestCase",
            "description": "Test with string having underscores and isMethod false, should convert to camelCase with uppercase first letter."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "_leading_underscore"
                },
                {
                    "parameter": "isMethod",
                    "value": true
                }
            ],
            "expected": "LeadingUnderscore",
            "description": "Test with string having leading underscore and isMethod true, should ignore leading underscore and capitalize next letter."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "trailing_underscore_"
                },
                {
                    "parameter": "isMethod",
                    "value": false
                }
            ],
            "expected": "TrailingUnderscore",
            "description": "Test with string having trailing underscore and isMethod false, should ignore trailing underscore."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "MULTIPLE__UNDERSCORES"
                },
                {
                    "parameter": "isMethod",
                    "value": true
                }
            ],
            "expected": "multipleUnderscores",
            "description": "Test with string having multiple consecutive underscores and isMethod true, should convert to camelCase ignoring extra underscores."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "alreadyCamelCase"
                },
                {
                    "parameter": "isMethod",
                    "value": true
                }
            ],
            "expected": "alreadyCamelCase",
            "description": "Test with string already in camelCase and isMethod true, should return the string unchanged."
        }
    ]
}