{
    "groups": [
        {
            "group": "Strict Policy Invalid Characters",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "base64String",
                            "value": "??!!"
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "When CodecPolicy is set to STRICT, a string composed solely of characters outside the Base64 alphabet must cause decodeBase64 to throw IllegalArgumentException."
                },
                {
                    "input": [
                        {
                            "parameter": "base64String",
                            "value": "SGVsbG8$%="
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "With STRICT policy, embedded non‑alphabet characters in an otherwise valid Base64 sequence should trigger IllegalArgumentException."
                },
                {
                    "input": [
                        {
                            "parameter": "base64String",
                            "value": " T W F u "
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "In STRICT mode, whitespace characters are not tolerated; decoding a string with spaces should raise IllegalArgumentException."
                },
                {
                    "input": [
                        {
                            "parameter": "base64String",
                            "value": "TWFu\r\nIGlz\r\nIGRpc3Rpbmd1aXNoZWQ="
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "When CodecPolicy is STRICT, CR/LF line‑breaks are considered illegal and must cause IllegalArgumentException."
                }
            ]
        },
        {
            "group": "Strict Policy Null Input",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "base64String",
                            "value": null
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "Even though the default (lenient) policy returns null for a null argument, STRICT policy treats null as invalid and should throw IllegalArgumentException."
                }
            ]
        }
    ]
}