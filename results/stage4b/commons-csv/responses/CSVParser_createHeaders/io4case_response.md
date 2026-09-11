{
    "testCases": [
        {
            "group": "createHeaders - valid header with blank header and no allowance",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "formatHeader",
                            "value": ["header1", "", "header3"]
                        }
                    ],
                    "expected": {
                        "exception": "IllegalArgumentException"
                    },
                    "description": "Test with a valid header that includes a blank header and does not allow missing names."
                }
            ]
        },
        {
            "group": "createHeaders - valid header with multiple blank headers and allowance",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "formatHeader",
                            "value": ["header1", "", "", "header3"]
                        }
                    ],
                    "expected": {
                        "headerMap": {
                            "header1": 0,
                            "header3": 3
                        },
                        "headerNames": ["header1", "header3"]
                    },
                    "description": "Test with a valid header that includes multiple blank headers and allows missing names."
                }
            ]
        },
        {
            "group": "createHeaders - valid header with all blank names and allowance",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "formatHeader",
                            "value": ["", "", ""]
                        }
                    ],
                    "expected": {
                        "headerMap": {},
                        "headerNames": []
                    },
                    "description": "Test with a valid header that includes all blank names and allows missing names."
                }
            ]
        },
        {
            "group": "createHeaders - valid header with duplicates and empty duplicates allowed",
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
                    "description": "Test with a valid header that allows duplicates and contains duplicates."
                }
            ]
        },
        {
            "group": "createHeaders - valid header with all unique names and no allowance for duplicates",
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
                    "description": "Test with a valid header that contains all unique names and does not allow duplicates."
                }
            ]
        }
    ]
}