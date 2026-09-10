{
    "group": "resolveExpression",
    "cases": [
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "#{}"
                },
                {
                    "parameter": "current",
                    "value": null
                },
                {
                    "parameter": "root",
                    "value": "ProviderRegistration instance"
                },
                {
                    "parameter": "context",
                    "value": "FakerContext instance"
                }
            ],
            "expected": "RuntimeException",
            "description": "Test with an empty expression directive. The method should throw a RuntimeException indicating the inability to resolve the empty directive."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "#{name.firstName} #{name.lastName} #{invalid.directive} #{invalid.directive2}"
                },
                {
                    "parameter": "current",
                    "value": null
                },
                {
                    "parameter": "root",
                    "value": "ProviderRegistration instance"
                },
                {
                    "parameter": "context",
                    "value": "FakerContext instance"
                }
            ],
            "expected": "RuntimeException",
            "description": "Test with multiple invalid directives in the expression. The method should throw a RuntimeException due to the invalid directives."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "#{name.firstName} #{name.lastName} #{address.city} #{address.invalidField}"
                },
                {
                    "parameter": "current",
                    "value": null
                },
                {
                    "parameter": "root",
                    "value": "ProviderRegistration instance"
                },
                {
                    "parameter": "context",
                    "value": "FakerContext instance"
                }
            ],
            "expected": "RuntimeException",
            "description": "Test with a valid expression and an invalid field at the end. The method should throw a RuntimeException due to the invalid directive."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "#{name.firstName} #{address.invalidField} #{name.lastName}"
                },
                {
                    "parameter": "current",
                    "value": null
                },
                {
                    "parameter": "root",
                    "value": "ProviderRegistration instance"
                },
                {
                    "parameter": "context",
                    "value": "FakerContext instance"
                }
            ],
            "expected": "RuntimeException",
            "description": "Test with an expression where the invalid field is in the middle. The method should throw a RuntimeException due to the invalid directive."
        }
    ]
}