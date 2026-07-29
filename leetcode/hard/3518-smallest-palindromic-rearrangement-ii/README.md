# Smallest Palindromic Rearrangement II

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given a  **palindromic**  string `s` and an integer `k`.

Return the  **k-th**   **lexicographically smallest**  palindromic permutation of `s`. If there are fewer than `k` distinct palindromic permutations, return an empty string.

 **Note:**  Different rearrangements that yield the same palindromic string are considered identical and are counted once.

 

 **Example 1:** 

 **Input:**  s = "abba", k = 2

 **Output:**  "baab"

 **Explanation:** 

- The two distinct palindromic rearrangements of "abba" are "abba" and "baab".
- Lexicographically, "abba" comes before "baab". Since k = 2, the output is "baab".

 **Example 2:** 

 **Input:**  s = "aa", k = 2

 **Output:**  ""

 **Explanation:** 

- There is only one palindromic rearrangement: "aa".
- The output is an empty string since k = 2 exceeds the number of possible rearrangements.

 **Example 3:** 

 **Input:**  s = "bacab", k = 1

 **Output:**  "abcba"

 **Explanation:** 

- The two distinct palindromic rearrangements of "bacab" are "abcba" and "bacab".
- Lexicographically, "abcba" comes before "bacab". Since k = 1, the output is "abcba".

 

 **Constraints:** 

- 1 <= s.length <= 104
- s consists of lowercase English letters.
- s is guaranteed to be palindromic.
- 1 <= k <= 106

## Solution

**Language:** Java  
**Runtime:** 19 ms (beats 74.19%)  
**Memory:** 47.6 MB (beats 35.48%)  
**Submitted:** 2026-07-29T09:09:37.639Z  

```java
class Solution {
    long nCr(int n, int r, long k) {
        if (r > n) return 0;
        r = Math.min(r, n - r);
        if (r == 0) return 1;
        
        long result = 1;
        // Early termination to avoid overflow
        for (int i = 0; i < r; i++) {
            result = result * (n - i) / (i + 1);
            if (result >= k) return k; // Early exit
        }
        return result;
    }
    
    public String smallestPalindrome(String s, int k) {
        int n = s.length();
        char mid = ' ';
        if (n % 2 == 1) {
            mid = s.charAt(n / 2);
        }
        
        // Count character frequencies (exclude middle if odd)
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            if (n % 2 == 1 && i == n / 2) continue;
            count[s.charAt(i) - 'a']++;
        }
        
        // Divide by 2 to get half counts
        for (int i = 0; i < 26; i++) {
            count[i] /= 2;
        }
        
        StringBuilder halfResult = new StringBuilder();
        int half = n / 2;
        
        for (int i = 0; i < half; i++) {
            boolean placed = false;
            
            // Calculate total remaining letters once per position
            int totalLetters = 0;
            for (int c = 0; c < 26; c++) {
                totalLetters += count[c];
            }
            
            for (int j = 0; j < 26; j++) {
                if (count[j] > 0) {
                    count[j]--;
                    
                    // Calculate ways more efficiently
                    long ways = 1;
                    int remaining = totalLetters - 1;
                    
                    for (int c = 0; c < 26; c++) {
                        if (count[c] > 0) {
                            ways *= nCr(remaining, count[c], k);
                            remaining -= count[c];
                            
                            if (ways >= k) break; // Early exit
                        }
                    }
                    
                    if (ways >= k) {
                        halfResult.append((char) (j + 'a'));
                        placed = true;
                        break;
                    }
                    
                    k -= ways;
                    count[j]++;
                }
            }
            
            if (!placed) return "";
        }
        
        StringBuilder rev = new StringBuilder(halfResult);
        rev.reverse();
        
        if (mid != ' ') {
            halfResult.append(mid);
        }
        
        return halfResult.toString() + rev.toString();
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/smallest-palindromic-rearrangement-ii/)