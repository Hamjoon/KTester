{
    "group": "Base32BuilderTests",
    "cases": [
        {
            "input": [],
            "expected": "A non‑null instance of Base32.Builder is returned",
            "description": "Verify that calling Base32.builder() never returns null."
        },
        {
            "input": [],
            "expected": "Two successive calls to Base32.builder() return distinct objects (different references)",
            "description": "Ensure that each invocation creates a fresh Builder rather than reusing a singleton."
        },
        {
            "input": [],
            "expected": "Calling build() on the returned Builder yields a non‑null Base32 object whose alphabet matches the standard RFC 4648 alphabet",
            "description": "Confirm that the default Builder configuration produces a Base32 codec with the expected default alphabet."
        },
        {
            "input": [],
            "expected": "When the Builder is used concurrently from multiple threads, each thread receives its own Builder instance and no shared mutable state is observed",
            "description": "Validate thread‑safety of the static builder() factory method by invoking it from several threads and checking that the resulting Builder objects are independent."
        },
        {
            "input": [],
            "expected": "The Builder returned by Base32.builder() can be chained with configuration methods (e.g., withPadding(false)) without throwing exceptions, and the resulting Base32 instance reflects the configured options",
            "description": "Check that the Builder supports fluent configuration and that the built Base32 respects the supplied options."
        }
    ]
}