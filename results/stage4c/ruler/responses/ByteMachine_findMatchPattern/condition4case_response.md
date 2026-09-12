{
    "group": "findMatchPattern",
    "cases": [
        {
            "input": [
                {
                    "parameter": "characters",
                    "value": []
                },
                {
                    "parameter": "pattern",
                    "value": "validPattern"
                }
            ],
            "expected": "null",
            "description": "Test case with empty characters array should return null since no transitions can be processed."
        },
        {
            "input": [
                {
                    "parameter": "characters",
                    "value": "nonMatchingCharacters"
                },
                {
                    "parameter": "pattern",
                    "value": "validPattern"
                }
            ],
            "expected": "null",
            "description": "Test case where no transitions match the characters, expecting null as no pattern can be found."
        },
        {
            "input": [
                {
                    "parameter": "characters",
                    "value": "matchingCharactersWithShortcut"
                },
                {
                    "parameter": "pattern",
                    "value": "shortcutMatchPattern"
                }
            ],
            "expected": "expectedNameState",
            "description": "Test case where a shortcut transition is available and matches the pattern, expecting the corresponding NameState."
        },
        {
            "input": [
                {
                    "parameter": "characters",
                    "value": "matchingCharactersWithoutShortcut"
                },
                {
                    "parameter": "pattern",
                    "value": "validPattern"
                }
            ],
            "expected": "expectedNameState",
            "description": "Test case where transitions match the characters without using shortcuts, expecting the correct NameState for the pattern."
        },
        {
            "input": [
                {
                    "parameter": "characters",
                    "value": "charactersLeadingToNoNextState"
                },
                {
                    "parameter": "pattern",
                    "value": "validPattern"
                }
            ],
            "expected": "null",
            "description": "Test case where transitions lead to a state with no next state, expecting null as the result."
        }
    ]
}