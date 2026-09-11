{
    "testCases": [
        {
            "group": "formatInternal Tests",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "internal",
                            "value": "<declaration>"
                        }
                    ],
                    "expected": "  \ndeclaration\n",
                    "description": "Test with a single declaration and no surrounding text. The output should have two spaces before the declaration and end with a newline."
                },
                {
                    "input": [
                        {
                            "parameter": "internal",
                            "value": "text <declaration> text"
                        }
                    ],
                    "expected": "text  \ndeclaration\ntext",
                    "description": "Test with text surrounding a declaration without trailing whitespace. The output should replace the declaration with two spaces and end without additional newline."
                },
                {
                    "input": [
                        {
                            "parameter": "internal",
                            "value": "'quoted text' <declaration> 'more quoted text'"
                        }
                    ],
                    "expected": "'quoted text'  \ndeclaration\n 'more quoted text'",
                    "description": "Test with quoted text before and after a declaration. The output should preserve the quoted texts and handle the declaration correctly."
                },
                {
                    "input": [
                        {
                            "parameter": "internal",
                            "value": "text <declaration> <another>"
                        }
                    ],
                    "expected": "text  \ndeclaration\n  \nanother\n",
                    "description": "Test with multiple declarations without surrounding text. Each declaration should be handled correctly, with proper newlines and spacing."
                },
                {
                    "input": [
                        {
                            "parameter": "internal",
                            "value": "text <declaration>   <another>   "
                        }
                    ],
                    "expected": "text  \ndeclaration\n  \nanother\n",
                    "description": "Test with multiple declarations and trailing whitespace. The output should handle the whitespace correctly after the last declaration."
                },
                {
                    "input": [
                        {
                            "parameter": "internal",
                            "value": "text <declaration> 'quoted text' <another> text"
                        }
                    ],
                    "expected": "text  \ndeclaration\n 'quoted text'  \nanother\ntext",
                    "description": "Test with a declaration and quoted text in between, followed by more text. The output should preserve the quoted text and handle declarations correctly."
                }
            ]
        }
    ]
}