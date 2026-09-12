Invalid control character at: line 62 column 62 (char 2251)
{
    "group": "nextNonWhitespace",
    "cases": [
        {
            "input": [
                {
                    "parameter": "throwOnEof",
                    "value": false
                }
            ],
            "expected": -1,
            "description": "Test case where buffer is empty and throwOnEof is false. The method should return -1 indicating no non-whitespace character found."
        },
        {
            "input": [
                {
                    "parameter": "throwOnEof",
                    "value": true
                }
            ],
            "expected": "EOFException",
            "description": "Test case where buffer is empty and throwOnEof is true. The method should throw an EOFException indicating end of input reached."
        },
        {
            "input": [
                {
                    "parameter": "throwOnEof",
                    "value": false
                }
            ],
            "expected": "non-whitespace character",
            "description": "Test case where buffer contains non-whitespace characters. The method should return the first non-whitespace character encountered."
        },
        {
            "input": [
                {
                    "parameter": "throwOnEof",
                    "value": false
                }
            ],
            "expected": "syntaxError",
            "description": "Test case where buffer contains an unterminated comment. The method should throw a syntax error indicating unterminated comment."
        },
        {
            "input": [
                {
                    "parameter": "throwOnEof",
                    "value": false
                }
            ],
            "expected": "non-whitespace character",
            "description": "Test case where buffer contains a complete C-style comment. The method should skip the comment and return the first non-whitespace character after the comment."
        },
        {
            "input": [
                {
                    "parameter": "throwOnEof",
                    "value": false
                }
            ],
            "expected": "non-whitespace character",
            "description": "Test case where buffer contains a // end-of-line comment. The method should skip the comment and return the first non-whitespace character after the comment."
        },
        {
            "input": [
                {
                    "parameter": "throwOnEof",
                    "value": false
                }
            ],
            "expected": "non-whitespace character",
            "description": "Test case where buffer contains a # end-of-line comment. The method should skip the comment and return the first non-whitespace character after the comment."
        }
    ]
}