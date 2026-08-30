# find-marks-from-ranks

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

_Description not available._

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-30T17:28:01.779Z  

```java
class Solution {
    public ArrayList<Integer> getMarks(int[] l, int[] r, int[] ranks) {
        ArrayList<Integer> marks = new ArrayList<Integer>();
        int[] cummLength = new int[r.length + 1];

        for (int i = 1; i <= l.length; i++) {
            cummLength[i] = cummLength[i - 1] + r[i - 1] - l[i - 1] + 1;
        }

        for (int rank: ranks) {
            int i = findRange(cummLength, rank);
            marks.add(l[i - 1] + rank - cummLength[i - 1] - 1);
        }

        return marks;
    }

    private int findRange(int[] cummLength, int rank) {
        int low = 0;
        int high = cummLength.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (rank == cummLength[mid]) return mid;
            else if (rank > cummLength[mid]) low = mid + 1;
            else high = mid - 1;
        }

        return low;
    }
}
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/find-marks-from-ranks/1)