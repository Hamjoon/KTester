{
    "group": "printDocument method additional tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "XMLStreamWriter",
                    "value": "mockXMLStreamWriter"
                },
                {
                    "parameter": "FormatStack",
                    "value": "mockFormatStackWithNullLineSeparator"
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
            "expected": "writeStartDocument called without writing line separator",
            "description": "Test case where the format stack has a null line separator and the document has a root element."
        },
        {
            "input": [
                {
                    "parameter": "XMLStreamWriter",
                    "value": "mockXMLStreamWriter"
                },
                {
                    "parameter": "FormatStack",
                    "value": "mockFormatStackWithOmitDeclarationAndEncoding"
                },
                {
                    "parameter": "NamespaceStack",
                    "value": "mockNamespaceStack"
                },
                {
                    "parameter": "Document",
                    "value": "mockDocumentWithMultipleRootElements"
                }
            ],
            "expected": "writeStartDocument called with no arguments, followed by line separator",
            "description": "Test case where the format stack is set to omit both declaration and encoding, and the document has multiple root elements."
        },
        {
            "input": [
                {
                    "parameter": "XMLStreamWriter",
                    "value": "mockXMLStreamWriter"
                },
                {
                    "parameter": "FormatStack",
                    "value": "mockFormatStackWithOmitDeclaration"
                },
                {
                    "parameter": "NamespaceStack",
                    "value": "mockNamespaceStack"
                },
                {
                    "parameter": "Document",
                    "value": "mockDocumentWithEntityRef"
                }
            ],
            "expected": "EntityRef processed correctly",
            "description": "Test case where the document contains an entity reference, ensuring it is processed correctly."
        },
        {
            "input": [
                {
                    "parameter": "XMLStreamWriter",
                    "value": "mockXMLStreamWriter"
                },
                {
                    "parameter": "FormatStack",
                    "value": "mockFormatStackWithOmitEncoding"
                },
                {
                    "parameter": "NamespaceStack",
                    "value": "mockNamespaceStack"
                },
                {
                    "parameter": "Document",
                    "value": "mockDocumentWithProcessingInstruction"
                }
            ],
            "expected": "ProcessingInstruction processed correctly",
            "description": "Test case where the document contains a processing instruction, ensuring it is processed correctly."
        },
        {
            "input": [
                {
                    "parameter": "XMLStreamWriter",
                    "value": "mockXMLStreamWriter"
                },
                {
                    "parameter": "FormatStack",
                    "value": "mockFormatStackWithEncoding"
                },
                {
                    "parameter": "NamespaceStack",
                    "value": "mockNamespaceStack"
                },
                {
                    "parameter": "Document",
                    "value": "mockDocumentWithEmptyContent"
                }
            ],
            "expected": "writeStartDocument called with encoding and version '1.0', followed by line separator",
            "description": "Test case where the format stack has encoding set and document has empty content."
        }
    ]
}