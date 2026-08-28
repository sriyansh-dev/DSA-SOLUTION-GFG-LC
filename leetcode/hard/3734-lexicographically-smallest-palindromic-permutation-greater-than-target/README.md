# Lexicographically Smallest Palindromic Permutation Greater Than Target

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given two strings `s` and `target`, each of length `n`, consisting of lowercase English letters.

Return the  **lexicographically smallest string**  that is  **both**  a  **palindromic permutation**  of `s` and  **strictly**  greater than `target`. If no such permutation exists, return an empty string.

 

 **Example 1:** 

 **Input:**  s = "baba", target = "abba"

 **Output:**  "baab"

 **Explanation:** 

- The palindromic permutations of s (in lexicographical order) are "abba" and "baab".
- The lexicographically smallest permutation that is strictly greater than target is "baab".

 **Example 2:** 

 **Input:**  s = "baba", target = "bbaa"

 **Output:**  ""

 **Explanation:** 

- The palindromic permutations of s (in lexicographical order) are "abba" and "baab".
- None of them is lexicographically strictly greater than target. Therefore, the answer is "".

 **Example 3:** 

 **Input:**  s = "abc", target = "abb"

 **Output:**  ""

 **Explanation:** 

`s` has no palindromic permutations. Therefore, the answer is `""`.

 **Example 4:** 

 **Input:**  s = "aac", target = "abb"

 **Output:**  "aca"

 **Explanation:** 

- The only palindromic permutation of s is "aca".
- "aca" is strictly greater than target. Therefore, the answer is "aca".

 

 **Constraints:** 

- 1 <= n == s.length == target.length <= 300
- s and target consist of only lowercase English letters.

## Solution

**Language:** C++  
**Runtime:** 162 ms (beats 12.31%)  
**Memory:** 109.9 MB (beats 10.77%)  
**Submitted:** 2026-08-28T16:55:36.217Z  

```cpp
class Solution {
public:
    string lexPalindromicPermutation(string s, string target) {
        int n = s.length();
        // Special case: length of 1
        if (n == 1) {
            return s > target ? s : "";
        }

        // Count the frequency of each character
        vector<int> cnt(26, 0);
        for (char c : s) {
            cnt[c - 'a']++;
        }

        // Check if it can form a palindrome and record the characters with odd
        // occurrences
        string oddChar = "";
        for (int i = 0; i < 26; i++) {
            if (cnt[i] % 2 == 1) {
                // More than one character appears an odd number of times,
                // cannot form a palindrome
                if (oddChar != "") {
                    return "";
                }
                oddChar = string(1, 'a' + i);
            }
            cnt[i] /= 2;  // It takes only half the characters to construct the
                          // left half
        }

        string prefix = "";

        auto check = [&](char c) -> bool {
            string left = prefix;
            left.push_back(c);
            for (int i = 25; i >= 0; i--) {
                left.append(cnt[i], 'a' + i);
            }

            string palindrome = left + oddChar;
            string reversed_left = left;
            reverse(reversed_left.begin(), reversed_left.end());
            palindrome += reversed_left;

            return palindrome > target;
        };

        // Construct the left part of each digit greedily
        for (int i = 0; i < n / 2; i++) {
            bool found = false;
            // Try to place the smallest character in lexicographical order
            for (int j = 0; j < 26; j++) {
                if (cnt[j] == 0) {
                    continue;
                }

                cnt[j]--;
                if (check('a' + j)) {
                    // If the constructed palindrome is greater than target,
                    // choose the character
                    prefix.push_back('a' + j);
                    found = true;
                    break;
                } else {
                    cnt[j]++;  // Not meeting the conditions, reset the counter
                }
            }
            if (!found) {
                return "";  // Cannot construct a palindrome larger than target
            }

            if (prefix[i] >
                target[i]) {  // prefix is already greater than target
                string left = prefix;
                for (int j = 0; j < 26; j++) {
                    left.append(cnt[j], 'a' + j);
                }
                string palindrome = left + oddChar;
                string reversed_left = left;
                reverse(reversed_left.begin(), reversed_left.end());
                palindrome += reversed_left;
                return palindrome;
            }
        }

        // Construct the final palindrome string
        string ans = prefix + oddChar;
        string reversed_prefix = prefix;
        reverse(reversed_prefix.begin(), reversed_prefix.end());
        ans += reversed_prefix;
        return ans;
    }
};

//LeetCode Daily Solution for 28 August

```

---

[View on LeetCode](https://leetcode.com/problems/lexicographically-smallest-palindromic-permutation-greater-than-target/)