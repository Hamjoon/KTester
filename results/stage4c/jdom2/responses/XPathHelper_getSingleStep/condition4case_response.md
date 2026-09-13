{
    "group": "getSingleStep Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "nsa",
                    "value": "TextContentInstance"
                },
                {
                    "parameter": "buffer",
                    "value": "new StringBuilder()"
                }
            ],
            "expected": "buffer with text() position path",
            "description": "Test getSingleStep with Content instance of Text. It should handle text nodes and append 'text()' position path to the buffer."
        },
        {
            "input": [
                {
                    "parameter": "nsa",
                    "value": "CommentContentInstance"
                },
                {
                    "parameter": "buffer",
                    "value": "new StringBuilder()"
                }
            ],
            "expected": "buffer with comment() position path",
            "description": "Test getSingleStep with Content instance of Comment. It should handle comment nodes and append 'comment()' position path to the buffer."
        },
        {
            "input": [
                {
                    "parameter": "nsa",
                    "value": "ProcessingInstructionContentInstance"
                },
                {
                    "parameter": "buffer",
                    "value": "new StringBuilder()"
                }
            ],
            "expected": "buffer with processing-instruction() position path",
            "description": "Test getSingleStep with Content instance of ProcessingInstruction. It should handle processing instruction nodes and append 'processing-instruction()' position path to the buffer."
        },
        {
            "input": [
                {
                    "parameter": "nsa",
                    "value": "NoNamespaceElementInstance"
                },
                {
                    "parameter": "buffer",
                    "value": "new StringBuilder()"
                }
            ],
            "expected": "buffer with element name position path",
            "description": "Test getSingleStep with Content instance of Element with no namespace. It should append the element name position path to the buffer."
        },
        {
            "input": [
                {
                    "parameter": "nsa",
                    "value": "NamespaceElementInstance"
                },
                {
                    "parameter": "buffer",
                    "value": "new StringBuilder()"
                }
            ],
            "expected": "buffer with namespace element position path",
            "description": "Test getSingleStep with Content instance of Element with a namespace. It should append the complex XPath position path with namespace details to the buffer."
        },
        {
            "input": [
                {
                    "parameter": "nsa",
                    "value": "OtherContentInstance"
                },
                {
                    "parameter": "buffer",
                    "value": "new StringBuilder()"
                }
            ],
            "expected": "buffer with node() position path",
            "description": "Test getSingleStep with Content instance that does not match any specific type. It should append a generic 'node()' position path to the buffer."
        },
        {
            "input": [
                {
                    "parameter": "nsa",
                    "value": "NoNamespaceAttributeInstance"
                },
                {
                    "parameter": "buffer",
                    "value": "new StringBuilder()"
                }
            ],
            "expected": "buffer with @attributeName",
            "description": "Test getSingleStep with Attribute instance with no namespace. It should append '@attributeName' to the buffer."
        },
        {
            "input": [
                {
                    "parameter": "nsa",
                    "value": "NamespaceAttributeInstance"
                },
                {
                    "parameter": "buffer",
                    "value": "new StringBuilder()"
                }
            ],
            "expected": "buffer with @*[local-name() = 'attributeName' and namespace-uri() = 'namespaceURI']",
            "description": "Test getSingleStep with Attribute instance with a namespace. It should append a complex XPath for attribute with namespace to the buffer."
        },
        {
            "input": [
                {
                    "parameter": "nsa",
                    "value": "NonNamespaceAwareInstance"
                },
                {
                    "parameter": "buffer",
                    "value": "new StringBuilder()"
                }
            ],
            "expected": "buffer unchanged",
            "description": "Test getSingleStep with an instance that is not NamespaceAware. The buffer should remain unchanged."
        }
    ]
}