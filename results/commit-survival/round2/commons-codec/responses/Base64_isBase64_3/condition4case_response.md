{
    "group": "Base64.isBase64(byte[]) - Valid Base64 characters",
    "cases": [
        {
            "input": [
                {
                    "parameter": "arrayOctet",
                    "value": "TWFu"  		}
            ],
            "expected": true,
            "description": "All bytes are valid Base64 characters (\"Man\" encoded as \"TWFu\"). The method should iterate through the whole array and return true."
        },
        {
            "input": [
                {
                    "parameter": "arrayOctet",
                    "value": "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
                }
            ],
            "expected": true,
            "description": "Array contains every character from the standard Base64 alphabet. The method must recognise each as Base64 and return true."
        }
    ]
}