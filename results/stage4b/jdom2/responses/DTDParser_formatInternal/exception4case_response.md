{
    "group": "formatInternal Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "internal",
                    "value": ""
                }
            ],
            "expected": "empty string",
            "description": "Test with an empty string input. The output should be an empty string since there are no characters to process."
        },
        {
            "input": [
                {
                    "parameter": "internal",
                    "value": "text <declaration> text <"
                }
            ],
            "expected": "JDOMException",
            "description": "Test with an incomplete declaration ending with '<'. This should throw a JDOMException due to malformed input."
        },
        {
            "input": [
                {
                    "parameter": "internal",
                    "value": "text <declaration> text >"
                }
            ],
            "expected": "JDOMException",
            "description": "Test with a declaration that does not have a proper closing sequence. This should throw a JDOMException due to malformed input."
        },
        {
            "input": [
                {
                    "parameter": "internal",
                    "value": "text <declaration> text <another declaration>"
                }
            ],
            "expected": "JDOMException",
            "description": "Test with nested declarations. This should throw a JDOMException due to invalid nested declaration structure."
        },
        {
            "input": [
                {
                    "parameter": "internal",
                    "value": "text <declaration> 'quoted text' <another declaration> text"
                }
            ],
            "expected": "JDOMException",
            "description": "Test with a declaration followed by a quoted string and another declaration. This should throw a JDOMException due to improper structure."
        }
    ]
}