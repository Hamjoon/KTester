{
    "group": "javaNameToYamlName method",
    "cases": [
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "simple"
                }
            ],
            "expected": "simple",
            "description": "Test with a lowercase string that should remain unchanged as no uppercase letters are present."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "Simple"
                }
            ],
            "expected": "simple",
            "description": "Test with a string that starts with an uppercase letter. It should convert the first letter to lowercase."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "simpleTest"
                }
            ],
            "expected": "simple_test",
            "description": "Test with a camel case string where only the first letter of the second word is uppercase. It should insert an underscore and convert the uppercase letter to lowercase."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "SimpleTest"
                }
            ],
            "expected": "simple_test",
            "description": "Test with a string where both words start with uppercase letters. It should convert the first letter to lowercase and insert an underscore before the second word."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "SIMPLETest"
                }
            ],
            "expected": "s_i_m_p_l_e_test",
            "description": "Test with a string where the first word is in all uppercase letters. It should convert each uppercase letter to lowercase and insert underscores between them."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "simpleTEST"
                }
            ],
            "expected": "simple_t_e_s_t",
            "description": "Test with a string where the second word is in all uppercase letters. It should convert each uppercase letter to lowercase and insert underscores between them."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "SIMPLE"
                }
            ],
            "expected": "s_i_m_p_l_e",
            "description": "Test with a string that is entirely in uppercase letters. It should convert each letter to lowercase and insert underscores between them."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": ""
                }
            ],
            "expected": "",
            "description": "Test with an empty string. It should return an empty string as there are no characters to process."
        },
        {
            "input": [
                {
                    "parameter": "expression",
                    "value": "already_yaml"
                }
            ],
            "expected": "already_yaml",
            "description": "Test with a string that is already in YAML format (lowercase with underscores). It should remain unchanged."
        }
    ]
}