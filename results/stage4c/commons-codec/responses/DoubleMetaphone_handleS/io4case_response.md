{
    "group": "handleS additional test cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "SHEIM"
                },
                {
                    "parameter": "result",
                    "value": "new DoubleMetaphoneResult(10)"
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
            "expected": 2,
            "description": "Test case for 'SH' followed by 'HEIM', 'HOEK', 'HOLM', 'HOLZ' where 'S' is appended and index increments by 2."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "SIA"
                },
                {
                    "parameter": "result",
                    "value": "new DoubleMetaphoneResult(10)"
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
            "expected": 3,
            "description": "Test case for 'SIA' with slavoGermanic true where 'S' is appended and index increments by 3."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "SIAN"
                },
                {
                    "parameter": "result",
                    "value": "new DoubleMetaphoneResult(10)"
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
            "expected": 3,
            "description": "Test case for 'SIAN' with slavoGermanic false where 'S' and 'X' are appended and index increments by 3."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "SAINT"
                },
                {
                    "parameter": "result",
                    "value": "new DoubleMetaphoneResult(10)"
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
            "expected": 1,
            "description": "Test case for initial 'S' followed by 'A', 'M', 'N', 'L', 'W' where 'S' and 'X' are appended and index increments by 1."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "SOIL"
                },
                {
                    "parameter": "result",
                    "value": "new DoubleMetaphoneResult(10)"
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
            "expected": 1,
            "description": "Test case for initial 'S' followed by 'O', 'M', 'N', 'L', 'W' where 'S' and 'X' are appended and index increments by 1."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "SOMEBODY"
                },
                {
                    "parameter": "result",
                    "value": "new DoubleMetaphoneResult(10)"
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
            "expected": 1,
            "description": "Test case for initial 'S' followed by 'O', 'M', 'N', 'L', 'W' where 'S' and 'X' are appended and index increments by 1."
        }
    ]
}