{
    "group": "formatInternal method tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "internal",
                    "value": ""
                }
            ],
            "expected": "",
            "description": "Test with empty string input to verify handling of no content."
        },
        {
            "input": [
                {
                    "parameter": "internal",
                    "value": " "
                }
            ],
            "expected": "",
            "description": "Test with single whitespace input to verify it's ignored."
        },
        {
            "input": [
                {
                    "parameter": "internal",
                    "value": "   "
                }
            ],
            "expected": "",
            "description": "Test with multiple whitespaces to verify they're reduced to a single space."
        },
        {
            "input": [
                {
                    "parameter": "internal",
                    "value": "a"
                }
            ],
            "expected": "a",
            "description": "Test with single non-whitespace character to verify it's returned as is."
        },
        {
            "input": [
                {
                    "parameter": "internal",
                    "value": "<>"
                }
            ],
            "expected": "  >\n",
            "description": "Test with declaration symbols to verify proper formatting with new line."
        },
        {
            "input": [
                {
                    "parameter": "internal",
                    "value": "<tag>"
                }
            ],
            "expected": "  tag>\n",
            "description": "Test with simple tag to verify it ends with newline and space is added before tag."
        },
        {
            "input": [
                {
                    "parameter": "internal",
                    "value": "<tag attr='value'>"
                }
            ],
            "expected": "  tag attr='value'>\n",
            "description": "Test with tag having attributes to verify attributes are included in output."
        },
        {
            "input": [
                {
                    "parameter": "internal",
                    "value": "<tag attr=\"value\">"
                }
            ],
            "expected": "  tag attr=\"value\">\n",
            "description": "Test with tag having double-quoted attributes to verify correct handling of quotes."
        },
        {
            "input": [
                {
                    "parameter": "internal",
                    "value": "<tag attr='value' attr2=\"value2\">"
                }
            ],
            "expected": "  tag attr='value' attr2=\"value2\">\n",
            "description": "Test with tag having mixed quotes for attributes to verify handling of both quote types."
        },
        {
            "input": [
                {
                    "parameter": "internal",
                    "value": "<tag>   </tag>"
                }
            ],
            "expected": "  tag> </tag>\n",
            "description": "Test with tag containing whitespace to verify whitespace is reduced correctly."
        },
        {
            "input": [
                {
                    "parameter": "internal",
                    "value": "<tag attr='value'><tag2>"
                }
            ],
            "expected": "  tag attr='value'>\n  tag2>\n",
            "description": "Test with nested tags to verify each tag is processed and formatted correctly."
        },
        {
            "input": [
                {
                    "parameter": "internal",
                    "value": "<tag attr='value'>text</tag>"
                }
            ],
            "expected": "  tag attr='value'>text</tag>\n",
            "description": "Test with tag containing text to verify text is preserved and formatted correctly."
        }
    ]
}