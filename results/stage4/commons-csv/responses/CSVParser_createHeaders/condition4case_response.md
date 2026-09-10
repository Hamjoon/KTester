{
    "testCases": [
        {
            "group": "createHeaders - Header Record is Null",
            "cases": [
                {
                    "input": [],
                    "expected": "Headers with empty header map and empty header names",
                    "description": "Test when the format header is null, expecting to get empty headers."
                }
            ]
        },
        {
            "group": "createHeaders - Header Record is Empty",
            "cases": [
                {
                    "input": [],
                    "expected": "Headers with empty header map and empty header names",
                    "description": "Test when the format header is an empty array, expecting to read the header from the first line."
                }
            ]
        },
        {
            "group": "createHeaders - Skip Header Record",
            "cases": [
                {
                    "input": [],
                    "expected": "Headers with header comment from the first record",
                    "description": "Test when the format specifies to skip the header record, expecting to retrieve the header comment from the first record."
                }
            ]
        },
        {
            "group": "createHeaders - Valid Headers",
            "cases": [
                {
                    "input": [],
                    "expected": "Headers with valid header mappings",
                    "description": "Test when the format header is valid and no duplicates or blank headers are present, expecting a proper mapping."
                }
            ]
        },
        {
            "group": "createHeaders - Duplicate Headers Not Allowed",
            "cases": [
                {
                    "input": [],
                    "expected": "IllegalArgumentException for duplicate header names",
                    "description": "Test when there are duplicate headers and duplicates are not allowed, expecting an IllegalArgumentException."
                }
            ]
        },
        {
            "group": "createHeaders - Blank Header Not Allowed",
            "cases": [
                {
                    "input": [],
                    "expected": "IllegalArgumentException for blank header names",
                    "description": "Test when there is a blank header and blank headers are not allowed, expecting an IllegalArgumentException."
                }
            ]
        },
        {
            "group": "createHeaders - Allow Empty Duplicates",
            "cases": [
                {
                    "input": [],
                    "expected": "Headers with valid mappings including empty duplicates",
                    "description": "Test when duplicates are allowed, including empty headers, expecting valid header mappings."
                }
            ]
        },
        {
            "group": "createHeaders - Allow All Duplicates",
            "cases": [
                {
                    "input": [],
                    "expected": "Headers with valid mappings including duplicates",
                    "description": "Test when all duplicates are allowed, expecting valid header mappings."
                }
            ]
        }
    ]
}