# Distinct Subsequences II

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

Given a string s, return  *the number of  **distinct non-empty subsequences**  of*  `s`. Since the answer may be very large, return it  **modulo**  `109 + 7`.

A  **subsequence**  of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., `"ace"` is a subsequence of `"abcde"` while `"aec"` is not.

 

 **Example 1:** 

```
Input: s = "abc"
Output: 7
Explanation: The 7 distinct subsequences are "a", "b", "c", "ab", "ac", "bc", and "abc".

```

 **Example 2:** 

```
Input: s = "aba"
Output: 6
Explanation: The 6 distinct subsequences are "a", "b", "ab", "aa", "ba", and "aba".

```

 **Example 3:** 

```
Input: s = "aaa"
Output: 3
Explanation: The 3 distinct subsequences are "a", "aa" and "aaa".

```

 

 **Constraints:** 

- 1 <= s.length <= 2000
- s consists of lowercase English letters.

## Solution

**Language:** Java  
**Runtime:** 6 ms (beats 43.62%)  
**Memory:** 43.2 MB (beats 70.21%)  
**Submitted:** 2026-09-07T17:09:41.984Z  

```java
class Solution {
    // Modulo value for preventing integer overflow
    private static final int MOD = (int) 1e9 + 7;

    /**
     * Counts the number of distinct non-empty subsequences in string s.
     * Uses dynamic programming where dp[i] represents the count of distinct
     * subsequences ending with character ('a' + i).
     * 
     * @param s the input string
     * @return the number of distinct subsequences modulo 10^9 + 7
     */
    public int distinctSubseqII(String s) {
        // dp[i] stores count of distinct subsequences ending with character ('a' + i)
        int[] dp = new int[26];
      
        // Process each character in the string
        for (int i = 0; i < s.length(); ++i) {
            // Get the character index (0-25 for 'a'-'z')
            int charIndex = s.charAt(i) - 'a';
          
            // Update count for subsequences ending with current character
            // New count = sum of all previous subsequences + 1 (for single character)
            dp[charIndex] = sum(dp) + 1;
        }
      
        // Return total count of all distinct subsequences
        return sum(dp);
    }

    /**
     * Calculates the sum of all elements in the array with modulo operation.
     * 
     * @param arr the input array
     * @return the sum of all elements modulo 10^9 + 7
     */
    private int sum(int[] arr) {
        int total = 0;
      
        // Add each element to the total with modulo to prevent overflow
        for (int value : arr) {
            total = (total + value) % MOD;
        }
      
        return total;
    }
}

```

---

[View on LeetCode](https://leetcode.com/problems/distinct-subsequences-ii/)