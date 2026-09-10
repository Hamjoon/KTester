{
    "group": "doubleMetaphone",
    "cases": [
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "BROWN"
                },
                {
                    "parameter": "alternate",
                    "value": true
                }
            ],
            "expected": "P",
            "description": "Test with name 'BROWN' for alternate should return appropriate alternate encoding."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "SCHMIDT"
                },
                {
                    "parameter": "alternate",
                    "value": true
                }
            ],
            "expected": "S",
            "description": "Test with name 'SCHMIDT' for alternate should return appropriate alternate encoding."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "JONES"
                },
                {
                    "parameter": "alternate",
                    "value": true
                }
            ],
            "expected": "J",
            "description": "Test with name 'JONES' for alternate should return appropriate alternate encoding."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "CHURCH"
                },
                {
                    "parameter": "alternate",
                    "value": true
                }
            ],
            "expected": "C",
            "description": "Test with name 'CHURCH' for alternate should return appropriate alternate encoding."
        },
        {
            "input": [
                {
                    "parameter": "value",
                    "value": "XAVIER"
                },
                {
                    "parameter": "alternate",
                    "value": true
                }
            ],
            "expected": "S",
            "description": "Test with name 'XAVIER' for alternate should return appropriate alternate encoding."
        }
    ]
}