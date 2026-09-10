{
    "output": [
        {
            "group": "printContent with SAXException",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "SAXTarget",
                            "value": "mockSAXTarget"
                        },
                        {
                            "parameter": "FormatStack",
                            "value": "mockFormatStack"
                        },
                        {
                            "parameter": "NamespaceStack",
                            "value": "mockNamespaceStack"
                        },
                        {
                            "parameter": "Walker",
                            "value": "mockWalkerThatThrowsSAXException"
                        }
                    ],
                    "expected": "SAXException is thrown",
                    "description": "Test printContent when Walker throws SAXException while processing Content."
                }
            ]
        },
        {
            "group": "printContent with IOException",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "SAXTarget",
                            "value": "mockSAXTarget"
                        },
                        {
                            "parameter": "FormatStack",
                            "value": "mockFormatStack"
                        },
                        {
                            "parameter": "NamespaceStack",
                            "value": "mockNamespaceStack"
                        },
                        {
                            "parameter": "Walker",
                            "value": "mockWalkerThatThrowsIOException"
                        }
                    ],
                    "expected": "IOException is thrown",
                    "description": "Test printContent when Walker throws IOException while processing Content."
                }
            ]
        },
        {
            "group": "printContent with NullPointerException",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "SAXTarget",
                            "value": "mockSAXTarget"
                        },
                        {
                            "parameter": "FormatStack",
                            "value": "mockFormatStack"
                        },
                        {
                            "parameter": "NamespaceStack",
                            "value": "mockNamespaceStack"
                        },
                        {
                            "parameter": "Walker",
                            "value": "mockWalkerThatReturnsNullContent"
                        }
                    ],
                    "expected": "NullPointerException is thrown",
                    "description": "Test printContent when Walker returns null Content leading to a NullPointerException."
                }
            ]
        }
    ]
}