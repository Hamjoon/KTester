{
    "group": "isXMLPublicIDCharacter Additional Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "c",
                    "value": "b"
                }
            ],
            "expected": true,
            "description": "Test with a lowercase letter 'b' which is within the range 'a' to 'z'."
        },
        {
            "input": [
                {
                    "parameter": "c",
                    "value": "M"
                }
            ],
            "expected": true,
            "description": "Test with an uppercase letter 'M' which is within the range '?' to 'Z'."
        },
        {
            "input": [
                {
                    "parameter": "c",
                    "value": "0"
                }
            ],
            "expected": true,
            "description": "Test with the digit '0' which is within the range '?' to 'Z'."
        },
        {
            "input": [
                {
                    "parameter": "c",
                    "value": "-"
                }
            ],
            "expected": true,
            "description": "Test with the character '-' which is within the range ''' to ';'."
        },
        {
            "input": [
                {
                    "parameter": "c",
                    "value": "+"
                }
            ],
            "expected": true,
            "description": "Test with the character '+' which is within the range ''' to ';'."
        },
        {
            "input": [
                {
                    "parameter": "c",
                    "value": ","
                }
            ],
            "expected": true,
            "description": "Test with the character ',' which is within the range ''' to ';'."
        },
        {
            "input": [
                {
                    "parameter": "c",
                    "value": "."
                }
            ],
            "expected": true,
            "description": "Test with the character '.' which is within the range ''' to ';'."
        },
        {
            "input": [
                {
                    "parameter": "c",
                    "value": ":"
                }
            ],
            "expected": true,
            "description": "Test with the character ':' which is within the range ''' to ';'."
        },
        {
            "input": [
                {
                    "parameter": "c",
                    "value": "*"
                }
            ],
            "expected": true,
            "description": "Test with the character '*' which is within the range ''' to ';'."
        },
        {
            "input": [
                {
                    "parameter": "c",
                    "value": "&"
                }
            ],
            "expected": false,
            "description": "Test with the character '&' which is not within any of the specified ranges or explicitly checked characters."
        },
        {
            "input": [
                {
                    "parameter": "c",
                    "value": ">"
                }
            ],
            "expected": false,
            "description": "Test with the character '>' which is not within any of the specified ranges or explicitly checked characters."
        },
        {
            "input": [
                {
                    "parameter": "c",
                    "value": "?"
                }
            ],
            "expected": true,
            "description": "Test with the character '?' which is the lower bound of the range '?' to 'Z'."
        },
        {
            "input": [
                {
                    "parameter": "c",
                    "value": "A"
                }
            ],
            "expected": true,
            "description": "Test with the character 'A' which is within the range '?' to 'Z'."
        }
    ]
}