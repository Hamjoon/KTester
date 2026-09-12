{
    "group": "soundex exception scenarios",
    "cases": [
        {
            "input": [
                {
                    "parameter": "source",
                    "value": "a"
                },
                {
                    "parameter": "branching",
                    "value": false
                }
            ],
            "expected": "NullPointerException",
            "description": "Test case for a single character input 'a' without matching rules, expecting NullPointerException due to RULES map being empty."
        },
        {
            "input": [
                {
                    "parameter": "source",
                    "value": "a"
                },
                {
                    "parameter": "branching",
                    "value": true
                }
            ],
            "expected": "NullPointerException",
            "description": "Test case for a single character input 'a' with branching enabled, expecting NullPointerException due to RULES map being empty."
        }
    ]
}