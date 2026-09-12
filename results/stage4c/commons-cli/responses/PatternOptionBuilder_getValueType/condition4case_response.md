{
    "group": "getValueType(char) Test Cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "ch",
                    "value": "@"
                }
            ],
            "expected": "Object.class",
            "description": "Verify that the method returns Object.class when the input character is '@'."
        },
        {
            "input": [
                {
                    "parameter": "ch",
                    "value": ":"
                }
            ],
            "expected": "String.class",
            "description": "Verify that the method returns String.class when the input character is ':'."
        },
        {
            "input": [
                {
                    "parameter": "ch",
                    "value": "%"
                }
            ],
            "expected": "Number.class",
            "description": "Verify that the method returns Number.class when the input character is '%'."
        },
        {
            "input": [
                {
                    "parameter": "ch",
                    "value": "+"
                }
            ],
            "expected": "Class.class",
            "description": "Verify that the method returns Class.class when the input character is '+'."
        },
        {
            "input": [
                {
                    "parameter": "ch",
                    "value": "#"
                }
            ],
            "expected": "Date.class",
            "description": "Verify that the method returns Date.class when the input character is '#'."
        },
        {
            "input": [
                {
                    "parameter": "ch",
                    "value": "<"
                }
            ],
            "expected": "FileInputStream.class",
            "description": "Verify that the method returns FileInputStream.class when the input character is '<'."
        },
        {
            "input": [
                {
                    "parameter": "ch",
                    "value": ">"
                }
            ],
            "expected": "File.class",
            "description": "Verify that the method returns File.class when the input character is '>'."
        },
        {
            "input": [
                {
                    "parameter": "ch",
                    "value": "*"
                }
            ],
            "expected": "File[].class",
            "description": "Verify that the method returns File[].class when the input character is '*'."
        },
        {
            "input": [
                {
                    "parameter": "ch",
                    "value": "/"
                }
            ],
            "expected": "URL.class",
            "description": "Verify that the method returns URL.class when the input character is '/'."
        },
        {
            "input": [
                {
                    "parameter": "ch",
                    "value": "x"
                }
            ],
            "expected": null,
            "description": "Verify that the method returns null when the input character does not match any case."
        }
    ]
}