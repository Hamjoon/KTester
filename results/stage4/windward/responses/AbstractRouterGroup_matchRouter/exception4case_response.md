{
    "group": "matchRouter - No router matching pattern found",
    "cases": [
        {
            "input": [
                {
                    "parameter": "relativePath",
                    "value": "/groupPath/resource/invalid"
                },
                {
                    "parameter": "method",
                    "value": "GET"
                }
            ],
            "expected": null,
            "description": "Test when the relativePath does not match any router pattern; should return null."
        }
    ]
}