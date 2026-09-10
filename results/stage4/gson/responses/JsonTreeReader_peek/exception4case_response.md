{
    "group": "peek method - stack contains unsupported type",
    "cases": [
        {
            "input": [
                {
                    "parameter": "stack",
                    "value": "Custom JsonElement subclass"
                }
            ],
            "expected": "MalformedJsonException with appropriate message",
            "description": "Test when the top of the stack is a Custom JsonElement subclass that is not supported, should throw MalformedJsonException."
        }
    ]
}