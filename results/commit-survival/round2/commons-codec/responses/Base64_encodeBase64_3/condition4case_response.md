{
    "group": "Null Input",
    "cases": [
        {
            "input": [
                {
                    "parameter": "binaryData",
                    "value": null
                },
                {
                    "parameter": "isChunked",
                    "value": false
                },
                {
                    "parameter": "urlSafe",
                    "value": false
                }
            ],
            "expected": null,
            "description": "When the source byte array is null the method should return null regardless of the flags."
        }
    ]
}