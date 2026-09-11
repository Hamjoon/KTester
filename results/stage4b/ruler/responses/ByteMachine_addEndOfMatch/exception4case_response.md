{
    "output": [
        {
            "group": "addEndOfMatch - Invalid State Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "null"
                        },
                        {
                            "parameter": "prevState",
                            "value": "new ByteState()"
                        },
                        {
                            "parameter": "characters",
                            "value": "[new InputCharacter('a')]"
                        },
                        {
                            "parameter": "charIndex",
                            "value": "0"
                        },
                        {
                            "parameter": "pattern",
                            "value": "new Patterns(MatchType.EXACT)"
                        },
                        {
                            "parameter": "nameStateCandidate",
                            "value": "new NameState()"
                        }
                    ],
                    "expected": "NullPointerException",
                    "description": "Test when the state parameter is null. It should throw a NullPointerException."
                }
            ]
        },
        {
            "group": "addEndOfMatch - Invalid Characters Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "new ByteState()"
                        },
                        {
                            "parameter": "prevState",
                            "value": "new ByteState()"
                        },
                        {
                            "parameter": "characters",
                            "value": "[]"
                        },
                        {
                            "parameter": "charIndex",
                            "value": "0"
                        },
                        {
                            "parameter": "pattern",
                            "value": "new Patterns(MatchType.EXACT)"
                        },
                        {
                            "parameter": "nameStateCandidate",
                            "value": "new NameState()"
                        }
                    ],
                    "expected": "ArrayIndexOutOfBoundsException",
                    "description": "Test when the characters array is empty. It should throw an ArrayIndexOutOfBoundsException."
                }
            ]
        },
        {
            "group": "addEndOfMatch - Invalid charIndex Handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "new ByteState()"
                        },
                        {
                            "parameter": "prevState",
                            "value": "new ByteState()"
                        },
                        {
                            "parameter": "characters",
                            "value": "[new InputCharacter('a')]"
                        },
                        {
                            "parameter": "charIndex",
                            "value": "1"
                        },
                        {
                            "parameter": "pattern",
                            "value": "new Patterns(MatchType.EXACT)"
                        },
                        {
                            "parameter": "nameStateCandidate",
                            "value": "new NameState()"
                        }
                    ],
                    "expected": "ArrayIndexOutOfBoundsException",
                    "description": "Test when charIndex is greater than the length of characters array. It should throw an ArrayIndexOutOfBoundsException."
                }
            ]
        }
    ]
}