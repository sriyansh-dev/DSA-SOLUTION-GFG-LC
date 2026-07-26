# Maximum Product of Three Numbers

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

Given an integer array `nums`,  *find three numbers whose product is maximum and return the maximum product*.

 

 **Example 1:** 

```
Input: nums = [1,2,3]
Output: 6

```

 **Example 2:** 

```
Input: nums = [1,2,3,4]
Output: 24

```

 **Example 3:** 

```
Input: nums = [-1,-2,-3]
Output: -6

```

 

 **Constraints:** 

- 3 <= nums.length <= 104
- -1000 <= nums[i] <= 1000

## Solution

**Language:** C++  
**Runtime:** 0 ms (beats 100.00%)  
**Memory:** 31.5 MB (beats 46.18%)  
**Submitted:** 2026-07-26T04:31:29.991Z  

```cpp
class Solution {
 public:
  int maximumProduct(vector<int>& nums) {
    const int n = nums.size();
    ranges::sort(nums);
    return max(nums[n - 1] * nums[0] * nums[1],
               nums[n - 1] * nums[n - 2] * nums[n - 3]);
  }
};
```

---

[View on LeetCode](https://leetcode.com/problems/maximum-product-of-three-numbers/)