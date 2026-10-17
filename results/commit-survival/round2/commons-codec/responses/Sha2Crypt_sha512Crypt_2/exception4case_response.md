{
    "groups": [
        {
            "group": "Invalid rounds format",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "\"password\".getBytes(StandardCharsets.UTF_8)"
                        },
                        {
                            "parameter": "salt",
                            "value": "\"$6$rounds=abc$mysalt\""
                        }
                    ],
                    "expected": "IllegalArgumentException is thrown (rounds value is not a number)",
                    "description": "The salt contains a rounds specifier with a non‑numeric value. The implementation must detect the malformed rounds part and reject the salt."
                },
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "\"password\".getBytes(StandardCharsets.UTF_8)"
                        },
                        {
                            "parameter": "salt",
                            "value": "\"$6$rounds=$mysalt\""
                        }
                    ],
                    "expected": "IllegalArgumentException is thrown (missing rounds number)",
                    "description": "The rounds specifier is present but the numeric part is omitted. The method should treat this as an invalid salt pattern."
                }
            ]
        },
        {
            "group": "Invalid rounds value range",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "\"pwd\".getBytes(StandardCharsets.UTF_8)"
                        },
                        {
                            "parameter": "salt",
                            "value": "\"$6$rounds=500$mysalt\""
                        }
                    ],
                    "expected": "IllegalArgumentException is thrown (rounds value below minimum of 1000)",
                    "description": "The rounds number is smaller than the allowed lower bound (1000). The method must reject such a salt."
                },
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "\"pwd\".getBytes(StandardCharsets.UTF_8)"
                        },
                        {
                            "parameter": "salt",
                            "value": "\"$6$rounds=1000000000$mysalt\""
                        }
                    ],
                    "expected": "IllegalArgumentException is thrown (rounds value above maximum of 999999999)",
                    "description": "The rounds number exceeds the permitted upper bound. The implementation should throw an IllegalArgumentException."
                }
            ]
        },
        {
            "group": "Invalid salt characters (null char)",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "\"test\".getBytes(StandardCharsets.UTF_8)"
                        },
                        {
                            "parameter": "salt",
                            "value": "\"$6$ab\\u0000cd\""
                        }
                    ],
                    "expected": "IllegalArgumentException is thrown (salt contains a null character)",
                    "description": "The salt includes the Unicode null character (U+0000), which is not part of the allowed crypt base‑64 alphabet. The method must reject it."
                }
            ]
        },
        {
            "group": "Invalid salt characters (whitespace)",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "\"test\".getBytes(StandardCharsets.UTF_8)"
                        },
                        {
                            "parameter": "salt",
                            "value": "\"$6$my salt\""
                        }
                    ],
                    "expected": "IllegalArgumentException is thrown (salt contains whitespace)",
                    "description": "Whitespace is not allowed in the crypt salt. Supplying a space should cause the method to throw an IllegalArgumentException."
                }
            ]
        }
    ]
}