{
    "group": "printDocument - IOException Scenarios",
    "cases": [
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "Mocked Writer that throws IOException"
                },
                {
                    "parameter": "fstack",
                    "value": "FormatStack with valid settings"
                },
                {
                    "parameter": "nstack",
                    "value": "NamespaceStack with valid namespaces"
                },
                {
                    "parameter": "doc",
                    "value": "Document with a root element"
                }
            ],
            "expected": "Throws IOException",
            "description": "Test printDocument when the Writer throws IOException during write operations."
        }
    ]
}