# Stone Game IV

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

Alice and Bob take turns playing a game, with Alice starting first.

Initially, there are `n` stones in a pile. On each player's turn, that player makes a  *move*  consisting of removing  **any**  non-zero  **square number**  of stones in the pile.

Also, if a player cannot make a move, he/she loses the game.

Given a positive integer `n`, return `true` if and only if Alice wins the game otherwise return `false`, assuming both players play optimally.

 

 **Example 1:** 

```
Input: n = 1
Output: true
Explanation: Alice can remove 1 stone winning the game because Bob doesn't have any moves.
```

 **Example 2:** 

```
Input: n = 2
Output: false
Explanation: Alice can only remove 1 stone, after that Bob removes the last one winning the game (2 -> 1 -> 0).

```

 **Example 3:** 

```
Input: n = 4
Output: true
Explanation: n is already a perfect square, Alice can win with one move, removing 4 stones (4 -> 0).

```

 

 **Constraints:** 

- 1 <= n <= 105

## Solution

**Language:** Java  
**Runtime:** 18 ms (beats 57.96%)  
**Memory:** 42.3 MB (beats 90.34%)  
**Submitted:** 2026-08-10T04:52:09.372Z  

```java
class Solution {
    public boolean winnerSquareGame(int n) {
        long[] dp = new long[(n >> 6) + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                int prev = i - j * j;
                if ((dp[prev >> 6] & (1L << (prev & 63))) == 0) {
                    dp[i >> 6] |= (1L << (i & 63));
                    break;
                }
            }
        }
        return (dp[n >> 6] & (1L << (n & 63))) != 0;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/stone-game-iv/)