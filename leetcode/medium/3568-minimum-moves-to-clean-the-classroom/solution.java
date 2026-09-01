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