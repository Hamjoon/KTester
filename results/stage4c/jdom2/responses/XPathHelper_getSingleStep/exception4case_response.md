{
    "group": "getSingleStep Exception Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "nsa",
                    "value": "NullInstance"
                },
                {
                    "parameter": "buffer",
                    "value": "new StringBuilder()"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test getSingleStep with a null NamespaceAware instance. This should trigger a NullPointerException as the method tries to access methods on a null object."
        }
    ]
}