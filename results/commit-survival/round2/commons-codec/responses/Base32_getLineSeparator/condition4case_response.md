{
    "testCases": [
        {
            "group": "Default constructor – no line separator",
            "cases": [
                {
                    "input": [],
                    "expected": null,
                    "description": "Instantiate Base32 using the default constructor. The internal `lineSeparator` field should be `null`, so `getLineSeparator()` must return `null`."
                },
                {
                    "input": [],
                    "expected": null,
                    "description": "Instantiate Base32 via the builder without calling `withLineSeparator`. The builder should create an instance equivalent to the default constructor, therefore `getLineSeparator()` must return `null`."
                }
            ]
        },
        {
            "group": "Custom line separator via constructor",
            "cases": [
                {
                    "input": [],
                    "expected": "[13,10]",
                    "description": "Create a Base32 instance with a CRLF line separator (`new Base32(0, new byte[]{'\\r','\\n'})`). `getLineSeparator()` must return the exact byte array `[13,10]`."
                },
                {
                    "input": [],
                    "expected": "[32,32,32]",
                    "description": "Create a Base32 instance with three space characters as line separator (`new Base32(0, new byte[]{' ',' ',' '})`). `getLineSeparator()` must return `[32,32,32]`."
                }
            ]
        },
        {
            "group": "Custom line separator via builder",
            "cases": [
                {
                    "input": [],
                    "expected": "[10]",
                    "description": "Use the builder to set a single LF line separator (`Base32.builder().withLineSeparator(new byte[]{'\\n'}).build()`). `getLineSeparator()` must return `[10]`."
                },
                {
                    "input": [],
                    "expected": "[124,124]",
                    "description": "Use the builder to set a custom separator consisting of two pipe characters (`Base32.builder().withLineSeparator(new byte[]{'|','|'}).build()`). `getLineSeparator()` must return `[124,124]`."
                }
            ]
        },
        {
            "group": "Reference semantics – returned array is the internal mutable field",
            "cases": [
                {
                    "input": [],
                    "expected": "[42,42]",
                    "description": "Create a Base32 instance with a mutable separator (`new byte[]{'*','*'}`). Retrieve the array via `getLineSeparator()`, modify the first element to `'*'` → `42`. Because the method returns the internal reference, the modification is reflected in subsequent calls, so a second call to `getLineSeparator()` must return `[42,42]`."
                },
                {
                    "input": [],
                    "expected": "[33]",
                    "description": "Create a Base32 instance with a single-byte separator (`new byte[]{'!'}`). Retrieve the array, replace the element with `'?'` (`63`). The internal state changes, therefore a later call to `getLineSeparator()` must return `[63]`."
                }
            ]
        },
        {
            "group": "Empty line separator array",
            "cases": [
                {
                    "input": [],
                    "expected": "[]",
                    "description": "Instantiate Base32 with an empty byte array as line separator (`new Base32(0, new byte[0])`). `getLineSeparator()` must return an empty array (`[]`)."
                }
            ]
        }
    ]
}