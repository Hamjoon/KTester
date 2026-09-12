{
    "group": "javaNameToYamlName method",
    "cases": [
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "SimpleXML"
                }
            ],
            "expected": "simple_x_m_l",
            "description": "Test with a string where multiple consecutive uppercase letters are present at the end of the string. It should convert each uppercase letter to lowercase and insert underscores between them."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "XMLSimple"
                }
            ],
            "expected": "x_m_l_simple",
            "description": "Test with a string where multiple consecutive uppercase letters are at the beginning of the string. It should convert each uppercase letter to lowercase and insert underscores between them."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "SimpleXMLParser"
                }
            ],
            "expected": "simple_x_m_l_parser",
            "description": "Test with a string where multiple uppercase letters are interspersed with lowercase letters. It should convert each uppercase letter to lowercase and insert underscores between them."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "XMLParserSimple"
                }
            ],
            "expected": "x_m_l_parser_simple",
            "description": "Test with a string where multiple uppercase letters are at the beginning followed by a word and then another word starting with an uppercase letter. It should convert each uppercase letter to lowercase and insert underscores between them."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "simpleXMLParser"
                }
            ],
            "expected": "simple_x_m_l_parser",
            "description": "Test with a camel case string where a word in the middle is in all uppercase. It should convert each uppercase letter to lowercase and insert underscores between them."
        }
    ]
}