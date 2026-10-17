{
    "group": "Valid Base64 inputs",
    "cases": [
        {
            "input": [
                {
                    "parameter": "pArray",
                    "value": [65, 81, 73, 68]  
                }
            ],
            "expected": "66051",
            "description": "Decode a standard Base64 string without padding (\"AQID\") which corresponds to the byte sequence {0x01,0x02,0x03}. The resulting BigInteger should be 0x010203 = 66051."
        },
        {
            "input": [
                {
                    "parameter": "pArray",
                    "value": [65, 81, 61, 61]   
                }
            ],
            "expected": "1",
            "description": "Decode a Base64 string with padding (\"AQ==\") representing a single byte {0x01}. The resulting BigInteger should be 1."
        },
        {
            "input": [
                {
                    "parameter": "pArray",
                    "value": [65, 81, 73, 68, 32]   
                }
            ],
            "expected": "66051",
            "description": "Decode a Base64 string that contains whitespace (\"AQID \") – whitespace should be ignored and the result be the same as the plain string."
        },
        {
            "input": [
                {
                    "parameter": "pArray",
                    "value": [65, 81, 13, 10, 73, 68]   
                }
            ],
            "expected": "66051",
            "description": "Decode a Base64 string that contains CRLF line separators (\"AQ\\r\\nID\"). Line separators are ignored, yielding the same integer as the simple case."
        }
    ]
}