{
    "new_cases": [
        {
            "group": "Multi‑byte custom line separator",
            "cases": [
                {
                    "input": [],
                    "expected": "[10,9]",
                    "description": "When the Base64 instance is built with a custom two‑character line separator \"\\n\\t\" (newline followed by a tab), `getLineSeparator()` must return a byte array containing the corresponding ASCII codes 10 and 9."
                }
            ]
        },
        {
            "group": "Empty line separator (no bytes)",
            "cases": [
                {
                    "input": [],
                    "expected": "[]",
                    "description": "If the builder is supplied an empty byte array as the line separator, the internal field should store an empty array. `getLineSeparator()` must therefore return an empty byte array rather than null."
                }
            ]
        },
        {
            "group": "Explicit null line separator via builder",
            "cases": [
                {
                    "input": [],
                    "expected": "null",
                    "description": "Even when the builder is called with `lineSeparator(null)`, the resulting Base64 instance should behave like the default: `getLineSeparator()` returns null."
                }
            ]
        },
        {
            "group": "Immutability of returned line separator array",
            "cases": [
                {
                    "input": [],
                    "expected": "original unchanged",
                    "description": "`getLineSeparator()` must return a defensive copy. After retrieving the array and modifying its contents, a subsequent call to `getLineSeparator()` should still return the original byte values (e.g., [124] for a pipe separator)."
                }
            ]
        }
    ]
}