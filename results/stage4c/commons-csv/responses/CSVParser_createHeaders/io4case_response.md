{
    "group": "createHeaders method additional tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "formatHeader",
                    "value": ["header1", "header2", ""]
                },
                {
                    "parameter": "allowMissingColumnNames",
                    "value": false
                }
            ],
            "expected": "IllegalArgumentException for missing header name",
            "description": "Test when formatHeader contains a blank header and allowMissingColumnNames is false, expecting IllegalArgumentException."
        },
        {
            "input": [
                {
                    "parameter": "formatHeader",
                    "value": ["header1", "header2", "header1"]
                },
                {
                    "parameter": "duplicateHeaderMode",
                    "value": "ALLOW_EMPTY"
                }
            ],
            "expected": "IllegalArgumentException for duplicate headers",
            "description": "Test when formatHeader contains duplicates and duplicateHeaderMode is ALLOW_EMPTY, expecting IllegalArgumentException."
        },
        {
            "input": [
                {
                    "parameter": "formatHeader",
                    "value": ["header1", "header2", "header3"]
                },
                {
                    "parameter": "skipHeaderRecord",
                    "value": false
                }
            ],
            "expected": "Headers with specified formatHeader names without skipping first record",
            "description": "Test when formatHeader is provided and skipHeaderRecord is false, expecting headers to match formatHeader without skipping the first record."
        },
        {
            "input": [
                {
                    "parameter": "formatHeader",
                    "value": ["header1", "header2", null]
                },
                {
                    "parameter": "allowMissingColumnNames",
                    "value": true
                }
            ],
            "expected": "Headers with missing column names allowed",
            "description": "Test when formatHeader contains a null header and allowMissingColumnNames is true, expecting no exception."
        },
        {
            "input": [
                {
                    "parameter": "formatHeader",
                    "value": ["header1", "header2", "header2"]
                },
                {
                    "parameter": "duplicateHeaderMode",
                    "value": "ALLOW_ALL"
                }
            ],
            "expected": "Headers with duplicate header names allowed",
            "description": "Test when formatHeader contains duplicates and duplicateHeaderMode is ALLOW_ALL, expecting no exception."
        },
        {
            "input": [
                {
                    "parameter": "formatHeader",
                    "value": ["", ""]
                },
                {
                    "parameter": "allowMissingColumnNames",
                    "value": false
                }
            ],
            "expected": "IllegalArgumentException for missing header names",
            "description": "Test when formatHeader contains only blank headers and allowMissingColumnNames is false, expecting IllegalArgumentException."
        }
    ]
}