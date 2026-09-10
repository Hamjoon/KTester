{
    "group": "getValueType",
    "cases": [
        {
            "input": [
                {
                    "parameter": "ch",
                    "value": "A"
                }
            ],
            "expected": null,
            "description": "Test for an invalid character 'A', which should return null."
        },
        {
            "input": [
                {
                    "parameter": "ch",
                    "value": " "
                }
            ],
            "expected": null,
            "description": "Test for a space character, which should return null."
        },
        {
            "input": [
                {
                    "parameter": "ch",
                    "value": "9"
                }
            ],
            "expected": null,
            "description": "Test for a numeric character '9', which should return null."
        },
        {
            "input": [
                {
                    "parameter": "ch",
                    "value": "!"
                }
            ],
            "expected": null,
            "description": "Test for an invalid character '!', which should return null."
        },
        {
            "input": [
                {
                    "parameter": "ch",
                    "value": ""
                }
            ],
            "expected": null,
            "description": "Test for an empty character input, which should return null."
        }
    ]
}