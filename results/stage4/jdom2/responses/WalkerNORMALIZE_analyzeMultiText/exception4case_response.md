{
    "output": [
        {
            "group": "analyzeMultiText - Invalid Offset",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "mtext",
                            "value": "new MultiText()"
                        },
                        {
                            "parameter": "offset",
                            "value": -1
                        },
                        {
                            "parameter": "len",
                            "value": 1
                        }
                    ],
                    "expected": "IndexOutOfBoundsException",
                    "description": "Test with a negative offset value, expecting an IndexOutOfBoundsException."
                }
            ]
        },
        {
            "group": "analyzeMultiText - Invalid Length",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "mtext",
                            "value": "new MultiText()"
                        },
                        {
                            "parameter": "offset",
                            "value": 0
                        },
                        {
                            "parameter": "len",
                            "value": -1
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "Test with a negative length value, expecting an IllegalArgumentException."
                },
                {
                    "input": [
                        {
                            "parameter": "mtext",
                            "value": "new MultiText()"
                        },
                        {
                            "parameter": "offset",
                            "value": 0
                        },
                        {
                            "parameter": "len",
                            "value": 1000
                        }
                    ],
                    "expected": "IndexOutOfBoundsException",
                    "description": "Test with a length that exceeds the size of the content list, expecting an IndexOutOfBoundsException."
                }
            ]
        },
        {
            "group": "analyzeMultiText - Null Content",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "mtext",
                            "value": "new MultiText()"
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
                    "expected": "NullPointerException",
                    "description": "Test with a null content list, expecting a NullPointerException."
                }
            ]
        }
    ]
}