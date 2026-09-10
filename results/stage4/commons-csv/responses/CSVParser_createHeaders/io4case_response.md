{
    "testCases": [
        {
            "group": "createHeaders - Missing Header Names Not Allowed",
            "cases": [
                {
                    "input": [],
                    "expected": "IllegalArgumentException for missing header names",
                    "description": "Test when there is a missing header name and missing headers are not allowed, expecting an IllegalArgumentException."
                }
            ]
        },
        {
            "group": "createHeaders - Valid Headers with Comments",
            "cases": [
                {
                    "input": [],
                    "expected": "Headers with valid header mappings and a comment",
                    "description": "Test when the format header is valid with a header comment, expecting a proper mapping and retrieval of the comment."
                }
            ]
        },
        {
            "group": "createHeaders - Single Header",
            "cases": [
                {
                    "input": [],
                    "expected": "Headers with single valid header mapping",
                    "description": "Test when the format header contains a single valid header, expecting a proper mapping to that header."
                }
            ]
        },
        {
            "group": "createHeaders - Multiple Blank Headers Not Allowed",
            "cases": [
                {
                    "input": [],
                    "expected": "IllegalArgumentException for multiple blank headers",
                    "description": "Test when there are multiple blank headers and blank headers are not allowed, expecting an IllegalArgumentException."
                }
            ]
        },
        {
            "group": "createHeaders - Mixed Valid and Blank Headers",
            "cases": [
                {
                    "input": [],
                    "expected": "IllegalArgumentException for mixed valid and blank headers",
                    "description": "Test when there is a mix of valid and blank headers, expecting an IllegalArgumentException since blank headers are not allowed."
                }
            ]
        },
        {
            "group": "createHeaders - Valid Headers with Duplicates Allowed",
            "cases": [
                {
                    "input": [],
                    "expected": "Headers with valid mappings including duplicates",
                    "description": "Test when the format header includes valid duplicate headers and duplicates are allowed, expecting valid header mappings."
                }
            ]
        },
        {
            "group": "createHeaders - Valid Headers with Empty Names Allowed",
            "cases": [
                {
                    "input": [],
                    "expected": "Headers with valid mappings including empty header names",
                    "description": "Test when the format header includes valid headers with empty names allowed, expecting valid header mappings."
                }
            ]
        }
    ]
}