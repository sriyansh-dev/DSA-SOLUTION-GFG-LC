# count-the-number-of-subarrays

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

_Description not available._

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-05T05:45:48.542Z  

```java
class Solution {
    public int countSubarray(int[] arr, int l, int r) {
        // code here
       return solve(arr,r) - solve(arr,l-1);
    }
    public static int solve(int[] arr,int r){
        int cnt = 0;
        int s = 0;
        int i = 0;
        int j = 0;
        int n = arr.length;
        
        while(j<n){
            s+=arr[j];
            
            while(s>r && i<=j){
                s-=arr[i];
                i++;
            }
            j++;
        cnt+=(j-i+1);
        }
        
    return cnt;
        
    }
}
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/count-the-number-of-subarrays/1)