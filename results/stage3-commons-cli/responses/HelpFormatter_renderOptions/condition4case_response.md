{
    "testCases": [
        {
            "group": "renderOptions with valid options",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "sb",
                            "value": "new StringBuffer()"
                        },
                        {
                            "parameter": "width",
                            "value": "74"
                        },
                        {
                            "parameter": "options",
                            "value": "new Options().addOption(new Option(\"a\", \"aaa\", true, \"description\"))"
                        },
                        {
                            "parameter": "leftPad",
                            "value": "1"
                        },
                        {
                            "parameter": "descPad",
                            "value": "3"
                        }
                    ],
                    "expected": "StringBuffer containing formatted option help",
                    "description": "Test rendering options with a single valid option that has a short and long form."
                }
            ]
        },
        {
            "group": "renderOptions with empty options",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "sb",
                            "value": "new StringBuffer()"
                        },
                        {
                            "parameter": "width",
                            "value": "74"
                        },
                        {
                            "parameter": "options",
                            "value": "new Options()"
                        },
                        {
                            "parameter": "leftPad",
                            "value": "1"
                        },
                        {
                            "parameter": "descPad",
                            "value": "3"
                        }
                    ],
                    "expected": "StringBuffer remains empty",
                    "description": "Test rendering options with no options provided."
                }
            ]
        },
        {
            "group": "renderOptions with options having no short form",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "sb",
                            "value": "new StringBuffer()"
                        },
                        {
                            "parameter": "width",
                            "value": "74"
                        },
                        {
                            "parameter": "options",
                            "value": "new Options().addOption(new Option(null, \"aaa\", true, \"description\"))"
                        },
                        {
                            "parameter": "leftPad",
                            "value": "1"
                        },
                        {
                            "parameter": "descPad",
                            "value": "3"
                        }
                    ],
                    "expected": "StringBuffer containing formatted option help with long option only",
                    "description": "Test rendering options with an option that has no short form."
                }
            ]
        },
        {
            "group": "renderOptions with options having blank argument name",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "sb",
                            "value": "new StringBuffer()"
                        },
                        {
                            "parameter": "width",
                            "value": "74"
                        },
                        {
                            "parameter": "options",
                            "value": "new Options().addOption(new Option(\"a\", \"aaa\", true, \"description\").setArgName(\"\"))"
                        },
                        {
                            "parameter": "leftPad",
                            "value": "1"
                        },
                        {
                            "parameter": "descPad",
                            "value": "3"
                        }
                    ],
                    "expected": "StringBuffer containing formatted option help with blank argument name",
                    "description": "Test rendering options with an option that has a blank argument name."
                }
            ]
        },
        {
            "group": "renderOptions with multiple options",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "sb",
                            "value": "new StringBuffer()"
                        },
                        {
                            "parameter": "width",
                            "value": "74"
                        },
                        {
                            "parameter": "options",
                            "value": "new Options().addOption(new Option(\"a\", \"aaa\", true, \"description\")).addOption(new Option(\"b\", \"bbb\", false, \"another description\"))"
                        },
                        {
                            "parameter": "leftPad",
                            "value": "1"
                        },
                        {
                            "parameter": "descPad",
                            "value": "3"
                        }
                    ],
                    "expected": "StringBuffer containing formatted option help for multiple options",
                    "description": "Test rendering options with multiple valid options."
                }
            ]
        },
        {
            "group": "renderOptions with custom option comparator",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "sb",
                            "value": "new StringBuffer()"
                        },
                        {
                            "parameter": "width",
                            "value": "74"
                        },
                        {
                            "parameter": "options",
                            "value": "new Options().addOption(new Option(\"b\", \"bbb\", true, \"description\")).addOption(new Option(\"a\", \"aaa\", true, \"another description\"))"
                        },
                        {
                            "parameter": "leftPad",
                            "value": "1"
                        },
                        {
                            "parameter": "descPad",
                            "value": "3"
                        }
                    ],
                    "expected": "StringBuffer containing formatted option help sorted by custom comparator",
                    "description": "Test rendering options with a custom comparator set for sorting."
                }
            ]
        }
    ]
}