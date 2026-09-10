{
    "testCases": [
        {
            "group": "next() method tests",
            "cases": [
                {
                    "input": [],
                    "expected": "NoSuchElementException",
                    "description": "Test when hasnext is false, expecting NoSuchElementException to be thrown."
                },
                {
                    "input": [],
                    "expected": "Content object",
                    "description": "Test when multitext is null and pending is non-null, expecting a Content object to be returned."
                },
                {
                    "input": [],
                    "expected": "null",
                    "description": "Test when pending is null after iterating through content, expecting null to be returned."
                },
                {
                    "input": [],
                    "expected": "Content object",
                    "description": "Test when isTextLike returns true for pending, expecting a Content object to be returned."
                },
                {
                    "input": [],
                    "expected": "Content object",
                    "description": "Test when isTextLike returns false for pending, expecting a Content object to be returned."
                },
                {
                    "input": [],
                    "expected": "Content object",
                    "description": "Test when pendingmt is non-null and mtpos is less than mtsize, expecting a Content object to be returned."
                },
                {
                    "input": [],
                    "expected": "null",
                    "description": "Test when mtpos is equal to or greater than mtsize, expecting null to be returned."
                },
                {
                    "input": [],
                    "expected": "Content object",
                    "description": "Test when newlineindent is non-null and pending is non-null, expecting a Content object to be returned with indentation."
                }
            ]
        }
    ]
}