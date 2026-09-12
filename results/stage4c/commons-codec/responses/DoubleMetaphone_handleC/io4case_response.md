{
    "group": "DoubleMetaphone_handleC",
    "cases": [
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "CIO"
                },
                {
                    "parameter": "result",
                    "value": "DoubleMetaphoneResult instance with maxLength 10"
                },
                {
                    "parameter": "index",
                    "value": 0
                }
            ],
            "expected": "index = 2, result.getPrimary() = 'S', result.getAlternate() = 'X'",
            "description": "Test case for the condition where the input contains 'CIO'."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "MCLELLAND"
                },
                {
                    "parameter": "result",
                    "value": "DoubleMetaphoneResult instance with maxLength 10"
                },
                {
                    "parameter": "index",
                    "value": 1
                }
            ],
            "expected": "index = handleCC return value, result updated accordingly",
            "description": "Test case for the condition where the input contains 'CC' and starts with 'M' at index 0."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "CQU"
                },
                {
                    "parameter": "result",
                    "value": "DoubleMetaphoneResult instance with maxLength 10"
                },
                {
                    "parameter": "index",
                    "value": 0
                }
            ],
            "expected": "index = 2, result.getPrimary() = 'K'",
            "description": "Test case for the condition where the input contains 'CQ'."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "CIEL"
                },
                {
                    "parameter": "result",
                    "value": "DoubleMetaphoneResult instance with maxLength 10"
                },
                {
                    "parameter": "index",
                    "value": 0
                }
            ],
            "expected": "index = 2, result.getPrimary() = 'S'",
            "description": "Test case for the condition where the input contains 'CI' followed by a vowel."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "CC"
                },
                {
                    "parameter": "result",
                    "value": "DoubleMetaphoneResult instance with maxLength 10"
                },
                {
                    "parameter": "index",
                    "value": 0
                }
            ],
            "expected": "index = handleCC return value, result updated accordingly",
            "description": "Test case for the condition where the input starts with 'CC'."
        }
    ]
}