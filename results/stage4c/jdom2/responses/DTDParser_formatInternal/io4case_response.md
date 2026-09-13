{
    "group": "formatInternal method additional tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "internal",
                    "value": "<tag attr1='value1' attr2='value2'>"
                }
            ],
            "expected": "  tag attr1='value1' attr2='value2'>\n",
            "description": "Test with tag having multiple single-quoted attributes to verify handling of multiple attributes."
        },
        {
            "input": [
                {
                    "parameter": "internal",
                    "value": "<tag attr1=\"value1\" attr2=\"value2\">"
                }
            ],
            "expected": "  tag attr1=\"value1\" attr2=\"value2\">\n",
            "description": "Test with tag having multiple double-quoted attributes to verify handling of multiple attributes."
        },
        {
            "input": [
                {
                    "parameter": "internal",
                    "value": "<tag>text1<inner>text2</inner>text3</tag>"
                }
            ],
            "expected": "  tag>text1\n  inner>text2</inner>\ntext3</tag>\n",
            "description": "Test with nested tags and text content to verify correct formatting of nested structures."
        },
        {
            "input": [
                {
                    "parameter": "internal",
                    "value": "<tag attr='value'>   text   </tag>"
                }
            ],
            "expected": "  tag attr='value'> text </tag>\n",
            "description": "Test with tag containing text surrounded by whitespace to verify whitespace reduction around text."
        },
        {
            "input": [
                {
                    "parameter": "internal",
                    "value": "<!DOCTYPE html>"
                }
            ],
            "expected": "  !DOCTYPE html>\n",
            "description": "Test with DOCTYPE declaration to verify correct formatting with new line."
        },
        {
            "input": [
                {
                    "parameter": "internal",
                    "value": "<tag attr='value'>text</tag><tag2>text2</tag2>"
                }
            ],
            "expected": "  tag attr='value'>text</tag>\n  tag2>text2</tag2>\n",
            "description": "Test with consecutive tags to verify each is processed and formatted correctly, maintaining text content."
        },
        {
            "input": [
                {
                    "parameter": "internal",
                    "value": "<tag attr='value'/>"
                }
            ],
            "expected": "  tag attr='value'/>\n",
            "description": "Test with self-closing tag to verify correct handling and formatting with new line."
        },
        {
            "input": [
                {
                    "parameter": "internal",
                    "value": "<tag attr='value' attr2='value2'/>"
                }
            ],
            "expected": "  tag attr='value' attr2='value2'/>\n",
            "description": "Test with self-closing tag having multiple attributes to verify correct handling and formatting."
        },
        {
            "input": [
                {
                    "parameter": "internal",
                    "value": "<tag attr='value'>text with spaces</tag>"
                }
            ],
            "expected": "  tag attr='value'>text with spaces</tag>\n",
            "description": "Test with text containing spaces within a tag to verify spaces within text are preserved."
        }
    ]
}