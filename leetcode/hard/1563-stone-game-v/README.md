# Stone Game V

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

There are several stones  **arranged in a row**, and each stone has an associated value which is an integer given in the array `stoneValue`.

In each round of the game, Alice divides the row into  **two non-empty rows**  (i.e. left row and right row), then Bob calculates the value of each row which is the sum of the values of all the stones in this row. Bob throws away the row which has the maximum value, and Alice's score increases by the value of the remaining row. If the value of the two rows are equal, Bob lets Alice decide which row will be thrown away. The next round starts with the remaining row.

The game ends when there is only  **one stone remaining**. Alice's score is initially  **zero**.

Return  *the maximum score that Alice can obtain*.

 

 **Example 1:** 

```
Input: stoneValue = [6,2,3,4,5,5]
Output: 18
Explanation: In the first round, Alice divides the row to [6,2,3], [4,5,5]. The left row has the value 11 and the right row has value 14. Bob throws away the right row and Alice's score is now 11.
In the second round Alice divides the row to [6], [2,3]. This time Bob throws away the left row and Alice's score becomes 16 (11 + 5).
The last round Alice has only one choice to divide the row which is [2], [3]. Bob throws away the right row and Alice's score is now 18 (16 + 2). The game ends because only one stone is remaining in the row.

```

 **Example 2:** 

```
Input: stoneValue = [7,7,7,7,7,7,7]
Output: 28

```

 **Example 3:** 

```
Input: stoneValue = [4]
Output: 0

```

 

 **Constraints:** 

- 1 <= stoneValue.length <= 500
- 1 <= stoneValue[i] <= 106

## Solution

**Language:** Java  
**Runtime:** 57 ms (beats 94.47%)  
**Memory:** 48.6 MB (beats 23.56%)  
**Submitted:** 2026-08-17T07:33:47.335Z  

```java
class Solution {
    private int n;                    // Total number of stones
    private int[] prefixSum;          // Prefix sum array for quick range sum calculation
    private int[] stoneValues;        // Original stone values array
    private Integer[][] memo;          // Memoization table for dynamic programming
  
    public int stoneGameV(int[] stoneValue) {
        // Initialize variables
        n = stoneValue.length;
        prefixSum = new int[n + 1];
        stoneValues = stoneValue;
        memo = new Integer[n][n];
      
        // Build prefix sum array for O(1) range sum queries
        // prefixSum[i] represents sum of elements from index 0 to i-1
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + stoneValues[i - 1];
        }
      
        // Start the recursive solution with memoization
        return dfs(0, n - 1);
    }
  
    private int dfs(int left, int right) {
        // Base case: if range is invalid or contains only one element
        if (left >= right) {
            return 0;
        }
      
        // Check if result is already computed
        if (memo[left][right] != null) {
            return memo[left][right];
        }
      
        int maxScore = 0;
        int leftSum = 0;  // Sum of left partition
        int rightSum = prefixSum[right + 1] - prefixSum[left];  // Sum of right partition
      
        // Try all possible split points
        for (int splitPoint = left; splitPoint < right; splitPoint++) {
            // Update partition sums
            leftSum += stoneValues[splitPoint];
            rightSum -= stoneValues[splitPoint];
          
            if (leftSum < rightSum) {
                // Left partition has smaller sum, Alice chooses left
                // Pruning: if current answer is already greater than maximum possible score
                if (maxScore > leftSum * 2) {
                    continue;
                }
                maxScore = Math.max(maxScore, leftSum + dfs(left, splitPoint));
            } else if (leftSum > rightSum) {
                // Right partition has smaller sum, Alice chooses right
                // Pruning: if current answer is already greater than maximum possible score
                if (maxScore > rightSum * 2) {
                    break;
                }
                maxScore = Math.max(maxScore, rightSum + dfs(splitPoint + 1, right));
            } else {
                // Both partitions have equal sum, Alice can choose either
                maxScore = Math.max(maxScore, 
                    Math.max(leftSum + dfs(left, splitPoint), 
                            rightSum + dfs(splitPoint + 1, right)));
            }
        }
      
        // Store result in memo table and return
        memo[left][right] = maxScore;
        return maxScore;
    }
}

```

---

[View on LeetCode](https://leetcode.com/problems/stone-game-v/)