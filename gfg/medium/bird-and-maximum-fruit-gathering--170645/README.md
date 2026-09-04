# bird-and-maximum-fruit-gathering--170645

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

_Description not available._

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-09-04T15:56:00.110Z  

```java
class Solution {
    public int maxFruits(ArrayList<Integer> arr, int m) {
        int maxSum = 0;
        int currSum = 0;

        for(int i=0;i<m && i < arr.size();i++){
            currSum += arr.get(i);
        }

        maxSum = Math.max(maxSum, currSum);

        if(arr.size() <= m){
            return maxSum;
        }

        for(int i=m;i<arr.size();i++){
            currSum -= arr.get(i-m);
            currSum += arr.get(i);
            maxSum = Math.max(maxSum, currSum);
        }

        //Now currSum contains sum of last k elements;
        //Lets overlap the window to calculate the running sum

        for(int i=0;i<m;i++){
            currSum -= arr.get(arr.size()-m+i);
            currSum += arr.get(i);
            maxSum = Math.max(maxSum, currSum);
        }

        return (maxSum);

    }
}
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/bird-and-maximum-fruit-gathering--170645/1)