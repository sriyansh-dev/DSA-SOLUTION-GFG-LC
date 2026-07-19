# max-absolute-difference4114

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

_Description not available._

## Solution

**Language:** Python  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-07-19T11:40:07.838Z  

```py
class Solution:
    def maxDiffSubArrays(self, arr):
        n=len(arr)
        lMax,lMin=[arr[0]]*n,[arr[0]]*n
        currLMax,currLMin=arr[0],arr[0]
        rMax,rMin=[arr[-1]]*n,[arr[-1]]*n
        currRMax,currRMin=arr[-1],arr[-1]
        for i in range(1,n):
            currLMax=max(arr[i],currLMax+arr[i])
            lMax[i]=currLMax
            currLMin=min(arr[i],currLMin+arr[i])
            lMin[i]=currLMin
            currRMax=max(arr[n-i-1],currRMax+arr[n-i-1])
            rMax[n-i-1]=currRMax
            currRMin=min(arr[n-i-1],currRMin+arr[n-i-1])
            rMin[n-i-1]=currRMin
        ans=0
        for i in range(n-1):
            ans=max(ans,abs(lMin[i]-rMax[i+1]),abs(lMax[i]-rMin[i+1]))
        return ans
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/max-absolute-difference4114/1)