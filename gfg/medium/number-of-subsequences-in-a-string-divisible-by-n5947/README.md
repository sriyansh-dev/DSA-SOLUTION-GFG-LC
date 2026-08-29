# number-of-subsequences-in-a-string-divisible-by-n5947

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

_Description not available._

## Solution

**Language:** C++  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-29T07:45:25.774Z  

```cpp
class Solution {
  public:
    int countSubsequences(string& s, int n) {
        const int MOD = 1000000007;

        vector<long long> dp(n, 0);

        for (char ch : s) {
            int digit = ch - '0';

            vector<long long> next = dp;

            for (int remainder = 0; remainder < n; ++remainder) {
                int newRemainder = (remainder * 10LL + digit) % n;

                next[newRemainder] =
                    (next[newRemainder] + dp[remainder]) % MOD;
            }

            next[digit % n] = (next[digit % n] + 1) % MOD;

            dp.swap(next);
        }

        return dp[0];
    }
};
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/number-of-subsequences-in-a-string-divisible-by-n5947/1)