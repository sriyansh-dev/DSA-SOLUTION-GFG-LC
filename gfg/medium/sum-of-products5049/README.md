# sum-of-products5049

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

_Description not available._

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-09-06T18:10:54.777Z  

```java
class Solution {
    public long pairAndSum(int[] arr) {
        long ans = 0;
        for (int bit = 0; bit < 31; bit++) {
           long count = 0;
           for (int num : arr) {
               if ((num & (1 << bit)) != 0) {
                   count++;
               }
           }
           long pairs = count * (count - 1) / 2;
           ans += pairs * (1L << bit);
       }
       return ans;
    }
}
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/sum-of-products5049/1)