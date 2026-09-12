{
    "group": "Flat3Map equals(Object) Method Additional Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "obj",
                    "value": "null"
                }
            ],
            "expected": false,
            "description": "Test where the object is null, expecting false since a null object cannot be equal to a map."
        },
        {
            "input": [
                {
                    "parameter": "obj",
                    "value": "new HashMap() {{ put(key1, value1); put(key2, value2); put(differentKey, differentValue); }}"
                }
            ],
            "expected": false,
            "description": "Test where the Flat3Map has size 2 but the Map contains an additional different key-value pair, expecting false."
        },
        {
            "input": [
                {
                    "parameter": "obj",
                    "value": "new HashMap() {{ put(key1, value1); put(key3, value3); }}"
                }
            ],
            "expected": false,
            "description": "Test where the Flat3Map has size 3 but the Map is missing one key-value pair, expecting false."
        },
        {
            "input": [
                {
                    "parameter": "obj",
                    "value": "new HashMap() {{ put(key1, value1); put(key2, value2); put(key3, value3); put(extraKey, extraValue); }}"
                }
            ],
            "expected": false,
            "description": "Test where the Flat3Map has size 3 and the Map contains the same key-value pairs plus an extra one, expecting false."
        },
        {
            "input": [
                {
                    "parameter": "obj",
                    "value": "new HashMap() {{ put(key1, value1); put(key2, null); }}"
                }
            ],
            "expected": false,
            "description": "Test where the Flat3Map has size 2 and the Map contains null for one of the values, expecting false."
        },
        {
            "input": [
                {
                    "parameter": "obj",
                    "value": "new HashMap() {{ put(key1, null); put(key2, value2); }}"
                }
            ],
            "expected": false,
            "description": "Test where the Flat3Map has size 2 and the Map contains null for the first value, expecting false."
        }
    ]
}