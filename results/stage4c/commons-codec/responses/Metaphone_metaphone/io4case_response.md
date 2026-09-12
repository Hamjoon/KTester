{
    "group": "Additional Metaphone Method Test Cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "txt",
                    "value": "GHOTI"
                }
            ],
            "expected": "FT",
            "description": "Test the metaphone method with a word containing 'GH' not at the end and followed by a vowel to verify it processes the 'GH' exception correctly."
        },
        {
            "input": [
                {
                    "parameter": "txt",
                    "value": "PNEUMONIA"
                }
            ],
            "expected": "NMN",
            "description": "Test the metaphone method with a word starting with 'PN' to verify it processes the initial exception and returns the correct metaphone code."
        },
        {
            "input": [
                {
                    "parameter": "txt",
                    "value": "PSYCHOLOGY"
                }
            ],
            "expected": "SKLJ",
            "description": "Test the metaphone method with a word starting with 'PS' to verify it processes the initial exception and returns the correct metaphone code."
        },
        {
            "input": [
                {
                    "parameter": "txt",
                    "value": "KNOCK"
                }
            ],
            "expected": "NK",
            "description": "Test the metaphone method with a word starting with 'KN' to verify it processes the initial exception and returns the correct metaphone code."
        },
        {
            "input": [
                {
                    "parameter": "txt",
                    "value": "XEROX"
                }
            ],
            "expected": "SRS",
            "description": "Test the metaphone method with a word starting with 'X' and containing 'X' to verify it processes the initial and internal 'X' correctly."
        },
        {
            "input": [
                {
                    "parameter": "txt",
                    "value": "SCHOLAR"
                }
            ],
            "expected": "SKLR",
            "description": "Test the metaphone method with a word containing 'SCH' to verify it processes the 'SCH' exception correctly."
        },
        {
            "input": [
                {
                    "parameter": "txt",
                    "value": "CIANI"
                }
            ],
            "expected": "XAN",
            "description": "Test the metaphone method with a word containing 'CIA' to verify it processes the 'CIA' exception correctly."
        },
        {
            "input": [
                {
                    "parameter": "txt",
                    "value": "QUEUE"
                }
            ],
            "expected": "K",
            "description": "Test the metaphone method with a word containing 'QUE' to verify it processes the 'QUE' exception correctly."
        },
        {
            "input": [
                {
                    "parameter": "txt",
                    "value": "YACHT"
                }
            ],
            "expected": "YXT",
            "description": "Test the metaphone method with a word containing 'CHT' to verify it processes the 'CH' exception correctly."
        },
        {
            "input": [
                {
                    "parameter": "txt",
                    "value": "ZEBRA"
                }
            ],
            "expected": "SBR",
            "description": "Test the metaphone method with a word starting with 'Z' to verify it processes 'Z' correctly."
        }
    ]
}