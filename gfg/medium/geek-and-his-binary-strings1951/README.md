# geek-and-his-binary-strings1951

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

_Description not available._

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-24T16:43:57.146Z  

```java
class Solution {

    static final long MOD = 1000000007L;

    public int prefixStrings(int n) {

        long numerator = 1;
        long denominator = 1;

        // Calculate C(2n, n)
        for (int i = 1; i <= n; i++) {
            numerator = numerator * (n + i) % MOD;
            denominator = denominator * i % MOD;
        }

        long combination =
            numerator * power(denominator, MOD - 2) % MOD;

        // Catalan number = C(2n,n) / (n+1)
        long answer =
            combination * power(n + 1, MOD - 2) % MOD;

        return (int) answer;
    }

    private long power(long a, long b) {
        long result = 1;

        while (b > 0) {
            if ((b & 1) == 1) {
                result = result * a % MOD;
            }

            a = a * a % MOD;
            b >>= 1;
        }

        return result;
    }
}

```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/geek-and-his-binary-strings1951/1)