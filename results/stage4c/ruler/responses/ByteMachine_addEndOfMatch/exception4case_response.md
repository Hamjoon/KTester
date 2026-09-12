{
    "group": "addEndOfMatch",
    "cases": [
        {
            "input": [
                {
                    "parameter": "state",
                    "value": "null"
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
            "expected": "NullPointerException",
            "description": "Test case where 'state' is null, expecting a NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "state",
                    "value": "ByteState instance"
                },
                {
                    "parameter": "prevState",
                    "value": "null"
                },
                {
                    "parameter": "characters",
                    "value": "[new InputCharacter('b')]"
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
            "expected": "NullPointerException",
            "description": "Test case where 'prevState' is null, expecting a NullPointerException."
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
                    "value": "null"
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
            "expected": "NullPointerException",
            "description": "Test case where 'characters' is null, expecting a NullPointerException."
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
                    "value": "[new InputCharacter('c')]"
                },
                {
                    "parameter": "charIndex",
                    "value": -1
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
            "expected": "ArrayIndexOutOfBoundsException",
            "description": "Test case where 'charIndex' is negative, expecting an ArrayIndexOutOfBoundsException."
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
                    "value": "[new InputCharacter('d')]"
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
            "expected": "ArrayIndexOutOfBoundsException",
            "description": "Test case where 'charIndex' is out of bounds for the characters array, expecting an ArrayIndexOutOfBoundsException."
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
                    "value": "null"
                },
                {
                    "parameter": "nameStateCandidate",
                    "value": "null"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test case where 'pattern' is null, expecting a NullPointerException."
        }
    ]
}