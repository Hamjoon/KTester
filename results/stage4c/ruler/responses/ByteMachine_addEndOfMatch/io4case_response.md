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
                    "value": "[new InputCharacter('x'), new InputCharacter('y'), new InputCharacter('z')]"
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
                    "value": "non-null NameState instance"
                }
            ],
            "expected": "NameState instance",
            "description": "Test case with multiple characters and a non-null nameStateCandidate, ensuring it is reused."
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
                    "value": "[new InputCharacter('f'), new InputCharacter('g')]"
                },
                {
                    "parameter": "charIndex",
                    "value": 0
                },
                {
                    "parameter": "pattern",
                    "value": "Patterns instance with type SUFFIX"
                },
                {
                    "parameter": "nameStateCandidate",
                    "value": "null"
                }
            ],
            "expected": "NameState instance",
            "description": "Test case where pattern type is SUFFIX, testing non-shortcut logic."
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
                    "value": "[new InputCharacter('h'), new InputCharacter('i')]"
                },
                {
                    "parameter": "charIndex",
                    "value": 1
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
            "description": "Test case starting from a non-zero charIndex, ensuring correct handling of partial character arrays."
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
                    "value": "[new InputCharacter('j')]"
                },
                {
                    "parameter": "charIndex",
                    "value": 0
                },
                {
                    "parameter": "pattern",
                    "value": "Patterns instance with type ANYTHING_BUT_SUFFIX"
                },
                {
                    "parameter": "nameStateCandidate",
                    "value": "null"
                }
            ],
            "expected": "NameState instance",
            "description": "Test case where pattern type is ANYTHING_BUT_SUFFIX, testing handling of this specific pattern type."
        }
    ]
}