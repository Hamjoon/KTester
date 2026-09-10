{
    "testCases": [
        {
            "group": "printDocument - No Root Element",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "new StringWriter()"
                        },
                        {
                            "parameter": "fstack",
                            "value": "new FormatStack()"
                        },
                        {
                            "parameter": "nstack",
                            "value": "new NamespaceStack()"
                        },
                        {
                            "parameter": "doc",
                            "value": "new Document()"
                        }
                    ],
                    "expected": "empty output",
                    "description": "Test case where the Document has no root element, expecting no output to be written."
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
                            "value": "new StringWriter()"
                        },
                        {
                            "parameter": "fstack",
                            "value": "new FormatStack()"
                        },
                        {
                            "parameter": "nstack",
                            "value": "new NamespaceStack()"
                        },
                        {
                            "parameter": "doc",
                            "value": "new Document(new Element('root'))"
                        }
                    ],
                    "expected": "output contains the root element",
                    "description": "Test case where the Document contains an empty root element, expecting the root element to be written out."
                }
            ]
        },
        {
            "group": "printDocument - Document with Comments",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "new StringWriter()"
                        },
                        {
                            "parameter": "fstack",
                            "value": "new FormatStack()"
                        },
                        {
                            "parameter": "nstack",
                            "value": "new NamespaceStack()"
                        },
                        {
                            "parameter": "doc",
                            "value": "new Document(new Element('root').addContent(new Comment('This is a comment')))"
                        }
                    ],
                    "expected": "output contains the comment",
                    "description": "Test case where the Document contains a comment, expecting the comment to be written out."
                }
            ]
        },
        {
            "group": "printDocument - Document with Text Content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "new StringWriter()"
                        },
                        {
                            "parameter": "fstack",
                            "value": "new FormatStack()"
                        },
                        {
                            "parameter": "nstack",
                            "value": "new NamespaceStack()"
                        },
                        {
                            "parameter": "doc",
                            "value": "new Document(new Element('root').addContent(new Text('Some text content')))"
                        }
                    ],
                    "expected": "output contains 'Some text content'",
                    "description": "Test case where the Document contains text content, expecting the text to be written out."
                }
            ]
        },
        {
            "group": "printDocument - Document with Processing Instruction",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "new StringWriter()"
                        },
                        {
                            "parameter": "fstack",
                            "value": "new FormatStack()"
                        },
                        {
                            "parameter": "nstack",
                            "value": "new NamespaceStack()"
                        },
                        {
                            "parameter": "doc",
                            "value": "new Document(new Element('root').addContent(new ProcessingInstruction('xml-stylesheet', 'type=\"text/xsl\" href=\"style.xsl\"')))"
                        }
                    ],
                    "expected": "output contains the processing instruction",
                    "description": "Test case where the Document contains a processing instruction, expecting it to be written out."
                }
            ]
        },
        {
            "group": "printDocument - Document with Multiple Content Types",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "new StringWriter()"
                        },
                        {
                            "parameter": "fstack",
                            "value": "new FormatStack()"
                        },
                        {
                            "parameter": "nstack",
                            "value": "new NamespaceStack()"
                        },
                        {
                            "parameter": "doc",
                            "value": "new Document(new Element('root').addContent(new Comment('Comment')).addContent(new Text('Text content')).addContent(new ProcessingInstruction('pi', 'data')))"
                        }
                    ],
                    "expected": "output contains comment, text content, and processing instruction",
                    "description": "Test case where the Document contains multiple content types, expecting all to be written out correctly."
                }
            ]
        }
    ]
}