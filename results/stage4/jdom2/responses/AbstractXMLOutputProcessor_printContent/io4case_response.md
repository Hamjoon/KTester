{
    "testCases": [
        {
            "group": "printContent - Multiple Content Types",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "Writer",
                            "value": "mocked Writer instance"
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
                            "value": "mocked Walker with multiple content types (CDATA, Comment, Element)"
                        }
                    ],
                    "expected": "printCDATA, printComment, and printElement called in sequence",
                    "description": "Test with Walker returning multiple content types in sequence, should call respective print methods for each content type."
                }
            ]
        },
        {
            "group": "printContent - Empty Content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "Writer",
                            "value": "mocked Writer instance"
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
                            "value": "mocked Walker with hasNext() returning false"
                        }
                    ],
                    "expected": "no exception thrown",
                    "description": "Test when Walker has no content, should not throw an exception and exit gracefully."
                }
            ]
        },
        {
            "group": "printContent - Exception Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "Writer",
                            "value": "mocked Writer instance"
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
                            "value": "mocked Walker that throws IOException on next()"
                        }
                    ],
                    "expected": "IOException thrown",
                    "description": "Test when Walker throws an IOException during iteration, should propagate the exception."
                }
            ]
        },
        {
            "group": "printContent - Text with Special Characters",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "Writer",
                            "value": "mocked Writer instance"
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
                            "value": "mocked Walker with hasNext() returning true, next() returning Text instance with special characters"
                        }
                    ],
                    "expected": "printText called with special characters handled correctly",
                    "description": "Test when Walker returns a Text content containing special characters, should call printText method and handle characters properly."
                }
            ]
        },
        {
            "group": "printContent - Mixed Content with Nulls",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "Writer",
                            "value": "mocked Writer instance"
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
                            "value": "mocked Walker with hasNext() returning true, next() returning null and then Text instance"
                        }
                    ],
                    "expected": "textRaw called with text value and printText called with Text instance",
                    "description": "Test when Walker returns null content followed by a Text instance, should call textRaw for null and printText for the Text instance."
                }
            ]
        }
    ]
}