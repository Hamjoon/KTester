{
    "groups": [
        {
            "group": "Null keyBytes handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": null
                        },
                        {
                            "parameter": "salt",
                            "value": "$5$validSalt"
                        },
                        {
                            "parameter": "random",
                            "value": "new SecureRandom()"
                        }
                    ],
                    "expected": "java.lang.NullPointerException",
                    "description": "When the keyBytes argument is null the method should propagate a NullPointerException thrown by the downstream sha2Crypt implementation."
                }
            ]
        },
        {
            "group": "Invalid salt format handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "cGFzc3dvcmQ="
                        },
                        {
                            "parameter": "salt",
                            "value": "invalidSaltWithoutPrefix"
                        },
                        {
                            "parameter": "random",
                            "value": "new SecureRandom()"
                        }
                    ],
                    "expected": "java.lang.IllegalArgumentException",
                    "description": "A salt that does not start with the required \"$5$\" prefix must cause an IllegalArgumentException."
                },
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "cGFzc3dvcmQ="
                        },
                        {
                            "parameter": "salt",
                            "value": "$5$"
                        },
                        {
                            "parameter": "random",
                            "value": "new SecureRandom()"
                        }
                    ],
                    "expected": "java.lang.IllegalArgumentException",
                    "description": "A salt that consists only of the prefix without any actual salt data is invalid and should raise IllegalArgumentException."
                },
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "cGFzc3dvcmQ="
                        },
                        {
                            "parameter": "salt",
                            "value": "$5$abc!def"
                        },
                        {
                            "parameter": "random",
                            "value": "new SecureRandom()"
                        }
                    ],
                    "expected": "java.lang.IllegalArgumentException",
                    "description": "A salt containing characters outside the allowed crypt base‑64 alphabet (e.g., '!') must trigger IllegalArgumentException."
                }
            ]
        }
    ]
}