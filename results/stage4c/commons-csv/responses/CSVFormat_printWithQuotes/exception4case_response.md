{
    "group": "printWithQuotes exception scenarios",
    "cases": [
        {
            "input": [
                {
                    "parameter": "object",
                    "value": "test"
                },
                {
                    "parameter": "charSeq",
                    "value": null
                },
                {
                    "parameter": "out",
                    "value": "StringWriter instance"
                },
                {
                    "parameter": "newRecord",
                    "value": true
                }
            ],
            "expected": "NullPointerException",
            "description": "Test with null CharSequence to trigger a NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "object",
                    "value": "test"
                },
                {
                    "parameter": "charSeq",
                    "value": "test"
                },
                {
                    "parameter": "out",
                    "value": null
                },
                {
                    "parameter": "newRecord",
                    "value": true
                }
            ],
            "expected": "NullPointerException",
            "description": "Test with null Appendable to trigger a NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "object",
                    "value": "test"
                },
                {
                    "parameter": "charSeq",
                    "value": "test"
                },
                {
                    "parameter": "out",
                    "value": "Appendable that throws IOException"
                },
                {
                    "parameter": "newRecord",
                    "value": true
                }
            ],
            "expected": "IOException",
            "description": "Test with an Appendable that throws IOException to trigger an IOException."
        }
    ]
}