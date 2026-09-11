{
    "testCases": [
        {
            "group": "printDocument - Root Element Exists",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "StringWriter"
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
                    "expected": "Output with content of the document",
                    "description": "Test printDocument when the document has a root element."
                }
            ]
        },
        {
            "group": "printDocument - Empty Document",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "StringWriter"
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
                            "value": "Document with no root element"
                        }
                    ],
                    "expected": "Empty output and no exceptions thrown",
                    "description": "Test printDocument when the document has no root element."
                }
            ]
        },
        {
            "group": "printDocument - Walker has Next",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "StringWriter"
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
                            "value": "Document with multiple content types including Comment, Element, Text"
                        }
                    ],
                    "expected": "Output includes comments, elements, and text",
                    "description": "Test printDocument when the walker has multiple content types to process."
                }
            ]
        },
        {
            "group": "printDocument - Handle Null Content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "StringWriter"
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
                            "value": "Document with a root element and null content"
                        }
                    ],
                    "expected": "Output handles null content gracefully without exceptions",
                    "description": "Test printDocument when the content includes null values."
                }
            ]
        },
        {
            "group": "printDocument - Whitespace Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "StringWriter"
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
                            "value": "Document with whitespace content"
                        }
                    ],
                    "expected": "Output includes only whitespace without CDATA",
                    "description": "Test printDocument when the content is only whitespace."
                }
            ]
        },
        {
            "group": "printDocument - Line Separator Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "StringWriter"
                        },
                        {
                            "parameter": "fstack",
                            "value": "FormatStack with line separator"
                        },
                        {
                            "parameter": "nstack",
                            "value": "NamespaceStack with valid namespaces"
                        },
                        {
                            "parameter": "doc",
                            "value": "Document with a root element and content"
                        }
                    ],
                    "expected": "Output ends with the specified line separator",
                    "description": "Test printDocument when the format stack has a line separator."
                }
            ]
        }
    ]
}