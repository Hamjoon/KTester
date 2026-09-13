{
  "group": "processPrunableElement",
  "cases": [
    {
      "input": [
        {
          "parameter": "factory",
          "value": "MockJDOMFactory"
        },
        {
          "parameter": "reader",
          "value": "MockXMLStreamReader with START_ELEMENT and multiple nested START_ELEMENT and END_ELEMENT events"
        },
        {
          "parameter": "topdepth",
          "value": 0
        },
        {
          "parameter": "filter",
          "value": "MockStAXFilter that prunes selectively based on depth"
        }
      ],
      "expected": "Element with selectively pruned nested structure",
      "description": "Test case where nested elements are selectively pruned based on depth, ensuring only certain levels of elements are included in the final structure."
    },
    {
      "input": [
        {
          "parameter": "factory",
          "value": "MockJDOMFactory"
        },
        {
          "parameter": "reader",
          "value": "MockXMLStreamReader with START_ELEMENT and multiple CHARACTERS and SPACE events"
        },
        {
          "parameter": "topdepth",
          "value": 0
        },
        {
          "parameter": "filter",
          "value": "MockStAXFilter that prunes SPACE but not CHARACTERS"
        }
      ],
      "expected": "Element with text content from CHARACTERS events only",
      "description": "Test case where SPACE events are pruned but CHARACTERS are not, verifying that only CHARACTERS text is added to the element."
    },
    {
      "input": [
        {
          "parameter": "factory",
          "value": "MockJDOMFactory"
        },
        {
          "parameter": "reader",
          "value": "MockXMLStreamReader with START_ELEMENT and nested ENTITY_REFERENCE and CDATA events"
        },
        {
          "parameter": "topdepth",
          "value": 0
        },
        {
          "parameter": "filter",
          "value": "MockStAXFilter that prunes ENTITY_REFERENCE but not CDATA"
        }
      ],
      "expected": "Element with CDATA content but without entity references",
      "description": "Test case where ENTITY_REFERENCE events are pruned but CDATA events are not, ensuring only CDATA content is included."
    },
    {
      "input": [
        {
          "parameter": "factory",
          "value": "MockJDOMFactory"
        },
        {
          "parameter": "reader",
          "value": "MockXMLStreamReader with START_ELEMENT and COMMENT events with varying depth"
        },
        {
          "parameter": "topdepth",
          "value": 0
        },
        {
          "parameter": "filter",
          "value": "MockStAXFilter that prunes comments based on depth"
        }
      ],
      "expected": "Element with comments pruned at certain depths",
      "description": "Test case where comments are pruned based on the depth of the element, ensuring comments are only included at specific levels."
    },
    {
      "input": [
        {
          "parameter": "factory",
          "value": "MockJDOMFactory"
        },
        {
          "parameter": "reader",
          "value": "MockXMLStreamReader with START_ELEMENT and nested PROCESSING_INSTRUCTION events"
        },
        {
          "parameter": "topdepth",
          "value": 0
        },
        {
          "parameter": "filter",
          "value": "MockStAXFilter that prunes PROCESSING_INSTRUCTION selectively"
        }
      ],
      "expected": "Element with selectively included processing instructions",
      "description": "Test case where processing instructions are selectively pruned based on criteria, verifying only specific instructions are included in the element."
    }
  ]
}