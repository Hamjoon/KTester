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
                    "description": "Test with null input, expecting an empty string output."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": ""
                        }
                    ],
                    "expected": "",
                    "description": "Test with an empty string input, expecting an empty string output."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": "A"
                        }
                    ],
                    "expected": "A",
                    "description": "Test with a single character input 'A', expecting 'A' as output."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": "K"
                        }
                    ],
                    "expected": "K",
                    "description": "Test with a single character input 'K', expecting 'K' as output."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": "KNIGHT"
                        }
                    ],
                    "expected": "NJT",
                    "description": "Test with input 'KNIGHT' which has 'K' followed by 'N', expecting 'NJT' as output."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": "WHISPER"
                        }
                    ],
                    "expected": "WISPR",
                    "description": "Test with input 'WHISPER', expecting 'WISPR' as output since 'WH' is treated as 'W'."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": "XENON"
                        }
                    ],
                    "expected": "SNN",
                    "description": "Test with input 'XENON', expecting 'SNN' as 'X' is treated as 'S'."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": "SHEEP"
                        }
                    ],
                    "expected": "S",
                    "description": "Test with input 'SHEEP', expecting 'S' as the output since 'SH' is treated as 'S'."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": "KNOW"
                        }
                    ],
                    "expected": "NO",
                    "description": "Test with input 'KNOW', expecting 'NO' as output because 'K' is silent."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": "PHOENIX"
                        }
                    ],
                    "expected": "FNS",
                    "description": "Test with input 'PHOENIX', expecting 'FNS' as 'PH' is treated as 'F'."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": "AEROPLANE"
                        }
                    ],
                    "expected": "ARPLN",
                    "description": "Test with input 'AEROPLANE', expecting 'ARPLN' as output."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": "SCHOOL"
                        }
                    ],
                    "expected": "SKL",
                    "description": "Test with input 'SCHOOL', expecting 'SKL' as 'SCH' is treated as 'SK'."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": "DANGER"
                        }
                    ],
                    "expected": "TNR",
                    "description": "Test with input 'DANGER', expecting 'TNR' since 'D' followed by 'G' and 'E' is transformed."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": "GHOST"
                        }
                    ],
                    "expected": "KST",
                    "description": "Test with input 'GHOST', expecting 'KST' since 'G' is silent before 'H'."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": "THOMAS"
                        }
                    ],
                    "expected": "T0MS",
                    "description": "Test with input 'THOMAS', expecting 'T0MS' since 'TH' is transformed to '0'."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": "SIOUX"
                        }
                    ],
                    "expected": "SX",
                    "description": "Test with input 'SIOUX', expecting 'SX' as 'SIO' is transformed to 'X'."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": "SWEET"
                        }
                    ],
                    "expected": "SWT",
                    "description": "Test with input 'SWEET', expecting 'SWT' since 'E' is not leading."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": "ZEBRA"
                        }
                    ],
                    "expected": "S",
                    "description": "Test with input 'ZEBRA', expecting 'S' since 'Z' is treated as 'S'."
                }
            ]
        }
    ]
}