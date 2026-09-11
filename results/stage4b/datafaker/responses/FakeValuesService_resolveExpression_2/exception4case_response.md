{
    "group": "resolveExpression Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "#{name} #{surname} #{unknownDirective} #{address.city}"
                },
                {
                    "parameter": "current",
                    "value": null
                },
                {
                    "parameter": "root",
                    "value": null
                },
                {
                    "parameter": "context",
                    "value": null
                }
            ],
            "expected": "RuntimeException: Unable to resolve #{unknownDirective} directive for FakerContext null.",
            "description": "Test with an unknown directive amidst valid directives to verify that it throws a RuntimeException."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "#{name} #{surname} #{address.street} #{address.city} #{address.country} #{unknownDirective}"
                },
                {
                    "parameter": "current",
                    "value": null
                },
                {
                    "parameter": "root",
                    "value": null
                },
                {
                    "parameter": "context",
                    "value": null
                }
            ],
            "expected": "RuntimeException: Unable to resolve #{unknownDirective} directive for FakerContext null.",
            "description": "Test with multiple valid directives and one unknown directive to ensure it throws RuntimeException."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "#{name} #{surname} #{address.city} #{address.country} #{unknownDirective1} #{unknownDirective2}"
                },
                {
                    "parameter": "current",
                    "value": null
                },
                {
                    "parameter": "root",
                    "value": null
                },
                {
                    "parameter": "context",
                    "value": null
                }
            ],
            "expected": "RuntimeException: Unable to resolve #{unknownDirective1} directive for FakerContext null.",
            "description": "Test with multiple unknown directives to verify that it throws RuntimeException for the first unresolved directive."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "#{unknownDirective}"
                },
                {
                    "parameter": "current",
                    "value": null
                },
                {
                    "parameter": "root",
                    "value": null
                },
                {
                    "parameter": "context",
                    "value": null
                }
            ],
            "expected": "RuntimeException: Unable to resolve #{unknownDirective} directive for FakerContext null.",
            "description": "Test with a single unknown directive to verify that it throws a RuntimeException."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "#{name} #{surname} #{address.street} #{unknownDirective} #{address.city}"
                },
                {
                    "parameter": "current",
                    "value": null
                },
                {
                    "parameter": "root",
                    "value": null
                },
                {
                    "parameter": "context",
                    "value": null
                }
            ],
            "expected": "RuntimeException: Unable to resolve #{unknownDirective} directive for FakerContext null.",
            "description": "Test with valid directives and an unknown directive to ensure it throws RuntimeException."
        }
    ]
}