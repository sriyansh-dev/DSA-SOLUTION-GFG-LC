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
**Runtime:** 335 ms (beats 69.05%)  
**Memory:** 147 MB (beats 69.05%)  
**Submitted:** 2026-09-01T18:28:43.724Z  

```java
import java.util.*;

class Solution {
    public int minMoves(String[] g, int energy) {
        int m = g.length, n = g[0].length();
        int sr = 0, sc = 0, bit = 0;
        int[] litterBitOf = new int[m * n];
        Arrays.fill(litterBitOf, -1);

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = g[r].charAt(c);
                if (ch == 'S') { sr = r; sc = c; }
                else if (ch == 'L') litterBitOf[r * n + c] = bit++;
            }
        }

        int full = (1 << bit) - 1;
        if (full == 0) return 0;

        int maskCnt = full + 1;
        int energyCnt = energy + 1;
        int cellStride = energyCnt * maskCnt; // per cell
        int total = m * n * cellStride;

        boolean[] seen = new boolean[total];

        int[] queue = new int[1 << 16];
        int head = 0, tail = 0, size = 0;

        int startIdx = (sr * n + sc) * cellStride + energy * maskCnt + 0;
        queue[tail++] = startIdx;
        size++;
        seen[startIdx] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        int moves = 0;

        while (size > 0) {
            int levelSize = size;
            for (int i = 0; i < levelSize; i++) {
                int idx = queue[head++];
                if (head == queue.length) head = 0;
                size--;

                int mask = idx % maskCnt;
                int t1 = idx / maskCnt;
                int e = t1 % energyCnt;
                int cellId = t1 / energyCnt;
                int r = cellId / n, c = cellId % n;

                if (mask == full) return moves;
                if (e == 0) continue;

                for (int k = 0; k < 4; k++) {
                    int nr = r + dr[k], nc = c + dc[k];
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;

                    char ch = g[nr].charAt(nc);
                    if (ch == 'X') continue;

                    int ne = (ch == 'R') ? energy : e - 1;
                    int nCellId = nr * n + nc;
                    int litBit = litterBitOf[nCellId];
                    int nm = (litBit == -1) ? mask : (mask | (1 << litBit));

                    int nIdx = nCellId * cellStride + ne * maskCnt + nm;
                    if (!seen[nIdx]) {
                        seen[nIdx] = true;
                        // grow queue if full
                        if (size == queue.length) {
                            int[] bigger = new int[queue.length * 2];
                            int n1 = queue.length - head;
                            System.arraycopy(queue, head, bigger, 0, n1);
                            System.arraycopy(queue, 0, bigger, n1, tail);
                            queue = bigger;
                            head = 0;
                            tail = size;
                        }
                        queue[tail++] = nIdx;
                        if (tail == queue.length) tail = 0;
                        size++;
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