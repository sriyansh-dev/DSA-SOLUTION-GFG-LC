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