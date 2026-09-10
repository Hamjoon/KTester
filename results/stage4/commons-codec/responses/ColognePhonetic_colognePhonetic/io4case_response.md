{
    "testCases": [
        {
            "group": "colognePhonetic",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": "A"
                        }
                    ],
                    "expected": "0",
                    "description": "Test case with a single character 'A'. Expected output should be '0' as 'A' is in AEIJOUY."
                },
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": "B"
                        }
                    ],
                    "expected": "1",
                    "description": "Test case with a single character 'B'. Expected output should be '1' as 'B' maps directly to '1'."
                },
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": "C"
                        }
                    ],
                    "expected": "8",
                    "description": "Test case with a single character 'C'. Expected output should be '8' as no preceding characters affect its output."
                },
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": "X"
                        }
                    ],
                    "expected": "4",
                    "description": "Test case with a single character 'X'. Expected output should be '4' since no previous character prevents it."
                },
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": "S"
                        }
                    ],
                    "expected": "8",
                    "description": "Test case with a single character 'S'. Expected output should be '8' as 'S' directly maps to '8'."
                },
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": "XABC"
                        }
                    ],
                    "expected": "48",
                    "description": "Test case with input 'XABC'. Expected output should be '48', as 'X' maps to '4' and 'A' to '0'."
                },
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": "ABX"
                        }
                    ],
                    "expected": "40",
                    "description": "Test case with input 'ABX'. Expected output should be '40' as 'A' maps to '0' and 'X' to '4'."
                },
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": "MNO"
                        }
                    ],
                    "expected": "66",
                    "description": "Test case with input 'MNO'. Expected output should be '66' as 'M' and 'N' both map to '6'."
                },
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": "A-B-C-D"
                        }
                    ],
                    "expected": "102",
                    "description": "Test case with input 'A-B-C-D'. Expected output should be '102', as only 'A', 'B', 'C', and 'D' are considered."
                },
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": "ABCDXYZ"
                        }
                    ],
                    "expected": "10082",
                    "description": "Test case with input 'ABCDXYZ'. Expected output should be '10082', as 'A' maps to '0', 'B' to '1', 'C' to '8', and 'Z' to '8'."
                },
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": "HELLO WORLD"
                        }
                    ],
                    "expected": "500",
                    "description": "Test case with input 'HELLO WORLD'. Expected output should be '500', as 'H' maps to ignore, 'E' to '0', 'L' to '5', and 'O' to '0'."
                }
            ]
        }
    ]
}