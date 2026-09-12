{
    "group": "JsonReader.skipValue Exception Scenarios",
    "cases": [
        {
            "input": [
                {
                    "parameter": "peeked",
                    "value": "PEEKED_END_OBJECT"
                },
                {
                    "parameter": "stackSize",
                    "value": "0"
                }
            ],
            "expected": "ArrayIndexOutOfBoundsException",
            "description": "Test when stackSize is 0 and peeked is PEEKED_END_OBJECT to ensure ArrayIndexOutOfBoundsException is thrown when decrementing stackSize."
        },
        {
            "input": [
                {
                    "parameter": "peeked",
                    "value": "PEEKED_END_ARRAY"
                },
                {
                    "parameter": "stackSize",
                    "value": "0"
                }
            ],
            "expected": "ArrayIndexOutOfBoundsException",
            "description": "Test when stackSize is 0 and peeked is PEEKED_END_ARRAY to ensure ArrayIndexOutOfBoundsException is thrown when decrementing stackSize."
        },
        {
            "input": [
                {
                    "parameter": "peeked",
                    "value": "PEEKED_END_OBJECT"
                },
                {
                    "parameter": "count",
                    "value": "0"
                },
                {
                    "parameter": "stackSize",
                    "value": "1"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test when count is 0, stackSize is 1, and peeked is PEEKED_END_OBJECT to ensure NullPointerException is thrown when trying to set pathNames[stackSize - 1] to null."
        }
    ]
}