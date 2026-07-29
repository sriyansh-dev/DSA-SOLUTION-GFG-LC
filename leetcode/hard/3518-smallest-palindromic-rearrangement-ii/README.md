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
**Runtime:** 13 ms (beats 93.55%)  
**Memory:** 47.2 MB (beats 74.19%)  
**Submitted:** 2026-07-29T09:10:06.828Z  

```java
class Solution {

    long nCr(int n, int r, long k) {
        if (r == 0 || r == n) return 1;
        r = Math.min(r, n - r);
        long result = 1;

        for (int i = 1; i <= r; i++) {
            result = result * (n - r + i) / i;
            if (result >= k) return k;
        }

        return result;
    }
    public String smallestPalindrome(String s, int k) {
        int n = s.length();
        char[] chars = s.toCharArray();
        
        char mid = ' ';
        if (n % 2 == 1) {
            mid = chars[n / 2];
        }

        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            if (n % 2 == 1 && i == n / 2) continue;
            count[chars[i] - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            count[i] /= 2;
        }

        int half = n / 2;
        char[] res = new char[n];
        if (n % 2 == 1) {
            res[half] = mid;
        }

        int totalLetters = half;
        long currentK = k;

        for (int i = 0; i < half; i++) {
            boolean placedCharacter = false;
            
            for (int j = 0; j < 26; j++) {
                if (count[j] > 0) {
                    count[j]--;

                    long ways = 1;
                    int letters = totalLetters - 1;
                    
                    for (int c = 0; c < 26; c++) {
                        if (count[c] > 0) {
                            ways *= nCr(letters, count[c], currentK);
                            if (ways >= currentK) {
                                ways = currentK;
                                break;
                            }
                            letters -= count[c];
                        }
                    }

                    if (ways >= currentK) {
                        res[i] = (char) (j + 'a');
                        res[n - 1 - i] = (char) (j + 'a');
                        totalLetters--;
                        placedCharacter = true;
                        break;
                    }
                    currentK -= ways;
                    count[j]++;
                }
            }
            if (!placedCharacter) return "";
        }
        return new String(res);
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/smallest-palindromic-rearrangement-ii/)