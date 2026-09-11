{
    "group": "createHeaders - missing header name with allowance",
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
            "description": "Test with a missing header name where missing names are allowed."
        }
    ]
}