{
    "group": "printContent exception scenarios",
    "cases": [
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "null"
                },
                {
                    "parameter": "fstack",
                    "value": "valid FormatStack"
                },
                {
                    "parameter": "nstack",
                    "value": "valid NamespaceStack"
                },
                {
                    "parameter": "walker",
                    "value": "valid Walker with some content"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test case where XMLStreamWriter is null, expecting NullPointerException to be thrown."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "valid XMLStreamWriter"
                },
                {
                    "parameter": "fstack",
                    "value": "null"
                },
                {
                    "parameter": "nstack",
                    "value": "valid NamespaceStack"
                },
                {
                    "parameter": "walker",
                    "value": "valid Walker with some content"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test case where FormatStack is null, expecting NullPointerException to be thrown."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "valid XMLStreamWriter"
                },
                {
                    "parameter": "fstack",
                    "value": "valid FormatStack"
                },
                {
                    "parameter": "nstack",
                    "value": "null"
                },
                {
                    "parameter": "walker",
                    "value": "valid Walker with some content"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test case where NamespaceStack is null, expecting NullPointerException to be thrown."
        },
        {
            "input": [
                {
                    "parameter": "out",
                    "value": "valid XMLStreamWriter"
                },
                {
                    "parameter": "fstack",
                    "value": "valid FormatStack"
                },
                {
                    "parameter": "nstack",
                    "value": "valid NamespaceStack"
                },
                {
                    "parameter": "walker",
                    "value": "null"
                }
            ],
            "expected": "NullPointerException",
            "description": "Test case where Walker is null, expecting NullPointerException to be thrown."
        }
    ]
}