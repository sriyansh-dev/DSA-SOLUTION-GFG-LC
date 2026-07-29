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
**Runtime:** 7 ms (beats 96.77%)  
**Memory:** 47.1 MB (beats 74.19%)  
**Submitted:** 2026-07-29T09:38:45.440Z  

```java
class Solution {

    long nCr(int n, int r, long k) {
        if (r == 0 || r == n) return 1;
        r = Math.min(r, n - r);
        if (r == 1) return Math.min((long) n, k);
        if (r == 2) return Math.min((long) n * (n - 1) / 2, k);

        long result = 1;
        for (int i = 1; i <= r; i++) {
            result = result * (n - r + i) / i;
            if (result >= k) return k;
        }

        return result;
    }

    public String smallestPalindrome(String s, int k) {
        int n = s.length();
        int[] count = new int[26];

        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'a']++;
        }

        int half = n / 2;
        char[] res = new char[n];

        if (n % 2 == 1) {
            char mid = s.charAt(half);
            res[half] = mid;
            count[mid - 'a']--;
        }

        int[] active = new int[26];
        int activeCount = 0;
        for (int i = 0; i < 26; i++) {
            count[i] /= 2;
            if (count[i] > 0) {
                active[activeCount++] = i;
            }
        }

        long currentK = k;
        int i = 0;

        while (i < half) {
            if (currentK == 1) {
                for (int jIdx = 0; jIdx < activeCount; jIdx++) {
                    int j = active[jIdx];
                    while (count[j] > 0) {
                        res[i] = (char) (j + 'a');
                        res[n - 1 - i] = (char) (j + 'a');
                        i++;
                        count[j]--;
                    }
                }
                break;
            }

            boolean placedCharacter = false;

            for (int jIdx = 0; jIdx < activeCount; jIdx++) {
                int j = active[jIdx];
                count[j]--;

                long ways = 1;
                int letters = half - i - 1;

                for (int cIdx = 0; cIdx < activeCount; cIdx++) {
                    int c = active[cIdx];
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
                    i++;
                    placedCharacter = true;

                    if (count[j] == 0) {
                        for (int shift = jIdx; shift < activeCount - 1; shift++) {
                            active[shift] = active[shift + 1];
                        }
                        activeCount--;
                    }
                    break;
                }
                currentK -= ways;
                count[j]++;
            }
            if (!placedCharacter) return "";
        }
        return new String(res);
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/smallest-palindromic-rearrangement-ii/)