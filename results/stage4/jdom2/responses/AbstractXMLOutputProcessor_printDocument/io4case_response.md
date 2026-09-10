{
    "testCases": [
        {
            "group": "printDocument - Document with CDATA",
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
                            "value": "new Document(new Element('root').addContent(new CDATA('Some CDATA content'))) "
                        }
                    ],
                    "expected": "output contains CDATA section",
                    "description": "Test case where the Document contains CDATA, expecting the CDATA to be written out correctly."
                }
            ]
        },
        {
            "group": "printDocument - Document with Entity Reference",
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
                            "value": "new Document(new Element('root').addContent(new EntityRef('entityName'))) "
                        }
                    ],
                    "expected": "output contains entity reference",
                    "description": "Test case where the Document contains an entity reference, expecting the entity reference to be written out."
                }
            ]
        },
        {
            "group": "printDocument - Document with Mixed Content",
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
                            "value": "new Document(new Element('root').addContent(new Text('Text')).addContent(new Comment('Comment')).addContent(new ProcessingInstruction('pi', 'data')).addContent(new CDATA('CDATA content')))"
                        }
                    ],
                    "expected": "output contains text, comment, processing instruction, and CDATA",
                    "description": "Test case where the Document contains mixed content types, expecting all to be written out correctly."
                }
            ]
        },
        {
            "group": "printDocument - Document with Whitespace",
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
                            "value": "new Document(new Element('root').addContent(new Text('   ')))"
                        }
                    ],
                    "expected": "output contains whitespace",
                    "description": "Test case where the Document contains only whitespace text, expecting the whitespace to be written out."
                }
            ]
        },
        {
            "group": "printDocument - Document with Nested Elements",
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
                            "value": "new Document(new Element('root').addContent(new Element('child1')).addContent(new Element('child2'))) "
                        }
                    ],
                    "expected": "output contains nested elements",
                    "description": "Test case where the Document contains nested elements, expecting both child elements to be written out."
                }
            ]
        }
    ]
}