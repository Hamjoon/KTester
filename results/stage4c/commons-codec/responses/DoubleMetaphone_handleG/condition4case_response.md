{
    "group": "handleG method test cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "GH"
                },
                {
                    "parameter": "result",
                    "value": "DoubleMetaphoneResult instance"
                },
                {
                    "parameter": "index",
                    "value": 0
                },
                {
                    "parameter": "slavoGermanic",
                    "value": false
                }
            ],
            "expected": "Index should be updated after handleGH",
            "description": "Test when 'G' is followed by 'H', handleGH should be invoked"
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "GN"
                },
                {
                    "parameter": "result",
                    "value": "DoubleMetaphoneResult instance"
                },
                {
                    "parameter": "index",
                    "value": 0
                },
                {
                    "parameter": "slavoGermanic",
                    "value": false
                }
            ],
            "expected": "KN, N to be appended",
            "description": "Test when 'G' is followed by 'N' and the condition is met, 'KN' and 'N' should be appended"
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "GNE"
                },
                {
                    "parameter": "result",
                    "value": "DoubleMetaphoneResult instance"
                },
                {
                    "parameter": "index",
                    "value": 0
                },
                {
                    "parameter": "slavoGermanic",
                    "value": false
                }
            ],
            "expected": "N, KN to be appended",
            "description": "Test when 'G' is followed by 'N' and the condition is not met, 'N' and 'KN' should be appended"
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "GLI"
                },
                {
                    "parameter": "result",
                    "value": "DoubleMetaphoneResult instance"
                },
                {
                    "parameter": "index",
                    "value": 0
                },
                {
                    "parameter": "slavoGermanic",
                    "value": false
                }
            ],
            "expected": "KL, L to be appended",
            "description": "Test when 'G' is followed by 'LI' and not Slavo-Germanic, 'KL' and 'L' should be appended"
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "GES"
                },
                {
                    "parameter": "result",
                    "value": "DoubleMetaphoneResult instance"
                },
                {
                    "parameter": "index",
                    "value": 0
                },
                {
                    "parameter": "slavoGermanic",
                    "value": false
                }
            ],
            "expected": "K, J to be appended",
            "description": "Test when 'G' is at the beginning followed by specific strings, 'K' and 'J' should be appended"
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "GER"
                },
                {
                    "parameter": "result",
                    "value": "DoubleMetaphoneResult instance"
                },
                {
                    "parameter": "index",
                    "value": 0
                },
                {
                    "parameter": "slavoGermanic",
                    "value": false
                }
            ],
            "expected": "K, J to be appended",
            "description": "Test when 'G' is followed by 'ER' or 'Y' and not preceded by specific strings, 'K' and 'J' should be appended"
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "GIE"
                },
                {
                    "parameter": "result",
                    "value": "DoubleMetaphoneResult instance"
                },
                {
                    "parameter": "index",
                    "value": 0
                },
                {
                    "parameter": "slavoGermanic",
                    "value": false
                }
            ],
            "expected": "J, K to be appended",
            "description": "Test when 'G' is followed by 'E', 'I', 'Y' or specific strings, 'J' and 'K' should be appended"
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "GG"
                },
                {
                    "parameter": "result",
                    "value": "DoubleMetaphoneResult instance"
                },
                {
                    "parameter": "index",
                    "value": 0
                },
                {
                    "parameter": "slavoGermanic",
                    "value": false
                }
            ],
            "expected": "Index should be incremented by 2",
            "description": "Test when 'G' is followed by another 'G', index should increase by 2"
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "GA"
                },
                {
                    "parameter": "result",
                    "value": "DoubleMetaphoneResult instance"
                },
                {
                    "parameter": "index",
                    "value": 0
                },
                {
                    "parameter": "slavoGermanic",
                    "value": false
                }
            ],
            "expected": "K to be appended",
            "description": "Test default case when none of the conditions are met, 'K' should be appended"
        }
    ]
}