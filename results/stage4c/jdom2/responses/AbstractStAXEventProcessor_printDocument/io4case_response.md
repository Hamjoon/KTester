{
    "group": "AdditionalPrintDocumentTests",
    "cases": [
        {
            "input": [
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
                    "parameter": "doc",
                    "value": {
                        "hasRootElement": true,
                        "content": [
                            {
                                "type": "Element",
                                "content": "<element>Text with <![CDATA[<some cdata>]]></element>"
                            }
                        ]
                    }
                }
            ],
            "expected": "printElement is called with the element containing CDATA",
            "description": "Test case where the document has a root element with CDATA content."
        },
        {
            "input": [
                {
                    "parameter": "fstack",
                    "value": {
                        "omitDeclaration": false,
                        "omitEncoding": false,
                        "encoding": "UTF-8",
                        "lineSeparator": null
                    }
                },
                {
                    "parameter": "doc",
                    "value": {
                        "hasRootElement": false,
                        "content": [
                            {
                                "type": "EntityRef",
                                "content": "&entity;"
                            }
                        ]
                    }
                }
            ],
            "expected": "printEntityRef is called with the entity reference content",
            "description": "Test case where the document has no root element, and the content includes an entity reference."
        },
        {
            "input": [
                {
                    "parameter": "fstack",
                    "value": {
                        "omitDeclaration": false,
                        "omitEncoding": false,
                        "encoding": "ISO-8859-1",
                        "lineSeparator": "\r\n"
                    }
                },
                {
                    "parameter": "doc",
                    "value": {
                        "hasRootElement": true,
                        "content": [
                            {
                                "type": "Text",
                                "content": "ISO-8859-1 encoded content"
                            }
                        ]
                    }
                }
            ],
            "expected": [
                "out.add(eventfactory.createStartDocument('ISO-8859-1', '1.0'))",
                "out.add(eventfactory.createCharacters('ISO-8859-1 encoded content'))"
            ],
            "description": "Test case where the document uses a different encoding and contains text content."
        },
        {
            "input": [
                {
                    "parameter": "fstack",
                    "value": {
                        "omitDeclaration": false,
                        "omitEncoding": true,
                        "encoding": "UTF-8",
                        "lineSeparator": "\n"
                    }
                },
                {
                    "parameter": "doc",
                    "value": {
                        "hasRootElement": false,
                        "content": [
                            {
                                "type": "Comment",
                                "content": "Comment with special characters: <>&"
                            }
                        ]
                    }
                }
            ],
            "expected": "printComment is called with the comment containing special characters",
            "description": "Test case where the document omits encoding and contains a comment with special characters."
        }
    ]
}