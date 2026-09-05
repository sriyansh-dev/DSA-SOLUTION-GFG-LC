# longest-sub-sequence-such-that-difference-between-adjacents-is-one2558

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

_Description not available._

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-09-05T16:57:37.048Z  

```java
class Solution {
    public int longestSubseq(int[] arr) {
        int n = arr.length;
        Map<Integer, Integer> dp = new HashMap<>();

        int result = 1;

        for(int i=0;i<n;i++) {
            int curr = 1;
            if(dp.containsKey(arr[i]+1)) {
                curr = Math.max(curr, dp.get(arr[i]+1) + 1);
            }
            if(dp.containsKey((arr[i]-1))) {
                curr = Math.max(curr, dp.get(arr[i]-1) + 1);
            }

            dp.put(arr[i], Math.max(dp.getOrDefault(arr[i], 0), curr));
            result = Math.max(result, dp.get(arr[i]));
        }

        return result;
    }
}
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/longest-sub-sequence-such-that-difference-between-adjacents-is-one2558/1)