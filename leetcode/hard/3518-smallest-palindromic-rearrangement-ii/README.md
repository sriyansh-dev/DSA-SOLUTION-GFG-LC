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
**Runtime:** 20 ms (beats 74.19%)  
**Memory:** 47.6 MB (beats 35.48%)  
**Submitted:** 2026-07-29T09:04:40.966Z  

```java
class Solution {

    long nCr(int n, int r, int k) {
        //nCr == nC(n-r)
        //5C3 == 5C2
        //5C2 == 5C(5-2) = 5C3
        r = Math.min(r, n - r); //nCr == nC(n-r)

        long result = 1;

        for (int i = 1; i <= r; i++) { //O(log2(k))
            result = result * (n - r + i) / i; //result is becoming twice

            if (result >= k)
                return k;
        }

        return result;
    }

    public String smallestPalindrome(String s, int k) {
        int n = s.length();

        char mid = ' ';
        if (n % 2 == 1) { //odd length
            mid = s.charAt(n / 2);
        }

        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            if (n % 2 == 1 && i == n / 2) continue; //mid character reserved for middle one
            count[s.charAt(i) - 'a']++;
        }

        //half frequency will be used to build halfResult
        for (int i = 0; i < 26; i++) {
            count[i] /= 2;
        }

        StringBuilder halfResult = new StringBuilder();
        int half = n / 2;

        for (int i = 0; i < half; i++) { //O(n/2)
            //I am trying to fill ith position
            //What if I could never fill a character in ith position
            boolean placedCharacter = false; //in ith position
            for (int j = 0; j < 26; j++) { //which character to put
                if (count[j] > 0) {
                    count[j] -= 1;

                    //count number of ways
                    long ways = 1;
                    int letters = 0;
                    for (int c = 0; c < 26; c++) {
                        letters += count[c];
                    }

                    for (int c = 0; c < 26; c++) {
                        if (count[c] > 0) {
                            ways *= nCr(letters, count[c], k); //log2(k)
                            letters -= count[c];
                        }

                        if (ways >= k) {
                            break;
                        }
                    }

                    if (ways >= k) { //this block contains my kth one
                        halfResult.append((char) (j + 'a')); //fixed this character at ith position
                        placedCharacter = true;
                        break;
                    }

                    k -= ways; //when k >= ways
                    count[j] += 1;
                }
            }

            if (placedCharacter == false)
                return "";
        }

        //halfResult + mid + (reverse of halfResult)
        StringBuilder rev = new StringBuilder(halfResult);
        rev.reverse(); //O(n/2)

        if (mid != ' ') {
            halfResult.append(mid);
        }

        return halfResult.toString() + rev.toString();
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/smallest-palindromic-rearrangement-ii/)