# Maximum Subset XOR

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given an array  **arr[]**, choose any subset of elements (possibly all elements) such that the XOR of the chosen elements is maximized.

 **Examples:** 

```
Input : arr[] = [2, 4, 5]
Output: 7
Explanation: The subset {2, 5} has the maximum XOR value.
```

```
Input : arr[] = [9, 8, 5]
Output: 13
Explanation: The subset {8, 5} has the maximum XOR value.
```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-07-30T07:13:55.752Z  

```java
class Solution {
    public int maxSubsetXOR(int[] arr) {
        int N = arr.length;
        int maxX = arr[0];
        
        // Find the maximum element in the array
        for (int i = 1; i < N; i++) {
            if (arr[i] > maxX) {
                maxX = arr[i];
            }
        }
        
        if (maxX == 0) return 0;
        
        int ans = 0;        
        while (true) {
            // Update answer with the maximum XOR possible with the current maxX
            ans = Math.max(ans, ans ^ maxX);
            
            // Reduce all elements using the current maxX
            for (int i = 0; i < N; i++) {
                arr[i] = Math.min(arr[i], arr[i] ^ maxX);
            }
            
            // Find the new maximum element in the updated array
            maxX = arr[0];
            for (int i = 0; i < N; i++) {
                maxX = Math.max(maxX, arr[i]);
            }
            
            // If the maximum element becomes 0, we can't maximize further
            if (maxX == 0) break;            
        }
        
        return ans;        
    }
}
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/maximum-subset-xor/1)