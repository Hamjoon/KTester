{
    "group": "printContent",
    "cases": [
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "SAXTarget instance"
                },
                {
                    "parameter": "fstack",
                    "value": "FormatStack instance"
                },
                {
                    "parameter": "nstack",
                    "value": "NamespaceStack instance"
                },
                {
                    "parameter": "walker",
                    "value": "Walker instance with hasNext() returning true and next() returning an empty text"
                }
            ],
            "expected": "printText is called with an empty Text instance",
            "description": "Test when Walker returns an empty text, ensuring printText is called with an empty Text instance."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "SAXTarget instance"
                },
                {
                    "parameter": "fstack",
                    "value": "FormatStack instance"
                },
                {
                    "parameter": "nstack",
                    "value": "NamespaceStack instance"
                },
                {
                    "parameter": "walker",
                    "value": "Walker instance with hasNext() returning true and next() returning multiple mixed Content types in sequence"
                }
            ],
            "expected": "Appropriate print methods are called for each Content type in sequence",
            "description": "Test when Walker returns multiple mixed Content types in sequence (e.g., Text, Element, Comment), ensuring each type triggers the appropriate print method."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "SAXTarget instance"
                },
                {
                    "parameter": "fstack",
                    "value": "FormatStack instance"
                },
                {
                    "parameter": "nstack",
                    "value": "NamespaceStack instance"
                },
                {
                    "parameter": "walker",
                    "value": "Walker instance with hasNext() returning true and next() returning a deeply nested Element"
                }
            ],
            "expected": "printElement is called correctly handling nested structure",
            "description": "Test when Walker returns a deeply nested Element, ensuring printElement is called, and the nested structure is properly handled."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "SAXTarget instance"
                },
                {
                    "parameter": "fstack",
                    "value": "FormatStack instance"
                },
                {
                    "parameter": "nstack",
                    "value": "NamespaceStack instance"
                },
                {
                    "parameter": "walker",
                    "value": "Walker instance with hasNext() returning true and next() returns an Element with various namespaces"
                }
            ],
            "expected": "printElement is called with correct namespace handling",
            "description": "Test when Walker returns an Element with various namespaces, ensuring printElement is called with correct namespace handling."
        }
    ]
}