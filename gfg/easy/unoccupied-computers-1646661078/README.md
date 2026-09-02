# Unoccupied Computers

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

A cafe has n computers. The customer events are represented by a string s of uppercase English letters, where each distinct letter appears exactly twice:

- The first occurrence denotes the customer's arrival.
- The second occurrence denotes the customer's departure.

A customer is assigned a computer only if one is available at the time of arrival, otherwise the customer is rejected and does not use a computer.

Return the number of customers who could not be assigned a computer upon arrival.

 **Examples:** 

```
Input: n = 3, s = "GACCBDDBAGEE"
Output: 1
Explanation: Only D will not be able to get any computer. So the answer is 1.
```

```
Input: n = 1, s = "ABCBAC"
Output: 2
Explanation: B and C will not be able to get any computers. So the answer is 2.
```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-09-02T16:56:47.144Z  

```java
class Solution {
    public int solve(int n, String s) {
        // status array to keep track of each customer (A-Z)
        // 0 = not arrived, 1 = occupying computer, -1 = rejected
        int[] status = new int[26];

        int occupied = 0;
        int rejectedCount = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = c - 'A';

            // First time seeing the customer (Arrival)
            if (status[index] == 0) {
                if (occupied < n) {
                    status[index] = 1; // Assign a computer
                    occupied++;
                } else {
                    status[index] = -1; // Reject the customer
                    rejectedCount++;
                }
            } 
            // Second time seeing the customer (Departure)
            else if (status[index] == 1) {
                // Only free up a computer if they actually had one
                occupied--;
            }
        }

        return rejectedCount;
    }
}
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/unoccupied-computers-1646661078/1)