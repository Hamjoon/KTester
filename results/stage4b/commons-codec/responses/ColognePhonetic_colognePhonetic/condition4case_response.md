{
    "test cases": [
        {
            "group": "colognePhonetic",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": null
                        }
                    ],
                    "expected": null,
                    "description": "Test with null input to verify that the method returns null."
                },
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": "A"
                        }
                    ],
                    "expected": "0",
                    "description": "Test with input 'A' to verify that the method returns '0' for vowels."
                },
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": "B"
                        }
                    ],
                    "expected": "1",
                    "description": "Test with input 'B' to verify that the method returns '1'."
                },
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": "P"
                        }
                    ],
                    "expected": "1",
                    "description": "Test with input 'P' to verify that the method returns '1' when not followed by 'H'."
                },
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": "PH"
                        }
                    ],
                    "expected": "0",
                    "description": "Test with input 'PH' to verify that the method returns '0' when 'P' is followed by 'H'."
                },
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": "D"
                        }
                    ],
                    "expected": "2",
                    "description": "Test with input 'D' to verify that the method returns '2'."
                },
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": "T"
                        }
                    ],
                    "expected": "2",
                    "description": "Test with input 'T' to verify that the method returns '2'."
                },
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": "X"
                        }
                    ],
                    "expected": "48",
                    "description": "Test with input 'X' to verify that the method returns '48' when 'X' is not preceded by 'C', 'K', or 'Q'."
                },
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": "C"
                        }
                    ],
                    "expected": "8",
                    "description": "Test with input 'C' to verify that the method returns '8' when followed by a character not in AHKLOQRUX."
                },
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": "CR"
                        }
                    ],
                    "expected": "4",
                    "description": "Test with input 'CR' to verify that the method returns '4' when 'C' is followed by 'R'."
                },
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": "S"
                        }
                    ],
                    "expected": "8",
                    "description": "Test with input 'S' to verify that the method returns '8'."
                },
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": "Z"
                        }
                    ],
                    "expected": "8",
                    "description": "Test with input 'Z' to verify that the method returns '8'."
                },
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": "R"
                        }
                    ],
                    "expected": "7",
                    "description": "Test with input 'R' to verify that the method returns '7'."
                },
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": "L"
                        }
                    ],
                    "expected": "5",
                    "description": "Test with input 'L' to verify that the method returns '5'."
                },
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": "M"
                        }
                    ],
                    "expected": "6",
                    "description": "Test with input 'M' to verify that the method returns '6'."
                },
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": "N"
                        }
                    ],
                    "expected": "6",
                    "description": "Test with input 'N' to verify that the method returns '6'."
                },
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": "H"
                        }
                    ],
                    "expected": "",
                    "description": "Test with input 'H' to verify that the method does not store 'H' and returns an empty string."
                },
                {
                    "input": [
                        {
                            "parameter": "text",
                            "value": "A B C D E F G H I J K L M N O P Q R S T U V W X Y Z"
                        }
                    ],
                    "expected": "012345678",
                    "description": "Test with a full alphabet string to verify the combined output of phonetic codes."
                }
            ]
        }
    ]
}