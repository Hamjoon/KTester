{
    "groups": [
        {
            "group": "encodeBase64URLSafe_nullInput",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": null
                        }
                    ],
                    "expected": null,
                    "description": "When the input byte array is null the method should return null (no exception is thrown)."
                }
            ]
        },
        {
            "group": "encodeBase64URLSafe_emptyArray",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": []
                        }
                    ],
                    "expected": [],
                    "description": "An empty byte array should be encoded to an empty byte array (zero‑length result)."
                }
            ]
        },
        {
            "group": "encodeBase64URLSafe_noPaddingNeeded",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": [97, 110, 121, 32, 99, 97, 114, 110, 97, 108, 32, 112, 108, 101, 97, 115, 117, 114, 101, 46]  }
                    ],
                    "expected": "YW55IGNhcm5hbCBwbGVhc3VyZS4",
                    "description": "Encode the ASCII string \"any carnal pleasure.\". Standard Base64 would end with '=', but URL‑safe mode must omit padding while leaving other characters unchanged."
                }
            ]
        },
        {
            "group": "encodeBase64URLSafe_paddingRemoved",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": [1]
                        }
                    ],
                    "expected": "AQ",
                    "description": "Single‑byte input produces \"AQ==\" in standard Base64; URL‑safe version must drop the two padding characters."
                },
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": [1, 2]
                        }
                    ],
                    "expected": "AQI",
                    "description": "Two‑byte input yields \"AQI=\" in standard Base64; the trailing '=' must be removed in URL‑safe mode."
                }
            ]
        },
        {
            "group": "encodeBase64URLSafe_plusSlashReplacement",
            "cases": [
                {
                    "input": [
                        {
                            "parameter": "binaryData",
                            "value": [251, 239, 255]
                        }
                    ],
                    "expected": "--__",
                    "description": "Byte sequence 0xFB,0xEF,0xFF encodes to \"++//\" in standard Base64. In URL‑safe mode '+' becomes '-', '/' becomes '_' and no padding is added."
                }
            ]
        }
    ]
}