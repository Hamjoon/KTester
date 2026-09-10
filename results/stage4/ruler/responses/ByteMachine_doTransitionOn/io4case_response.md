{"group": "doTransitionOn", "cases": [
    {
        "input": [
            {
                "parameter": "valString",
                "value": "abc"
            },
            {
                "parameter": "transitionTo",
                "value": "new HashSet<NameState>()"
            },
            {
                "parameter": "fieldValueIsNumeric",
                "value": false
            }
        ],
        "expected": "Expected NameState(s) based on the matches found for 'abc'",
        "description": "Test with a standard alphabetical string input to verify transitions."
    },
    {
        "input": [
            {
                "parameter": "valString",
                "value": "456"
            },
            {
                "parameter": "transitionTo",
                "value": "new HashSet<NameState>()"
            },
            {
                "parameter": "fieldValueIsNumeric",
                "value": true
            }
        ],
        "expected": "Expected NameState(s) based on numeric matches for '456'",
        "description": "Test with a numeric string input to verify transitions with numeric flag set."
    },
    {
        "input": [
            {
                "parameter": "valString",
                "value": "0.99"
            },
            {
                "parameter": "transitionTo",
                "value": "new HashSet<NameState>()"
            },
            {
                "parameter": "fieldValueIsNumeric",
                "value": true
            }
        ],
        "expected": "Expected NameState(s) based on numeric matches for '0.99'",
        "description": "Test with a decimal numeric string input to verify transitions."
    },
    {
        "input": [
            {
                "parameter": "valString",
                "value": "test123"
            },
            {
                "parameter": "transitionTo",
                "value": "new HashSet<NameState>()"
            },
            {
                "parameter": "fieldValueIsNumeric",
                "value": false
            }
        ],
        "expected": "Expected transitions based on mixed input with no numeric flag set.",
        "description": "Test with a string containing both letters and digits to verify transition handling."
    },
    {
        "input": [
            {
                "parameter": "valString",
                "value": "special@#$%"
            },
            {
                "parameter": "transitionTo",
                "value": "new HashSet<NameState>()"
            },
            {
                "parameter": "fieldValueIsNumeric",
                "value": false
            }
        ],
        "expected": "Expected transitions based on special character input.",
        "description": "Test with a string containing special characters to verify transition behavior."
    },
    {
        "input": [
            {
                "parameter": "valString",
                "value": "  "
            },
            {
                "parameter": "transitionTo",
                "value": "new HashSet<NameState>()"
            },
            {
                "parameter": "fieldValueIsNumeric",
                "value": false
            }
        ],
        "expected": "Expected no transitions for a string with only whitespace.",
        "description": "Test with a string containing only spaces to verify no transitions occur."
    },
    {
        "input": [
            {
                "parameter": "valString",
                "value": "longstringinputfortesting"
            },
            {
                "parameter": "transitionTo",
                "value": "new HashSet<NameState>()"
            },
            {
                "parameter": "fieldValueIsNumeric",
                "value": false
            }
        ],
        "expected": "Expected transitions based on the matches found for a long string.",
        "description": "Test with a long string input to verify handling of larger inputs."
    },
    {
        "input": [
            {
                "parameter": "valString",
                "value": "1234567890"
            },
            {
                "parameter": "transitionTo",
                "value": "new HashSet<NameState>()"
            },
            {
                "parameter": "fieldValueIsNumeric",
                "value": true
            }
        ],
        "expected": "Expected transitions based on numeric matches for a long numeric input.",
        "description": "Test with a long numeric string input to verify transitions."
    }
]}