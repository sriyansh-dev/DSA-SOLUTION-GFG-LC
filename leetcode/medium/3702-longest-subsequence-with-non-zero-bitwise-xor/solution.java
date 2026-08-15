class Solution {
    public int longestSubsequence(int[] nums) {
        int n = nums.length;
        int totalXor = 0;
        boolean allZeros = true;

        for (int num : nums) {
            totalXor ^= num;
            if (num != 0) {
                allZeros = false;
            }
        }

        // If the array contains only zeros, no non-zero XOR subsequence can be formed.
        if (allZeros) {
            return 0;
        }

        // If the total XOR sum of all elements is already non-zero, 
        // the longest subsequence is the entire array itself.
        if (totalXor != 0) {
            return n;
        }

        // If the total XOR is zero (and elements are not all zero), 
        // removing exactly one non-zero element will break the zero-balance, 
        // making the XOR sum of the remaining (n - 1) elements non-zero.
        return n - 1;
    }
}
