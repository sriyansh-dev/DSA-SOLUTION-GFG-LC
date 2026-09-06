# Distinct Subsequences

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

Given two strings s and t, return  *the number of distinct*   ***subsequences** ** of  *s*  which equals *t.

The test cases are generated so that the answer fits on a 32-bit signed integer.

 

 **Example 1:** 

```
Input: s = "rabbbit", t = "rabbit"
Output: 3
Explanation:
As shown below, there are 3 ways you can generate "rabbit" from s.
rabbbit
rabbbit
rabbbit

```

 **Example 2:** 

```
Input: s = "babgbag", t = "bag"
Output: 5
Explanation:
As shown below, there are 5 ways you can generate "bag" from s.
babgbag
babgbag
babgbag
babgbag
babgbag
```

 

 **Constraints:** 

- 1 <= s.length, t.length <= 1000
- s and t consist of English letters.

## Solution

**Language:** Java  
**Runtime:** 19 ms (beats 69.08%)  
**Memory:** 54.3 MB (beats 62.97%)  
**Submitted:** 2026-09-06T13:23:31.323Z  

```java
class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        int[][] f = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; ++i) {
            f[i][0] = 1;
        }
        for (int i = 1; i < m + 1; ++i) {
            for (int j = 1; j < n + 1; ++j) {
                f[i][j] = f[i - 1][j];
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    f[i][j] += f[i - 1][j - 1];
                }
            }
        }
        return f[m][n];
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/distinct-subsequences/)