{
    "group": "printContent",
    "cases": [
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "mock Writer that throws IOException"
                },
                {
                    "parameter": "fstack",
                    "value": "mock FormatStack"
                },
                {
                    "parameter": "nstack",
                    "value": "mock NamespaceStack"
                },
                {
                    "parameter": "walker",
                    "value": "mock Walker with hasNext() returning true and next() returning a CDATA object"
                }
            ],
            "expected": "IOException is thrown",
            "description": "Test case where writer throws an IOException while processing a CDATA object."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "mock Writer that throws IOException"
                },
                {
                    "parameter": "fstack",
                    "value": "mock FormatStack"
                },
                {
                    "parameter": "nstack",
                    "value": "mock NamespaceStack"
                },
                {
                    "parameter": "walker",
                    "value": "mock Walker with hasNext() returning true and next() returning a Comment object"
                }
            ],
            "expected": "IOException is thrown",
            "description": "Test case where writer throws an IOException while processing a Comment object."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "mock Writer that throws IOException"
                },
                {
                    "parameter": "fstack",
                    "value": "mock FormatStack"
                },
                {
                    "parameter": "nstack",
                    "value": "mock NamespaceStack"
                },
                {
                    "parameter": "walker",
                    "value": "mock Walker with hasNext() returning true and next() returning a DocType object"
                }
            ],
            "expected": "IOException is thrown",
            "description": "Test case where writer throws an IOException while processing a DocType object."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "mock Writer that throws IOException"
                },
                {
                    "parameter": "fstack",
                    "value": "mock FormatStack"
                },
                {
                    "parameter": "nstack",
                    "value": "mock NamespaceStack"
                },
                {
                    "parameter": "walker",
                    "value": "mock Walker with hasNext() returning true and next() returning an Element object"
                }
            ],
            "expected": "IOException is thrown",
            "description": "Test case where writer throws an IOException while processing an Element object."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "mock Writer that throws IOException"
                },
                {
                    "parameter": "fstack",
                    "value": "mock FormatStack"
                },
                {
                    "parameter": "nstack",
                    "value": "mock NamespaceStack"
                },
                {
                    "parameter": "walker",
                    "value": "mock Walker with hasNext() returning true and next() returning an EntityRef object"
                }
            ],
            "expected": "IOException is thrown",
            "description": "Test case where writer throws an IOException while processing an EntityRef object."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "mock Writer that throws IOException"
                },
                {
                    "parameter": "fstack",
                    "value": "mock FormatStack"
                },
                {
                    "parameter": "nstack",
                    "value": "mock NamespaceStack"
                },
                {
                    "parameter": "walker",
                    "value": "mock Walker with hasNext() returning true and next() returning a ProcessingInstruction object"
                }
            ],
            "expected": "IOException is thrown",
            "description": "Test case where writer throws an IOException while processing a ProcessingInstruction object."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "mock Writer that throws IOException"
                },
                {
                    "parameter": "fstack",
                    "value": "mock FormatStack"
                },
                {
                    "parameter": "nstack",
                    "value": "mock NamespaceStack"
                },
                {
                    "parameter": "walker",
                    "value": "mock Walker with hasNext() returning true and next() returning a Text object"
                }
            ],
            "expected": "IOException is thrown",
            "description": "Test case where writer throws an IOException while processing a Text object."
        }
    ]
}