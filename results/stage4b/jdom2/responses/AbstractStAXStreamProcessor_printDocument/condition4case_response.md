{
    "testCases": [
        {
            "group": "printDocument - Omit Declaration",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mock XMLStreamWriter that captures output"
                        },
                        {
                            "parameter": "fstack",
                            "value": "FormatStack with isOmitDeclaration() returning true"
                        },
                        {
                            "parameter": "nstack",
                            "value": "NamespaceStack"
                        },
                        {
                            "parameter": "doc",
                            "value": "Document with a root element"
                        }
                    ],
                    "expected": "Start document with default version and encoding",
                    "description": "Test printDocument when fstack omits declaration, ensuring start document is called with defaults."
                }
            ]
        },
        {
            "group": "printDocument - Omit Encoding",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mock XMLStreamWriter that captures output"
                        },
                        {
                            "parameter": "fstack",
                            "value": "FormatStack with isOmitEncoding() returning true"
                        },
                        {
                            "parameter": "nstack",
                            "value": "NamespaceStack"
                        },
                        {
                            "parameter": "doc",
                            "value": "Document with a root element"
                        }
                    ],
                    "expected": "Start document with version 1.0 and no encoding",
                    "description": "Test printDocument when fstack omits encoding, ensuring start document is called with version 1.0."
                }
            ]
        },
        {
            "group": "printDocument - Full Declaration",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mock XMLStreamWriter that captures output"
                        },
                        {
                            "parameter": "fstack",
                            "value": "FormatStack with encoding set to UTF-8"
                        },
                        {
                            "parameter": "nstack",
                            "value": "NamespaceStack"
                        },
                        {
                            "parameter": "doc",
                            "value": "Document with a root element"
                        }
                    ],
                    "expected": "Start document with UTF-8 encoding and version 1.0",
                    "description": "Test printDocument when no flags are omitted, ensuring start document is called with specific encoding."
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
                            "value": "mock XMLStreamWriter that captures output"
                        },
                        {
                            "parameter": "fstack",
                            "value": "FormatStack with default settings"
                        },
                        {
                            "parameter": "nstack",
                            "value": "NamespaceStack"
                        },
                        {
                            "parameter": "doc",
                            "value": "Document with no root element"
                        }
                    ],
                    "expected": "No content written, only end document called",
                    "description": "Test printDocument with an empty document, ensuring no content is written and only end document is called."
                }
            ]
        },
        {
            "group": "printDocument - Content Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mock XMLStreamWriter that captures output"
                        },
                        {
                            "parameter": "fstack",
                            "value": "FormatStack with default settings"
                        },
                        {
                            "parameter": "nstack",
                            "value": "NamespaceStack"
                        },
                        {
                            "parameter": "doc",
                            "value": "Document with a root element and mixed content"
                        }
                    ],
                    "expected": "All content types (Comment, DocType, Element, ProcessingInstruction, Text) processed correctly",
                    "description": "Test printDocument with a document containing various content types, ensuring each is processed correctly."
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
                            "value": "mock XMLStreamWriter that captures output"
                        },
                        {
                            "parameter": "fstack",
                            "value": "FormatStack with default settings"
                        },
                        {
                            "parameter": "nstack",
                            "value": "NamespaceStack"
                        },
                        {
                            "parameter": "doc",
                            "value": "Document with whitespace content"
                        }
                    ],
                    "expected": "Whitespace is handled correctly without CDATA",
                    "description": "Test printDocument with whitespace content, ensuring it is processed correctly without being treated as CDATA."
                }
            ]
        }
    ]
}