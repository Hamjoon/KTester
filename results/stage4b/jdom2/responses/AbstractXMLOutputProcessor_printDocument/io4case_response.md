{
    "testCases": [
        {
            "group": "printDocument - Document with Mixed Content",
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
                            "value": "Document with mixed content types including Element, Comment, and Text"
                        }
                    ],
                    "expected": "Output includes elements, comments, and text in the correct order",
                    "description": "Test printDocument when the document has a mix of content types."
                }
            ]
        },
        {
            "group": "printDocument - Document with Multiple Root Elements",
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
                            "value": "Document with multiple root elements"
                        }
                    ],
                    "expected": "Throws IllegalDataException",
                    "description": "Test printDocument when the document has multiple root elements, which is invalid."
                }
            ]
        },
        {
            "group": "printDocument - Document with CDATA Section",
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
                            "value": "Document with a CDATA section"
                        }
                    ],
                    "expected": "Output includes CDATA section correctly formatted",
                    "description": "Test printDocument when the document contains a CDATA section."
                }
            ]
        },
        {
            "group": "printDocument - Document with Special Characters",
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
                            "value": "Document with special characters in text"
                        }
                    ],
                    "expected": "Output correctly escapes special characters",
                    "description": "Test printDocument when the document contains special characters that need to be escaped."
                }
            ]
        },
        {
            "group": "printDocument - Document with Empty Elements",
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
                            "value": "Document with empty elements"
                        }
                    ],
                    "expected": "Output includes empty elements correctly formatted",
                    "description": "Test printDocument when the document contains elements with no content."
                }
            ]
        }
    ]
}