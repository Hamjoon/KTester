{
    "group": "DoubleMetaphone_handleC",
    "cases": [
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "CAESAR"
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
            "description": "Test case for the condition where the input starts with 'CAESAR'."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "CHERRY"
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
            "expected": "index = handleCH return value, result updated accordingly",
            "description": "Test case for the condition where the input contains 'CH'."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "CZERNY"
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
            "description": "Test case for the condition where the input contains 'CZ' and not 'WICZ'."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "FOCACCIA"
                },
                {
                    "parameter": "result",
                    "value": "DoubleMetaphoneResult instance with maxLength 10"
                },
                {
                    "parameter": "index",
                    "value": 3
                }
            ],
            "expected": "index = 6, result.getPrimary() = 'X'",
            "description": "Test case for the condition where the input contains 'CIA'."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "ACCEPT"
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
            "description": "Test case for the condition where the input contains 'CC' and not 'McClelland'."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "ROCK"
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
            "description": "Test case for the condition where the input contains 'CK', 'CG', or 'CQ'."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "CIRCLE"
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
            "description": "Test case for the condition where the input contains 'CI', 'CE', or 'CY'."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "MAC GREGOR"
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
            "expected": "index = 3, result.getPrimary() = 'K'",
            "description": "Test case for the condition where the input contains 'C' followed by ' C', ' Q', or ' G'."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "CARMEN"
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
            "expected": "index = 1, result.getPrimary() = 'K'",
            "description": "Test case for the default condition where none of the specific patterns match."
        }
    ]
}