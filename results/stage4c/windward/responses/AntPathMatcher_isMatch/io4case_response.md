{
    "group": "isMatch method additional test cases",
    "cases": [
        {
            "input": [
                {
                    "parameter": "pattern",
                    "value": "some/**"
                },
                {
                    "parameter": "path",
                    "value": "some/any/path"
                }
            ],
            "expected": true,
            "description": "Pattern is 'some/**' and path is 'some/any/path', should return true as '**' matches any sequence of characters including path separators."
        },
        {
            "input": [
                {
                    "parameter": "pattern",
                    "value": "some/*"
                },
                {
                    "parameter": "path",
                    "value": "some/"
                }
            ],
            "expected": true,
            "description": "Pattern is 'some/*' and path is 'some/', should return true since '*' can match zero or more characters."
        },
        {
            "input": [
                {
                    "parameter": "pattern",
                    "value": "some/**/path"
                },
                {
                    "parameter": "path",
                    "value": "some/any/deep/path"
                }
            ],
            "expected": true,
            "description": "Pattern is 'some/**/path' and path is 'some/any/deep/path', should return true as '**' can match any path segment."
        },
        {
            "input": [
                {
                    "parameter": "pattern",
                    "value": "some/*/path"
                },
                {
                    "parameter": "path",
                    "value": "some/any/path"
                }
            ],
            "expected": true,
            "description": "Pattern is 'some/*/path' and path is 'some/any/path', should return true as '*' can match a single path segment."
        },
        {
            "input": [
                {
                    "parameter": "pattern",
                    "value": "some/*/path"
                },
                {
                    "parameter": "path",
                    "value": "some/path"
                }
            ],
            "expected": false,
            "description": "Pattern is 'some/*/path' and path is 'some/path', should return false as '*' should match exactly one segment."
        },
        {
            "input": [
                {
                    "parameter": "pattern",
                    "value": "*/path"
                },
                {
                    "parameter": "path",
                    "value": "/path"
                }
            ],
            "expected": false,
            "description": "Pattern is '*/path' and path is '/path', should return false because '*' cannot match an empty segment when path starts with a separator."
        },
        {
            "input": [
                {
                    "parameter": "pattern",
                    "value": "some/**"
                },
                {
                    "parameter": "path",
                    "value": "some"
                }
            ],
            "expected": true,
            "description": "Pattern is 'some/**' and path is 'some', should return true as '**' can match zero or more characters."
        },
        {
            "input": [
                {
                    "parameter": "pattern",
                    "value": "some/**/"
                },
                {
                    "parameter": "path",
                    "value": "some/"
                }
            ],
            "expected": true,
            "description": "Pattern is 'some/**/' and path is 'some/', should return true as '**' can match zero or more characters including path separators."
        },
        {
            "input": [
                {
                    "parameter": "pattern",
                    "value": "some/path/**"
                },
                {
                    "parameter": "path",
                    "value": "some/path/deeply/nested"
                }
            ],
            "expected": true,
            "description": "Pattern is 'some/path/**' and path is 'some/path/deeply/nested', should return true as '**' matches any sequence of characters following 'some/path/'."
        },
        {
            "input": [
                {
                    "parameter": "pattern",
                    "value": "some/path/*"
                },
                {
                    "parameter": "path",
                    "value": "some/path/deeply/nested"
                }
            ],
            "expected": false,
            "description": "Pattern is 'some/path/*' and path is 'some/path/deeply/nested', should return false as '*' only matches a single segment."
        }
    ]
}