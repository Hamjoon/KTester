{
    "group": "Metaphone Method Test Cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "txt",
                    "value": null
                }
            ],
            "expected": "",
            "description": "Test the metaphone method with null input to verify it returns an empty string."
        },
        {
            "input": [
                {
                    "parameter": "txt",
                    "value": ""
                }
            ],
            "expected": "",
            "description": "Test the metaphone method with an empty string to ensure it returns an empty string."
        },
        {
            "input": [
                {
                    "parameter": "txt",
                    "value": "A"
                }
            ],
            "expected": "A",
            "description": "Test the metaphone method with a single character string to verify it returns the uppercase of the character."
        },
        {
            "input": [
                {
                    "parameter": "txt",
                    "value": "KNIGHT"
                }
            ],
            "expected": "NKT",
            "description": "Test the metaphone method with a word starting with 'KN' to verify it processes the initial exception and returns the correct metaphone code."
        },
        {
            "input": [
                {
                    "parameter": "txt",
                    "value": "AEON"
                }
            ],
            "expected": "AN",
            "description": "Test the metaphone method with a word starting with 'AE' to verify it processes the initial exception and returns the correct metaphone code."
        },
        {
            "input": [
                {
                    "parameter": "txt",
                    "value": "WRONG"
                }
            ],
            "expected": "RNK",
            "description": "Test the metaphone method with a word starting with 'WR' to verify it processes the initial exception and returns the correct metaphone code."
        },
        {
            "input": [
                {
                    "parameter": "txt",
                    "value": "WHITE"
                }
            ],
            "expected": "WT",
            "description": "Test the metaphone method with a word starting with 'WH' to verify it processes the initial exception and returns the correct metaphone code."
        },
        {
            "input": [
                {
                    "parameter": "txt",
                    "value": "XENON"
                }
            ],
            "expected": "SNN",
            "description": "Test the metaphone method with a word starting with 'X' to verify it processes the initial exception and returns the correct metaphone code."
        },
        {
            "input": [
                {
                    "parameter": "txt",
                    "value": "SCIENCE"
                }
            ],
            "expected": "SNS",
            "description": "Test the metaphone method with a word containing 'SCI' to verify it processes the 'SCI' exception and returns the correct metaphone code."
        },
        {
            "input": [
                {
                    "parameter": "txt",
                    "value": "DGE"
                }
            ],
            "expected": "J",
            "description": "Test the metaphone method with a word containing 'DGE' to verify it processes the 'DGE' exception and returns the correct metaphone code."
        },
        {
            "input": [
                {
                    "parameter": "txt",
                    "value": "GNOME"
                }
            ],
            "expected": "NM",
            "description": "Test the metaphone method with a word containing 'GN' to verify it processes the 'GN' exception and returns the correct metaphone code."
        },
        {
            "input": [
                {
                    "parameter": "txt",
                    "value": "TCH"
                }
            ],
            "expected": "",
            "description": "Test the metaphone method with a word containing 'TCH' to verify it processes the 'TCH' exception and returns the correct metaphone code."
        },
        {
            "input": [
                {
                    "parameter": "txt",
                    "value": "PHIL"
                }
            ],
            "expected": "FL",
            "description": "Test the metaphone method with a word containing 'PH' to verify it processes the 'PH' exception and returns the correct metaphone code."
        },
        {
            "input": [
                {
                    "parameter": "txt",
                    "value": "THOUGHT"
                }
            ],
            "expected": "0T",
            "description": "Test the metaphone method with a word containing 'TH' to verify it processes the 'TH' exception and returns the correct metaphone code."
        },
        {
            "input": [
                {
                    "parameter": "txt",
                    "value": "SUGAR"
                }
            ],
            "expected": "XKR",
            "description": "Test the metaphone method with a word containing 'SUGAR' to verify it processes the 'SUGAR' exception and returns the correct metaphone code."
        }
    ]
}