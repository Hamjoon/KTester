{
    "group": "ColognePhonetic.colognePhonetic",
    "cases": [
        {
            "input": [
                {
                    "parameter": "text",
                    "value": "SCH"
                }
            ],
            "expected": "84",
            "description": "Test case for 'SCH'. 'S' and 'C' should be encoded as '8' if 'C' is followed by 'H', otherwise '4'. 'H' is ignored."
        },
        {
            "input": [
                {
                    "parameter": "text",
                    "value": "CK"
                }
            ],
            "expected": "4",
            "description": "Test case for 'C' followed by 'K'. 'C' should be encoded as '4' when followed by 'K'."
        },
        {
            "input": [
                {
                    "parameter": "text",
                    "value": "QX"
                }
            ],
            "expected": "48",
            "description": "Test case for 'Q' followed by 'X'. 'Q' should be encoded as '4', and 'X' should be encoded as '48' when not preceded by 'C', 'K', or 'Q'."
        },
        {
            "input": [
                {
                    "parameter": "text",
                    "value": "TSC"
                }
            ],
            "expected": "84",
            "description": "Test case for 'TSC'. 'T' is encoded as '8', 'S' is '8', and 'C' is '4' when preceded by 'S'."
        },
        {
            "input": [
                {
                    "parameter": "text",
                    "value": "AHKLOQRUX"
                }
            ],
            "expected": "045740478",
            "description": "Test case for each character in 'AHKLOQRUX'. 'A' is '0', 'H' is ignored, 'K' is '4', 'L' is '5', 'O' is '0', 'Q' is '4', 'R' is '7', 'U' is '0', 'X' is '48'."
        },
        {
            "input": [
                {
                    "parameter": "text",
                    "value": "ZXC"
                }
            ],
            "expected": "884",
            "description": "Test case for 'ZXC'. 'Z' is '8', 'X' is '48', but since 'C' follows, it should be encoded as '4'."
        },
        {
            "input": [
                {
                    "parameter": "text",
                    "value": "PHT"
                }
            ],
            "expected": "38",
            "description": "Test case for 'PHT'. 'PH' is encoded as '3', 'T' is encoded as '8' when not followed by 'C', 'S', or 'Z'."
        },
        {
            "input": [
                {
                    "parameter": "text",
                    "value": "MNZ"
                }
            ],
            "expected": "668",
            "description": "Test case for 'MNZ'. 'M' is '6', 'N' is '6', 'Z' is '8'."
        },
        {
            "input": [
                {
                    "parameter": "text",
                    "value": "LHR"
                }
            ],
            "expected": "57",
            "description": "Test case for 'LHR'. 'L' is '5', 'H' is ignored, 'R' is '7'."
        },
        {
            "input": [
                {
                    "parameter": "text",
                    "value": "GQ"
                }
            ],
            "expected": "44",
            "description": "Test case for 'GQ'. 'G' is '4', 'Q' is '4'."
        }
    ]
}