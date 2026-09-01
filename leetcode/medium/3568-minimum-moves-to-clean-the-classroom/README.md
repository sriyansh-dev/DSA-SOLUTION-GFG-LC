# Minimum Moves to Clean the Classroom

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given an `m x n` grid `classroom` where a student volunteer is tasked with cleaning up litter scattered around the room. Each cell in the grid is one of the following:

- 'S': Starting position of the student
- 'L': Litter that must be collected (once collected, the cell becomes empty)
- 'R': Reset area that restores the student's energy to full capacity, regardless of their current energy level (can be used multiple times)
- 'X': Obstacle the student cannot pass through
- '.': Empty space

You are also given an integer `energy`, representing the student's maximum energy capacity. The student starts with this energy from the starting position `'S'`.

Each move to an adjacent cell (up, down, left, or right) costs 1 unit of energy. If the energy reaches 0, the student can only continue if they are on a reset area `'R'`, which resets the energy to its  **maximum**  capacity `energy`.

Return the  **minimum**  number of moves required to collect all litter items, or `-1` if it's impossible.

 

 **Example 1:** 

 **Input:**  classroom = ["S.", "XL"], energy = 2

 **Output:**  2

 **Explanation:** 

- The student starts at cell (0, 0) with 2 units of energy.
- Since cell (1, 0) contains an obstacle 'X', the student cannot move directly downward.
- A valid sequence of moves to collect all litter is as follows: Move 1: From (0, 0) → (0, 1) with 1 unit of energy and 1 unit remaining. Move 2: From (0, 1) → (1, 1) to collect the litter 'L'.
- The student collects all the litter using 2 moves. Thus, the output is 2.

 **Example 2:** 

 **Input:**  classroom = ["LS", "RL"], energy = 4

 **Output:**  3

 **Explanation:** 

- The student starts at cell (0, 1) with 4 units of energy.
- A valid sequence of moves to collect all litter is as follows: Move 1: From (0, 1) → (0, 0) to collect the first litter 'L' with 1 unit of energy used and 3 units remaining. Move 2: From (0, 0) → (1, 0) to 'R' to reset and restore energy back to 4. Move 3: From (1, 0) → (1, 1) to collect the second litter 'L'.
- The student collects all the litter using 3 moves. Thus, the output is 3.

 **Example 3:** 

 **Input:**  classroom = ["L.S", "RXL"], energy = 3

 **Output:**  -1

 **Explanation:** 

No valid path collects all `'L'`.

 

 **Constraints:** 

- 1 <= m == classroom.length <= 20
- 1 <= n == classroom[i].length <= 20
- classroom[i][j] is one of 'S', 'L', 'R', 'X', or '.'
- 1 <= energy <= 50
- There is exactly one 'S' in the grid.
- There are at most 10 'L' cells in the grid.

## Solution

**Language:** Java  
**Runtime:** 886 ms (beats 14.28%)  
**Memory:** 285.4 MB (beats 9.52%)  
**Submitted:** 2026-09-01T18:27:20.804Z  

```java
import java.util.*;

class Solution {
    public int minMoves(String[] g, int energy) {
        int m = g.length, n = g[0].length();
        int sr = 0, sc = 0, bit = 0, full = 0;
        int[][] litter = new int[10][2];

        for (int r = 0; r < m; r++)
            for (int c = 0; c < n; c++) {
                char ch = g[r].charAt(c);
                if (ch == 'S') { sr = r; sc = c; }
                else if (ch == 'L') litter[bit++] = new int[]{r, c};
            }
        full = (1 << bit) - 1;
        if (full == 0) return 0;

        boolean[][][] seen = new boolean[m * n][energy + 1][full + 1];
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sr, sc, energy, 0});
        seen[sr * n + sc][energy][0] = true;
        int[] d = {-1, 1, 0, 0, 0, 0, -1, 1}; 
        int moves = 0;

        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; i--) {
                int[] s = q.poll();
                int r = s[0], c = s[1], e = s[2], mask = s[3];
                if (mask == full) return moves;
                if (e == 0) continue;

                int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
                for (int[] dir : dirs) {
                    int nr = r + dir[0], nc = c + dir[1];
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                    char ch = g[nr].charAt(nc);
                    if (ch == 'X') continue;

                    int ne = ch == 'R' ? energy : e - 1;
                    int nm = mask;
                    for (int b = 0; b < bit; b++)
                        if (litter[b][0] == nr && litter[b][1] == nc) nm |= (1 << b);

                    int id = nr * n + nc;
                    if (!seen[id][ne][nm]) {
                        seen[id][ne][nm] = true;
                        q.add(new int[]{nr, nc, ne, nm});
                    }
                }
            }
            moves++;
        }
        return -1;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/minimum-moves-to-clean-the-classroom/)