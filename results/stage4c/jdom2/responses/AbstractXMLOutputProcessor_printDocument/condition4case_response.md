{
    "group": "printDocument Method Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "MockWriter"
                },
                {
                    "parameter": "fstack",
                    "value": "MockFormatStack"
                },
                {
                    "parameter": "nstack",
                    "value": "MockNamespaceStack"
                },
                {
                    "parameter": "doc",
                    "value": "Document with no root element and empty content"
                }
            ],
            "expected": "printDeclaration is called; no other content is written",
            "description": "Test with a document that has no root element and no content"
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "MockWriter"
                },
                {
                    "parameter": "fstack",
                    "value": "MockFormatStack"
                },
                {
                    "parameter": "nstack",
                    "value": "MockNamespaceStack"
                },
                {
                    "parameter": "doc",
                    "value": "Document with root element and content"
                }
            ],
            "expected": "Content is processed and written correctly",
            "description": "Test with a document that has a root element and various content types"
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "MockWriter"
                },
                {
                    "parameter": "fstack",
                    "value": "MockFormatStack"
                },
                {
                    "parameter": "nstack",
                    "value": "MockNamespaceStack"
                },
                {
                    "parameter": "doc",
                    "value": "Document with only whitespace text outside root"
                }
            ],
            "expected": "Whitespace text is written",
            "description": "Test with a document containing only whitespace text outside the root element"
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "MockWriter"
                },
                {
                    "parameter": "fstack",
                    "value": "MockFormatStack"
                },
                {
                    "parameter": "nstack",
                    "value": "MockNamespaceStack"
                },
                {
                    "parameter": "doc",
                    "value": "Document with a Comment content"
                }
            ],
            "expected": "printComment is called",
            "description": "Test with a document containing a Comment content"
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "MockWriter"
                },
                {
                    "parameter": "fstack",
                    "value": "MockFormatStack"
                },
                {
                    "parameter": "nstack",
                    "value": "MockNamespaceStack"
                },
                {
                    "parameter": "doc",
                    "value": "Document with a DocType content"
                }
            ],
            "expected": "printDocType is called",
            "description": "Test with a document containing a DocType content"
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "MockWriter"
                },
                {
                    "parameter": "fstack",
                    "value": "MockFormatStack"
                },
                {
                    "parameter": "nstack",
                    "value": "MockNamespaceStack"
                },
                {
                    "parameter": "doc",
                    "value": "Document with a ProcessingInstruction content"
                }
            ],
            "expected": "printProcessingInstruction is called",
            "description": "Test with a document containing a ProcessingInstruction content"
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "MockWriter"
                },
                {
                    "parameter": "fstack",
                    "value": "MockFormatStack"
                },
                {
                    "parameter": "nstack",
                    "value": "MockNamespaceStack"
                },
                {
                    "parameter": "doc",
                    "value": "Document with a Text content containing only whitespace"
                }
            ],
            "expected": "Whitespace text is written",
            "description": "Test with a document containing a Text content with only whitespace"
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "MockWriter"
                },
                {
                    "parameter": "fstack",
                    "value": "MockFormatStack"
                },
                {
                    "parameter": "nstack",
                    "value": "MockNamespaceStack"
                },
                {
                    "parameter": "doc",
                    "value": "Document with a Text content that is not all whitespace"
                }
            ],
            "expected": "Text content is ignored",
            "description": "Test with a document containing a Text content that is not all whitespace"
        }
    ]
}