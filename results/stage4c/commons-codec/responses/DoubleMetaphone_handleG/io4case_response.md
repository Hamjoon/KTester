{
    "group": "handleG method additional test cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "GIER"
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
            "expected": "J to be appended",
            "description": "Test when 'G' is followed by 'IER', 'J' should be appended"
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "GOGGI"
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
            "description": "Test when 'G' is followed by 'OGGI', 'J' and 'K' should be appended"
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "GERMAN"
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
                    "value": true
                }
            ],
            "expected": "K to be appended",
            "description": "Test when 'G' is followed by 'ER' in a Slavo-Germanic context, 'K' should be appended"
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "GON"
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
            "description": "Test when 'G' is followed by 'ON', default case should apply, 'K' should be appended"
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "G"
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
            "description": "Test when 'G' is a standalone character, 'K' should be appended"
        }
    ]
}