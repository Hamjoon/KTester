Invalid control character at: line 40 column 53 (char 1600)
{
    "test_cases": [
        {
            "group": "md5Crypt(byte[]) - Basic Functionality",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[]{'p','a','s','s'}"
                        }
                    ],
                    "expected": "String matching regex '^\\$1\\$[./0-9A-Za-z]{8}\\$[./0-9A-Za-z]{22}$'",
                    "description": "A typical non‑empty password should be accepted and the returned hash must start with the MD5‑crypt prefix \"$1$\", contain an 8‑character Base64 salt and a 22‑character hash."
                }
            ]
        },
        {
            "group": "md5Crypt(byte[]) - Empty Input",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[0]"
                        }
                    ],
                    "expected": "String matching regex '^\\$1\\$[./0-9A-Za-z]{8}\\$[./0-9A-Za-z]{22}$'",
                    "description": "An empty byte array is a valid key; the method should still produce a correctly formatted MD5‑crypt string."
                }
            ]
        },
        {
            "group": "md5Crypt(byte[]) - Large Input",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[1024] // filled with pseudo‑random data"
                        }
                    ],
                    "expected": "String matching regex '^\\$1\\$[./0-9A-Za-z]{8}\\$[./0-9A-Za-z]{22}$'",
                    "description": "A large password (1 KB) must be processed without throwing exceptions and must still yield a correctly formatted hash."
                }
            ]
        },
        {
            "group": "md5Crypt(byte[]) - Non‑ASCII Bytes",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[]{(byte)0xC3, (byte)0xA9, (byte)0x20, (byte)0xF0, (byte)0x9F, (byte)0x98, (byte)0x81}"
                        }
                    ],
                    "expected": "String matching regex '^\\$1\\$[./0-9A-Za-z]{8}\\$[./0-9A-Za-z]{22}$'",
                    "description": "Byte arrays containing UTF‑8 multibyte sequences (e.g., \"é 😁\") should be handled correctly and produce a valid MD5‑crypt string."
                }
            ]
        },
        {
            "group": "md5Crypt(byte[]) - Salt Randomness",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "new byte[]{'s','a','m','e'}"
                        }
                    ],
                    "expected": "Two successive calls return strings with different 8‑character salts",
                    "description": "Calling md5Crypt with the same key twice should generate different random salts, resulting in different hash strings."
                }
            ]
        },
        {
            "group": "md5Crypt(byte[]) - Input Array Integrity",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "keyBytes",
                            "value": "byte[] original = {'i','n','p','u','t'}; byte[] copy = Arrays.copyOf(original, original.length);"
                        }
                    ],
                    "expected": "After invocation, original array equals copy (unchanged)",
                    "description": "The method must not modify the supplied byte array; the content before and after the call should be identical."
                }
            ]
        }
    ]
}