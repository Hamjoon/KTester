{
    "group": "printContent",
    "cases": [
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "null"
                },
                {
                    "parameter": "fstack",
                    "value": "MockFormatStack"
                },
                {
                    "parameter": "nstack",
                    "value": "MockNamespaceStack"
                },
                {
                    "parameter": "walker",
                    "value": "MockWalkerWithValidContent"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test case where Writer is null, expecting NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "MockWriter"
                },
                {
                    "parameter": "fstack",
                    "value": "null"
                },
                {
                    "parameter": "nstack",
                    "value": "MockNamespaceStack"
                },
                {
                    "parameter": "walker",
                    "value": "MockWalkerWithValidContent"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test case where FormatStack is null, expecting NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "MockWriter"
                },
                {
                    "parameter": "fstack",
                    "value": "MockFormatStack"
                },
                {
                    "parameter": "nstack",
                    "value": "null"
                },
                {
                    "parameter": "walker",
                    "value": "MockWalkerWithValidContent"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test case where NamespaceStack is null, expecting NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "MockWriter"
                },
                {
                    "parameter": "fstack",
                    "value": "MockFormatStack"
                },
                {
                    "parameter": "nstack",
                    "value": "MockNamespaceStack"
                },
                {
                    "parameter": "walker",
                    "value": "null"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test case where Walker is null, expecting NullPointerException."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "MockWriter"
                },
                {
                    "parameter": "fstack",
                    "value": "MockFormatStack"
                },
                {
                    "parameter": "nstack",
                    "value": "MockNamespaceStack"
                },
                {
                    "parameter": "walker",
                    "value": "MockWalkerWithIOException"
                }
            ],
            "expected": "IOException",
            "description": "Test case where Walker throws IOException during iteration, expecting IOException."
        }
    ]
}