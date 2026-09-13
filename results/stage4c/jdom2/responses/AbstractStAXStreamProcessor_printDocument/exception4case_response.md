{
    "group": "printDocument exception tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "XMLStreamWriter",
                    "value": "null"
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
                    "parameter": "Document",
                    "value": "mockDocumentWithRootElement"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test case where the XMLStreamWriter is null, expecting a NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "XMLStreamWriter",
                    "value": "mockXMLStreamWriter"
                },
                {
                    "parameter": "FormatStack",
                    "value": "null"
                },
                {
                    "parameter": "NamespaceStack",
                    "value": "mockNamespaceStack"
                },
                {
                    "parameter": "Document",
                    "value": "mockDocumentWithRootElement"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test case where the FormatStack is null, expecting a NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "XMLStreamWriter",
                    "value": "mockXMLStreamWriter"
                },
                {
                    "parameter": "FormatStack",
                    "value": "mockFormatStack"
                },
                {
                    "parameter": "NamespaceStack",
                    "value": "null"
                },
                {
                    "parameter": "Document",
                    "value": "mockDocumentWithRootElement"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test case where the NamespaceStack is null, expecting a NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "XMLStreamWriter",
                    "value": "mockXMLStreamWriter"
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
                    "parameter": "Document",
                    "value": "null"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test case where the Document is null, expecting a NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "XMLStreamWriter",
                    "value": "mockXMLStreamWriterThrowingException"
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
                    "parameter": "Document",
                    "value": "mockDocumentWithRootElement"
                }
            ],
            "expected": "XMLStreamException",
            "description": "Test case where the XMLStreamWriter throws an XMLStreamException, expecting the method to propagate the exception."
        }
    ]
}