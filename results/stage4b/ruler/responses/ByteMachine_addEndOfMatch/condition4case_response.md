{
    "testCases": [
        {
            "group": "addEndOfMatch - Wildcard Match",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "state",
                            "value": "new ByteState()"
                        },
                        {
                            "parameter": "prevState",
                            "value": "null"
                        },
                        {
                            "parameter": "characters",
                            "value": "[new InputCharacter('*')]"
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
                            "value": "null"
                        }
                    ],
                    "expected": "new NameState()",
                    "description": "Test when the only character is a wildcard '*'. It should create a new NameState and set the startStateMatch."
                }
            ]
        },
        {
            "group": "addEndOfMatch - Shortcut Transition",
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
                            "value": "[new InputCharacter('a'), new InputCharacter('b')]"
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
                    "expected": "new NameState()",
                    "description": "Test when there is a shortcut transition for the first character. It should adjust the transitions accordingly."
                },
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
                            "value": "[new InputCharacter('a'), new InputCharacter('c')]"
                        },
                        {
                            "parameter": "charIndex",
                            "value": "0"
                        },
                        {
                            "parameter": "pattern",
                            "value": "new Patterns(MatchType.EXISTS)"
                        },
                        {
                            "parameter": "nameStateCandidate",
                            "value": "new NameState()"
                        }
                    ],
                    "expected": "new NameState()",
                    "description": "Test when there is no shortcut transition for the first character. It should continue to the next character."
                }
            ]
        },
        {
            "group": "addEndOfMatch - Exact Match Handling",
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
                            "value": "[new InputCharacter('a'), new InputCharacter('b')]"
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
                    "expected": "insertMatch(characters, length - 1, state, nameState, pattern, prevState)",
                    "description": "Test when the last character is reached and it should insert the match at the end."
                }
            ]
        },
        {
            "group": "addEndOfMatch - Non-Match Handling",
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
                            "value": "[new InputCharacter('x'), new InputCharacter('y')]"
                        },
                        {
                            "parameter": "charIndex",
                            "value": "0"
                        },
                        {
                            "parameter": "pattern",
                            "value": "new Patterns(MatchType.ANYTHING_BUT_SUFFIX)"
                        },
                        {
                            "parameter": "nameStateCandidate",
                            "value": "new NameState()"
                        }
                    ],
                    "expected": "insertMatch(characters, length - 1, state, nameState, pattern, prevState)",
                    "description": "Test when the characters do not match any existing transitions. It should handle non-matching characters appropriately."
                }
            ]
        }
    ]
}