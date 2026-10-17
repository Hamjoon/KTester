{
    "testCases": [
        {
            "group": "Single Byte Edge Cases",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": "[255]"
                        }
                    ],
                    "expected": "_w",
                    "description": "A single byte 0xFF yields standard Base64 \"/w==\"; URL‑safe conversion changes '/' to '_' and strips padding, resulting in \"_w\"."
                },
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": "[250]"
                        }
                    ],
                    "expected": "-g",
                    "description": "A single byte 0xFA yields standard Base64 \"+g==\"; URL‑safe conversion changes '+' to '-' and removes padding, giving \"-g\"."
                },
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": "[255, 255]"
                        }
                    ],
                    "expected": "__8",
                    "description": "Two bytes 0xFF 0xFF produce standard Base64 \"//8=\"; URL‑safe conversion replaces '/' with '_' and drops '=', resulting in \"__8\"."
                }
            ]
        },
        {
            "group": "Input Immutability",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": "[1, 2, 3]"
                        }
                    ],
                    "expected": "original array unchanged",
                    "description": "Calling encodeBase64URLSafeString must not modify the supplied byte array; after the call the array should still contain [1,2,3]."
                }
            ]
        },
        {
            "group": "No Padding Presence",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": "[1, 2, 3, 4]"
                        }
                    ],
                    "expected": "AQIDBA",
                    "description": "Four bytes produce standard Base64 \"AQIDBA==\"; URL‑safe version removes the two padding '=' characters, yielding \"AQIDBA\" which contains no '='."
                }
            ]
        },
        {
            "group": "Large Input Length Verification",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": "[0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31]"
                        }
                    ],
                    "expected": "result length 44",
                    "description": "For 32 sequential bytes the URL‑safe Base64 string should have length ((32+2)/3)*4 = 44 characters and contain no line‑breaks."
                }
            ]
        }
    ]
}