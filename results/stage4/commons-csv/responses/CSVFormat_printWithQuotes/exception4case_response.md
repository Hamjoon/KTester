{
    "output": [
        {
            "group": "printWithQuotes - IOException",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "object",
                            "value": "null"
                        },
                        {
                            "parameter": "charSeq",
                            "value": "value"
                        },
                        {
                            "parameter": "out",
                            "value": "new Appendable() { public Appendable append(CharSequence csq) throws IOException { throw new IOException(); } public Appendable append(CharSequence csq, int start, int end) throws IOException { throw new IOException(); } public Appendable append(char c) throws IOException { throw new IOException(); } }"
                        },
                        {
                            "parameter": "newRecord",
                            "value": "true"
                        }
                    ],
                    "expected": "IOException",
                    "description": "Test when an IOException is thrown while appending to the Appendable; should propagate IOException."
                }
            ]
        },
        {
            "group": "printWithQuotes - QuoteMode.NONE with IOException",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "object",
                            "value": "null"
                        },
                        {
                            "parameter": "charSeq",
                            "value": "value"
                        },
                        {
                            "parameter": "out",
                            "value": "new Appendable() { public Appendable append(CharSequence csq) throws IOException { throw new IOException(); } public Appendable append(CharSequence csq, int start, int end) throws IOException { throw new IOException(); } public Appendable append(char c) throws IOException { throw new IOException(); } }"
                        },
                        {
                            "parameter": "newRecord",
                            "value": "false"
                        }
                    ],
                    "expected": "IOException",
                    "description": "Test when an IOException is thrown while writing the original value in QuoteMode NONE; should propagate IOException."
                }
            ]
        }
    ]
}