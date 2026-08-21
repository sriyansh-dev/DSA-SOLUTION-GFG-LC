# Kth Smallest Amount With Single Denomination Combination

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given an integer array `coins` representing coins of different denominations and an integer `k`.

You have an infinite number of coins of each denomination. However, you are  **not allowed**  to combine coins of different denominations.

Return the `kth`  **smallest**  amount that can be made using these coins.

 

 **Example 1:** 

 **Input:**  coins = [3,6,9], k = 3

 **Output:**  9

 **Explanation:**  The given coins can make the following amounts:
Coin 3 produces multiples of 3: 3, 6, 9, 12, 15, etc.
Coin 6 produces multiples of 6: 6, 12, 18, 24, etc.
Coin 9 produces multiples of 9: 9, 18, 27, 36, etc.
All of the coins combined produce: 3, 6,  **9**, 12, 15, etc.

 **Example 2:** 

 **Input:**  coins = [5,2], k = 7

 **Output:**  12

 **Explanation:**  The given coins can make the following amounts:
Coin 5 produces multiples of 5: 5, 10, 15, 20, etc.
Coin 2 produces multiples of 2: 2, 4, 6, 8, 10, 12, etc.
All of the coins combined produce: 2, 4, 5, 6, 8, 10,  **12**, 14, 15, etc.

 

 **Constraints:** 

- 1 <= coins.length <= 15
- 1 <= coins[i] <= 25
- 1 <= k <= 2 * 109
- coins contains pairwise distinct integers.

## Solution

**Language:** Java  
**Runtime:** 229 ms (beats 5.88%)  
**Memory:** 43.2 MB (beats 96.08%)  
**Submitted:** 2026-08-21T17:23:51.490Z  

```java
class Solution {
    private int[] coins;
    private int k;

    /**
     * Finds the k-th smallest positive integer that is divisible by at least one coin value.
     * Uses binary search on the answer combined with inclusion-exclusion principle.
     */
    public long findKthSmallest(int[] coins, int k) {
        this.coins = coins;
        this.k = k;

        // Binary search using the standard template
        long left = 1;
        long right = (long) 1e11;
        long firstTrueIndex = -1;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (feasible(mid)) {
                firstTrueIndex = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return firstTrueIndex;
    }

    /**
     * Check if there are at least k valid amounts <= maxValue.
     */
    private boolean feasible(long maxValue) {
        return countMultiples(maxValue) >= k;
    }

    /**
     * Counts how many positive integers up to maxValue are divisible by at least one coin.
     * Uses inclusion-exclusion principle with bitmask.
     */
    private long countMultiples(long maxValue) {
        long count = 0;
        int n = coins.length;

        for (int bitmask = 1; bitmask < (1 << n); ++bitmask) {
            long lcmValue = 1;

            for (int j = 0; j < n; ++j) {
                if ((bitmask >> j & 1) == 1) {
                    lcmValue = lcm(lcmValue, coins[j]);
                    if (lcmValue > maxValue) {
                        break;
                    }
                }
            }

            int subsetSize = Integer.bitCount(bitmask);
            if (subsetSize % 2 == 1) {
                count += maxValue / lcmValue;
            } else {
                count -= maxValue / lcmValue;
            }
        }

        return count;
    }

    private long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}

```

---

[View on LeetCode](https://leetcode.com/problems/kth-smallest-amount-with-single-denomination-combination/)