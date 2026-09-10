{
    "group": "isXMLPublicIDCharacter",
    "cases": [
        {
            "input": [
                {
                    "parameter": "c",
                    "value": ">"
                }
            ],
            "expected": false,
            "description": "Test with a greater than '>', which is not a valid XML Public ID character."
        },
        {
            "input": [
                {
                    "parameter": "c",
                    "value": "<"
                }
            ],
            "expected": false,
            "description": "Test with a less than '<', which is not a valid XML Public ID character."
        },
        {
            "input": [
                {
                    "parameter": "c",
                    "value": "&"
                }
            ],
            "expected": false,
            "description": "Test with an ampersand '&', which is not a valid XML Public ID character."
        },
        {
            "input": [
                {
                    "parameter": "c",
                    "value": "}"
                }
            ],
            "expected": false,
            "description": "Test with a closing brace '}', which is not a valid XML Public ID character."
        },
        {
            "input": [
                {
                    "parameter": "c",
                    "value": "!"
                }
            ],
            "expected": true,
            "description": "Test with an exclamation mark '!', which is a valid XML Public ID character."
        },
        {
            "input": [
                {
                    "parameter": "c",
                    "value": "'"
                }
            ],
            "expected": true,
            "description": "Test with a single quote ''', which is a valid XML Public ID character."
        },
        {
            "input": [
                {
                    "parameter": "c",
                    "value": "("
                }
            ],
            "expected": true,
            "description": "Test with an opening parenthesis '(', which is a valid XML Public ID character."
        },
        {
            "input": [
                {
                    "parameter": "c",
                    "value": ")"
                }
            ],
            "expected": true,
            "description": "Test with a closing parenthesis ')', which is a valid XML Public ID character."
        }
    ]
}