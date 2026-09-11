{
    "group": "isXMLPublicIDCharacter",
    "cases": [
        {
            "input": [
                {
                    "parameter": "c",
                    "value": "}"
                }
            ],
            "expected": false,
            "description": "Test with the character '}', which is not a valid XML Public ID character."
        },
        {
            "input": [
                {
                    "parameter": "c",
                    "value": "&"
                }
            ],
            "expected": false,
            "description": "Test with the character '&', which is not a valid XML Public ID character."
        },
        {
            "input": [
                {
                    "parameter": "c",
                    "value": "<"
                }
            ],
            "expected": false,
            "description": "Test with the character '<', which is not a valid XML Public ID character."
        },
        {
            "input": [
                {
                    "parameter": "c",
                    "value": "\""
                }
            ],
            "expected": false,
            "description": "Test with the character '\"', which is not a valid XML Public ID character."
        }
    ]
}