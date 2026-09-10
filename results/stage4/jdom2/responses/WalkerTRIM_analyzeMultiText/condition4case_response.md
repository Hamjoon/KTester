{
    "group": "analyzeMultiText",
    "cases": [
        {
            "input": [
                {
                    "parameter": "mtext",
                    "value": "MultiText instance with no Text or CDATA"
                },
                {
                    "parameter": "offset",
                    "value": 0
                },
                {
                    "parameter": "len",
                    "value": 0
                }
            ],
            "expected": "mtext remains unchanged",
            "description": "Test case where the input length is 0, expecting no changes to mtext."
        },
        {
            "input": [
                {
                    "parameter": "mtext",
                    "value": "MultiText instance"
                },
                {
                    "parameter": "offset",
                    "value": 0
                },
                {
                    "parameter": "len",
                    "value": 1
                }
            ],
            "expected": "mtext contains the Text value from Content",
            "description": "Test case where the content at offset is a Text instance with non-whitespace value."
        },
        {
            "input": [
                {
                    "parameter": "mtext",
                    "value": "MultiText instance"
                },
                {
                    "parameter": "offset",
                    "value": 0
                },
                {
                    "parameter": "len",
                    "value": 1
                }
            ],
            "expected": "mtext contains the CDATA value from Content",
            "description": "Test case where the content at offset is a CDATA instance with non-whitespace value."
        },
        {
            "input": [
                {
                    "parameter": "mtext",
                    "value": "MultiText instance"
                },
                {
                    "parameter": "offset",
                    "value": 0
                },
                {
                    "parameter": "len",
                    "value": 2
                }
            ],
            "expected": "mtext contains the first Text/CData and the second as raw content",
            "description": "Test case where the first content is Text and the second is an EntityRef, testing the switch case."
        },
        {
            "input": [
                {
                    "parameter": "mtext",
                    "value": "MultiText instance"
                },
                {
                    "parameter": "offset",
                    "value": 0
                },
                {
                    "parameter": "len",
                    "value": 3
                }
            ],
            "expected": "mtext contains Text, CDATA, and raw content in the order they are processed",
            "description": "Test case with multiple content types including Text, CDATA, and raw content."
        },
        {
            "input": [
                {
                    "parameter": "mtext",
                    "value": "MultiText instance"
                },
                {
                    "parameter": "offset",
                    "value": 0
                },
                {
                    "parameter": "len",
                    "value": 5
                }
            ],
            "expected": "mtext contains only whitespace Texts and remains unchanged",
            "description": "Test case where all contents are whitespace, expecting no changes to mtext."
        },
        {
            "input": [
                {
                    "parameter": "mtext",
                    "value": "MultiText instance"
                },
                {
                    "parameter": "offset",
                    "value": 1
                },
                {
                    "parameter": "len",
                    "value": 1
                }
            ],
            "expected": "mtext processes only the second Content as Text",
            "description": "Test case where the offset starts at 1 and the content is a Text instance."
        }
    ]
}