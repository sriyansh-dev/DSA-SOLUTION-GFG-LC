class Solution {
    public int countSquare(int[][] mat, int x) {
        int n = mat.length, m = mat[0].length;

        // Build prefix sum matrix
        int[][] prefix = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                prefix[i][j] = mat[i-1][j-1] 
                              + prefix[i-1][j] 
                              + prefix[i][j-1] 
                              - prefix[i-1][j-1];
            }
        }

        int count = 0;

        // Try all square sizes
        for (int len = 1; len <= Math.min(n, m); len++) {
            for (int i = 0; i + len <= n; i++) {
                for (int j = 0; j + len <= m; j++) {
                    int r1 = i, c1 = j;
                    int r2 = i + len, c2 = j + len;

                    // Sum of submatrix (i..i+len-1, j..j+len-1)
                    int sum = prefix[r2][c2] 
                            - prefix[r1][c2] 
                            - prefix[r2][c1] 
                            + prefix[r1][c1];

                    if (sum == x) count++;
                }
            }
        }

        return count;

    }
}