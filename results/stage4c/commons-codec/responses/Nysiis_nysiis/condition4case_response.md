{
    "group": "Nysiis Method Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "str",
                    "value": null
                }
            ],
            "expected": null,
            "description": "Test case for null input. The method should return null when the input string is null."
        },
        {
            "input": [
                {
                    "parameter": "str",
                    "value": ""
                }
            ],
            "expected": "",
            "description": "Test case for empty string input. The method should return an empty string when the input string is empty."
        },
        {
            "input": [
                {
                    "parameter": "str",
                    "value": "MACDONALD"
                }
            ],
            "expected": "MCCDONALD",
            "description": "Test case for input starting with 'MAC'. The method should replace 'MAC' with 'MCC'."
        },
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
                    "value": "KELLY"
                }
            ],
            "expected": "CELLY",
            "description": "Test case for input starting with 'K'. The method should replace 'K' with 'C'."
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
        }
    ]
}