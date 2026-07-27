# Maximum Product of Two Elements in an Array

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

Given the array of integers `nums`, you will choose two different indices `i` and `j` of that array.  *Return the maximum value of*  `(nums[i]-1)*(nums[j]-1)`.

 

 **Example 1:** 

```
Input: nums = [3,4,5,2]
Output: 12 
Explanation: If you choose the indices i=1 and j=2 (indexed from 0), you will get the maximum value, that is, (nums[1]-1) *(nums[2]-1) = (4-1)* (5-1) = 3*4 = 12. 

```

 **Example 2:** 

```
Input: nums = [1,5,4,5]
Output: 16
Explanation: Choosing the indices i=1 and j=3 (indexed from 0), you will get the maximum value of (5-1)*(5-1) = 16.

```

 **Example 3:** 

```
Input: nums = [3,7]
Output: 12

```

 

 **Constraints:** 

- 2 <= nums.length <= 500
- 1 <= nums[i] <= 10^3

## Solution

**Language:** Java  
**Runtime:** 1 ms (beats 66.20%)  
**Memory:** 44.5 MB (beats 73.61%)  
**Submitted:** 2026-07-27T03:37:32.999Z  

```java
public class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;

        int maxNum = nums[0];
        int result = 0;

        for (int i = 1; i < n; i++) {
            result = Math.max(result, (maxNum - 1) * (nums[i] - 1));
            maxNum = Math.max(maxNum, nums[i]);
        }

        return result;
    }
}

```

---

[View on LeetCode](https://leetcode.com/problems/maximum-product-of-two-elements-in-an-array/)