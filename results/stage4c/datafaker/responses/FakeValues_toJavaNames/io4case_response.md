{
    "group": "toJavaNames method additional tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "ALLCAPITALS"
                },
                {
                    "parameter": "isMethod",
                    "value": true
                }
            ],
            "expected": "allcapitals",
            "description": "Test with all uppercase string and isMethod true, should convert first letter to lowercase."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "all_lowercase"
                },
                {
                    "parameter": "isMethod",
                    "value": false
                }
            ],
            "expected": "AllLowercase",
            "description": "Test with all lowercase string and isMethod false, should convert first letter to uppercase and camelCase the rest."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "Mixed_Case_With_Underscores"
                },
                {
                    "parameter": "isMethod",
                    "value": true
                }
            ],
            "expected": "mixedCaseWithUnderscores",
            "description": "Test with mixed case and underscores, isMethod true, should convert to camelCase with lowercase first letter."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "single"
                },
                {
                    "parameter": "isMethod",
                    "value": false
                }
            ],
            "expected": "Single",
            "description": "Test with single word and isMethod false, should capitalize the first letter."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "single"
                },
                {
                    "parameter": "isMethod",
                    "value": true
                }
            ],
            "expected": "single",
            "description": "Test with single word and isMethod true, should return the string unchanged."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "underscore_at_end_"
                },
                {
                    "parameter": "isMethod",
                    "value": true
                }
            ],
            "expected": "underscoreAtEnd",
            "description": "Test with underscore at the end and isMethod true, should ignore trailing underscore and camelCase the rest."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "underscore_at_start"
                },
                {
                    "parameter": "isMethod",
                    "value": false
                }
            ],
            "expected": "UnderscoreAtStart",
            "description": "Test with underscore at the start and isMethod false, should ignore leading underscore and capitalize the next letter."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "a"
                },
                {
                    "parameter": "isMethod",
                    "value": true
                }
            ],
            "expected": "a",
            "description": "Test with single character string and isMethod true, should return the string unchanged."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "A"
                },
                {
                    "parameter": "isMethod",
                    "value": false
                }
            ],
            "expected": "A",
            "description": "Test with single uppercase character and isMethod false, should return the string unchanged."
        }
    ]
}