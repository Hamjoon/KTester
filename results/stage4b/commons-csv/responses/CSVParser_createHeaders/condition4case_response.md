{
    "testCases": [
        {
            "group": "createHeaders - valid header with no duplicates",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "formatHeader",
                            "value": ["header1", "header2", "header3"]
                        }
                    ],
                    "expected": {
                        "headerMap": {
                            "header1": 0,
                            "header2": 1,
                            "header3": 2
                        },
                        "headerNames": ["header1", "header2", "header3"]
                    },
                    "description": "Test with a valid header that has no duplicates."
                }
            ]
        },
        {
            "group": "createHeaders - valid header with duplicates allowed",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "formatHeader",
                            "value": ["header1", "header1"]
                        }
                    ],
                    "expected": {
                        "headerMap": {
                            "header1": 0
                        },
                        "headerNames": ["header1"]
                    },
                    "description": "Test with a valid header that allows duplicate names."
                }
            ]
        },
        {
            "group": "createHeaders - missing header name without allowance",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "formatHeader",
                            "value": ["", "header2"]
                        }
                    ],
                    "expected": {
                        "exception": "IllegalArgumentException"
                    },
                    "description": "Test with a missing header name where missing names are not allowed."
                }
            ]
        },
        {
            "group": "createHeaders - duplicate header name without allowance",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "formatHeader",
                            "value": ["header1", "header1"]
                        }
                    ],
                    "expected": {
                        "exception": "IllegalArgumentException"
                    },
                    "description": "Test with a duplicate header name where duplicates are not allowed."
                }
            ]
        },
        {
            "group": "createHeaders - empty header",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "formatHeader",
                            "value": []
                        }
                    ],
                    "expected": {
                        "headerMap": {},
                        "headerNames": []
                    },
                    "description": "Test with an empty header array."
                }
            ]
        },
        {
            "group": "createHeaders - valid header with blank header and allowance",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "formatHeader",
                            "value": ["header1", "", "header3"]
                        }
                    ],
                    "expected": {
                        "headerMap": {
                            "header1": 0,
                            "header3": 2
                        },
                        "headerNames": ["header1", "header3"]
                    },
                    "description": "Test with a valid header that includes a blank header and allows missing names."
                }
            ]
        },
        {
            "group": "createHeaders - header with comment",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "formatHeader",
                            "value": ["header1", "header2"]
                        },
                        {
                            "parameter": "nextRecord",
                            "value": {
                                "values": ["value1", "value2"],
                                "comment": "This is a comment"
                            }
                        }
                    ],
                    "expected": {
                        "headerMap": {
                            "header1": 0,
                            "header2": 1
                        },
                        "headerNames": ["header1", "header2"],
                        "headerComment": "This is a comment"
                    },
                    "description": "Test with a valid header that includes a comment from the next record."
                }
            ]
        }
    ]
}