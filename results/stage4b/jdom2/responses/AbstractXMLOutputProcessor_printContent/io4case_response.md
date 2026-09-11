{
    "test cases": [
        {
            "group": "printContent",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mock Writer"
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
                            "value": "mock Walker with hasNext() returning true and next() returning a null object"
                        }
                    ],
                    "expected": "textRaw method is called with empty string",
                    "description": "Test case where walker has next content as null, expecting raw text handling with an empty string."
                },
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mock Writer"
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
                            "value": "mock Walker with hasNext() returning true and next() returning a mixed content object"
                        }
                    ],
                    "expected": "all relevant print methods are called in sequence",
                    "description": "Test case where walker returns a mixed content object, expecting the method to invoke relevant print methods for each content type in sequence."
                },
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mock Writer"
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
                            "value": "mock Walker with hasNext() returning true and next() returning a CDATA object with invalid content"
                        }
                    ],
                    "expected": "IOException is thrown",
                    "description": "Test case where walker returns a CDATA object with invalid content, expecting an IOException to be thrown."
                },
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mock Writer"
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
                            "value": "mock Walker with hasNext() returning true and next() returning a Comment object with invalid data"
                        }
                    ],
                    "expected": "IOException is thrown",
                    "description": "Test case where walker returns a Comment object with invalid data, expecting an IOException to be thrown."
                },
                {
                    "input": [
                        {
                            "parameter": "out",
                            "value": "mock Writer"
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
                            "value": "mock Walker with hasNext() returning true and next() returning a Document object"
                        }
                    ],
                    "expected": "printDocument method is called",
                    "description": "Test case where walker returns a Document object, expecting printDocument to be invoked."
                }
            ]
        }
    ]
}