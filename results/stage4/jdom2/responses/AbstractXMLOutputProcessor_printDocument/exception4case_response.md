{
    "group": "printDocument - Exception Scenarios",
    "cases": [
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "null"
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
            "expected": "IOException",
            "description": "Test case where the Writer is null, expecting an IOException to be thrown."
        },
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
                    "value": "new Document(new Element('root').addContent(new Comment('Comment')))"
                }
            ],
            "expected": "IOException",
            "description": "Test case where the Writer fails to write during the process, expecting an IOException to be thrown."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "new StringWriter()"
                },
                {
                    "parameter": "fstack",
                    "value": "null"
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
            "expected": "NullPointerException",
            "description": "Test case where the FormatStack is null, expecting a NullPointerException to be thrown."
        },
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
                    "value": "null"
                },
                {
                    "parameter": "doc",
                    "value": "new Document(new Element('root'))"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test case where the NamespaceStack is null, expecting a NullPointerException to be thrown."
        }
    ]
}