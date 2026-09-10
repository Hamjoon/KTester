{
    "result": [
        {
            "group": "skipValue - Exception Scenarios",
            "cases": [
                {
                    "input": [],
                    "expected": "IOException thrown",
                    "description": "Test skipValue when an IOException is thrown during doPeek, should propagate the exception."
                },
                {
                    "input": [],
                    "expected": "IOException thrown",
                    "description": "Test skipValue when an IOException is thrown during skipUnquotedValue, should propagate the exception."
                },
                {
                    "input": [],
                    "expected": "IOException thrown",
                    "description": "Test skipValue when an IOException is thrown during skipQuotedValue, should propagate the exception."
                }
            ]
        }
    ]
}