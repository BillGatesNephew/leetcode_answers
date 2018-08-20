Minimum Height Trees | Leetcode Problem #310
===
## Problem Statement:
For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.

## Problem Input(s):
- **int n** - Number of nodes appearing in the input graph. Nodes are labeled from 0 to n - 1.
- **int[][] edges** - An array of the edges of the graph. No edge will appear twice; [0,1] and [1,0] would not both be in edges.

## Examples:

### Example 1: 
```
Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3 

Output: [1]
```

### Example 2:
```
Input: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5 

Output: [3, 4]
```



