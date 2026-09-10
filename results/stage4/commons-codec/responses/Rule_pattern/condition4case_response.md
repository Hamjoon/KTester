{
    "test name": "pattern method test cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": "^abc$"
                }
            ],
            "expected": true,
            "description": "Test exact match with non-empty string 'abc'. Should return true."
        },
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": "^abc$"
                }
            ],
            "expected": false,
            "description": "Test exact match with non-matching string 'abcd'. Should return false."
        },
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": "^$"
                }
            ],
            "expected": true,
            "description": "Test for empty string match. Should return true for empty input."
        },
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": "^abc"
                }
            ],
            "expected": true,
            "description": "Test 'starts with' condition. Input 'abcde' should return true."
        },
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": "abc$"
                }
            ],
            "expected": true,
            "description": "Test 'ends with' condition. Input 'xyzabc' should return true."
        },
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": "abc$"
                }
            ],
            "expected": false,
            "description": "Test 'ends with' condition. Input 'abcxyz' should return false."
        },
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": "[abc]"
                }
            ],
            "expected": true,
            "description": "Test character class match. Input 'a' should return true."
        },
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": "[^abc]"
                }
            ],
            "expected": true,
            "description": "Test negated character class match. Input 'd' should return true."
        },
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": "[abc]"
                }
            ],
            "expected": false,
            "description": "Test character class match. Input 'd' should return false."
        },
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": "[a-c]"
                }
            ],
            "expected": true,
            "description": "Test character range match. Input 'b' should return true."
        },
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": "[a-c]"
                }
            ],
            "expected": false,
            "description": "Test character range match. Input 'd' should return false."
        },
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": "[a-c][d-f]"
                }
            ],
            "expected": true,
            "description": "Test two-character class match. Input 'be' should return true."
        },
        {
            "input": [
                {
                    "parameter": "regex",
                    "value": "[a-c][d-f]"
                }
            ],
            "expected": false,
            "description": "Test two-character class match. Input 'ge' should return false."
        }
    ]
}