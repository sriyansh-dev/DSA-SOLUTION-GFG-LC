class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;

        if (n == 1 || n == 2)
            return n;

        int ans = 1; // 2^0
        while (ans <= n) {
            ans = (ans << 1);
        }

        return ans;
    }
}