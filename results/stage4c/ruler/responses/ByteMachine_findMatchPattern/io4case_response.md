{
    "group": "findMatchPattern",
    "cases": [
        {
            "input": [
                {
                    "parameter": "characters",
                    "value": "singleCharacter"
                },
                {
                    "parameter": "pattern",
                    "value": "validPattern"
                }
            ],
            "expected": "expectedNameState",
            "description": "Test case with a single character that matches a pattern exactly, expecting the correct NameState."
        },
        {
            "input": [
                {
                    "parameter": "characters",
                    "value": "charactersMatchingMultiplePatterns"
                },
                {
                    "parameter": "pattern",
                    "value": "secondPattern"
                }
            ],
            "expected": "expectedNameStateForSecondPattern",
            "description": "Test case where characters match multiple patterns, expecting NameState for the specified pattern."
        },
        {
            "input": [
                {
                    "parameter": "characters",
                    "value": "charactersWithNonShortcutMatch"
                },
                {
                    "parameter": "pattern",
                    "value": "nonShortcutPattern"
                }
            ],
            "expected": "expectedNameState",
            "description": "Test case where characters match a pattern without using a shortcut, expecting the correct NameState."
        },
        {
            "input": [
                {
                    "parameter": "characters",
                    "value": "charactersLeadingToMultipleFinalTransitions"
                },
                {
                    "parameter": "pattern",
                    "value": "validPattern"
                }
            ],
            "expected": "expectedNameState",
            "description": "Test case where multiple final transitions are possible, expecting the correct NameState for the pattern."
        },
        {
            "input": [
                {
                    "parameter": "characters",
                    "value": "charactersMatchingAnythingButSuffix"
                },
                {
                    "parameter": "pattern",
                    "value": "anythingButSuffixPattern"
                }
            ],
            "expected": "expectedNameState",
            "description": "Test case where characters match a pattern with 'anything but suffix', expecting the correct NameState."
        }
    ]
}