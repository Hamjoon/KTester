{"group": "printContent", "cases": [
    {
        "input": [
            {
                "parameter": "out",
                "value": "mock XMLStreamWriter"
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
                "value": "mock Walker with hasNext() returning false"
            }
        ],
        "expected": "No exception, method completes without processing any content",
        "description": "Test case where walker has no next content. The method should complete without any content processing."
    },
    {
        "input": [
            {
                "parameter": "out",
                "value": "mock XMLStreamWriter"
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
        "expected": "No exception, calls printDocType with the correct DocType object",
        "description": "Test case where walker returns a DocType object. It should call printDocType."
    },
    {
        "input": [
            {
                "parameter": "out",
                "value": "mock XMLStreamWriter"
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
                "value": "mock Walker with hasNext() returning true and next() returning an unexpected Content type"
            }
        ],
        "expected": "Throws IllegalStateException with message 'Unexpected Content'",
        "description": "Test case where walker returns an unexpected content type, should throw IllegalStateException."
    },
    {
        "input": [
            {
                "parameter": "out",
                "value": "mock XMLStreamWriter"
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
                "value": "mock Walker with hasNext() returning true and next() returning a null content after processing a valid content"
            }
        ],
        "expected": "No exception, calls printText with the correct Text object after processing valid content",
        "description": "Test case where walker returns a valid content followed by null. It should call printText for the null case."
    }
]}