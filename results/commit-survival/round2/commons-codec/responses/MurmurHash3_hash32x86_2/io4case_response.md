{
    "group": "Zero‑length input",
    "cases": [
        {
            "input": [
                { "parameter": "data", "value": "new byte[]{}" },
                { "parameter": "offset", "value": "0" },
                { "parameter": "length", "value": "0" },
                { "parameter": "seed", "value": "0" }
            ],
            "expected": "0",
            "description": "An empty array with length 0 returns fmix32(seed). With seed 0 the final hash is 0."
        },
        {
            "input": [
                { "parameter": "data", "value": "new byte[]{}" },
                { "parameter": "offset", "value": "0" },
                { "parameter": "length", "value": "0" },
                { "parameter": "seed", "value": "12345" }
            ],
            "expected": "fmix32(12345)",
            "description": "Empty input with a non‑zero seed should return the result of the final‑mix function applied to the seed."
        }
    ]
}