{
    "group": "printContent method tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "walker",
                    "value": "Walker with mixed content types including CDATA, Text, and Comment"
                }
            ],
            "expected": "printCDATA, printText, and printComment methods are called in sequence",
            "description": "Test case where walker has mixed content types, expecting the corresponding print methods to be called in sequence."
        },
        {
            "input": [
                {
                    "parameter": "walker",
                    "value": "Walker with nested Element content"
                }
            ],
            "expected": "printElement method is called recursively for nested elements",
            "description": "Test case where walker has nested Element content, expecting printElement to handle nested elements recursively."
        },
        {
            "input": [
                {
                    "parameter": "walker",
                    "value": "Walker with empty content list"
                }
            ],
            "expected": "No print methods are called",
            "description": "Test case where walker has an empty content list, expecting no print methods to be called."
        },
        {
            "input": [
                {
                    "parameter": "walker",
                    "value": "Walker with content that causes XMLStreamException"
                }
            ],
            "expected": "XMLStreamException is thrown",
            "description": "Test case where walker has content that causes an XMLStreamException, expecting the exception to be thrown."
        },
        {
            "input": [
                {
                    "parameter": "walker",
                    "value": "Walker with all supported content types in random order"
                }
            ],
            "expected": "All corresponding print methods are called in the order of content types",
            "description": "Test case where walker has all supported content types in random order, expecting all print methods to be called in the order of content types."
        }
    ]
}