# longest-subarray-with-majority-greater-than-k

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

_Description not available._

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-06T17:29:44.657Z  

```java
class Solution {
    public int longestSubarray(int[] arr, int k) {
        // Code Here
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,-1);
        int diff = 0;
        int gt = 0;
        int lt = 0;
        int maxLen = 0;
        for(int i = 0;i < arr.length;i++){
            if(arr[i] > k) gt++;
            else lt++;
            diff = gt - lt;
            int res = diff - 1;
            if(map.containsKey(res)){
                maxLen = Math.max(maxLen, i-map.get(res));
            }
            if(!map.containsKey(diff)){
                map.put(diff, i);
            }
            
        }
        // edge case where entire array could be a possible subarray
        if((gt-lt) > 1) return arr.length;
        return maxLen;
    }
}
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/longest-subarray-with-majority-greater-than-k/1)