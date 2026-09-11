{
    "test cases": [
        {
            "group": "printContent",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mocked XMLStreamWriter"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mocked FormatStack"
                        },
                        {
                            "parameter": "nstack",
                            "value": "mocked NamespaceStack"
                        },
                        {
                            "parameter": "walker",
                            "value": "mocked Walker that returns multiple elements"
                        }
                    ],
                    "expected": "printElement should be called for each element returned by walker",
                    "description": "Test when walker.next() returns multiple Element contents. It should call printElement for each element."
                },
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mocked XMLStreamWriter"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mocked FormatStack"
                        },
                        {
                            "parameter": "nstack",
                            "value": "mocked NamespaceStack"
                        },
                        {
                            "parameter": "walker",
                            "value": "mocked Walker that returns a mixture of content types"
                        }
                    ],
                    "expected": "print methods should be called according to the content type returned by walker",
                    "description": "Test when walker.next() returns a mixture of CDATA, Comment, Element, and Text contents. It should call the respective print methods for each type."
                },
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mocked XMLStreamWriter"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mocked FormatStack"
                        },
                        {
                            "parameter": "nstack",
                            "value": "mocked NamespaceStack"
                        },
                        {
                            "parameter": "walker",
                            "value": "mocked Walker that returns no content and is not CDATA"
                        }
                    ],
                    "expected": "printText should be called with empty text",
                    "description": "Test when walker.next() returns null and walker.isCDATA() returns false. It should call printText with an empty string."
                },
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mocked XMLStreamWriter"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mocked FormatStack"
                        },
                        {
                            "parameter": "nstack",
                            "value": "mocked NamespaceStack"
                        },
                        {
                            "parameter": "walker",
                            "value": "mocked Walker that returns a large amount of text content"
                        }
                    ],
                    "expected": "printText should be called for large text content without issues",
                    "description": "Test when walker.next() returns a large text content. It should call printText without throwing exceptions."
                },
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mocked XMLStreamWriter"
                        },
                        {
                            "parameter": "fstack",
                            "value": "mocked FormatStack"
                        },
                        {
                            "parameter": "nstack",
                            "value": "mocked NamespaceStack"
                        },
                        {
                            "parameter": "walker",
                            "value": "mocked Walker that returns unexpected content type"
                        }
                    ],
                    "expected": "An IllegalStateException should be thrown for unexpected content type",
                    "description": "Test when walker.next() returns content of an unexpected CType that is not handled. It should throw IllegalStateException."
                }
            ]
        }
    ]
}