{
    "group": "printContent method test cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "walker",
                    "value": "Walker with next() returning null and isCDATA() true"
                }
            ],
            "expected": "printCDATA is called with appropriate parameters",
            "description": "Test case where walker.next() returns null and walker.isCDATA() is true, expecting printCDATA to be called."
        },
        {
            "input": [
                {
                    "parameter": "walker",
                    "value": "Walker with next() returning null and isCDATA() false"
                }
            ],
            "expected": "printText is called with appropriate parameters",
            "description": "Test case where walker.next() returns null and walker.isCDATA() is false, expecting printText to be called."
        },
        {
            "input": [
                {
                    "parameter": "walker",
                    "value": "Walker with next() returning Content of type CDATA"
                }
            ],
            "expected": "printCDATA is called with the CDATA content",
            "description": "Test case where walker.next() returns a CDATA content, expecting printCDATA to be called."
        },
        {
            "input": [
                {
                    "parameter": "walker",
                    "value": "Walker with next() returning Content of type Comment"
                }
            ],
            "expected": "printComment is called with the Comment content",
            "description": "Test case where walker.next() returns a Comment content, expecting printComment to be called."
        },
        {
            "input": [
                {
                    "parameter": "walker",
                    "value": "Walker with next() returning Content of type Element"
                }
            ],
            "expected": "printElement is called with the Element content",
            "description": "Test case where walker.next() returns an Element content, expecting printElement to be called."
        },
        {
            "input": [
                {
                    "parameter": "walker",
                    "value": "Walker with next() returning Content of type EntityRef"
                }
            ],
            "expected": "printEntityRef is called with the EntityRef content",
            "description": "Test case where walker.next() returns an EntityRef content, expecting printEntityRef to be called."
        },
        {
            "input": [
                {
                    "parameter": "walker",
                    "value": "Walker with next() returning Content of type ProcessingInstruction"
                }
            ],
            "expected": "printProcessingInstruction is called with the ProcessingInstruction content",
            "description": "Test case where walker.next() returns a ProcessingInstruction content, expecting printProcessingInstruction to be called."
        },
        {
            "input": [
                {
                    "parameter": "walker",
                    "value": "Walker with next() returning Content of type Text"
                }
            ],
            "expected": "printText is called with the Text content",
            "description": "Test case where walker.next() returns a Text content, expecting printText to be called."
        },
        {
            "input": [
                {
                    "parameter": "walker",
                    "value": "Walker with next() returning Content of type DocType"
                }
            ],
            "expected": "printDocType is called with the DocType content",
            "description": "Test case where walker.next() returns a DocType content, expecting printDocType to be called."
        },
        {
            "input": [
                {
                    "parameter": "walker",
                    "value": "Walker with next() returning Content of unknown type"
                }
            ],
            "expected": "IllegalStateException is thrown with message 'Unexpected Content '",
            "description": "Test case where walker.next() returns a Content of an unknown type, expecting an IllegalStateException to be thrown."
        }
    ]
}