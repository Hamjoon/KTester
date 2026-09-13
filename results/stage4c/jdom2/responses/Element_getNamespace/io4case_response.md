{
    "group": "getNamespace Method Additional Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "prefix",
                    "value": ""
                }
            ],
            "expected": null,
            "description": "Test with an empty string as the prefix to verify that the method returns null when no matching namespace is found."
        },
        {
            "input": [
                {
                    "parameter": "prefix",
                    "value": "nonexistent"
                }
            ],
            "expected": null,
            "description": "Test with a prefix that does not match any in the current element or parent to ensure it returns null."
        },
        {
            "input": [
                {
                    "parameter": "prefix",
                    "value": "multiple"
                }
            ],
            "expected": "Namespace with prefix 'multiple'",
            "description": "Test with a prefix that matches multiple namespaces in additionalNamespaces to verify it returns the first match."
        },
        {
            "input": [
                {
                    "parameter": "prefix",
                    "value": "nested"
                }
            ],
            "expected": "Namespace from nested parent element",
            "description": "Test with a prefix that exists in a nested parent element to ensure it correctly navigates up the hierarchy to find the namespace."
        }
    ]
}