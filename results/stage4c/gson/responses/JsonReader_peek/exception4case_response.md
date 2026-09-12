{
    "group": "JsonReader.peek() method exception tests",
    "cases": [
        {
            "input": [],
            "expected": "IOException",
            "description": "Test when doPeek() throws IOException, expect IOException."
        },
        {
            "input": [],
            "expected": "EOFException",
            "description": "Test when doPeek() throws EOFException, expect EOFException."
        }
    ]
}