{
    "test cases": [
        {
            "group": "formatInternal Tests",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "internal",
                            "value": "   "
                        }
                    ],
                    "expected": "newline character",
                    "description": "Test with only whitespace input. The output should be a newline character as all whitespaces are ignored."
                },
                {
                    "input": [
                        {
                            "parameter": "internal",
                            "value": "'quoted text'"
                        }
                    ],
                    "expected": "'quoted text'",
                    "description": "Test with a single quoted string. The output should be the same quoted string since it is not affected by whitespace handling."
                },
                {
                    "input": [
                        {
                            "parameter": "internal",
                            "value": "text < declaration > more text"
                        }
                    ],
                    "expected": "text  \ndeclaration\nmore text",
                    "description": "Test with text surrounding a declaration. The output should replace the declaration with two spaces, and end the declaration with a newline."
                },
                {
                    "input": [
                        {
                            "parameter": "internal",
                            "value": "text < declaration >  "
                        }
                    ],
                    "expected": "text  \ndeclaration\n",
                    "description": "Test with trailing whitespace after a declaration. The output should remove the trailing whitespace after the declaration."
                },
                {
                    "input": [
                        {
                            "parameter": "internal",
                            "value": "text < declaration > more text < another >"
                        }
                    ],
                    "expected": "text  \ndeclaration\nmore text  \nanother\n",
                    "description": "Test with multiple declarations. Each declaration should end with a newline, and whitespace should be handled correctly."
                },
                {
                    "input": [
                        {
                            "parameter": "internal",
                            "value": "text <declaration> 'quoted text' <another>"
                        }
                    ],
                    "expected": "text  \ndeclaration\n 'quoted text'  \nanother\n",
                    "description": "Test with a declaration and a quoted string in between. The output should preserve the quoted string and handle declarations correctly."
                },
                {
                    "input": [
                        {
                            "parameter": "internal",
                            "value": "<declaration> 'quoted text' <another> "
                        }
                    ],
                    "expected": "  \ndeclaration\n 'quoted text'  \nanother\n",
                    "description": "Test with a declaration at the start and trailing whitespace. The output should handle the declaration and preserve the quoted text."
                }
            ]
        }
    ]
}