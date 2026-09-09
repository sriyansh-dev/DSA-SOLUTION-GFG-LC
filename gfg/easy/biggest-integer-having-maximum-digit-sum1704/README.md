# Max Digit Sum Number in 1 to n

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

Given a number  **n**, find a number in the range from 1 to  **n** such that its digit sum is maximum. If there are multiple such numbers, return the largest of them.

 **Examples:** 

```
Input: n = 48
Output: 48
Explanation: There are two numbers with maximum digit sum = 12. The numbers are 48 and 39. Since 48 > 39, so 48 is the answer.
```

```
Input: n = 90
Output: 89
Explanation: 89 gives us the largest digit sum in the range from 1 to n. Hence the answer is 89. 
```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-09-09T17:29:56.718Z  

```java
class Solution {
    public static int findMax(int n) {
        String s = Integer.toString(n);
        int len = s.length();

        int bestNum = n;
        int maxSum = getSum(n);

        // Try reducing each digit from left to right and setting the rest to '9'
        for (int i = 0; i < len; i++) {
            char[] arr = s.toCharArray();

            if (arr[i] > '0') {
                arr[i]--;
                for (int j = i + 1; j < len; j++) {
                    arr[j] = '9';
                }

                int cand = Integer.parseInt(new String(arr));
                int candSum = getSum(cand);

                if (candSum > maxSum || (candSum == maxSum && cand > bestNum)) {
                    maxSum = candSum;
                    bestNum = cand;
                }
            }
        }

        return bestNum;
    }

    private static int getSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/biggest-integer-having-maximum-digit-sum1704/1)