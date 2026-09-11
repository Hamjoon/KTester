{
    "test cases": [
        {
            "group": "transcodeRemaining Tests",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "prev",
                            "value": "A"
                        },
                        {
                            "parameter": "curr",
                            "value": "H"
                        },
                        {
                            "parameter": "next",
                            "value": "B"
                        },
                        {
                            "parameter": "aNext",
                            "value": "C"
                        }
                    ],
                    "expected": "['A']",
                    "description": "Test case for 'H' condition where previous is vowel and next is non-vowel, expecting return value of prev."
                },
                {
                    "input": [
                        {
                            "parameter": "prev",
                            "value": "B"
                        },
                        {
                            "parameter": "curr",
                            "value": "H"
                        },
                        {
                            "parameter": "next",
                            "value": "C"
                        },
                        {
                            "parameter": "aNext",
                            "value": "D"
                        }
                    ],
                    "expected": "['B']",
                    "description": "Test case for 'H' condition where previous is non-vowel and next is non-vowel, expecting return value of prev."
                },
                {
                    "input": [
                        {
                            "parameter": "prev",
                            "value": "E"
                        },
                        {
                            "parameter": "curr",
                            "value": "W"
                        },
                        {
                            "parameter": "next",
                            "value": "A"
                        },
                        {
                            "parameter": "aNext",
                            "value": "C"
                        }
                    ],
                    "expected": "['E']",
                    "description": "Test case for 'W' condition where previous is vowel and next is vowel, expecting return value of prev."
                },
                {
                    "input": [
                        {
                            "parameter": "prev",
                            "value": "B"
                        },
                        {
                            "parameter": "curr",
                            "value": "W"
                        },
                        {
                            "parameter": "next",
                            "value": "C"
                        },
                        {
                            "parameter": "aNext",
                            "value": "D"
                        }
                    ],
                    "expected": "['W']",
                    "description": "Test case for 'W' condition where previous is non-vowel and next is non-vowel, expecting return value of curr."
                },
                {
                    "input": [
                        {
                            "parameter": "prev",
                            "value": "A"
                        },
                        {
                            "parameter": "curr",
                            "value": "E"
                        },
                        {
                            "parameter": "next",
                            "value": "A"
                        },
                        {
                            "parameter": "aNext",
                            "value": "B"
                        }
                    ],
                    "expected": "['A']",
                    "description": "Test case for vowel condition where curr is 'E', expecting return value of CHARS_A."
                }
            ]
        }
    ]
}