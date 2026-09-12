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
                    "value": "E"
                },
                {
                    "parameter": "next",
                    "value": "V"
                },
                {
                    "parameter": "aNext",
                    "value": "A"
                }
            ],
            "expected": "['A', 'F']",
            "description": "Test case for EV -> AF transformation."
        },
        {
            "input": [
                {
                    "parameter": "prev",
                    "value": "B"
                },
                {
                    "parameter": "curr",
                    "value": "A"
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
            "expected": "['A']",
            "description": "Test case for vowel transformation A -> A."
        },
        {
            "input": [
                {
                    "parameter": "prev",
                    "value": "B"
                },
                {
                    "parameter": "curr",
                    "value": "Q"
                },
                {
                    "parameter": "next",
                    "value": "U"
                },
                {
                    "parameter": "aNext",
                    "value": "E"
                }
            ],
            "expected": "['G']",
            "description": "Test case for Q -> G transformation."
        },
        {
            "input": [
                {
                    "parameter": "prev",
                    "value": "B"
                },
                {
                    "parameter": "curr",
                    "value": "Z"
                },
                {
                    "parameter": "next",
                    "value": "O"
                },
                {
                    "parameter": "aNext",
                    "value": "E"
                }
            ],
            "expected": "['S']",
            "description": "Test case for Z -> S transformation."
        },
        {
            "input": [
                {
                    "parameter": "prev",
                    "value": "B"
                },
                {
                    "parameter": "curr",
                    "value": "M"
                },
                {
                    "parameter": "next",
                    "value": "O"
                },
                {
                    "parameter": "aNext",
                    "value": "E"
                }
            ],
            "expected": "['N']",
            "description": "Test case for M -> N transformation."
        },
        {
            "input": [
                {
                    "parameter": "prev",
                    "value": "B"
                },
                {
                    "parameter": "curr",
                    "value": "K"
                },
                {
                    "parameter": "next",
                    "value": "N"
                },
                {
                    "parameter": "aNext",
                    "value": "O"
                }
            ],
            "expected": "['N', 'N']",
            "description": "Test case for KN -> NN transformation."
        },
        {
            "input": [
                {
                    "parameter": "prev",
                    "value": "B"
                },
                {
                    "parameter": "curr",
                    "value": "K"
                },
                {
                    "parameter": "next",
                    "value": "A"
                },
                {
                    "parameter": "aNext",
                    "value": "T"
                }
            ],
            "expected": "['C']",
            "description": "Test case for K -> C transformation."
        },
        {
            "input": [
                {
                    "parameter": "prev",
                    "value": "B"
                },
                {
                    "parameter": "curr",
                    "value": "S"
                },
                {
                    "parameter": "next",
                    "value": "C"
                },
                {
                    "parameter": "aNext",
                    "value": "H"
                }
            ],
            "expected": "['S', 'S', 'S']",
            "description": "Test case for SCH -> SSS transformation."
        },
        {
            "input": [
                {
                    "parameter": "prev",
                    "value": "B"
                },
                {
                    "parameter": "curr",
                    "value": "P"
                },
                {
                    "parameter": "next",
                    "value": "H"
                },
                {
                    "parameter": "aNext",
                    "value": "O"
                }
            ],
            "expected": "['F', 'F']",
            "description": "Test case for PH -> FF transformation."
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
                    "value": "A"
                },
                {
                    "parameter": "aNext",
                    "value": "T"
                }
            ],
            "expected": "['B']",
            "description": "Test case for H -> previous when previous is not a vowel."
        },
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
                    "value": "T"
                }
            ],
            "expected": "['A']",
            "description": "Test case for H -> previous when next is not a vowel."
        },
        {
            "input": [
                {
                    "parameter": "prev",
                    "value": "A"
                },
                {
                    "parameter": "curr",
                    "value": "W"
                },
                {
                    "parameter": "next",
                    "value": "B"
                },
                {
                    "parameter": "aNext",
                    "value": "T"
                }
            ],
            "expected": "['A']",
            "description": "Test case for W -> previous when previous is a vowel."
        },
        {
            "input": [
                {
                    "parameter": "prev",
                    "value": "B"
                },
                {
                    "parameter": "curr",
                    "value": "X"
                },
                {
                    "parameter": "next",
                    "value": "Y"
                },
                {
                    "parameter": "aNext",
                    "value": "Z"
                }
            ],
            "expected": "['X']",
            "description": "Test case for default branch returning current character."
        }
    ]
}