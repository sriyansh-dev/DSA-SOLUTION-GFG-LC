class Solution {
    public boolean winnerSquareGame(int n) {
        long[] dp = new long[(n >> 6) + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                int prev = i - j * j;
                if ((dp[prev >> 6] & (1L << (prev & 63))) == 0) {
                    dp[i >> 6] |= (1L << (i & 63));
                    break;
                }
            }
        }
        return (dp[n >> 6] & (1L << (n & 63))) != 0;
    }
}