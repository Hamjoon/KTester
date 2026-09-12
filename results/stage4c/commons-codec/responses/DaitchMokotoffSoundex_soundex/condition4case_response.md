{
    "group": "soundex method tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "source",
                    "value": null
                },
                {
                    "parameter": "branching",
                    "value": false
                }
            ],
            "expected": null,
            "description": "Test case for null input, expecting null output."
        },
        {
            "input": [
                {
                    "parameter": "source",
                    "value": ""
                },
                {
                    "parameter": "branching",
                    "value": false
                }
            ],
            "expected": ["000000"],
            "description": "Test case for empty string input, expecting a single branch with all zeroes."
        },
        {
            "input": [
                {
                    "parameter": "source",
                    "value": "   "
                },
                {
                    "parameter": "branching",
                    "value": false
                }
            ],
            "expected": ["000000"],
            "description": "Test case for input string with only spaces, expecting a single branch with all zeroes."
        },
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
            "expected": ["000000"],
            "description": "Test case for single character input with no matching rule, expecting a single branch with all zeroes."
        },
        {
            "input": [
                {
                    "parameter": "source",
                    "value": "nm"
                },
                {
                    "parameter": "branching",
                    "value": false
                }
            ],
            "expected": ["000000"],
            "description": "Test case for special rule 'nm', expecting a single branch with all zeroes due to forced replacement."
        },
        {
            "input": [
                {
                    "parameter": "source",
                    "value": "mn"
                },
                {
                    "parameter": "branching",
                    "value": false
                }
            ],
            "expected": ["000000"],
            "description": "Test case for special rule 'mn', expecting a single branch with all zeroes due to forced replacement."
        },
        {
            "input": [
                {
                    "parameter": "source",
                    "value": "example"
                },
                {
                    "parameter": "branching",
                    "value": false
                }
            ],
            "expected": "expected output",
            "description": "Test case for a normal string input to check the normal flow of soundex processing without branching."
        },
        {
            "input": [
                {
                    "parameter": "source",
                    "value": "example"
                },
                {
                    "parameter": "branching",
                    "value": true
                }
            ],
            "expected": "expected output",
            "description": "Test case for a normal string input to check the normal flow of soundex processing with branching."
        }
    ]
}