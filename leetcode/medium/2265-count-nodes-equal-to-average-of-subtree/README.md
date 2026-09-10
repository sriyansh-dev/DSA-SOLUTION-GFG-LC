# Count Nodes Equal to Average of Subtree

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given the `root` of a binary tree, return  *the number of nodes where the value of the node is equal to the  **average**  of the values in its  **subtree***.

 **Note:** 

- The average of n elements is the sum of the n elements divided by n and rounded down to the nearest integer.
- A subtree of root is a tree consisting of root and all of its descendants.

 

 **Example 1:** 

```
Input: root = [4,8,5,0,1,null,6]
Output: 5
Explanation: 
For the node with value 4: The average of its subtree is (4 + 8 + 5 + 0 + 1 + 6) / 6 = 24 / 6 = 4.
For the node with value 5: The average of its subtree is (5 + 6) / 2 = 11 / 2 = 5.
For the node with value 0: The average of its subtree is 0 / 1 = 0.
For the node with value 1: The average of its subtree is 1 / 1 = 1.
For the node with value 6: The average of its subtree is 6 / 1 = 6.

```

 **Example 2:** 

```
Input: root = [1]
Output: 1
Explanation: For the node with value 1: The average of its subtree is 1 / 1 = 1.

```

 

 **Constraints:** 

- The number of nodes in the tree is in the range [1, 1000].
- 0 <= Node.val <= 1000

## Solution

**Language:** Java  
**Runtime:** 0 ms (beats 100.00%)  
**Memory:** 45.7 MB (beats 24.23%)  
**Submitted:** 2026-09-10T17:48:42.997Z  

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int nodeCount;

    /**
     * Counts the number of nodes where the node's value equals 
     * the average value of all nodes in its subtree (including itself).
     * 
     * @param root The root of the binary tree
     * @return The count of nodes meeting the criteria
     */
    public int averageOfSubtree(TreeNode root) {
        nodeCount = 0;
        dfs(root);
        return nodeCount;
    }

    /**
     * Performs depth-first search to calculate sum and count of nodes in each subtree.
     * 
     * @param root The current node being processed
     * @return An array where index 0 contains the sum of values in the subtree,
     *         and index 1 contains the count of nodes in the subtree
     */
    private int[] dfs(TreeNode root) {
        // Base case: null node contributes 0 sum and 0 count
        if (root == null) {
            return new int[]{0, 0};
        }
      
        // Recursively process left subtree
        int[] leftSubtree = dfs(root.left);
      
        // Recursively process right subtree
        int[] rightSubtree = dfs(root.right);
      
        // Calculate total sum of current subtree (left + right + current node)
        int subtreeSum = leftSubtree[0] + rightSubtree[0] + root.val;
      
        // Calculate total count of nodes in current subtree
        int subtreeNodeCount = leftSubtree[1] + rightSubtree[1] + 1;
      
        // Check if average of subtree equals current node's value
        // Using integer division as per problem requirements
        if (subtreeSum / subtreeNodeCount == root.val) {
            nodeCount++;
        }
      
        // Return sum and count for parent node's calculation
        return new int[]{subtreeSum, subtreeNodeCount};
    }
}

```

---

[View on LeetCode](https://leetcode.com/problems/count-nodes-equal-to-average-of-subtree/)