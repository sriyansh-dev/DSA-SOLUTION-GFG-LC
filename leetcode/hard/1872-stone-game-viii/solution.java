class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        
        // Step 1: build prefix sums in place
        for (int i = 1; i < n; i++) {
            stones[i] += stones[i - 1];
        }
        
        // Step 2: dp from right to left
        // dp = best score-difference the current player can force
        // starting with the earliest allowed stop being the last index
        int dp = stones[n - 1];
        
        for (int i = n - 2; i >= 1; i--) {
            dp = Math.max(dp, stones[i] - dp);
        }
        
        return dp;
    }
}