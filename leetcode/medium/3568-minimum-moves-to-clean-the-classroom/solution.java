import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int sr = -1, sc = -1;
        Map<Integer, Integer> litterBit = new HashMap<>(); // cellId -> bit index
        int litterCount = 0;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = classroom[r].charAt(c);
                if (ch == 'S') {
                    sr = r; sc = c;
                } else if (ch == 'L') {
                    litterBit.put(r * n + c, litterCount++);
                }
            }
        }

        int fullMask = (1 << litterCount) - 1;
        if (fullMask == 0) return 0;

        // visited[cellId][energy][mask]
        boolean[][][] visited = new boolean[m * n][energy + 1][fullMask + 1];

        Deque<int[]> queue = new ArrayDeque<>(); // {r, c, e, mask}
        queue.add(new int[]{sr, sc, energy, 0});
        visited[sr * n + sc][energy][0] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int moves = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int r = cur[0], c = cur[1], e = cur[2], mask = cur[3];

                if (mask == fullMask) return moves;
                if (e == 0) continue;

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d], nc = c + dc[d];
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;

                    char ch = classroom[nr].charAt(nc);
                    if (ch == 'X') continue;

                    int newE = (ch == 'R') ? energy : e - 1;
                    int newMask = mask;
                    Integer bit = litterBit.get(nr * n + nc);
                    if (bit != null) newMask |= (1 << bit);

                    int cellId = nr * n + nc;
                    if (!visited[cellId][newE][newMask]) {
                        visited[cellId][newE][newMask] = true;
                        queue.add(new int[]{nr, nc, newE, newMask});
                    }
                }
            }
            moves++;
        }

        return -1;
    }
}