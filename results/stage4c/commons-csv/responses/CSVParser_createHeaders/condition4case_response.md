{
    "group": "createHeaders method tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "formatHeader",
                    "value": null
                }
            ],
            "expected": "Headers with empty headerNames list",
            "description": "Test when formatHeader is null, expecting empty headerNames list."
        },
        {
            "input": [
                {
                    "parameter": "formatHeader",
                    "value": []
                }
            ],
            "expected": "Headers with header names from the first record",
            "description": "Test when formatHeader is empty, expecting headers to be read from the first line of the file."
        },
        {
            "input": [
                {
                    "parameter": "formatHeader",
                    "value": ["header1", "header2"]
                },
                {
                    "parameter": "skipHeaderRecord",
                    "value": true
                }
            ],
            "expected": "Headers with specified formatHeader names and skipped first record",
            "description": "Test when formatHeader is provided and skipHeaderRecord is true, expecting headers to match formatHeader and first record skipped."
        },
        {
            "input": [
                {
                    "parameter": "formatHeader",
                    "value": ["header1", "header1"]
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
                    "value": ["header1", "header1"]
                },
                {
                    "parameter": "duplicateHeaderMode",
                    "value": "DISALLOW"
                }
            ],
            "expected": "IllegalArgumentException for duplicate headers",
            "description": "Test when formatHeader contains duplicates and duplicateHeaderMode is DISALLOW, expecting IllegalArgumentException."
        },
        {
            "input": [
                {
                    "parameter": "formatHeader",
                    "value": ["header1", ""]
                },
                {
                    "parameter": "allowMissingColumnNames",
                    "value": false
                }
            ],
            "expected": "IllegalArgumentException for missing header name",
            "description": "Test when formatHeader contains blank header and allowMissingColumnNames is false, expecting IllegalArgumentException."
        },
        {
            "input": [
                {
                    "parameter": "formatHeader",
                    "value": ["header1", ""]
                },
                {
                    "parameter": "allowMissingColumnNames",
                    "value": true
                }
            ],
            "expected": "Headers with missing column names allowed",
            "description": "Test when formatHeader contains blank header and allowMissingColumnNames is true, expecting no exception."
        }
    ]
}