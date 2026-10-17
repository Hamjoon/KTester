{
    "test_cases": [
        {
            "group": "Negative maxResultSize",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": [10, 20, 30]
                        },
                        {
                            "parameter": "isChunked",
                            "value": false
                        },
                        {
                            "parameter": "urlSafe",
                            "value": false
                        },
                        {
                            "parameter": "maxResultSize",
                            "value": -1
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "When maxResultSize is negative, any non‑empty input results in an IllegalArgumentException because the calculated encoded length is always greater than a negative limit."
                }
            ]
        },
        {
            "group": "Zero maxResultSize with non‑empty input",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": [1, 2, 3]
                        },
                        {
                            "parameter": "isChunked",
                            "value": false
                        },
                        {
                            "parameter": "urlSafe",
                            "value": false
                        },
                        {
                            "parameter": "maxResultSize",
                            "value": 0
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "A maxResultSize of zero cannot accommodate any encoded output; the method must throw IllegalArgumentException for any non‑empty binaryData."
                }
            ]
        },
        {
            "group": "Maximum size limit exceeded with chunked encoding",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": [97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97, 97]
                        },
                        {
                            "parameter": "isChunked",
                            "value": true
                        },
                        {
                            "parameter": "urlSafe",
                            "value": false
                        },
                        {
                            "parameter": "maxResultSize",
                            "value": 70
                        }
                    ],
                    "expected": "IllegalArgumentException",
                    "description": "With chunking enabled the encoded output includes line separators, increasing its length. Providing a maxResultSize (70) smaller than this total length forces an IllegalArgumentException."
                }
            ]
        }
    ]
}