{
    "testCases": [
        {
            "group": "Default / Null line separator",
            "cases": [
                {
                    "input": [],
                    "expected": "null",
                    "description": "When the internal `lineSeparator` field is `null` (the default state for a freshly‑constructed Base64 instance that is not configured for chunked output), `getLineSeparator()` should return `null`."
                }
            ]
        },
        {
            "group": "CRLF line separator (standard chunked mode)",
            "cases": [
                {
                    "input": [],
                    "expected": "[13,10]",
                    "description": "When the Base64 object is configured for chunked encoding the implementation sets `lineSeparator` to a CR‑LF byte pair (`\\r\\n`). After such configuration, `getLineSeparator()` must return a byte array containing the two bytes 13 and 10."
                }
            ]
        },
        {
            "group": "Custom line separator",
            "cases": [
                {
                    "input": [],
                    "expected": "[124]",
                    "description": "If a custom line‑separator is supplied (e.g., the pipe character `|` whose ASCII code is 124) the internal field should hold exactly that byte. `getLineSeparator()` must return a byte array with the same single element."
                }
            ]
        }
    ]
}