{
    "test cases": [
        {
            "group": "addEndOfMatch - Single Character Match",
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
                            "value": "[new InputCharacter('g')]"
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
                    "description": "Test case where there is a single character 'g' and expects a new NameState with transitions for an exact match."
                }
            ]
        },
        {
            "group": "addEndOfMatch - Multiple Characters with Wildcard",
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
                            "value": "[new InputCharacter('h'), new InputCharacter('*')]"
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
                    "description": "Test case to check behavior when there are multiple characters with the last being a wildcard."
                }
            ]
        },
        {
            "group": "addEndOfMatch - Transition with Non-Wildcard Last Character",
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
                            "value": "[new InputCharacter('i'), new InputCharacter('j')]"
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
                    "expected": "new NameState() with specific transitions",
                    "description": "Test case to verify behavior when both characters are non-wildcard and should create a new transition path."
                }
            ]
        },
        {
            "group": "addEndOfMatch - Empty Input Characters",
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
                            "value": "new Patterns(MatchType.EXISTS)"
                        },
                        {
                            "parameter": "nameStateCandidate",
                            "value": "new NameState()"
                        }
                    ],
                    "expected": "new NameState()",
                    "description": "Test case to check behavior when no input characters are provided."
                }
            ]
        },
        {
            "group": "addEndOfMatch - Boundary Condition - Max Characters",
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
                            "value": "[new InputCharacter('k'), new InputCharacter('l'), new InputCharacter('m'), new InputCharacter('n')]"
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
                    "description": "Test case to check behavior when maximum characters are provided for exact match."
                }
            ]
        }
    ]
}