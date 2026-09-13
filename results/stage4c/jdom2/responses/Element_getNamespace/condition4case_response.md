{
  "group": "getNamespace Method Tests",
  "cases": [
    {
      "input": [
        {
          "parameter": "prefix",
          "value": null
        }
      ],
      "expected": null,
      "description": "Test with null prefix to verify method returns null."
    },
    {
      "input": [
        {
          "parameter": "prefix",
          "value": "xml"
        }
      ],
      "expected": "Namespace.XML_NAMESPACE",
      "description": "Test with 'xml' prefix to ensure it returns the XML namespace."
    },
    {
      "input": [
        {
          "parameter": "prefix",
          "value": "default"
        }
      ],
      "expected": "Namespace for the element's namespace",
      "description": "Test with prefix matching the element's namespace prefix to verify it returns the element's namespace."
    },
    {
      "input": [
        {
          "parameter": "prefix",
          "value": "additional"
        }
      ],
      "expected": "Namespace with prefix 'additional'",
      "description": "Test with a prefix that matches one of the additional namespaces to ensure it returns the correct namespace."
    },
    {
      "input": [
        {
          "parameter": "prefix",
          "value": "attribute"
        }
      ],
      "expected": "Namespace with prefix 'attribute'",
      "description": "Test with a prefix that matches one of the attribute namespaces to ensure it returns the correct namespace."
    },
    {
      "input": [
        {
          "parameter": "prefix",
          "value": "parent"
        }
      ],
      "expected": "Namespace from parent element",
      "description": "Test with a prefix that does not match any in the current element but exists in the parent element to ensure it returns the parent's namespace."
    },
    {
      "input": [
        {
          "parameter": "prefix",
          "value": "nonexistent"
        }
      ],
      "expected": null,
      "description": "Test with a non-matching prefix to verify the method returns null when no matching namespace is found."
    }
  ]
}