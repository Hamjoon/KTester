{
    "group": "printWithQuotes - Exception Handling",
    "cases": [
        {
            "input": [
                {
                    "parameter": "object",
                    "value": "Sample Object"
                },
                {
                    "parameter": "charSeq",
                    "value": "Sample String"
                },
                {
                    "parameter": "out",
                    "value": "new StringWriter()"
                },
                {
                    "parameter": "newRecord",
                    "value": true
                }
            ],
            "expected": "IOException",
            "description": "Test when the Appendable out throws IOException during append, should throw IOException."
        },
        {
            "input": [
                {
                    "parameter": "object",
                    "value": "Sample Object"
                },
                {
                    "parameter": "charSeq",
                    "value": "Sample String"
                },
                {
                    "parameter": "out",
                    "value": "null"
                },
                {
                    "parameter": "newRecord",
                    "value": true
                }
            ],
            "expected": "IOException",
            "description": "Test when the Appendable out is null, should throw IOException."
        }
    ]
}