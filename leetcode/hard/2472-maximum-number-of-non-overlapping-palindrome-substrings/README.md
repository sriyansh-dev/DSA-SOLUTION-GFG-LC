# Maximum Number of Non-overlapping Palindrome Substrings

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given a string `s` and a  **positive**  integer `k`.

Select a set of  **non-overlapping**  substrings from the string `s` that satisfy the following conditions:

- The length of each substring is at least k.
- Each substring is a palindrome.

Return  *the  **maximum**  number of substrings in an optimal selection*.

A  **substring**  is a contiguous sequence of characters within a string.

 

 **Example 1:** 

```
Input: s = "abaccdbbd", k = 3
Output: 2
Explanation: We can select the substrings underlined in s = "abaccdbbd". Both "aba" and "dbbd" are palindromes and have a length of at least k = 3.
It can be shown that we cannot find a selection with more than two valid substrings.

```

 **Example 2:** 

```
Input: s = "adbcda", k = 2
Output: 0
Explanation: There is no palindrome substring of length at least 2 in the string.

```

 

 **Constraints:** 

- 1 <= k <= s.length <= 2000
- s consists of lowercase English letters.

## Solution

**Language:** Java  
**Runtime:** 153 ms (beats 15.08%)  
**Memory:** 62.3 MB (beats 16.08%)  
**Submitted:** 2026-09-15T18:15:27.719Z  

```java
class Solution {
    private int[] memo;
    private boolean[][] isPalindrome;
    private String s;
    private int n;
    private int k;

    public int maxPalindromes(String s, int k) {
        this.n = s.length();
        this.s = s;
        this.k = k;
      
        this.memo = new int[n];
        Arrays.fill(memo, -1);
      
        this.isPalindrome = new boolean[n][n];
      
        for (int i = 0; i < n; i++) {
            Arrays.fill(isPalindrome[i], true);
        }
      
        
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                isPalindrome[i][j] = (s.charAt(i) == s.charAt(j)) && isPalindrome[i + 1][j - 1];
            }
        }
      
        return dfs(0);
    }

  
    private int dfs(int i) {
        if (i >= n) {
            return 0;
        }
              if (memo[i] != -1) {
            return memo[i];
        }
      
        int maxPalindromes = dfs(i + 1);
      
        for (int j = i + k - 1; j < n; j++) {
            if (isPalindrome[i][j]) {
                maxPalindromes = Math.max(maxPalindromes, 1 + dfs(j + 1));
            }
        }
        memo[i] = maxPalindromes;
        return maxPalindromes;
    }
}

```

---

[View on LeetCode](https://leetcode.com/problems/maximum-number-of-non-overlapping-palindrome-substrings/)