class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] best = new int[n];
        int sum = 0, left = 0, minLen = Integer.MAX_VALUE, res = Integer.MAX_VALUE;
        int currentBest = Integer.MAX_VALUE;

        for (int right = 0; right < n; right++) {
            sum += arr[right];
            while (sum > target) {
                sum -= arr[left];
                left++;
            }
            if (sum == target) {
                int len = right - left + 1;
                // Check if there is a valid non-overlapping sub-array before 'left'
                if (left > 0 && best[left - 1] != Integer.MAX_VALUE) {
                    res = Math.min(res, len + best[left - 1]);
                }
                currentBest = Math.min(currentBest, len);
            }
            best[right] = currentBest;
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
