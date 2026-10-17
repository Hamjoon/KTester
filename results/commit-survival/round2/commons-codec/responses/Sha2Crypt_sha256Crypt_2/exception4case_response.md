{
    "testCases": [
        {
            "group": "Invalid rounds format non‑numeric",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "\"password\".getBytes(StandardCharsets.UTF_8)"
                        },
                        {
                            "parameter": "salt",
                            "value": "\"$5$rounds=abc$mysalt$\""
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "The rounds part contains non‑numeric characters; the method must reject the salt and throw IllegalArgumentException."
                }
            ]
        },
        {
            "group": "Rounds below minimum",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "\"password\".getBytes(StandardCharsets.UTF_8)"
                        },
                        {
                            "parameter": "salt",
                            "value": "\"$5$rounds=500$mysalt$\""
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "The specified rounds value is lower than the allowed minimum (1000); the method must throw IllegalArgumentException."
                }
            ]
        },
        {
            "group": "Rounds above maximum",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "\"password\".getBytes(StandardCharsets.UTF_8)"
                        },
                        {
                            "parameter": "salt",
                            "value": "\"$5$rounds=1000000000$mysalt$\""
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "The specified rounds value exceeds the allowed maximum (999999999); the method must reject the salt with IllegalArgumentException."
                }
            ]
        },
        {
            "group": "Missing trailing delimiter",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "\"password\".getBytes(StandardCharsets.UTF_8)"
                        },
                        {
                            "parameter": "salt",
                            "value": "\"$5$mysalt\""
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "The salt does not end with the required trailing '$' separator; the method must throw IllegalArgumentException."
                }
            ]
        },
        {
            "group": "Empty rounds value",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "\"password\".getBytes(StandardCharsets.UTF_8)"
                        },
                        {
                            "parameter": "salt",
                            "value": "\"$5$rounds=$mysalt$\""
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "The rounds specification is present but the numeric value is missing; the method must treat the salt as malformed and throw IllegalArgumentException."
                }
            ]
        },
        {
            "group": "Whitespace in salt",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "\"password\".getBytes(StandardCharsets.UTF_8)"
                        },
                        {
                            "parameter": "salt",
                            "value": "\"$5$my salt$\""
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "A space character is not part of the allowed salt alphabet; the method must reject the salt with IllegalArgumentException."
                }
            ]
        }
    ]
}