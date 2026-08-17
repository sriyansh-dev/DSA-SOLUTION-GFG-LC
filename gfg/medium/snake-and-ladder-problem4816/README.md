# snake-and-ladder-problem4816

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

_Description not available._

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-17T07:32:03.527Z  

```java
import java.util.*;
class Solution {
    public int minThrows(int n, int[] lad, int[] sn) {
        int N = n * n;
        int[] jump = new int[N + 1];
        Arrays.fill(jump, -1);
        for (int i = 0; i < lad.length; i += 2) {
            jump[lad[i]] = lad[i + 1];
        }
        for (int i = 0; i < sn.length; i += 2) {
            jump[sn[i]] = sn[i + 1];
        }
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        q.offer(1);
        visited[1] = true;
        int throwsCount = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int curr = q.poll();
                if (curr == N) {
                    return throwsCount;
                }
                for (int dice = 1; dice <= 6; dice++) {
                    int next = curr + dice;
                    if (next > N) {
                        continue;
                    }
                    if (jump[next] != -1) {
                        next = jump[next];
                    }
                    if (!visited[next]) {
                        visited[next] = true;
                        q.offer(next);
                    }
                }
            }
            throwsCount++;
        }

        return -1;
    }
}
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/snake-and-ladder-problem4816/1)