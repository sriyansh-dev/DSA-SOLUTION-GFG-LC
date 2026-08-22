# Check Divisibility by Digit Sum and Product

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

You are given a positive integer `n`. Determine whether `n` is divisible by the  **sum** of the following two values:

- The digit sum of n (the sum of its digits).
- The digit product of n (the product of its digits).

Return `true` if `n` is divisible by this sum; otherwise, return `false`.

 

 **Example 1:** 

 **Input:**  n = 99

 **Output:**  true

 **Explanation:** 

Since 99 is divisible by the sum (9 + 9 = 18) plus product (9 * 9 = 81) of its digits (total 99), the output is true.

 **Example 2:** 

 **Input:**  n = 23

 **Output:**  false

 **Explanation:** 

Since 23 is not divisible by the sum (2 + 3 = 5) plus product (2 * 3 = 6) of its digits (total 11), the output is false.

 

 **Constraints:** 

- 1 <= n <= 106

## Solution

**Language:** Java  
**Runtime:** 0 ms (beats 100.00%)  
**Memory:** 42.3 MB (beats 79.33%)  
**Submitted:** 2026-08-22T16:10:06.501Z  

```java
class Solution {
    /**
     * Checks if a number is divisible by the sum of (digit sum + digit product)
     * @param n the number to check
     * @return true if n is divisible by (sum of digits + product of digits), false otherwise
     */
    public boolean checkDivisibility(int n) {
        int digitSum = 0;      // Sum of all digits
        int digitProduct = 1;  // Product of all digits
        int number = n;        // Copy of n for digit extraction
      
        // Extract each digit and calculate sum and product
        while (number != 0) {
            int currentDigit = number % 10;  // Get the last digit
            number /= 10;                     // Remove the last digit
          
            digitSum += currentDigit;        // Add digit to sum
            digitProduct *= currentDigit;    // Multiply digit to product
        }
      
        // Check if n is divisible by (digitSum + digitProduct)
        return n % (digitSum + digitProduct) == 0;
    }
}

```

---

[View on LeetCode](https://leetcode.com/problems/check-divisibility-by-digit-sum-and-product/)