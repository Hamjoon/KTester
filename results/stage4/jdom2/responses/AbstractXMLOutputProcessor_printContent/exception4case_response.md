{
    "group": "printContent - IOException from Writer",
    "cases": [
        {
            "input": [
                {
                    "parameter": "Writer",
                    "value": "mocked Writer that throws IOException on write()"
                },
                {
                    "parameter": "FormatStack",
                    "value": "mocked FormatStack instance"
                },
                {
                    "parameter": "NamespaceStack",
                    "value": "mocked NamespaceStack instance"
                },
                {
                    "parameter": "Walker",
                    "value": "mocked Walker with hasNext() returning true, next() returning Text instance"
                }
            ],
            "expected": "IOException thrown",
            "description": "Test when Writer throws an IOException during writing, should propagate the exception."
        }
    ]
}