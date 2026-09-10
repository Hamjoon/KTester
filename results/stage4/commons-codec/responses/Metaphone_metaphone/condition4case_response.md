{
    "testCases": [
        {
            "group": "metaphone",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": null
                        }
                    ],
                    "expected": "",
                    "description": "Test with null input, should return an empty string."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": ""
                        }
                    ],
                    "expected": "",
                    "description": "Test with empty string input, should return an empty string."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": "A"
                        }
                    ],
                    "expected": "A",
                    "description": "Test with single character input 'A', should return 'A'."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": "K"
                        }
                    ],
                    "expected": "K",
                    "description": "Test with single character input 'K', should return 'K'."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": "KNIGHT"
                        }
                    ],
                    "expected": "NJT",
                    "description": "Test with input 'KNIGHT', should handle 'K' followed by 'N' correctly."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": "WHISTLE"
                        }
                    ],
                    "expected": "WSTL",
                    "description": "Test with input 'WHISTLE', should handle 'W' followed by 'H' correctly."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": "AEON"
                        }
                    ],
                    "expected": "N",
                    "description": "Test with input 'AEON', should handle 'A' followed by 'E' correctly."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": "CHURCH"
                        }
                    ],
                    "expected": "KX",
                    "description": "Test with input 'CHURCH', should handle 'CH' correctly."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": "SCHOOL"
                        }
                    ],
                    "expected": "SKL",
                    "description": "Test with input 'SCHOOL', should handle 'SC' followed by 'H' correctly."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": "THOMAS"
                        }
                    ],
                    "expected": "T0MS",
                    "description": "Test with input 'THOMAS', should handle 'TH' correctly."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": "SILVER"
                        }
                    ],
                    "expected": "SLFR",
                    "description": "Test with input 'SILVER', should handle 'S' followed by 'I' correctly."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": "GHOST"
                        }
                    ],
                    "expected": "KST",
                    "description": "Test with input 'GHOST', should handle 'G' followed by 'H' correctly."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": "KILLED"
                        }
                    ],
                    "expected": "KLT",
                    "description": "Test with input 'KILLED', should handle 'K' correctly."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": "BOMB"
                        }
                    ],
                    "expected": "BM",
                    "description": "Test with input 'BOMB', should handle silent 'B' at the end."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": "PHOENIX"
                        }
                    ],
                    "expected": "FNKS",
                    "description": "Test with input 'PHOENIX', should handle 'PH' correctly."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": "XENON"
                        }
                    ],
                    "expected": "SNN",
                    "description": "Test with input 'XENON', should handle initial 'X' correctly."
                }
            ]
        }
    ]
}