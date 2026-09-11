{
    "testCases": [
        {
            "group": "metaphone",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": "AE"
                        }
                    ],
                    "expected": "R",
                    "description": "Test with input 'AE', expecting 'R' since 'A' followed by 'E' is treated as 'A'."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": "BOMB"
                        }
                    ],
                    "expected": "BM",
                    "description": "Test with input 'BOMB', expecting 'BM' since 'B' is silent at the end of 'MB'."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": "WATER"
                        }
                    ],
                    "expected": "WTR",
                    "description": "Test with input 'WATER', expecting 'WTR' since 'W' is treated as leading."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": "CHAIR"
                        }
                    ],
                    "expected": "X",
                    "description": "Test with input 'CHAIR', expecting 'X' since 'CH' followed by a vowel is treated as 'X'."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": "SCHOOL"
                        }
                    ],
                    "expected": "SKL",
                    "description": "Test with input 'SCHOOL', expecting 'SKL' since 'SCH' is treated as 'SK'."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": "THOUGHT"
                        }
                    ],
                    "expected": "T0T",
                    "description": "Test with input 'THOUGHT', expecting 'T0T' since 'TH' is transformed to '0'."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": "DAGGER"
                        }
                    ],
                    "expected": "TGR",
                    "description": "Test with input 'DAGGER', expecting 'TGR' since 'D' followed by 'G' and 'E' is transformed."
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
                            "value": "PHILADELPHIA"
                        }
                    ],
                    "expected": "FALF",
                    "description": "Test with input 'PHILADELPHIA', expecting 'FALF' since 'PH' is treated as 'F'."
                },
                {
                    "input": [
                        {
                            "parameter": "txt",
                            "value": "XAVIER"
                        }
                    ],
                    "expected": "SFR",
                    "description": "Test with input 'XAVIER', expecting 'SFR' since 'X' is treated as 'S'."
                }
            ]
        }
    ]
}