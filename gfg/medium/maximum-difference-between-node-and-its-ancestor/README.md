# Node and Ancestor Max Diff

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given the root of a binary tree, find the maximum difference between an ancestor node A and its descendant node B, i.e., maximize A - B.

 **Examples :** 

```
Input: root[] = [5, 2, 1] 

Output: 4
Explanation: The maximum difference we can get is 4, which is between 5 and 1.
```

```
Input: root[] = [1, 2, 3, N, N, N, 7] 

Output: -1
Explanation: The maximum difference we can get is -1, which is between 1 and 2.
```

## Solution

**Language:** C++  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-20T17:34:10.956Z  

```cpp
/* Structure of Binary Tree Node
class Node {
  public:
    int data;
    Node *left;
    Node *right;
    Node(int val) {
        data = val;
        left = right = nullptr;
    }
};*/

class Solution {
  public:
    int ans = INT_MIN;
    int solve(Node* root){
        if(!root) return INT_MAX;
        
        int leftMin = solve(root->left);
        int rightMin = solve(root->right);
        
        int minVal = min(leftMin,rightMin);
        ans = max(ans,root->data-minVal);
        
        return min(root->data,min(leftMin,rightMin));
    }
    int maxDiff(Node* root) {
        // code here
        solve(root);
        return ans;
    }
};

```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/maximum-difference-between-node-and-its-ancestor/1)