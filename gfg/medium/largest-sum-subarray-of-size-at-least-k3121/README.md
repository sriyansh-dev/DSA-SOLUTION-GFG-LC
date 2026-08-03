# largest-sum-subarray-of-size-at-least-k3121

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

_Description not available._

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-03T04:32:28.979Z  

```java
class Solution {
    public int maxSumWithK(int[] arr, int k) {
        int n = arr.length;

        // maxSum[i] stores maximum subarray sum ending at index i
        int[] maxSum = new int[n];
        maxSum[0] = arr[0];

        int curr = arr[0];
        for (int i = 1; i < n; i++) {
            curr = Math.max(arr[i], curr + arr[i]);
            maxSum[i] = curr;
        }

        // Sum of first k elements
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }

        int ans = sum;

        // Extend window
        for (int i = k; i < n; i++) {
            sum += arr[i] - arr[i - k];

            // Window of exactly k
            ans = Math.max(ans, sum);

            // Window of size > k
            ans = Math.max(ans, sum + maxSum[i - k]);
        }

        return ans;
    }
}
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/largest-sum-subarray-of-size-at-least-k3121/1)