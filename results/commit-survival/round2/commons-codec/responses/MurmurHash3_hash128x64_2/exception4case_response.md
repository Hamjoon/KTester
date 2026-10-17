{
    "group": "Offset/length overflow checks",
    "cases": [
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "new byte[10]"
                },
                {
                    "parameter": "offset",
                    "value": "2147483647"
                },
                {
                    "parameter": "length",
                    "value": "1"
                },
                {
                    "parameter": "seed",
                    "value": "0"
                }
            ],
            "expected": "IndexOutOfBoundsException",
            "description": "When offset + length overflows the int range (Integer.MAX_VALUE + 1 wraps to negative), the method must detect the illegal range and throw IndexOutOfBoundsException."
        },
        {
            "input": [
                {
                    "parameter": "data",
                    "value": "new byte[10]"
                },
                {
                    "parameter": "offset",
                    "value": "2147483646"
                },
                {
                    "parameter": "length",
                    "value": "3"
                },
                {
                    "parameter": "seed",
                    "value": "0"
                }
            ],
            "expected": "IndexOutOfBoundsException",
            "description": "offset (Integer.MAX_VALUE‑1) plus length (3) overflows and becomes negative; the method must treat this as out‑of‑bounds and throw IndexOutOfBoundsException."
        }
    ]
}