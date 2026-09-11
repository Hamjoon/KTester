{"group": "soundex", "cases": [
    {
        "input": [
            {
                "parameter": "source",
                "value": "A"
            },
            {
                "parameter": "branching",
                "value": false
            }
        ],
        "expected": ["A000"],
        "description": "Test with a single vowel character input to verify it returns the appropriate soundex code."
    },
    {
        "input": [
            {
                "parameter": "source",
                "value": "Mann"
            },
            {
                "parameter": "branching",
                "value": false
            }
        ],
        "expected": ["M500"],
        "description": "Test with a name that has repeated characters to verify soundex handles it correctly without branching."
    },
    {
        "input": [
            {
                "parameter": "source",
                "value": "M"
            },
            {
                "parameter": "branching",
                "value": true
            }
        ],
        "expected": ["M000"],
        "description": "Test with a single consonant character input to check if it returns the appropriate soundex code with branching."
    },
    {
        "input": [
            {
                "parameter": "source",
                "value": "Nash"
            },
            {
                "parameter": "branching",
                "value": true
            }
        ],
        "expected": ["N020"],
        "description": "Test with a name that includes a combination of consonants and vowels to validate soundex generation with branching."
    },
    {
        "input": [
            {
                "parameter": "source",
                "value": "O'Connor"
            },
            {
                "parameter": "branching",
                "value": false
            }
        ],
        "expected": ["O250"],
        "description": "Test with a name that includes an apostrophe to ensure soundex generates the correct code."
    },
    {
        "input": [
            {
                "parameter": "source",
                "value": "Alfred"
            },
            {
                "parameter": "branching",
                "value": true
            }
        ],
        "expected": ["A634"],
        "description": "Test with a name that includes both vowels and consonants to check soundex generation with branching."
    },
    {
        "input": [
            {
                "parameter": "source",
                "value": "Avery"
            },
            {
                "parameter": "branching",
                "value": false
            }
        ],
        "expected": ["A640"],
        "description": "Test with a name that starts with a vowel and includes consonants to verify soundex generation without branching."
    },
    {
        "input": [
            {
                "parameter": "source",
                "value": "Bach"
            },
            {
                "parameter": "branching",
                "value": true
            }
        ],
        "expected": ["B200"],
        "description": "Test with a name that has a unique consonant-vowel combination to validate soundex generation with branching."
    }
]}