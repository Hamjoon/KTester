{
    "group": "Null salt handling",
    "cases": [
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "cGFzc3dvcmQ="  },
                {
                    "parameter": "salt",
                    "value": null
                },
                {
                    "parameter": "random",
                    "value": "new SecureRandom()"
                }
            ],
            "expected": "String that starts with \"$5$\" and contains 43‑44 characters (standard SHA‑256 crypt format).",
            "description": "When salt is null the method must generate an 8‑character base‑64 salt using the supplied Random instance and return a valid SHA‑256 crypt string prefixed with \"$5$\"."
        },
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "cGFzc3dvcmQ="
                },
                {
                    "parameter": "salt",
                    "value": null
                },
                {
                    "parameter": "random",
                    "value": null
                }
            ],
            "expected": "java.lang.NullPointerException",
            "description": "If the Random argument is null the call to B64.getRandomSalt will throw NPE; the test verifies that the method propagates this exception."
        }
    ]
}