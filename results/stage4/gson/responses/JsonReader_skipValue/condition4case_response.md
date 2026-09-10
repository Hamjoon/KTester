{
    "test cases": [
        {
            "group": "skipValue - Basic Cases",
            "cases": [
                {
                    "input": [],
                    "expected": "No exception thrown, state updated correctly",
                    "description": "Test skipValue when peeked is PEEKED_NONE, should call doPeek and proceed without exceptions."
                },
                {
                    "input": [],
                    "expected": "Stack size increased by 1 and count incremented",
                    "description": "Test skipValue when peeked is PEEKED_BEGIN_ARRAY, should push new state and increment count."
                },
                {
                    "input": [],
                    "expected": "Stack size increased by 1 and count incremented",
                    "description": "Test skipValue when peeked is PEEKED_BEGIN_OBJECT, should push new state and increment count."
                },
                {
                    "input": [],
                    "expected": "Stack size decreased by 1 and count decremented",
                    "description": "Test skipValue when peeked is PEEKED_END_ARRAY, should decrement stack size and count."
                },
                {
                    "input": [],
                    "expected": "Stack size decreased by 1 and count decremented",
                    "description": "Test skipValue when peeked is PEEKED_END_OBJECT, should decrement stack size and count."
                }
            ]
        },
        {
            "group": "skipValue - Quoted and Unquoted Values",
            "cases": [
                {
                    "input": [],
                    "expected": "No exception thrown, correctly skips unquoted value",
                    "description": "Test skipValue when peeked is PEEKED_UNQUOTED, should call skipUnquotedValue and continue without exceptions."
                },
                {
                    "input": [],
                    "expected": "No exception thrown, correctly skips single quoted value",
                    "description": "Test skipValue when peeked is PEEKED_SINGLE_QUOTED, should call skipQuotedValue with single quote."
                },
                {
                    "input": [],
                    "expected": "No exception thrown, correctly skips double quoted value",
                    "description": "Test skipValue when peeked is PEEKED_DOUBLE_QUOTED, should call skipQuotedValue with double quote."
                },
                {
                    "input": [],
                    "expected": "No exception thrown, correctly skips unquoted name",
                    "description": "Test skipValue when peeked is PEEKED_UNQUOTED_NAME, should call skipUnquotedValue and update pathNames."
                },
                {
                    "input": [],
                    "expected": "No exception thrown, correctly skips single quoted name",
                    "description": "Test skipValue when peeked is PEEKED_SINGLE_QUOTED_NAME, should call skipQuotedValue with single quote and update pathNames."
                },
                {
                    "input": [],
                    "expected": "No exception thrown, correctly skips double quoted name",
                    "description": "Test skipValue when peeked is PEEKED_DOUBLE_QUOTED_NAME, should call skipQuotedValue with double quote and update pathNames."
                }
            ]
        },
        {
            "group": "skipValue - Numbers and EOF",
            "cases": [
                {
                    "input": [],
                    "expected": "No exception thrown, pos updated correctly",
                    "description": "Test skipValue when peeked is PEEKED_NUMBER, should update pos with peekedNumberLength."
                },
                {
                    "input": [],
                    "expected": "No exception thrown, method returns without state change",
                    "description": "Test skipValue when peeked is PEEKED_EOF, should exit the method without any changes."
                }
            ]
        },
        {
            "group": "skipValue - Edge Cases",
            "cases": [
                {
                    "input": [],
                    "expected": "No exception thrown, pathNames updated correctly",
                    "description": "Test skipValue when count is 0 and peeked is PEEKED_END_OBJECT, should set last pathName to null."
                },
                {
                    "input": [],
                    "expected": "No exception thrown, pathNames updated correctly",
                    "description": "Test skipValue when count is 0 and peeked is PEEKED_UNQUOTED_NAME, should set last pathName to '<skipped>'."
                },
                {
                    "input": [],
                    "expected": "No exception thrown, pathNames updated correctly",
                    "description": "Test skipValue when count is 0 and peeked is PEEKED_SINGLE_QUOTED_NAME, should set last pathName to '<skipped>'."
                },
                {
                    "input": [],
                    "expected": "No exception thrown, pathNames updated correctly",
                    "description": "Test skipValue when count is 0 and peeked is PEEKED_DOUBLE_QUOTED_NAME, should set last pathName to '<skipped>'."
                }
            ]
        }
    ]
}