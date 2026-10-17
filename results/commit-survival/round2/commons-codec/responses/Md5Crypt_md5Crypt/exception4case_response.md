{
    "groups": [
        {
            "group": "Null argument handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": null
                        },
                        {
                            "parameter": "salt",
                            "value": "$1$abc$"
                        },
                        {
                            "parameter": "prefix",
                            "value": "$1$"
                        },
                        {
                            "parameter": "random",
                            "value": null
                        }
                    ],
                    "expected": "throws NullPointerException",
                    "description": "keyBytes is null – the method accesses keyBytes.length and later updates the digest, which should result in a NullPointerException."
                },
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "somePass"
                        },
                        {
                            "parameter": "salt",
                            "value": "$1$abc$"
                        },
                        {
                            "parameter": "prefix",
                            "value": null
                        },
                        {
                            "parameter": "random",
                            "value": null
                        }
                    ],
                    "expected": "throws NullPointerException",
                    "description": "prefix is null – it is used in prefix.replace and prefix.getBytes, causing a NullPointerException."
                }
            ]
        },
        {
            "group": "Invalid prefix handling",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "testPass"
                        },
                        {
                            "parameter": "salt",
                            "value": "$1$valid$"
                        },
                        {
                            "parameter": "prefix",
                            "value": "$1$("
                        },
                        {
                            "parameter": "random",
                            "value": null
                        }
                    ],
                    "expected": "throws java.util.regex.PatternSyntaxException",
                    "description": "prefix contains an unescaped '(' which makes the generated regular expression invalid; Pattern.compile should throw a PatternSyntaxException."
                }
            ]
        }
    ]
}