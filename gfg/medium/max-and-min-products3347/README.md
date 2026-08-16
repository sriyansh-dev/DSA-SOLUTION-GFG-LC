# Min Product Subset

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Given an integer array  **arr[]**, find the minimum possible product that can be obtained by multiplying the elements of any non-empty subset of the array.

 **Examples:** 

```
Input: arr[] = [1, 2, 3]
Output: 1
Explanation: The possible subset products are 1, 2, 3, 2, 3, 6, and 6. The minimum product is 1, obtained by selecting the subset [1].
```

```
Input: arr[] = [4, -2, 5]
Output: -40
Explanation: The minimum product is -40, obtained by selecting the subset [4, -2, 5].
```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-16T12:43:14.134Z  

```java
class Solution {
    public int minProd(int[] arr) {
        int n = arr.length;
        long minProd = Long.MAX_VALUE;

        for (int mask = 1; mask < (1 << n); mask++) {
            long product = 1;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    product *= arr[i];
                }
            }
            minProd = Math.min(minProd, product);
        }

        return (int) minProd;
    }
}

```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/max-and-min-products3347/1)