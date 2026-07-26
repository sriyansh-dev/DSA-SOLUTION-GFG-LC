# Complete Binary Tree Traversal with Array Input

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given an integer array  **arr[]**  representing the nodes of a Complete Binary Tree in level order traversal, return the nodes at each level in sorted ascending order.

For every level of the binary tree, sort the values present at that level independently and return the resulting levels as a 2D array, where the i-th row contains the sorted values of the i-th level.

 **Examples:** 

```
Input: arr[] = [7, 6, 5, 4, 3, 2, 1]
Output: [[7], [5, 6], [1, 2, 3, 4]]
Explanation: The complete binary tree formed from the given level order traversal is:   
        
The nodes at each level after sorting are:
Level 0: [7]
Level 1: [5, 6]
Level 2: [1, 2, 3, 4]

```

```
Input: arr[] = [7, 16, 1, 4, 13]
Output: [[7], [1, 16], [4, 13]]
Explanation: The complete binary tree formed from the given level order traversal is:      
           
The nodes at each level after sorting are:
Level 0: [7]
Level 1: [1, 16]
Level 2: [4, 13]
```

## Solution

**Language:** C++  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-07-26T04:27:56.204Z  

```cpp
class Solution {
  public:
    vector<vector<int>> levelSort(vector<int>& arr) {
        // code here
        int n=arr.size();
        vector<vector<int>>ans;
        queue<int>q;
        q.push(0);
        while(!q.empty()){
            int size=q.size();
            vector<int>level;
            while(size>0){
                int ind=q.front();
                q.pop();
                level.push_back(arr[ind]);
                if((2*ind+1)<n)
                    q.push(2*ind+1);
                if((2*ind+2)<n)
                    q.push(2*ind+2);
                size--;    
            }
            sort(level.begin(),level.end());
            ans.push_back(level);
        }
        return ans;
    }
};
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/print-binary-tree-levels-in-sorted-order3241/1)