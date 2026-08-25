# morning-assembly3038

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

_Description not available._

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-25T17:21:51.262Z  

```java
class Solution {
    public int minMoves(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n + 1];
        int maxLen = 0;

        for (int num : arr) {
            dp[num] = dp[num - 1] + 1;
            maxLen = Math.max(maxLen, dp[num]);
        }

        return n - maxLen;
    }
}
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/morning-assembly3038/1)