{
    "group": "ColognePhonetic.colognePhonetic",
    "cases": [
        {
            "input": [
                {
                    "parameter": "text",
                    "value": null
                }
            ],
            "expected": null,
            "description": "Test case for null input. The method should return null when the input text is null."
        },
        {
            "input": [
                {
                    "parameter": "text",
                    "value": ""
                }
            ],
            "expected": "",
            "description": "Test case for empty string input. The method should return an empty string when the input text is empty."
        },
        {
            "input": [
                {
                    "parameter": "text",
                    "value": "AEIJOUY"
                }
            ],
            "expected": "0000000",
            "description": "Test case for input containing only vowels and 'Y'. Each vowel and 'Y' should be encoded as '0'."
        },
        {
            "input": [
                {
                    "parameter": "text",
                    "value": "BP"
                }
            ],
            "expected": "11",
            "description": "Test case for input containing 'B' and 'P'. Each should be encoded as '1', unless 'P' is followed by 'H'."
        },
        {
            "input": [
                {
                    "parameter": "text",
                    "value": "PH"
                }
            ],
            "expected": "3",
            "description": "Test case for 'P' followed by 'H'. 'PH' should be encoded as '3'."
        },
        {
            "input": [
                {
                    "parameter": "text",
                    "value": "DT"
                }
            ],
            "expected": "28",
            "description": "Test case for 'D' and 'T' not followed by 'C', 'S', or 'Z'. Each should be encoded as '2'."
        },
        {
            "input": [
                {
                    "parameter": "text",
                    "value": "CSZ"
                }
            ],
            "expected": "848",
            "description": "Test case for 'C', 'S', and 'Z'. 'C' should be encoded as '8' if followed by 'S' or 'Z', otherwise '4'. 'S' and 'Z' should be encoded as '8'."
        },
        {
            "input": [
                {
                    "parameter": "text",
                    "value": "FPVW"
                }
            ],
            "expected": "3333",
            "description": "Test case for 'F', 'P', 'V', and 'W'. Each should be encoded as '3'."
        },
        {
            "input": [
                {
                    "parameter": "text",
                    "value": "GKQ"
                }
            ],
            "expected": "444",
            "description": "Test case for 'G', 'K', and 'Q'. Each should be encoded as '4'."
        },
        {
            "input": [
                {
                    "parameter": "text",
                    "value": "X"
                }
            ],
            "expected": "48",
            "description": "Test case for 'X' not preceded by 'C', 'K', or 'Q'. 'X' should be encoded as '48'."
        },
        {
            "input": [
                {
                    "parameter": "text",
                    "value": "R"
                }
            ],
            "expected": "7",
            "description": "Test case for 'R'. 'R' should be encoded as '7'."
        },
        {
            "input": [
                {
                    "parameter": "text",
                    "value": "L"
                }
            ],
            "expected": "5",
            "description": "Test case for 'L'. 'L' should be encoded as '5'."
        },
        {
            "input": [
                {
                    "parameter": "text",
                    "value": "MN"
                }
            ],
            "expected": "66",
            "description": "Test case for 'M' and 'N'. Each should be encoded as '6'."
        },
        {
            "input": [
                {
                    "parameter": "text",
                    "value": "H"
                }
            ],
            "expected": "",
            "description": "Test case for 'H'. 'H' should be ignored."
        },
        {
            "input": [
                {
                    "parameter": "text",
                    "value": "ABCD"
                }
            ],
            "expected": "0142",
            "description": "Comprehensive test case for multiple characters. 'A' is '0', 'B' is '1', 'C' followed by 'D' is '4', 'D' followed by 'C' is '2'."
        }
    ]
}