# Smallest Palindromic Rearrangement I

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given a  **palindromic**  string `s`.

Return the  **lexicographically smallest**  palindromic permutation of `s`.

 

 **Example 1:** 

 **Input:**  s = "z"

 **Output:**  "z"

 **Explanation:** 

A string of only one character is already the lexicographically smallest palindrome.

 **Example 2:** 

 **Input:**  s = "babab"

 **Output:**  "abbba"

 **Explanation:** 

Rearranging `"babab"` → `"abbba"` gives the smallest lexicographic palindrome.

 **Example 3:** 

 **Input:**  s = "daccad"

 **Output:**  "acddca"

 **Explanation:** 

Rearranging `"daccad"` → `"acddca"` gives the smallest lexicographic palindrome.

 

 **Constraints:** 

- 1 <= s.length <= 105
- s consists of lowercase English letters.
- s is guaranteed to be palindromic.

## Solution

**Language:** Java  
**Runtime:** 31 ms (beats 63.05%)  
**Memory:** 48.4 MB (beats 23.69%)  
**Submitted:** 2026-07-28T03:37:49.961Z  

```java
class Solution {
    public String smallestPalindrome(String s) {
        int n = s.length();
        int mid = n / 2;
        char[] chars = s.toCharArray();
        Arrays.sort(chars, 0, mid); // note: mid is not included i.e. [0, mid)
        for (int i = 0; i < mid; i++) {
            chars[n - 1 - i] = chars[i];
        }
        return new String(chars);
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/smallest-palindromic-rearrangement-i/)