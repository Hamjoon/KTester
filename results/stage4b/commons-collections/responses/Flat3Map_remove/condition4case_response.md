{
  "testCases": [
    {
      "group": "remove with delegateMap",
      "cases": [
        {
          "input": [
            {
              "parameter": "key",
              "value": "someKey"
            }
          ],
          "expected": "value associated with 'someKey' in delegateMap",
          "description": "Test removing a key when delegateMap is not null."
        }
      ]
    },
    {
      "group": "remove when size is 0",
      "cases": [
        {
          "input": [
            {
              "parameter": "key",
              "value": "anyKey"
            }
          ],
          "expected": "null",
          "description": "Test removing a key when size is 0, should return null."
        }
      ]
    },
    {
      "group": "remove when key is null and size is 3",
      "cases": [
        {
          "input": [
            {
              "parameter": "key",
              "value": null
            }
          ],
          "expected": "value3",
          "description": "Test removing null key when size is 3 and key3 is null, should return value3."
        },
        {
          "input": [
            {
              "parameter": "key",
              "value": null
            }
          ],
          "expected": "value2",
          "description": "Test removing null key when size is 3 and key2 is null, should return value2."
        },
        {
          "input": [
            {
              "parameter": "key",
              "value": null
            }
          ],
          "expected": "value1",
          "description": "Test removing null key when size is 3 and key1 is null, should return value1."
        }
      ]
    },
    {
      "group": "remove when key is null and size is 2",
      "cases": [
        {
          "input": [
            {
              "parameter": "key",
              "value": null
            }
          ],
          "expected": "value2",
          "description": "Test removing null key when size is 2 and key2 is null, should return value2."
        },
        {
          "input": [
            {
              "parameter": "key",
              "value": null
            }
          ],
          "expected": "value1",
          "description": "Test removing null key when size is 2 and key1 is null, should return value1."
        }
      ]
    },
    {
      "group": "remove when key is null and size is 1",
      "cases": [
        {
          "input": [
            {
              "parameter": "key",
              "value": null
            }
          ],
          "expected": "value1",
          "description": "Test removing null key when size is 1 and key1 is null, should return value1."
        }
      ]
    },
    {
      "group": "remove with valid keys when size is 3",
      "cases": [
        {
          "input": [
            {
              "parameter": "key",
              "value": "key3"
            }
          ],
          "expected": "value3",
          "description": "Test removing key3 when size is 3, should return value3."
        },
        {
          "input": [
            {
              "parameter": "key",
              "value": "key2"
            }
          ],
          "expected": "value2",
          "description": "Test removing key2 when size is 3, should return value2."
        },
        {
          "input": [
            {
              "parameter": "key",
              "value": "key1"
            }
          ],
          "expected": "value1",
          "description": "Test removing key1 when size is 3, should return value1."
        }
      ]
    },
    {
      "group": "remove with valid keys when size is 2",
      "cases": [
        {
          "input": [
            {
              "parameter": "key",
              "value": "key2"
            }
          ],
          "expected": "value2",
          "description": "Test removing key2 when size is 2, should return value2."
        },
        {
          "input": [
            {
              "parameter": "key",
              "value": "key1"
            }
          ],
          "expected": "value1",
          "description": "Test removing key1 when size is 2, should return value1."
        }
      ]
    },
    {
      "group": "remove with valid key when size is 1",
      "cases": [
        {
          "input": [
            {
              "parameter": "key",
              "value": "key1"
            }
          ],
          "expected": "value1",
          "description": "Test removing key1 when size is 1, should return value1."
        }
      ]
    },
    {
      "group": "remove non-existing key",
      "cases": [
        {
          "input": [
            {
              "parameter": "key",
              "value": "nonExistingKey"
            }
          ],
          "expected": "null",
          "description": "Test removing a non-existing key, should return null."
        }
      ]
    }
  ]
}