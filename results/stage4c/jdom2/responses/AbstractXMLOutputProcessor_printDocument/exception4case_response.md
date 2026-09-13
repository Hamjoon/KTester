{
    "group": "Exception Handling Tests for printDocument",
    "cases": [
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "null"
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
            "expected": "NullPointerException",
            "description": "Test with a null Writer to ensure proper exception handling when the output stream is not provided."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "MockWriter"
                },
                {
                    "parameter": "fstack",
                    "value": "null"
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
            "expected": "NullPointerException",
            "description": "Test with a null FormatStack to ensure proper exception handling when the format stack is not provided."
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
                    "value": "null"
                },
                {
                    "parameter": "doc",
                    "value": "Document with root element and content"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test with a null NamespaceStack to ensure proper exception handling when the namespace stack is not provided."
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
                    "value": "null"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test with a null Document to ensure proper exception handling when the document is not provided."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "MockWriter that throws IOException"
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
            "expected": "IOException",
            "description": "Test with a Writer that throws IOException to ensure that IO exceptions are properly propagated."
        }
    ]
}