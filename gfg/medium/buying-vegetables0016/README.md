# Minimum Cost Selection

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given an  **n × 3**  matrix  **mat[][]**, where each row represents the costs of three available choices at a shop, select exactly one choice from each row such that the same choice is not selected in two adjacent rows.

Return the minimum total cost required.

 **Examples:** 

```
Input: mat[][] = [[1, 50, 50], [50, 50, 50], [1, 50, 50]]
Output: 52
Explanation: One optimal selection is- Row 1: Choice 1 (Cost = 1), Row 2: Choice 2 (Cost = 50), Row 3: Choice 1 (Cost = 1)
Total cost = 1 + 50 + 1 = 52.
```

```
Input: mat[][] = [[1, 4, 1], [3, 2, 2], [3, 2, 3]]
Output: 5
Explanation: One optimal selection is- Row 1: Choice 1 (Cost = 1), Row 2: Choice 2 (Cost = 2), Row 3: Choice 3 (Cost = 2)
Total cost = 1 + 2 + 2 = 5.
```

## Solution

**Language:** C++  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-28T16:55:13.453Z  

```cpp
class Solution {
  public:
    int dp[100001][4];
    int func(int level,int prev,vector<vector<int>>&mat,int n)
    {
        if(level==n)
        {
            return 0;
        }
        if(dp[level][prev]!=-1)
        {
            return dp[level][prev];
        }
        int ans=INT_MAX;
        for(int i=0;i<=2;i++)
        {
            if(i!=prev)
            {
                int p=func(level+1,i,mat,n);
                if(p!=INT_MAX)
                {
                    ans=min(ans,p+mat[level][i]);
                }
            }
        }
        return dp[level][prev]=ans;
    }
    int minCost(vector<vector<int>>& mat) {
        // code here
        int n=mat.size();
        memset(dp,-1,sizeof(dp));
        int ans=func(0,3,mat,n);
        return ans;
    }
};
//GFG POTD solution for 28 August

```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/buying-vegetables0016/1)