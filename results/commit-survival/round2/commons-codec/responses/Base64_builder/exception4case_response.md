{
    "group": "Base64.builder() exception tests",
    "cases": [
        {
            "input": [],
            "expected": "NullPointerException",
            "description": "Calling withLineSeparator(null) on a Builder should throw NullPointerException because a null line separator is not allowed."
        },
        {
            "input": [],
            "expected": "IllegalArgumentException",
            "description": "Calling withLineSeparator(new byte[]{}) (empty array) on a Builder should throw IllegalArgumentException because the line separator cannot be empty."
        },
        {
            "input": [],
            "expected": "IllegalArgumentException",
            "description": "Calling withLineSeparator(new byte[]{'A','B','C'}) (array length not a multiple of 2) on a Builder should throw IllegalArgumentException because the line separator must consist of CR/LF pair(s)."
        },
        {
            "input": [],
            "expected": "IllegalStateException",
            "description": "Invoking build() on a Builder after calling withLineSeparator(new byte[]{'\\r','\\n','\\r','\\n','\\r'}) (invalid length) should result in IllegalStateException during validation in the build process."
        }
    ]
}