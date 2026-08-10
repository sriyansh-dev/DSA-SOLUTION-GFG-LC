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
**Runtime:** 15 ms (beats 64.49%)  
**Memory:** 42.3 MB (beats 90.34%)  
**Submitted:** 2026-08-10T04:43:27.154Z  

```java
class Solution {
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];
        int maxRoot = (int) Math.sqrt(n);
        int[] sq = new int[maxRoot + 1];
        for (int k = 1; k <= maxRoot; k++) sq[k] = k * k;

        for (int i = 1; i <= n; i++) {
            for (int k = 1; k <= maxRoot && sq[k] <= i; k++) {
                if (!dp[i - sq[k]]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}

```

---

[View on LeetCode](https://leetcode.com/problems/stone-game-iv/)