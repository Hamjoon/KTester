{
  "group": "ReflectiveTypeAdapterFactory.getBoundFields",
  "cases": [
    {
      "input": [
        {
          "parameter": "context",
          "value": "MockGsonInstance"
        },
        {
          "parameter": "type",
          "value": "TypeToken.get(MockClass.class)"
        },
        {
          "parameter": "raw",
          "value": "MockClass.class"
        },
        {
          "parameter": "blockInaccessible",
          "value": false
        },
        {
          "parameter": "isRecord",
          "value": false
        }
      ],
      "expected": "An empty map",
      "description": "Test case where 'raw' is an interface, should return an empty map."
    },
    {
      "input": [
        {
          "parameter": "context",
          "value": "MockGsonInstance"
        },
        {
          "parameter": "type",
          "value": "TypeToken.get(ConcreteClass.class)"
        },
        {
          "parameter": "raw",
          "value": "ConcreteClass.class"
        },
        {
          "parameter": "blockInaccessible",
          "value": false
        },
        {
          "parameter": "isRecord",
          "value": false
        }
      ],
      "expected": "JsonIOException",
      "description": "Test case where ReflectionAccessFilter blocks all access, should throw JsonIOException."
    },
    {
      "input": [
        {
          "parameter": "context",
          "value": "MockGsonInstance"
        },
        {
          "parameter": "type",
          "value": "TypeToken.get(ConcreteClass.class)"
        },
        {
          "parameter": "raw",
          "value": "ConcreteClass.class"
        },
        {
          "parameter": "blockInaccessible",
          "value": true
        },
        {
          "parameter": "isRecord",
          "value": false
        }
      ],
      "expected": "A map with bound fields",
      "description": "Test case where fields are accessible and not blocked, should return a map with bound fields."
    },
    {
      "input": [
        {
          "parameter": "context",
          "value": "MockGsonInstance"
        },
        {
          "parameter": "type",
          "value": "TypeToken.get(RecordClass.class)"
        },
        {
          "parameter": "raw",
          "value": "RecordClass.class"
        },
        {
          "parameter": "blockInaccessible",
          "value": false
        },
        {
          "parameter": "isRecord",
          "value": true
        }
      ],
      "expected": "A map with bound fields using accessor methods",
      "description": "Test case for a record class with accessible fields, should use accessor methods for bound fields."
    },
    {
      "input": [
        {
          "parameter": "context",
          "value": "MockGsonInstance"
        },
        {
          "parameter": "type",
          "value": "TypeToken.get(ConflictingClass.class)"
        },
        {
          "parameter": "raw",
          "value": "ConflictingClass.class"
        },
        {
          "parameter": "blockInaccessible",
          "value": false
        },
        {
          "parameter": "isRecord",
          "value": false
        }
      ],
      "expected": "IllegalArgumentException",
      "description": "Test case where class has conflicting JSON field names, should throw IllegalArgumentException."
    }
  ]
}