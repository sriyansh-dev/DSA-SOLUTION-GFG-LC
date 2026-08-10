class Solution {
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];
        int maxRoot = (int) Math.sqrt(n);
        int[] sq = new int[maxRoot + 1];
        for (int k = 1; k <= maxRoot; k++) sq[k] = k * k;

        for (int i = 1; i <= n; i++) {
            for (int k = 1; k <= maxRoot && sq[k] <= i; k++) {
                if (!dp[i - sq[k]]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
