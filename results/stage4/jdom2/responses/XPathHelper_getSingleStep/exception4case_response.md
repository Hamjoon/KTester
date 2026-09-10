{
    "group": "getSingleStep with Content",
    "cases": [
        {
            "input": [
                {
                    "parameter": "nsa",
                    "value": "Content instance with null parent"
                },
                {
                    "parameter": "buffer",
                    "value": "new StringBuilder()"
                }
            ],
            "expected": "StringBuilder containing the position path for node()",
            "description": "Test case for when nsa is a Content instance with a null parent. It should return the position path for a generic node."
        }
    ]
}