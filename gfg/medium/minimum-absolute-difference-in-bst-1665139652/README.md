# minimum-absolute-difference-in-bst-1665139652

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

_Description not available._

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-09-18T16:52:56.519Z  

```java
/* The Node structure is defined as
 class Node {
    int data;
    Node left;
    Node right;
    Node(int data){
        this.data = data;
        left = null;
        right = null;
    }
}
*/

class Solution {
    int prev = -1;
    int minDiff = Integer.MAX_VALUE;

    public int absDiff(Node root) {
        inorder(root);
        return minDiff;
    }

    private void inorder(Node root) {
        if (root == null) {
            return;
        }

        // Traverse left subtree
        inorder(root.left);

        // Compare current node with previous node
        if (prev != -1) {
            minDiff = Math.min(minDiff, root.data - prev);
        }

        // Update previous value
        prev = root.data;

        // Traverse right subtree
        inorder(root.right);
    }
}

```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/minimum-absolute-difference-in-bst-1665139652/1)