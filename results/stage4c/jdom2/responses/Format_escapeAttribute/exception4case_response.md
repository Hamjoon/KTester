{
    "group": "escapeAttribute Exception Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "DefaultEscapeStrategy"
                },
                {
                    "parameter": "value",
                    "value": "\u0000D800"
                }
            ],
            "expected": "IllegalDataException",
            "description": "Test with a string containing a high surrogate character without a following low surrogate. Should throw IllegalDataException."
        },
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "DefaultEscapeStrategy"
                },
                {
                    "parameter": "value",
                    "value": "\u0000D800A"
                }
            ],
            "expected": "IllegalDataException",
            "description": "Test with a string containing a high surrogate character followed by a non-surrogate character. Should throw IllegalDataException."
        },
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "DefaultEscapeStrategy"
                },
                {
                    "parameter": "value",
                    "value": "\u0000D800D800"
                }
            ],
            "expected": "IllegalDataException",
            "description": "Test with a string containing consecutive high surrogate characters. Should throw IllegalDataException."
        },
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "DefaultEscapeStrategy"
                },
                {
                    "parameter": "value",
                    "value": "\u0000DC00"
                }
            ],
            "expected": "IllegalDataException",
            "description": "Test with a string containing a low surrogate character without a preceding high surrogate. Should throw IllegalDataException."
        },
        {
            "input": [
                {
                    "parameter": "strategy",
                    "value": "DefaultEscapeStrategy"
                },
                {
                    "parameter": "value",
                    "value": "\u0000D800DC00D800"
                }
            ],
            "expected": "IllegalDataException",
            "description": "Test with a string containing a valid surrogate pair followed by a high surrogate without a low surrogate. Should throw IllegalDataException."
        }
    ]
}