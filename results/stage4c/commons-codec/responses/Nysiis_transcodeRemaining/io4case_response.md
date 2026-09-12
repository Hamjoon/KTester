{
    "group": "Additional transcodeRemaining Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "prev",
                    "value": "B"
                },
                {
                    "parameter": "curr",
                    "value": "I"
                },
                {
                    "parameter": "next",
                    "value": "O"
                },
                {
                    "parameter": "aNext",
                    "value": "U"
                }
            ],
            "expected": "['A']",
            "description": "Test case for vowel transformation I -> A."
        },
        {
            "input": [
                {
                    "parameter": "prev",
                    "value": "B"
                },
                {
                    "parameter": "curr",
                    "value": "O"
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
            "expected": "['A']",
            "description": "Test case for vowel transformation O -> A."
        },
        {
            "input": [
                {
                    "parameter": "prev",
                    "value": "B"
                },
                {
                    "parameter": "curr",
                    "value": "U"
                },
                {
                    "parameter": "next",
                    "value": "E"
                },
                {
                    "parameter": "aNext",
                    "value": "I"
                }
            ],
            "expected": "['A']",
            "description": "Test case for vowel transformation U -> A."
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
                    "value": "E"
                },
                {
                    "parameter": "aNext",
                    "value": "T"
                }
            ],
            "expected": "['A']",
            "description": "Test case for H -> previous when next is a vowel and previous is a vowel."
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
                    "value": "O"
                },
                {
                    "parameter": "aNext",
                    "value": "R"
                }
            ],
            "expected": "['W']",
            "description": "Test case for W -> current when previous is not a vowel."
        }
    ]
}