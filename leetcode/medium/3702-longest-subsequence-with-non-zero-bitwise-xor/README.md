# Longest Subsequence With Non-Zero Bitwise XOR

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given an integer array `nums`.

Return the length of the  **longest subsequence**  in `nums` whose bitwise  **XOR**  is  **non-zero**. If no such  **subsequence**  exists, return 0.

 

 **Example 1:** 

 **Input:**  nums = [1,2,3]

 **Output:**  2

 **Explanation:** 

One longest subsequence is `[2, 3]`. The bitwise XOR is computed as `2 XOR 3 = 1`, which is non-zero.

 **Example 2:** 

 **Input:**  nums = [2,3,4]

 **Output:**  3

 **Explanation:** 

The longest subsequence is `[2, 3, 4]`. The bitwise XOR is computed as `2 XOR 3 XOR 4 = 5`, which is non-zero.

 

 **Constraints:** 

- 1 <= nums.length <= 105
- 0 <= nums[i] <= 109

## Solution

**Language:** Java  
**Runtime:** 2 ms (beats 68.38%)  
**Memory:** 133.2 MB (beats 88.24%)  
**Submitted:** 2026-08-15T07:24:24.128Z  

```java
class Solution {
    public int longestSubsequence(int[] nums) {
        int n = nums.length;
        int totalXor = 0;
        boolean allZeros = true;

        for (int num : nums) {
            totalXor ^= num;
            if (num != 0) {
                allZeros = false;
            }
        }

        // If the array contains only zeros, no non-zero XOR subsequence can be formed.
        if (allZeros) {
            return 0;
        }

        // If the total XOR sum of all elements is already non-zero, 
        // the longest subsequence is the entire array itself.
        if (totalXor != 0) {
            return n;
        }

        // If the total XOR is zero (and elements are not all zero), 
        // removing exactly one non-zero element will break the zero-balance, 
        // making the XOR sum of the remaining (n - 1) elements non-zero.
        return n - 1;
    }
}

```

---

[View on LeetCode](https://leetcode.com/problems/longest-subsequence-with-non-zero-bitwise-xor/)