{
    "result": [
        {
            "group": "transcodeRemaining - Edge Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "prev",
                            "value": " "
                        },
                        {
                            "parameter": "curr",
                            "value": " "
                        },
                        {
                            "parameter": "next",
                            "value": " "
                        },
                        {
                            "parameter": "aNext",
                            "value": " "
                        }
                    ],
                    "expected": "[' ']",
                    "description": "Test case for all parameters being spaces to ensure it returns [' ']."
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
                            "value": " "
                        },
                        {
                            "parameter": "aNext",
                            "value": " "
                        }
                    ],
                    "expected": "['A']",
                    "description": "Test case for 'H' with vowel previous and space next to ensure it returns ['A']."
                },
                {
                    "input": [
                        {
                            "parameter": "prev",
                            "value": " "
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
                    "expected": "[' ']",
                    "description": "Test case for 'H' with non-vowel previous to ensure it returns [' ']."
                }
            ]
        },
        {
            "group": "transcodeRemaining - Invalid Characters",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "prev",
                            "value": "#"
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
                    "expected": "['#']",
                    "description": "Test case for invalid character '#' as previous to ensure it returns ['#']."
                }
            ]
        }
    ]
}