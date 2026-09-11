{
    "group": "printContent exception scenarios",
    "cases": [
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "null"
                },
                {
                    "parameter": "fstack",
                    "value": "Valid FormatStack"
                },
                {
                    "parameter": "nstack",
                    "value": "Valid NamespaceStack"
                },
                {
                    "parameter": "walker",
                    "value": "MockedWalker with hasNext returning true and next returning Element instance"
                }
            ],
            "expected": "SAXException is thrown due to null SAXTarget.",
            "description": "Test the printContent method when the SAXTarget is null, ensuring SAXException is thrown."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "Valid SAXTarget"
                },
                {
                    "parameter": "fstack",
                    "value": "null"
                },
                {
                    "parameter": "nstack",
                    "value": "Valid NamespaceStack"
                },
                {
                    "parameter": "walker",
                    "value": "MockedWalker with hasNext returning true and next returning Element instance"
                }
            ],
            "expected": "SAXException is thrown due to null FormatStack.",
            "description": "Test the printContent method when the FormatStack is null, ensuring SAXException is thrown."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "Valid SAXTarget"
                },
                {
                    "parameter": "fstack",
                    "value": "Valid FormatStack"
                },
                {
                    "parameter": "nstack",
                    "value": "null"
                },
                {
                    "parameter": "walker",
                    "value": "MockedWalker with hasNext returning true and next returning Element instance"
                }
            ],
            "expected": "SAXException is thrown due to null NamespaceStack.",
            "description": "Test the printContent method when the NamespaceStack is null, ensuring SAXException is thrown."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "Valid SAXTarget"
                },
                {
                    "parameter": "fstack",
                    "value": "Valid FormatStack"
                },
                {
                    "parameter": "nstack",
                    "value": "Valid NamespaceStack"
                },
                {
                    "parameter": "walker",
                    "value": "MockedWalker with hasNext returning true and next returning an invalid Content type"
                }
            ],
            "expected": "SAXException is thrown due to invalid Content type.",
            "description": "Test the printContent method when the walker returns an invalid Content type, ensuring SAXException is thrown."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "Valid SAXTarget"
                },
                {
                    "parameter": "fstack",
                    "value": "Valid FormatStack"
                },
                {
                    "parameter": "nstack",
                    "value": "Valid NamespaceStack"
                },
                {
                    "parameter": "walker",
                    "value": "MockedWalker with hasNext returning true and next returning Element instance that throws SAXException"
                }
            ],
            "expected": "SAXException is thrown due to SAXException in printElement.",
            "description": "Test the printContent method when the walker returns an Element instance that throws SAXException during processing."
        }
    ]
}