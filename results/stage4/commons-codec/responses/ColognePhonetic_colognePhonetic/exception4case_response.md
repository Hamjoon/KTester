{
    "group": "colognePhonetic",
    "cases": [
        {
            "input": [
                {
                    "parameter": "text",
                    "value": ""
                }
            ],
            "expected": "",
            "description": "Test case with an empty string. The method should return an empty string as there are no characters to process."
        },
        {
            "input": [
                {
                    "parameter": "text",
                    "value": "   "
                }
            ],
            "expected": "",
            "description": "Test case with a string containing only spaces. The method should return an empty string as spaces are ignored."
        }
    ]
}