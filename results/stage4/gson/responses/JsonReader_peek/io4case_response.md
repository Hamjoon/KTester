{
    "testCases": [
        {
            "group": "test peek with edge cases",
            "cases": [
                {
                    "input": [],
                    "expected": "JsonToken.BEGIN_OBJECT",
                    "description": "Test peek when the input JSON starts with a begin object token."
                },
                {
                    "input": [],
                    "expected": "JsonToken.END_ARRAY",
                    "description": "Test peek when the input JSON ends with an end array token."
                },
                {
                    "input": [],
                    "expected": "JsonToken.NUMBER",
                    "description": "Test peek when the input JSON has a number token at the beginning."
                },
                {
                    "input": [],
                    "expected": "JsonToken.STRING",
                    "description": "Test peek when the input JSON has a string token at the beginning."
                },
                {
                    "input": [],
                    "expected": "JsonToken.BOOLEAN",
                    "description": "Test peek when the input JSON has a boolean token at the beginning."
                }
            ]
        },
        {
            "group": "test peek with complex JSON structures",
            "cases": [
                {
                    "input": [],
                    "expected": "JsonToken.BEGIN_ARRAY",
                    "description": "Test peek when the input JSON is a complex structure that starts with a begin array token."
                },
                {
                    "input": [],
                    "expected": "JsonToken.END_OBJECT",
                    "description": "Test peek when the input JSON is a complex structure that ends with an end object token."
                }
            ]
        },
        {
            "group": "test peek after multiple operations",
            "cases": [
                {
                    "input": [],
                    "expected": "JsonToken.END_DOCUMENT",
                    "description": "Test peek after reading all tokens in the input JSON, expecting an end document token."
                },
                {
                    "input": [],
                    "expected": "JsonToken.NULL",
                    "description": "Test peek after reading a null token in the input JSON."
                }
            ]
        }
    ]
}