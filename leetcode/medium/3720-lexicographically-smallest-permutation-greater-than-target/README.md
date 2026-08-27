# Lexicographically Smallest Permutation Greater Than Target

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given two strings `s` and `target`, both having length `n`, consisting of lowercase English letters.

Return the  **lexicographically smallest permutation**  of `s` that is  **strictly**  greater than `target`. If no permutation of `s` is lexicographically strictly greater than `target`, return an empty string.

A string `a` is  **lexicographically strictly greater** than a string `b` (of the same length) if in the first position where `a` and `b` differ, string `a` has a letter that appears later in the alphabet than the corresponding letter in `b`.

 

 **Example 1:** 

 **Input:**  s = "abc", target = "bba"

 **Output:**  "bca"

 **Explanation:** 

- The permutations of s (in lexicographical order) are "abc", "acb", "bac", "bca", "cab", and "cba".
- The lexicographically smallest permutation that is strictly greater than target is "bca".

 **Example 2:** 

 **Input:**  s = "leet", target = "code"

 **Output:**  "eelt"

 **Explanation:** 

- The permutations of s (in lexicographical order) are "eelt", "eetl", "elet", "elte", "etel", "etle", "leet", "lete", "ltee", "teel", "tele", and "tlee".
- The lexicographically smallest permutation that is strictly greater than target is "eelt".

 **Example 3:** 

 **Input:**  s = "baba", target = "bbaa"

 **Output:**  ""

 **Explanation:** 

- The permutations of s (in lexicographical order) are "aabb", "abab", "abba", "baab", "baba", and "bbaa".
- None of them is lexicographically strictly greater than target. Therefore, the answer is "".

 

 **Constraints:** 

- 1 <= s.length == target.length <= 300
- s and target consist of only lowercase English letters.

## Solution

**Language:** C++  
**Runtime:** 16 ms (beats 22.93%)  
**Memory:** 18.9 MB (beats 21.66%)  
**Submitted:** 2026-08-27T07:50:27.562Z  

```cpp
class Solution {
public:
    string lexGreaterPermutation(string s, string target) {
        vector<int> cnt(26, 0);
        for (char c : s) {
            cnt[c - 'a']++;
        }

        string res;
        int n = target.size();
        for (int i = 0; i < n; i++) {
            int targetChar = target[i] - 'a';

            // Case 1: First try to place the same character as target[i] at the
            // current position
            if (cnt[targetChar] > 0) {
                cnt[targetChar]--;
                // Check if the remaining characters can form a string greater
                // than target[i+1:]
                if (canFormGreater(cnt, target, i + 1)) {
                    res.push_back(target[i]);
                    continue;
                }
                // Cannot form a larger string, backtrack
                cnt[targetChar]++;
            }

            // Case 2: Place a character greater than target[i] at the current
            // position
            for (int j = targetChar + 1; j < 26; j++) {
                if (cnt[j] > 0) {
                    cnt[j]--;
                    res.push_back('a' + j);
                    // Fill remaining positions with the smallest
                    // lexicographical order
                    res += getMinString(cnt);
                    return res;
                }
            }

            // No feasible solution found, return directly
            return "";
        }

        return "";
    }

private:
    // Check if the remaining characters can form a string greater than the
    // suffix.
    bool canFormGreater(const vector<int>& cnt, const string& target,
                        int start) {
        string maxStr = getMaxString(cnt);
        string suffix = target.substr(start);
        return maxStr > suffix;
    }

    // Get the maximum lexicographical string (in descending order)
    string getMaxString(const vector<int>& cnt) {
        string res;
        for (int i = 25; i >= 0; i--) {
            res.append(cnt[i], 'a' + i);
        }
        return res;
    }

    // Get the lexicographically smallest string (in ascending order)
    string getMinString(const vector<int>& cnt) {
        string res;
        for (int i = 0; i < 26; i++) {
            res.append(cnt[i], 'a' + i);
        }
        return res;
    }
};

//LeetCode Daily Solution for 27 August

```

---

[View on LeetCode](https://leetcode.com/problems/lexicographically-smallest-permutation-greater-than-target/)