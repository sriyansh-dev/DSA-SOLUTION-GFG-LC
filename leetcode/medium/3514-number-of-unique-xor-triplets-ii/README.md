# Number of Unique XOR Triplets II

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given an integer array `nums`.

A  **XOR triplet**  is defined as the XOR of three elements `nums[i] XOR nums[j] XOR nums[k]` where `i <= j <= k`.

Return the number of  **unique**  XOR triplet values from all possible triplets `(i, j, k)`.

 

 **Example 1:** 

 **Input:**  nums = [1,3]

 **Output:**  2

 **Explanation:** 

The possible XOR triplet values are:

- (0, 0, 0) → 1 XOR 1 XOR 1 = 1
- (0, 0, 1) → 1 XOR 1 XOR 3 = 3
- (0, 1, 1) → 1 XOR 3 XOR 3 = 1
- (1, 1, 1) → 3 XOR 3 XOR 3 = 3

The unique XOR values are `{1, 3}`. Thus, the output is 2.

 **Example 2:** 

 **Input:**  nums = [6,7,8,9]

 **Output:**  4

 **Explanation:** 

The possible XOR triplet values are `{6, 7, 8, 9}`. Thus, the output is 4.

 

 **Constraints:** 

- 1 <= nums.length <= 1500
- 1 <= nums[i] <= 1500

## Solution

**Language:** Java  
**Runtime:** 0 ms  
**Memory:** 42.4 MB  
**Submitted:** 2026-07-24T10:27:55.850Z  

```java
class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        int maxEl = 0;
        for(int num : nums) {
            maxEl = Math.max(maxEl, num);
        }
        int T = 1;
        while(T <= maxEl) {
            T <<= 1; //T = T*2
        }
        boolean[] s1 = new boolean[T]; //XOR pair values set to true
        boolean[] s2 = new boolean[T]; //XOR triplet values set to true
        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                s1[nums[i] ^ nums[j]] = true;
            }
        }
        for(int i = 0; i < T; i++) {
            if(s1[i] == true) {
                for(int num : nums) {
                    s2[i ^ num] = true;
                }
            }
        }
        int count = 0; //unique xor count
        for(int i = 0; i < T; i++) {
            if(s2[i] == true)
                count++;
        }
        return count;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/number-of-unique-xor-triplets-ii/)