{
    "group": "Random is null but salt provided",
    "cases": [
        {
            "input": [
                {
                    "parameter": "keyBytes",
                    "value": "new byte[]{112, 97, 115, 115}"
                },
                {
                    "parameter": "salt",
                    "value": "\"$6$explicit\""
                },
                {
                    "parameter": "random",
                    "value": "null"
                }
            ],
            "expected": "A valid SHA‑512‑crypt string that starts with \"$6$explicit$\" and whose hash part is 86 characters long. No NullPointerException is thrown because the random argument is ignored when a non‑null salt is supplied.",
            "description": "When a non‑null salt is given the method must not use the Random instance at all; passing null for random must be safe and the result must contain the supplied salt."
        }
    ]
}