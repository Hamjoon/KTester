{
    "group": "EOF early‑return",
    "cases": [
        {
            "input": [
                {
                    "parameter": "input",
                    "value": "new byte[]{0x61, 0x62}"
                },
                {
                    "parameter": "inPos",
                    "value": "0"
                },
                {
                    "parameter": "inAvail",
                    "value": "-1"
                },
                {
                    "parameter": "context",
                    "value": "Context with eof=true"
                }
            ],
            "expected": "No bytes are written to the context buffer and the method returns immediately.",
            "description": "When the context already signals EOF, encode must exit without touching the buffer."
        }
    ]
}