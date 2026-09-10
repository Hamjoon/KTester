{
    "testCases": [
        {
            "group": "formatInternal",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "internal",
                            "value": "<tag> text <nested> </nested> </tag>"
                        }
                    ],
                    "expected": "  <tag> text <nested> </nested> </tag>\n",
                    "description": "Test with a tag that contains an empty nested tag. The output should format the nested structure correctly with new line after the tag."
                },
                {
                    "input": [
                        {
                            "parameter": "internal",
                            "value": "<tag> 'quoted text' </tag>"
                        }
                    ],
                    "expected": "  <tag> 'quoted text' </tag>\n",
                    "description": "Test with a tag containing quoted text. The output should preserve the quoted text and format the tag with a new line."
                },
                {
                    "input": [
                        {
                            "parameter": "internal",
                            "value": "<tag> \"quoted text\" </tag>"
                        }
                    ],
                    "expected": "  <tag> \"quoted text\" </tag>\n",
                    "description": "Test with a tag containing double-quoted text. The output should preserve the double quotes and format the tag with a new line."
                },
                {
                    "input": [
                        {
                            "parameter": "internal",
                            "value": "text <tag> text <tag> nested </tag> </tag> text"
                        }
                    ],
                    "expected": "text  <tag> text <tag> nested </tag> </tag>\n text",
                    "description": "Test with a string containing text around nested tags. The output should preserve text and format the nested tags correctly."
                },
                {
                    "input": [
                        {
                            "parameter": "internal",
                            "value": "<tag> text <tag> more text </tag> </tag> <tag> another </tag>"
                        }
                    ],
                    "expected": "  <tag> text <tag> more text </tag> </tag>\n  <tag> another </tag>\n",
                    "description": "Test with multiple tags. The output should format each tag correctly with new lines."
                },
                {
                    "input": [
                        {
                            "parameter": "internal",
                            "value": "<tag> text <nested> more text <inner> inner text </inner> </nested> </tag>"
                        }
                    ],
                    "expected": "  <tag> text <nested> more text <inner> inner text </inner> </nested> </tag>\n",
                    "description": "Test with a complex nested structure. The output should correctly format the nested tags with appropriate new lines."
                }
            ]
        }
    ]
}