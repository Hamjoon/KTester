{
    "group": "Additional Nysiis Method Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "str",
                    "value": "KNIGHT"
                }
            ],
            "expected": "NIGHT",
            "description": "Test case for input starting with 'KN'. The method should replace 'KN' with 'NN'."
        },
        {
            "input": [
                {
                    "parameter": "str",
                    "value": "PHILIP"
                }
            ],
            "expected": "FFILIP",
            "description": "Test case for input starting with 'PH'. The method should replace 'PH' with 'FF'."
        },
        {
            "input": [
                {
                    "parameter": "str",
                    "value": "SCHMIDT"
                }
            ],
            "expected": "SSSMIDT",
            "description": "Test case for input starting with 'SCH'. The method should replace 'SCH' with 'SSS'."
        },
        {
            "input": [
                {
                    "parameter": "str",
                    "value": "LIE"
                }
            ],
            "expected": "LY",
            "description": "Test case for input ending with 'IE'. The method should replace 'IE' with 'Y'."
        },
        {
            "input": [
                {
                    "parameter": "str",
                    "value": "BEE"
                }
            ],
            "expected": "BY",
            "description": "Test case for input ending with 'EE'. The method should replace 'EE' with 'Y'."
        },
        {
            "input": [
                {
                    "parameter": "str",
                    "value": "BAND"
                }
            ],
            "expected": "BAD",
            "description": "Test case for input ending with 'ND'. The method should replace 'ND' with 'D'."
        },
        {
            "input": [
                {
                    "parameter": "str",
                    "value": "MART"
                }
            ],
            "expected": "MAD",
            "description": "Test case for input ending with 'RT'. The method should replace 'RT' with 'D'."
        },
        {
            "input": [
                {
                    "parameter": "str",
                    "value": "MARS"
                }
            ],
            "expected": "MAR",
            "description": "Test case for input ending with 'S'. The method should remove the last 'S'."
        },
        {
            "input": [
                {
                    "parameter": "str",
                    "value": "MAYA"
                }
            ],
            "expected": "MY",
            "description": "Test case for input ending with 'AY'. The method should replace 'AY' with 'Y'."
        },
        {
            "input": [
                {
                    "parameter": "str",
                    "value": "MAA"
                }
            ],
            "expected": "M",
            "description": "Test case for input ending with 'A'. The method should remove the last 'A'."
        },
        {
            "input": [
                {
                    "parameter": "str",
                    "value": "D"
                }
            ],
            "expected": "D",
            "description": "Test case for single character input. The method should return the character itself."
        },
        {
            "input": [
                {
                    "parameter": "str",
                    "value": "PHARMACY"
                }
            ],
            "expected": "FFARMACY",
            "description": "Test case for input starting with 'PH'. The method should replace 'PH' with 'FF' and transcode the rest."
        },
        {
            "input": [
                {
                    "parameter": "str",
                    "value": "SCHOLAR"
                }
            ],
            "expected": "SSSCHOLAR",
            "description": "Test case for input starting with 'SCH'. The method should replace 'SCH' with 'SSS' and transcode the rest."
        },
        {
            "input": [
                {
                    "parameter": "str",
                    "value": "KNACK"
                }
            ],
            "expected": "NNACK",
            "description": "Test case for input starting with 'KN'. The method should replace 'KN' with 'NN' and transcode the rest."
        },
        {
            "input": [
                {
                    "parameter": "str",
                    "value": "MACINTOSH"
                }
            ],
            "expected": "MCCINTOSH",
            "description": "Test case for input starting with 'MAC'. The method should replace 'MAC' with 'MCC' and transcode the rest."
        }
    ]
}