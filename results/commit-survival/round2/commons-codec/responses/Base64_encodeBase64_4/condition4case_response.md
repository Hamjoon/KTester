{
  "group": "Empty input handling",
  "cases": [
    {
      "input": [
        {
          "parameter": "binaryData",
          "value": []
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
          "value": 10
        }
      ],
      "expected": "[]",
      "description": "When the input byte array is empty, the method should return the same empty array without invoking any other logic."
    }
  ]
}