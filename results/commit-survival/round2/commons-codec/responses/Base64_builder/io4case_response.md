{
    "testCases": [
        {
            "group": "Base64.builder() build configuration tests",
            "cases": [
                {
                    "input": [],
                    "expected": "Base64 instance where isUrlSafe() returns false and getLineSeparator() returns null (default configuration)",
                    "description": "Validate that a Builder created with Base64.builder() and built without any further configuration produces a Base64 object using the library's default settings (non‑URL‑safe and no custom line separator)."
                },
                {
                    "input": [],
                    "expected": "Base64 instance where isUrlSafe() returns true",
                    "description": "Verify that invoking the urlSafe() option on the Builder (Base64.builder().urlSafe().build()) yields a Base64 object that reports URL‑safe mode via isUrlSafe()."
                },
                {
                    "input": [],
                    "expected": "Base64 instance whose getLineSeparator() returns a byte array equal to {13,10}",
                    "description": "Check that supplying a custom line separator via withLineSeparator(new byte[]{'\\r','\\n'}) on the Builder results in a Base64 object that returns the same separator from getLineSeparator()."
                }
            ]
        }
    ]
}