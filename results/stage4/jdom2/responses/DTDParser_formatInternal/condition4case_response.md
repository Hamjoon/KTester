{
    "test cases": [
        {
            "group": "formatInternal",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "internal",
                            "value": ""
                        }
                    ],
                    "expected": "",
                    "description": "Test with an empty string input. The output should also be an empty string."
                },
                {
                    "input": [
                        {
                            "parameter": "internal",
                            "value": "   "
                        }
                    ],
                    "expected": " ",
                    "description": "Test with a string containing only whitespace. The output should be a single space."
                },
                {
                    "input": [
                        {
                            "parameter": "internal",
                            "value": "text"
                        }
                    ],
                    "expected": "text",
                    "description": "Test with a normal string without whitespace or quotes. The output should be the same string."
                },
                {
                    "input": [
                        {
                            "parameter": "internal",
                            "value": "'quoted text'"
                        }
                    ],
                    "expected": "'quoted text'",
                    "description": "Test with a string containing single quotes. The output should match the input as the quotes are preserved."
                },
                {
                    "input": [
                        {
                            "parameter": "internal",
                            "value": "\"quoted text\""
                        }
                    ],
                    "expected": "\"quoted text\"",
                    "description": "Test with a string containing double quotes. The output should match the input as the quotes are preserved."
                },
                {
                    "input": [
                        {
                            "parameter": "internal",
                            "value": "<declaration>  </declaration>"
                        }
                    ],
                    "expected": "  <declaration>\n  </declaration>\n",
                    "description": "Test with a string containing a declaration. The output should have new lines added after the declaration."
                },
                {
                    "input": [
                        {
                            "parameter": "internal",
                            "value": "<tag> text </tag>"
                        }
                    ],
                    "expected": "  <tag> text </tag>\n",
                    "description": "Test with a string containing a tag with text. The output should preserve the tag and text while adding new line after the tag."
                },
                {
                    "input": [
                        {
                            "parameter": "internal",
                            "value": "text <tag> more text </tag> text"
                        }
                    ],
                    "expected": "text  <tag> more text </tag>\n text",
                    "description": "Test with a string containing text before and after a tag. The output should preserve the text and format the tag correctly."
                },
                {
                    "input": [
                        {
                            "parameter": "internal",
                            "value": "<tag> text <nested> more text </nested> </tag>"
                        }
                    ],
                    "expected": "  <tag> text <nested> more text </nested> </tag>\n",
                    "description": "Test with a nested tag inside a tag. The output should format the nested structure correctly."
                }
            ]
        }
    ]
}