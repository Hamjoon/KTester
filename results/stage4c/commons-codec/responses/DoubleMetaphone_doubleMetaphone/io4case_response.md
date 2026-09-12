{
    "group": "DoubleMetaphone Additional Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "BACH"
                },
                {
                    "parameter": "alternate",
                    "value": false
                }
            ],
            "expected": "BK",
            "description": "Test when input contains 'CH' after a vowel, expecting 'K' in primary encoding."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "MACDONALD"
                },
                {
                    "parameter": "alternate",
                    "value": false
                }
            ],
            "expected": "MKTLNT",
            "description": "Test when input contains 'MAC' at the start, expecting 'MK' in primary encoding."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "KNIGHT"
                },
                {
                    "parameter": "alternate",
                    "value": false
                }
            ],
            "expected": "NKT",
            "description": "Test when input starts with silent 'K', expecting 'N' in primary encoding."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "PSALM"
                },
                {
                    "parameter": "alternate",
                    "value": false
                }
            ],
            "expected": "SLM",
            "description": "Test when input starts with silent 'P', expecting 'S' in primary encoding."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "SCHMIDT"
                },
                {
                    "parameter": "alternate",
                    "value": false
                }
            ],
            "expected": "XMT",
            "description": "Test when input starts with 'SCH', expecting 'X' in primary encoding."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "THOMAS"
                },
                {
                    "parameter": "alternate",
                    "value": false
                }
            ],
            "expected": "TMS",
            "description": "Test when input starts with 'TH', expecting 'T' in primary encoding."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "WRIGHT"
                },
                {
                    "parameter": "alternate",
                    "value": false
                }
            ],
            "expected": "RT",
            "description": "Test when input starts with silent 'W', expecting 'R' in primary encoding."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "XENON"
                },
                {
                    "parameter": "alternate",
                    "value": false
                }
            ],
            "expected": "SN",
            "description": "Test when input starts with 'X', expecting 'S' in primary encoding."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "YACHT"
                },
                {
                    "parameter": "alternate",
                    "value": false
                }
            ],
            "expected": "KT",
            "description": "Test when input contains 'CHT', expecting 'KT' in primary encoding."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "PHARAOH"
                },
                {
                    "parameter": "alternate",
                    "value": false
                }
            ],
            "expected": "FR",
            "description": "Test when input starts with 'PH', expecting 'F' in primary encoding."
        }
    ]
}