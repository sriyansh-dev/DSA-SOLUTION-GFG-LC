# shortest-unique-prefix-for-every-word

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

_Description not available._

## Solution

**Language:** Python  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-07-20T12:49:23.044Z  

```py
class Solution:
    def findPrefixes(self, arr):
        # code here
        s = dict()
        for i in arr:
            for j in range(1, len(i) + 1):
                s[i[:j]] = s.get(i[:j], 0) + 1
        res = []
        for i in arr:
            for j in range(1, len(i) + 1):
                if s[i[:j]] == 1:
                    res.append(i[:j])
                    break
                if j == len(i):
                    res.append(i)
        return res
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/shortest-unique-prefix-for-every-word/1)