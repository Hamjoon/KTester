{
    "group": "getSingleStep Additional Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "nsa",
                    "value": "NullContentInstance"
                },
                {
                    "parameter": "buffer",
                    "value": "new StringBuilder()"
                }
            ],
            "expected": "buffer unchanged",
            "description": "Test getSingleStep with a null Content instance. The buffer should remain unchanged as there is no valid NamespaceAware instance."
        },
        {
            "input": [
                {
                    "parameter": "nsa",
                    "value": "ElementWithNoParentInstance"
                },
                {
                    "parameter": "buffer",
                    "value": "new StringBuilder()"
                }
            ],
            "expected": "buffer with element name position path",
            "description": "Test getSingleStep with an Element instance that has no parent. It should append the element name position path to the buffer assuming no siblings."
        },
        {
            "input": [
                {
                    "parameter": "nsa",
                    "value": "AttributeWithNoNamespaceAndNoParentInstance"
                },
                {
                    "parameter": "buffer",
                    "value": "new StringBuilder()"
                }
            ],
            "expected": "buffer with @attributeName",
            "description": "Test getSingleStep with an Attribute instance that has no namespace and no parent. It should append '@attributeName' to the buffer."
        },
        {
            "input": [
                {
                    "parameter": "nsa",
                    "value": "ElementWithNamespaceAndNoParentInstance"
                },
                {
                    "parameter": "buffer",
                    "value": "new StringBuilder()"
                }
            ],
            "expected": "buffer with namespace element position path",
            "description": "Test getSingleStep with an Element instance with a namespace but no parent. It should append the complex XPath position path with namespace details to the buffer assuming no siblings."
        },
        {
            "input": [
                {
                    "parameter": "nsa",
                    "value": "TextWithParentInstance"
                },
                {
                    "parameter": "buffer",
                    "value": "new StringBuilder()"
                }
            ],
            "expected": "buffer with text() position path",
            "description": "Test getSingleStep with a Text instance that has a parent. It should append 'text()' position path to the buffer considering the parent's children."
        }
    ]
}