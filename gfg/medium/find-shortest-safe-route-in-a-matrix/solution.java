class Solution {
    public static int shortestPath(int[][] mat) {
        int n = mat.length;
        if (n == 0) return -1;
        int m = mat[0].length;

        // 1. Create a matrix to mark safe vs unsafe cells
        boolean[][] isSafe = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(isSafe[i], true);
        }

        // Direction vectors for checking adjacent landmines
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // Mark landmines and their neighbors as unsafe
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    isSafe[i][j] = false;
                    for (int k = 0; k < 4; k++) {
                        int ni = i + dx[k];
                        int nj = j + dy[k];
                        if (ni >= 0 && ni < n && nj >= 0 && nj < m) {
                            isSafe[ni][nj] = false;
                        }
                    }
                }
            }
        }

        // 2. Initialize BFS Queue and Visited Matrix
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        // Push all safe cells of the first column into the queue
        for (int i = 0; i < n; i++) {
            if (isSafe[i][0]) {
                queue.add(new int[]{i, 0, 1}); // {row, col, distance}
                visited[i][0] = true;
            }
        }

        // 3. Perform Multi-source BFS Traversal
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];
            int dist = current[2];

            // If we reached the rightmost column, return the step count
            if (c == m - 1) {
                return dist;
            }

            // Move in 4 possible directions
            for (int k = 0; k < 4; k++) {
                int nr = r + dx[k];
                int nc = c + dy[k];

                if (nr >= 0 && nr < n && nc >= 0 && nc < m && isSafe[nr][nc] && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    queue.add(new int[]{nr, nc, dist + 1});
                }
            }
        }

        // If no safe route exists to the destination column
        return -1;
    }
}