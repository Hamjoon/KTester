{
    "group": "isXMLExtender Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "c",
                    "value": 183
                }
            ],
            "expected": true,
            "description": "Test with character 0xb7 to ensure it returns true, as it is a valid XML extender."
        },
        {
            "input": [
                {
                    "parameter": "c",
                    "value": 720
                }
            ],
            "expected": true,
            "description": "Test with character 0x2d0 to ensure it returns true, as it is a valid XML extender."
        },
        {
            "input": [
                {
                    "parameter": "c",
                    "value": 721
                }
            ],
            "expected": true,
            "description": "Test with character 0x2d1 to ensure it returns true, as it is a valid XML extender."
        },
        {
            "input": [
                {
                    "parameter": "c",
                    "value": 903
                }
            ],
            "expected": true,
            "description": "Test with character 0x387 to ensure it returns true, as it is a valid XML extender."
        },
        {
            "input": [
                {
                    "parameter": "c",
                    "value": 1600
                }
            ],
            "expected": true,
            "description": "Test with character 0x640 to ensure it returns true, as it is a valid XML extender."
        },
        {
            "input": [
                {
                    "parameter": "c",
                    "value": 3654
                }
            ],
            "expected": true,
            "description": "Test with character 0xe46 to ensure it returns true, as it is a valid XML extender."
        },
        {
            "input": [
                {
                    "parameter": "c",
                    "value": 3782
                }
            ],
            "expected": true,
            "description": "Test with character 0xec6 to ensure it returns true, as it is a valid XML extender."
        },
        {
            "input": [
                {
                    "parameter": "c",
                    "value": 12293
                }
            ],
            "expected": true,
            "description": "Test with character 0x3005 to ensure it returns true, as it is a valid XML extender."
        },
        {
            "input": [
                {
                    "parameter": "c",
                    "value": 12353
                }
            ],
            "expected": false,
            "description": "Test with character 0x3031 to ensure it returns false, as it is not a valid XML extender."
        },
        {
            "input": [
                {
                    "parameter": "c",
                    "value": 12357
                }
            ],
            "expected": true,
            "description": "Test with character 0x3035 to ensure it returns true, as it is a valid XML extender."
        },
        {
            "input": [
                {
                    "parameter": "c",
                    "value": 12445
                }
            ],
            "expected": false,
            "description": "Test with character 0x309d to ensure it returns false, as it is not a valid XML extender."
        },
        {
            "input": [
                {
                    "parameter": "c",
                    "value": 12446
                }
            ],
            "expected": true,
            "description": "Test with character 0x309e to ensure it returns true, as it is a valid XML extender."
        },
        {
            "input": [
                {
                    "parameter": "c",
                    "value": 12476
                }
            ],
            "expected": false,
            "description": "Test with character 0x30fc to ensure it returns false, as it is not a valid XML extender."
        },
        {
            "input": [
                {
                    "parameter": "c",
                    "value": 12478
                }
            ],
            "expected": true,
            "description": "Test with character 0x30fe to ensure it returns true, as it is a valid XML extender."
        },
        {
            "input": [
                {
                    "parameter": "c",
                    "value": 65535
                }
            ],
            "expected": false,
            "description": "Test with character 0xffff to ensure it returns false, as it is not a valid XML extender."
        }
    ]
}