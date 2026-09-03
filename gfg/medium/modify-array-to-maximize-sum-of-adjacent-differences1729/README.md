# Max Adjacent Diffs Sum with 1 Replacements

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given an integer array  **arr[]**, you are allowed to replace any elements with 1.  Find the maximum sum of absolute differences between consecutive elements after any number of modifications.

 **Examples:** 

```
Input: arr[] = [3, 2, 1, 4, 5]
Output: 8
Explanation: Modify the array as arr[] = [3, 1, 1, 4, 1]. 
Sum = |1-3| + |1-1| + |4-1| + |1-4| = 8, the maximum possible.
```

```
Input: arr[] = [1, 5]
Output: 4
Explanation: No modification needed. Sum = |5-1| = 4.
```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-09-03T17:14:54.111Z  

```java
class Solution {
    public int maxDiffSum(int[] arr) {
        int n = arr.length;
        if (n == 1) return 0;
        int dp0 = 0, dp1 = 0;

        for (int i = 1; i < n; i++) {
            int ndp0 = Math.max(dp0 + Math.abs(arr[i] - arr[i - 1]),
                                dp1 + Math.abs(arr[i] - 1));
            int ndp1 = Math.max(dp0 + Math.abs(1 - arr[i - 1]),
                                dp1);
            dp0 = ndp0;
            dp1 = ndp1;
        }

        return Math.max(dp0, dp1);
    }
}
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/modify-array-to-maximize-sum-of-adjacent-differences1729/1)