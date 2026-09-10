{
    "test cases": [
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
                            "value": "new ByteState()"
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
                    "description": "Test case where the only character is a wildcard '*'. It should create a new start state match."
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
                    "expected": "new NameState() with specific transitions",
                    "description": "Test case to verify behavior when a shortcut transition is encountered with an exact match."
                }
            ]
        },
        {
            "group": "addEndOfMatch - No Shortcut Needed",
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
                            "value": "[new InputCharacter('c'), new InputCharacter('d')]"
                        },
                        {
                            "parameter": "charIndex",
                            "value": "0"
                        },
                        {
                            "parameter": "pattern",
                            "value": "new Patterns(MatchType.SUFFIX)"
                        },
                        {
                            "parameter": "nameStateCandidate",
                            "value": "new NameState()"
                        }
                    ],
                    "expected": "new NameState() with extended transitions",
                    "description": "Test case when no shortcut is eligible and should extend all characters to the byte state path."
                }
            ]
        },
        {
            "group": "addEndOfMatch - Last Character Wildcard",
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
                            "value": "[new InputCharacter('e'), new InputCharacter('*')]"
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
                    "expected": "new NameState() with specific transitions",
                    "description": "Test case to check behavior when the last character is a wildcard, preventing reuse of state."
                }
            ]
        },
        {
            "group": "addEndOfMatch - Empty Transition",
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
                            "value": "[new InputCharacter('f')]"
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
                            "value": "null"
                        }
                    ],
                    "expected": "new NameState() with transitions created",
                    "description": "Test case to verify behavior when an empty transition is encountered."
                }
            ]
        }
    ]
}