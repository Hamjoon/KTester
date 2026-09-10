{
    "group": "toJavaNames Tests",
    "cases": [
        {
            "input": [
                {
                    "parameter": "string",
                    "value": null
                },
                {
                    "parameter": "isMethod",
                    "value": false
                }
            ],
            "expected": null,
            "description": "Test with null string input, should return null."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": ""
                },
                {
                    "parameter": "isMethod",
                    "value": false
                }
            ],
            "expected": "",
            "description": "Test with empty string input, should return empty string."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "helloWorld"
                },
                {
                    "parameter": "isMethod",
                    "value": false
                }
            ],
            "expected": "HelloWorld",
            "description": "Test with a standard camel case string, should return string with first character capitalized."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "hello_world"
                },
                {
                    "parameter": "isMethod",
                    "value": false
                }
            ],
            "expected": "HelloWorld",
            "description": "Test with snake case string, should convert to camel case with first character capitalized."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "hello_world"
                },
                {
                    "parameter": "isMethod",
                    "value": true
                }
            ],
            "expected": "helloWorld",
            "description": "Test with snake case string and isMethod true, should convert to camel case with first character in lower case."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "HelloWorld"
                },
                {
                    "parameter": "isMethod",
                    "value": false
                }
            ],
            "expected": "HelloWorld",
            "description": "Test with already formatted camel case string, should return the same string."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "hello_world_test"
                },
                {
                    "parameter": "isMethod",
                    "value": false
                }
            ],
            "expected": "HelloWorldTest",
            "description": "Test with multiple underscores in snake case, should convert to camel case with first character capitalized."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "hello_world_test"
                },
                {
                    "parameter": "isMethod",
                    "value": true
                }
            ],
            "expected": "helloWorldTest",
            "description": "Test with multiple underscores in snake case and isMethod true, should convert to camel case with first character in lower case."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "_leadingUnderscore"
                },
                {
                    "parameter": "isMethod",
                    "value": false
                }
            ],
            "expected": "LeadingUnderscore",
            "description": "Test with leading underscore, should capitalize the first letter after the underscore."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "_leadingUnderscore"
                },
                {
                    "parameter": "isMethod",
                    "value": true
                }
            ],
            "expected": "leadingUnderscore",
            "description": "Test with leading underscore and isMethod true, should keep the first letter in lower case."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "alreadyCamelCase"
                },
                {
                    "parameter": "isMethod",
                    "value": true
                }
            ],
            "expected": "alreadyCamelCase",
            "description": "Test with already formatted camel case string and isMethod true, should return the same string."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "singleword"
                },
                {
                    "parameter": "isMethod",
                    "value": false
                }
            ],
            "expected": "Singleword",
            "description": "Test with a single word input, should capitalize the first letter."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "SINGLE_WORD"
                },
                {
                    "parameter": "isMethod",
                    "value": false
                }
            ],
            "expected": "SingleWord",
            "description": "Test with all uppercase letters in snake case, should convert to camel case with first letter capitalized."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "another_example_here"
                },
                {
                    "parameter": "isMethod",
                    "value": false
                }
            ],
            "expected": "AnotherExampleHere",
            "description": "Test with multiple words in snake case, should convert to camel case with first letter capitalized."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "another_example_here"
                },
                {
                    "parameter": "isMethod",
                    "value": true
                }
            ],
            "expected": "anotherExampleHere",
            "description": "Test with multiple words in snake case and isMethod true, should convert to camel case with first letter in lower case."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "withNumbers123"
                },
                {
                    "parameter": "isMethod",
                    "value": false
                }
            ],
            "expected": "WithNumbers123",
            "description": "Test with a string containing numbers, should capitalize the first letter."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "with_numbers_123"
                },
                {
                    "parameter": "isMethod",
                    "value": false
                }
            ],
            "expected": "WithNumbers123",
            "description": "Test with snake case containing numbers, should convert to camel case with first letter capitalized."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "with_numbers_123"
                },
                {
                    "parameter": "isMethod",
                    "value": true
                }
            ],
            "expected": "withNumbers123",
            "description": "Test with snake case containing numbers and isMethod true, should convert to camel case with first letter in lower case."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "a"
                },
                {
                    "parameter": "isMethod",
                    "value": false
                }
            ],
            "expected": "A",
            "description": "Test with a single character input, should capitalize the letter."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "a"
                },
                {
                    "parameter": "isMethod",
                    "value": true
                }
            ],
            "expected": "a",
            "description": "Test with a single character input and isMethod true, should return the same character."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "mixed_Case_Example"
                },
                {
                    "parameter": "isMethod",
                    "value": false
                }
            ],
            "expected": "MixedCaseExample",
            "description": "Test with mixed case and snake case, should convert to camel case with first letter capitalized."
        },
        {
            "input": [
                {
                    "parameter": "string",
                    "value": "mixed_Case_Example"
                },
                {
                    "parameter": "isMethod",
                    "value": true
                }
            ],
            "expected": "mixedCaseExample",
            "description": "Test with mixed case and snake case and isMethod true, should convert to camel case with first letter in lower case."
        }
    ]
}