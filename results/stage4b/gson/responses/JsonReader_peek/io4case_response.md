{
    "test cases": [
        {
            "group": "peek method - PEEKED_BUFFERED case",
            "cases": [
                {
                    "input": [],
                    "expected": "JsonToken.STRING",
                    "description": "Test that calling peek() when peeked is PEEKED_BUFFERED returns JsonToken.STRING."
                }
            ]
        },
        {
            "group": "peek method - PEEKED_TRUE case",
            "cases": [
                {
                    "input": [],
                    "expected": "JsonToken.BOOLEAN",
                    "description": "Test that calling peek() when peeked is PEEKED_TRUE returns JsonToken.BOOLEAN."
                }
            ]
        },
        {
            "group": "peek method - PEEKED_FALSE case",
            "cases": [
                {
                    "input": [],
                    "expected": "JsonToken.BOOLEAN",
                    "description": "Test that calling peek() when peeked is PEEKED_FALSE returns JsonToken.BOOLEAN."
                }
            ]
        },
        {
            "group": "peek method - PEEKED_LONG case",
            "cases": [
                {
                    "input": [],
                    "expected": "JsonToken.NUMBER",
                    "description": "Test that calling peek() when peeked is PEEKED_LONG returns JsonToken.NUMBER."
                }
            ]
        },
        {
            "group": "peek method - PEEKED_NUMBER case",
            "cases": [
                {
                    "input": [],
                    "expected": "JsonToken.NUMBER",
                    "description": "Test that calling peek() when peeked is PEEKED_NUMBER returns JsonToken.NUMBER."
                }
            ]
        },
        {
            "group": "peek method - invalid peeked value case",
            "cases": [
                {
                    "input": [],
                    "expected": "AssertionError",
                    "description": "Test that calling peek() when peeked is an invalid value (not defined) throws an AssertionError."
                }
            ]
        }
    ]
}