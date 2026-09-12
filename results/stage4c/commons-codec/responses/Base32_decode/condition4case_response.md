{
  "group": "Base32 decode method tests",
  "cases": [
    {
      "input": [
        {
          "parameter": "input",
          "value": "[]"
        },
        {
          "parameter": "inPos",
          "value": "0"
        },
        {
          "parameter": "inAvail",
          "value": "-1"
        },
        {
          "parameter": "context",
          "value": "new Context()"
        }
      ],
      "expected": "context.eof = true",
      "description": "Tests handling of negative inAvail, setting context.eof to true."
    },
    {
      "input": [
        {
          "parameter": "input",
          "value": "[61]" 	}
        ,
        {
          "parameter": "inPos",
          "value": "0"
        },
        {
          "parameter": "inAvail",
          "value": "1"
        },
        {
          "parameter": "context",
          "value": "new Context()"
        }
      ],
      "expected": "context.eof = true",
      "description": "Tests handling of padding character '=', setting context.eof to true."
    },
    {
      "input": [
        {
          "parameter": "input",
          "value": "[32]"
        },
        {
          "parameter": "inPos",
          "value": "0"
        },
        {
          "parameter": "inAvail",
          "value": "1"
        },
        {
          "parameter": "context",
          "value": "new Context()"
        }
      ],
      "expected": "context.modulus = 1, context.lbitWorkArea = result",
      "description": "Tests valid byte input affecting context.modulus and context.lbitWorkArea."
    },
    {
      "input": [
        {
          "parameter": "input",
          "value": "[32, 32, 32, 32, 32, 32, 32, 32]"
        },
        {
          "parameter": "inPos",
          "value": "0"
        },
        {
          "parameter": "inAvail",
          "value": "8"
        },
        {
          "parameter": "context",
          "value": "new Context()"
        }
      ],
      "expected": "5 bytes decoded and stored in buffer",
      "description": "Tests complete decoding of 8 valid bytes, producing 5 decoded bytes."
    },
    {
      "input": [
        {
          "parameter": "input",
          "value": "[32, 32, 32, 32, 32, 32, 32]"
        },
        {
          "parameter": "inPos",
          "value": "0"
        },
        {
          "parameter": "inAvail",
          "value": "7"
        },
        {
          "parameter": "context",
          "value": "new Context()"
        }
      ],
      "expected": "validateCharacter(MASK_3BITS, context) called",
      "description": "Tests EOF handling with a modulus of 7, invoking validateCharacter with MASK_3BITS."
    },
    {
      "input": [
        {
          "parameter": "input",
          "value": "[32, 32, 32, 32, 32, 32]"
        },
        {
          "parameter": "inPos",
          "value": "0"
        },
        {
          "parameter": "inAvail",
          "value": "6"
        },
        {
          "parameter": "context",
          "value": "new Context()"
        }
      ],
      "expected": "validateTrailingCharacters() called",
      "description": "Tests EOF handling with a modulus of 6, invoking validateTrailingCharacters."
    },
    {
      "input": [
        {
          "parameter": "input",
          "value": "[32, 32, 32, 32, 32]"
        },
        {
          "parameter": "inPos",
          "value": "0"
        },
        {
          "parameter": "inAvail",
          "value": "5"
        },
        {
          "parameter": "context",
          "value": "new Context()"
        }
      ],
      "expected": "validateCharacter(MASK_1BITS, context) called",
      "description": "Tests EOF handling with a modulus of 5, invoking validateCharacter with MASK_1BITS."
    },
    {
      "input": [
        {
          "parameter": "input",
          "value": "[32, 32, 32, 32]"
        },
        {
          "parameter": "inPos",
          "value": "0"
        },
        {
          "parameter": "inAvail",
          "value": "4"
        },
        {
          "parameter": "context",
          "value": "new Context()"
        }
      ],
      "expected": "validateCharacter(MASK_4BITS, context) called",
      "description": "Tests EOF handling with a modulus of 4, invoking validateCharacter with MASK_4BITS."
    },
    {
      "input": [
        {
          "parameter": "input",
          "value": "[32, 32, 32]"
        },
        {
          "parameter": "inPos",
          "value": "0"
        },
        {
          "parameter": "inAvail",
          "value": "3"
        },
        {
          "parameter": "context",
          "value": "new Context()"
        }
      ],
      "expected": "validateTrailingCharacters() called",
      "description": "Tests EOF handling with a modulus of 3, invoking validateTrailingCharacters."
    },
    {
      "input": [
        {
          "parameter": "input",
          "value": "[32, 32]"
        },
        {
          "parameter": "inPos",
          "value": "0"
        },
        {
          "parameter": "inAvail",
          "value": "2"
        },
        {
          "parameter": "context",
          "value": "new Context()"
        }
      ],
      "expected": "validateCharacter(MASK_2BITS, context) called",
      "description": "Tests EOF handling with a modulus of 2, invoking validateCharacter with MASK_2BITS."
    },
    {
      "input": [
        {
          "parameter": "input",
          "value": "[32]"
        },
        {
          "parameter": "inPos",
          "value": "0"
        },
        {
          "parameter": "inAvail",
          "value": "1"
        },
        {
          "parameter": "context",
          "value": "new Context()"
        }
      ],
      "expected": "validateTrailingCharacters() called",
      "description": "Tests EOF handling with a modulus of 1, invoking validateTrailingCharacters."
    }
  ]
}