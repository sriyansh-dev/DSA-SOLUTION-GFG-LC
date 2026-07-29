# split-array-into-minimum-subsets

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

_Description not available._

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-07-29T04:25:33.998Z  

```java
class Solution {
    int minSubsets(int arr[]) {
        int n = arr.length;
        int sets = 0;
        HashSet<Integer> o = new HashSet<Integer>();
        
        for (int x = 0; x < n; x++) {
            int data = arr[x];
            
            boolean left = o.contains(data - 1);
            boolean right = o.contains(data + 1);
        
            if (!left && !right) sets++;
            if (left && right) sets--;
        
            o.add(data);
        }
        
        return sets;
    }
}
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/split-array-into-minimum-subsets/1)