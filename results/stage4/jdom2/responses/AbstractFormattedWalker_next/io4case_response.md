{
    "test cases": [
        {
            "group": "next() method tests",
            "cases": [
                {
                    "input": [],
                    "expected": "Content object",
                    "description": "Test when multitext is not null and mtpos is less than mtsize, expecting a Content object to be returned."
                },
                {
                    "input": [],
                    "expected": "Content object",
                    "description": "Test when pendingmt is null and content has more elements, expecting a Content object to be returned."
                },
                {
                    "input": [],
                    "expected": "NoSuchElementException",
                    "description": "Test when next() is called after all content has been iterated, expecting NoSuchElementException."
                },
                {
                    "input": [],
                    "expected": "Content object",
                    "description": "Test when newlineindent is null but pending is non-null, expecting a Content object to be returned without indentation."
                },
                {
                    "input": [],
                    "expected": "Content object",
                    "description": "Test when the first call to next() is made and content has non-text items, expecting a Content object to be returned."
                }
            ]
        }
    ]
}