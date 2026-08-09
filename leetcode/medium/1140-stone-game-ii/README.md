# Stone Game II

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Alice and Bob continue their games with piles of stones. There are a number of piles  **arranged in a row**, and each pile has a positive integer number of stones `piles[i]`. The objective of the game is to end with the most stones.

Alice and Bob take turns, with Alice starting first.

On each player's turn, that player can take  **all the stones**  in the  **first**  `X` remaining piles, where `1 <= X <= 2M`. Then, we set `M = max(M, X)`. Initially, M = 1.

The game continues until all the stones have been taken.

Assuming Alice and Bob play optimally, return the maximum number of stones Alice can get.

 

 **Example 1:** 

 **Input:**  piles = [2,7,9,4,4]

 **Output:**  10

 **Explanation:** 

- If Alice takes one pile at the beginning, Bob takes two piles, then Alice takes 2 piles again. Alice can get 2 + 4 + 4 = 10 stones in total.
- If Alice takes two piles at the beginning, then Bob can take all three piles left. In this case, Alice get 2 + 7 = 9 stones in total.

So we return 10 since it's larger.

 **Example 2:** 

 **Input:**  piles = [1,2,3,4,5,100]

 **Output:**  104

 

 **Constraints:** 

- 1 <= piles.length <= 100
- 1 <= piles[i] <= 104

## Solution

**Language:** Java  
**Runtime:** 4 ms (beats 73.16%)  
**Memory:** 44.1 MB (beats 82.63%)  
**Submitted:** 2026-08-09T04:51:56.910Z  

```java
	

class Solution {
  public int stoneGameII(int[] piles) {
    final int n = piles.length;
    int[][] mem = new int[n][n];
    int[] suffix = new int[n]; // suffix[i] := sum(piles[i..n))
    Arrays.stream(mem).forEach(A -> Arrays.fill(A, -1));
    suffix[n - 1] = piles[n - 1];
    for (int i = n - 2; i >= 0; --i)
      suffix[i] = suffix[i + 1] + piles[i];
    return stoneGameII(suffix, 0, 1, mem);
  }

  // Returns the maximum number of stones Alice can get from piles[i..n) with M.
  private int stoneGameII(int[] suffix, int i, int M, int[][] mem) {
    if (i + 2 * M >= suffix.length)
      return suffix[i];
    if (mem[i][M] != -1)
      return mem[i][M];

    int opponent = suffix[i];

    for (int X = 1; X <= 2 * M; ++X)
      opponent = Math.min(opponent, stoneGameII(suffix, i + X, Math.max(M, X), mem));

    return mem[i][M] = suffix[i] - opponent;
  }
}

```

---

[View on LeetCode](https://leetcode.com/problems/stone-game-ii/)