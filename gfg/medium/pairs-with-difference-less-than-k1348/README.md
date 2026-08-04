# pairs-with-difference-less-than-k1348

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

_Description not available._

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-04T04:20:10.202Z  

```java
class Solution {

    public static int countPairs(int arr[], int k) {

        Arrays.sort(arr);

        int i = 0, cnt = 0;

        

        // Expansion pointer

        for (int j = 0; j < arr.length; j++) {

            // Shrink window from left until the difference is strictly less than k

            while (arr[j] - arr[i] >= k) {

                i++;

            }

            // All elements from index i to j-1 form a valid pair with arr[j]

            cnt += (j - i);

        }

        return cnt;

    }

}

```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/pairs-with-difference-less-than-k1348/1)