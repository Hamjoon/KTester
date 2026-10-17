{
    "group": "Null salt handling",
    "cases": [
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "\"password\".getBytes(StandardCharsets.UTF_8)"
                },
                {
                    "parameter": "salt",
                    "value": "null"
                }
            ],
            "expected": "String that matches the pattern \"\\\\$5\\\\$[A-Za-z0-9./]{8}\\\\$[A-Za-z0-9./]{43}\" (i.e., starts with \"$5$\", contains an 8‑character random salt and a 43‑character base‑64 hash).",
            "description": "When salt is null the method must generate a random 8‑character salt prefixed with \"$5$\" and return a valid SHA‑256 crypt string."
        },
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "\"\".getBytes(StandardCharsets.UTF_8)"
                },
                {
                    "parameter": "salt",
                    "value": "null"
                }
            ],
            "expected": "String that matches the pattern \"\\\\$5\\\\$[A-Za-z0-9./]{8}\\\\$[A-Za-z0-9./]{43}\".",
            "description": "Even with an empty password the method must still generate a random salt and produce a correctly formatted result."
        }
    ]
}