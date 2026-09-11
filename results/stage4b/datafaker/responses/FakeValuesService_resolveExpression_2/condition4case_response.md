{
    "group": "resolveExpression Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "Hello, World!"
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
            "expected": "Hello, World!",
            "description": "Test with a simple string expression that does not contain any directives."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "Hello, #{name}!"
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
            "expected": "Hello, John Doe!",  "description": "Test with a directive that should resolve to a name."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "No directives here."
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
            "expected": "No directives here.",
            "description": "Test with a string that does not contain any directives."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "Hello, #{name} #{surname}!"
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
            "expected": "Hello, John Doe!",
            "description": "Test with multiple directives that should resolve to a name and surname."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "Hello, #{unknownDirective}!"
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
            "description": "Test with an unknown directive to verify that it throws a RuntimeException."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "Hello, #{name} #{address.street}!"
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
            "expected": "Hello, John Doe 123 Main St!",
            "description": "Test with nested directives to verify that they resolve correctly."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "Just a string"
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
            "expected": "Just a string",
            "description": "Test with a string that contains no directives and should return as is."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "#{name} #{surname} #{address.city} #{address.country}"
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
            "expected": "John Doe New York USA",
            "description": "Test with multiple directives to verify that all resolve correctly and concatenate."
        }
    ]
}