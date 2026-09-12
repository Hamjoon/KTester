{
    "group": "addEndOfMatch",
    "cases": [
        {
            "input": [
                {
                    "parameter": "state",
                    "value": "ByteState instance"
                },
                {
                    "parameter": "prevState",
                    "value": "ByteState instance"
                },
                {
                    "parameter": "characters",
                    "value": "[new InputCharacter('*')]"
                },
                {
                    "parameter": "charIndex",
                    "value": 0
                },
                {
                    "parameter": "pattern",
                    "value": "Patterns instance with type EXACT"
                },
                {
                    "parameter": "nameStateCandidate",
                    "value": "null"
                }
            ],
            "expected": "NameState instance with startStateMatch set",
            "description": "Test case where the only character is '*', which should match empty input."
        },
        {
            "input": [
                {
                    "parameter": "state",
                    "value": "ByteState instance"
                },
                {
                    "parameter": "prevState",
                    "value": "ByteState instance"
                },
                {
                    "parameter": "characters",
                    "value": "[new InputCharacter('a')]"
                },
                {
                    "parameter": "charIndex",
                    "value": 0
                },
                {
                    "parameter": "pattern",
                    "value": "Patterns instance with type EXACT"
                },
                {
                    "parameter": "nameStateCandidate",
                    "value": "null"
                }
            ],
            "expected": "NameState instance",
            "description": "Test case where the transition is not empty and is a shortcut transition."
        },
        {
            "input": [
                {
                    "parameter": "state",
                    "value": "ByteState instance"
                },
                {
                    "parameter": "prevState",
                    "value": "ByteState instance"
                },
                {
                    "parameter": "characters",
                    "value": "[new InputCharacter('b'), new InputCharacter('c')]"
                },
                {
                    "parameter": "charIndex",
                    "value": 0
                },
                {
                    "parameter": "pattern",
                    "value": "Patterns instance with type EXACT"
                },
                {
                    "parameter": "nameStateCandidate",
                    "value": "null"
                }
            ],
            "expected": "NameState instance",
            "description": "Test case with multiple characters where no shortcut is needed."
        },
        {
            "input": [
                {
                    "parameter": "state",
                    "value": "ByteState instance"
                },
                {
                    "parameter": "prevState",
                    "value": "ByteState instance"
                },
                {
                    "parameter": "characters",
                    "value": "[new InputCharacter('d'), new InputCharacter('*')]"
                },
                {
                    "parameter": "charIndex",
                    "value": 0
                },
                {
                    "parameter": "pattern",
                    "value": "Patterns instance with type EXACT"
                },
                {
                    "parameter": "nameStateCandidate",
                    "value": "null"
                }
            ],
            "expected": "NameState instance",
            "description": "Test case where the transition has a wildcard at the end."
        },
        {
            "input": [
                {
                    "parameter": "state",
                    "value": "ByteState instance"
                },
                {
                    "parameter": "prevState",
                    "value": "ByteState instance"
                },
                {
                    "parameter": "characters",
                    "value": "[new InputCharacter('e')]"
                },
                {
                    "parameter": "charIndex",
                    "value": 0
                },
                {
                    "parameter": "pattern",
                    "value": "Patterns instance with type EXISTS"
                },
                {
                    "parameter": "nameStateCandidate",
                    "value": "null"
                }
            ],
            "expected": "NameState instance",
            "description": "Test case where pattern type is EXISTS, testing non-shortcut logic."
        }
    ]
}