{
  "group": "swapPosition Tests",
  "cases": [
    {
      "input": [
        {
          "parameter": "x",
          "value": "Node(key=1, value='A')"
        },
        {
          "parameter": "y",
          "value": "Node(key=2, value='B')"
        },
        {
          "parameter": "dataElement",
          "value": "KEY"
        }
      ],
      "expected": "Nodes x and y should swap positions without being direct parents",
      "description": "Test swapping two nodes that are not direct parent-child. Verify that the nodes swap positions correctly and their parent-child relationships are updated."
    },
    {
      "input": [
        {
          "parameter": "x",
          "value": "Node(key=1, value='A')"
        },
        {
          "parameter": "y",
          "value": "Node(key=1, value='A')"
        },
        {
          "parameter": "dataElement",
          "value": "KEY"
        }
      ],
      "expected": "No change since nodes are identical",
      "description": "Test swapping a node with itself. Expect no changes in the tree structure."
    },
    {
      "input": [
        {
          "parameter": "x",
          "value": "Node(key=1, value='A')"
        },
        {
          "parameter": "y",
          "value": "Node(key=2, value='B')"
        },
        {
          "parameter": "dataElement",
          "value": "VALUE"
        }
      ],
      "expected": "Nodes x and y should swap positions based on value",
      "description": "Test swapping two nodes with different values. Verify that the swap happens based on the VALUE data element."
    },
    {
      "input": [
        {
          "parameter": "x",
          "value": "Node(key=1, value='A')"
        },
        {
          "parameter": "y",
          "value": "Node(key=2, value='B')"
        },
        {
          "parameter": "dataElement",
          "value": "KEY"
        }
      ],
      "expected": "x becomes y's parent",
      "description": "Test case where x is y's parent. Verify that x becomes y's parent correctly and the rest of the relationships are updated accordingly."
    },
    {
      "input": [
        {
          "parameter": "x",
          "value": "Node(key=2, value='B')"
        },
        {
          "parameter": "y",
          "value": "Node(key=1, value='A')"
        },
        {
          "parameter": "dataElement",
          "value": "KEY"
        }
      ],
      "expected": "y becomes x's parent",
      "description": "Test case where y is x's parent. Verify that y becomes x's parent correctly and the rest of the relationships are updated accordingly."
    },
    {
      "input": [
        {
          "parameter": "x",
          "value": "Node(key=1, value='A')"
        },
        {
          "parameter": "y",
          "value": "Node(key=2, value='B')"
        },
        {
          "parameter": "dataElement",
          "value": "KEY"
        }
      ],
      "expected": "Root node changes if x or y is the root",
      "description": "Test scenario where either x or y is the root node. Verify that the root node reference updates correctly."
    }
  ]
}