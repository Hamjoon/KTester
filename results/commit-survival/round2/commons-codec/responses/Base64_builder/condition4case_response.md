{
    "group": "Base64.builder() method tests",
    "cases": [
        {
            "input": [],
            "expected": "non-null instance of org.apache.commons.codec.binary.Base64$Builder",
            "description": "Verify that calling Base64.builder() returns a non-null Builder object."
        },
        {
            "input": [],
            "expected": "two distinct Builder instances on successive calls",
            "description": "Ensure that each invocation of Base64.builder() creates a new Builder instance (different object references)."
        },
        {
            "input": [],
            "expected": "instance of Base64.Builder class",
            "description": "Confirm that the returned object's runtime type is exactly Base64.Builder."
        }
    ]
}