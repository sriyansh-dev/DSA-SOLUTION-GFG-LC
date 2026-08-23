class Solution {
public:
    int numberOfCells(int r, int c, int u, int d, vector<vector<char>> &mat) {
        int n = mat.size();
        int m = mat[0].size();

        if (mat[r][c] == '#')
            return 0;

        // dist[i][j] = minimum number of upward moves
        // required to reach (i,j)
        vector<vector<int>> dist(n, vector<int>(m, INT_MAX));

        deque<pair<int, int>> dq;

        dist[r][c] = 0;
        dq.push_front({r, c});

        int dx[] = {-1, 1, 0, 0};
        int dy[] = {0, 0, -1, 1};

        while (!dq.empty()) {
            auto [x, y] = dq.front();
            dq.pop_front();

            int curUp = dist[x][y];

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                    continue;

                if (mat[nx][ny] == '#')
                    continue;

                // Moving upward increases number of upward moves
                int newUp = curUp + (nx < x ? 1 : 0);

                // Number of downward moves used
                int newDown = newUp + (nx - r);

                if (newUp > u || newDown > d)
                    continue;

                if (newUp < dist[nx][ny]) {
                    dist[nx][ny] = newUp;

                    // Horizontal movement costs 0 upward moves,
                    // vertical upward movement costs 1.
                    if (nx == x)
                        dq.push_front({nx, ny});
                    else
                        dq.push_back({nx, ny});
                }
            }
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dist[i][j] == INT_MAX)
                    continue;

                int upUsed = dist[i][j];
                int downUsed = upUsed + (i - r);

                if (upUsed <= u && downUsed <= d)
                    ans++;
            }
        }

        return ans;
    }
};
