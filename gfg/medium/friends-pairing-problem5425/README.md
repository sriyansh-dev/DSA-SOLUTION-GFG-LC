# friends-pairing-problem5425

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

_Description not available._

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-07T09:07:12.183Z  

```java
class Solution {
    public int countFriendsPairings(int n) {

        if (n == 1) return 1;
        if (n == 2) return 2;

        // prev2 = f(i-2), prev1 = f(i-1)
        int prev2 = 1;
        int prev1 = 2;

        for (int i = 3; i <= n; i++) {

            // Current friend stays single
            int choice1 = prev1;

            // Current friend pairs with one of the (i-1) friends
            int choice2 = (i - 1) * prev2;

            // Total ways for i friends
            int curr = choice1 + choice2;

            // Shift values for next iteration
            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }
}
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/friends-pairing-problem5425/1)