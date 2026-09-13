{
    "group": "printDocument method tests",
    "cases": [
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
                    "value": "mockDocumentWithRootElement"
                }
            ],
            "expected": "writeStartDocument called with no arguments, followed by line separator",
            "description": "Test case where the format stack is set to omit declaration and document has a root element."
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
                    "value": "mockDocumentWithoutRootElement"
                }
            ],
            "expected": "writeStartDocument called with version '1.0', followed by line separator",
            "description": "Test case where the format stack is set to omit encoding and document does not have a root element."
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
                    "value": "mockDocumentWithMixedContent"
                }
            ],
            "expected": "Correct methods called for each content type in the document",
            "description": "Test case where the document contains mixed content types, ensuring each type is processed correctly."
        },
        {
            "input": [
                {
                    "parameter": "XMLStreamWriter",
                    "value": "mockXMLStreamWriter"
                },
                {
                    "parameter": "FormatStack",
                    "value": "mockFormatStackWithWhitespace"
                },
                {
                    "parameter": "NamespaceStack",
                    "value": "mockNamespaceStack"
                },
                {
                    "parameter": "Document",
                    "value": "mockDocumentWithWhitespaceText"
                }
            ],
            "expected": "writeCharacters called with whitespace text",
            "description": "Test case where the document contains text content that is all XML whitespace, ensuring it is written correctly."
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
                    "value": "mockDocumentWithCDATA"
                }
            ],
            "expected": "CDATA content is not written as characters",
            "description": "Test case where the document contains CDATA content, ensuring it is not treated as regular text."
        }
    ]
}