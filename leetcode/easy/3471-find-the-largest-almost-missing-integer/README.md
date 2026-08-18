# Find the Largest Almost Missing Integer

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

You are given an integer array `nums` and an integer `k`.

An integer `x` is  **almost missing**  from `nums` if `x` appears in  *exactly*  one subarray of size `k` within `nums`.

Return the  **largest**   **almost missing**  integer from `nums`. If no such integer exists, return `-1`.

A  **subarray**  is a contiguous sequence of elements within an array.

 

 **Example 1:** 

 **Input:**  nums = [3,9,2,1,7], k = 3

 **Output:**  7

 **Explanation:** 

- 1 appears in 2 subarrays of size 3: [9, 2, 1] and [2, 1, 7].
- 2 appears in 3 subarrays of size 3: [3, 9, 2], [9, 2, 1], [2, 1, 7].
- 3 appears in 1 subarray of size 3: [3, 9, 2].
- 7 appears in 1 subarray of size 3: [2, 1, 7].
- 9 appears in 2 subarrays of size 3: [3, 9, 2], and [9, 2, 1].

We return 7 since it is the largest integer that appears in exactly one subarray of size `k`.

 **Example 2:** 

 **Input:**  nums = [3,9,7,2,1,7], k = 4

 **Output:**  3

 **Explanation:** 

- 1 appears in 2 subarrays of size 4: [9, 7, 2, 1], [7, 2, 1, 7].
- 2 appears in 3 subarrays of size 4: [3, 9, 7, 2], [9, 7, 2, 1], [7, 2, 1, 7].
- 3 appears in 1 subarray of size 4: [3, 9, 7, 2].
- 7 appears in 3 subarrays of size 4: [3, 9, 7, 2], [9, 7, 2, 1], [7, 2, 1, 7].
- 9 appears in 2 subarrays of size 4: [3, 9, 7, 2], [9, 7, 2, 1].

We return 3 since it is the largest and only integer that appears in exactly one subarray of size `k`.

 **Example 3:** 

 **Input:**  nums = [0,0], k = 1

 **Output:**  -1

 **Explanation:** 

There is no integer that appears in only one subarray of size 1.

 

 **Constraints:** 

- 1 <= nums.length <= 50
- 0 <= nums[i] <= 50
- 1 <= k <= nums.length

## Solution

**Language:** Java  
**Runtime:** 3 ms (beats 63.51%)  
**Memory:** 45.2 MB (beats 48.42%)  
**Submitted:** 2026-08-18T15:51:52.908Z  

```java
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        
        // Count total occurrences of each number in the array
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }
        
        // Case 1: Subarray size is 1
        if (k == 1) {
            int maxVal = -1;
            for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
                if (entry.getValue() == 1) {
                    maxVal = Math.max(maxVal, entry.getKey());
                }
            }
            return maxVal;
        }
        
        // Case 2: Subarray size is equal to the full length of the array
        if (k == n) {
            int maxVal = -1;
            for (int num : nums) {
                maxVal = Math.max(maxVal, num);
            }
            return maxVal;
        }
        
        // Case 3: 1 < k < n
        int ans = -1;
        // Check first element
        if (counts.get(nums[0]) == 1) {
            ans = Math.max(ans, nums[0]);
        }
        // Check last element
        if (counts.get(nums[n - 1]) == 1) {
            ans = Math.max(ans, nums[n - 1]);
        }
        
        return ans;
    }
}

```

---

[View on LeetCode](https://leetcode.com/problems/find-the-largest-almost-missing-integer/)