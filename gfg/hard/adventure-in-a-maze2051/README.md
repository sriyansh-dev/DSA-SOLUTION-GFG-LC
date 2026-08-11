# Adventure in a Maze

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

Given a maze represented as an  **n x n**  grid,  **grid[][]**, using 0-based indexing. Each cell contains one of the values 1, 2, or 3, which determines the direction(s) you are allowed to move from that cell:

- 1 - you may move Right only.
- 2 - you may move Down only.
- 3 - you may move Right or Down (both directions are available).

You start at the top-left cell (0, 0) (the Entry) and must reach the bottom-right cell (n-1, n-1) (the Exit), following the movement rule of each cell you pass through. You are never allowed to move outside the boundaries of the grid.

The Adventure of a path is the sum of the values of all cells visited along that path (including both the entry and exit cells).

Find the total number of distinct valid paths from Entry to Exit, and among all such paths, the maximum possible Adventure. Return the answer as [totalPaths, maxAdventure].

 **Note:**  Return totalPaths  **modulo**  109 + 7, maxAdventure needs no modulo, as it stays small regardless of grid size.

 **Examples:** 

```
Input: grid[][] = [[3, 2], [1, 3]]
Output: [2, 8]
Explanation:
There are 2 valid paths from [0, 0] to [1, 1]:
Path 1: [0, 0] -> [0, 1] -> [1, 1], values 3 + 2 + 3 = 8
Path 2: [0,0] -> [1, 0] -> [1, 1], values 3 + 1 + 3 = 7
The maximum Adventure among these is 8, so the output is [2, 8].
```

```
Input: grid[][] = [[1, 1, 3, 2, 1], [3, 2, 2, 1, 2], [1, 3, 3, 1, 3], [1, 2, 3, 1, 2], [1, 1, 1, 3, 1]]
Output: [4, 18]
Explanation: There are 4 valid paths from Entry to Exit, with total Adventures 
18, 17, 17, and 16 respectively. The maximum among these is 18, so the output is [4, 18].

```

 **Constraints:** 
1 ≤ n ≤ 100

## Solution

**Language:** C++  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-11T20:10:06.771Z  

```cpp
class Solution {
public:
    vector<int> findWays(vector<vector<int>>& grid) {
        int n = grid.size();
        const int MOD = 1e9 + 7;

        vector<vector<long long>> ways(n, vector<long long>(n, 0));
        vector<vector<int>> best(n, vector<int>(n, 0));

        ways[0][0] = 1;
        best[0][0] = grid[0][0];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                // If this cell is unreachable, skip it
                if (ways[i][j] == 0)
                    continue;

                // Move Right
                if ((grid[i][j] == 1 || grid[i][j] == 3) &&
                    j + 1 < n) {

                    ways[i][j + 1] =
                        (ways[i][j + 1] + ways[i][j]) % MOD;

                    best[i][j + 1] =
                        max(best[i][j + 1],
                            best[i][j] + grid[i][j + 1]);
                }

                // Move Down
                if ((grid[i][j] == 2 || grid[i][j] == 3) &&
                    i + 1 < n) {

                    ways[i + 1][j] =
                        (ways[i + 1][j] + ways[i][j]) % MOD;

                    best[i + 1][j] =
                        max(best[i + 1][j],
                            best[i][j] + grid[i + 1][j]);
                }
            }
        }

        // Destination unreachable
        if (ways[n - 1][n - 1] == 0)
            return {0, 0};

        return {
            (int)ways[n - 1][n - 1],
            best[n - 1][n - 1]
        };
    }
};
//GFG POTD solution for 12 August

```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/adventure-in-a-maze2051/1)