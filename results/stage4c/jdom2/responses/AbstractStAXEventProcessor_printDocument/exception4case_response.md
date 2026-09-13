{
    "group": "ExceptionScenarios",
    "cases": [
        {
            "input": [
                {
                    "parameter": "out",
                    "value": null
                },
                {
                    "parameter": "fstack",
                    "value": {
                        "omitDeclaration": false,
                        "omitEncoding": false,
                        "encoding": "UTF-8",
                        "lineSeparator": "\n"
                    }
                },
                {
                    "parameter": "nstack",
                    "value": {}
                },
                {
                    "parameter": "eventfactory",
                    "value": {}
                },
                {
                    "parameter": "doc",
                    "value": {
                        "hasRootElement": true,
                        "content": []
                    }
                }
            ],
            "expected": "NullPointerException",
            "description": "Test case where the XMLEventConsumer 'out' is null, which should trigger a NullPointerException when attempting to add events."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": {}
                },
                {
                    "parameter": "fstack",
                    "value": null
                },
                {
                    "parameter": "nstack",
                    "value": {}
                },
                {
                    "parameter": "eventfactory",
                    "value": {}
                },
                {
                    "parameter": "doc",
                    "value": {
                        "hasRootElement": true,
                        "content": []
                    }
                }
            ],
            "expected": "NullPointerException",
            "description": "Test case where the FormatStack 'fstack' is null, which should trigger a NullPointerException when accessing its methods."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": {}
                },
                {
                    "parameter": "fstack",
                    "value": {
                        "omitDeclaration": false,
                        "omitEncoding": false,
                        "encoding": "UTF-8",
                        "lineSeparator": "\n"
                    }
                },
                {
                    "parameter": "nstack",
                    "value": {}
                },
                {
                    "parameter": "eventfactory",
                    "value": null
                },
                {
                    "parameter": "doc",
                    "value": {
                        "hasRootElement": true,
                        "content": []
                    }
                }
            ],
            "expected": "NullPointerException",
            "description": "Test case where the XMLEventFactory 'eventfactory' is null, which should trigger a NullPointerException when attempting to create events."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": {}
                },
                {
                    "parameter": "fstack",
                    "value": {
                        "omitDeclaration": false,
                        "omitEncoding": false,
                        "encoding": "UTF-8",
                        "lineSeparator": "\n"
                    }
                },
                {
                    "parameter": "nstack",
                    "value": {}
                },
                {
                    "parameter": "eventfactory",
                    "value": {}
                },
                {
                    "parameter": "doc",
                    "value": null
                }
            ],
            "expected": "NullPointerException",
            "description": "Test case where the Document 'doc' is null, which should trigger a NullPointerException when attempting to access its content."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": {}
                },
                {
                    "parameter": "fstack",
                    "value": {
                        "omitDeclaration": false,
                        "omitEncoding": false,
                        "encoding": "UTF-8",
                        "lineSeparator": "\n"
                    }
                },
                {
                    "parameter": "nstack",
                    "value": {}
                },
                {
                    "parameter": "eventfactory",
                    "value": {}
                },
                {
                    "parameter": "doc",
                    "value": {
                        "hasRootElement": true,
                        "content": [
                            {
                                "type": "InvalidType",
                                "content": "Invalid content"
                            }
                        ]
                    }
                }
            ],
            "expected": "ClassCastException",
            "description": "Test case where the document content includes an invalid type, which should trigger a ClassCastException when casting to expected content types."
        }
    ]
}