class Solution {
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        // dp[i] represents the max relative score a player can gain from subarray nums[i...j]
        int[] dp = new int[n];
        
        // Base case: Subarrays of length 1. The player must take that single element.
        for (int i = 0; i < n; i++) {
            dp[i] = nums[i];
        }
        
        // Build the solution for larger subarrays
        for (int len = 1; len < n; len++) {
            for (int i = 0; i < n - len; i++) {
                int j = i + len;
                // Maximum score choosing either the left boundary (i) or right boundary (j)
                dp[i] = Math.max(nums[i] - dp[i + 1], nums[j] - dp[i]);
            }
        }
        
        // Player 1 wins if the net score difference for the entire array is >= 0
        return dp[0] >= 0;
    }
}
