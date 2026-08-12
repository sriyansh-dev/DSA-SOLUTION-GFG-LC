import java.util.Arrays;

class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        int n = nums.length;

        // Compress values into a dense range [0, m) using a sorted+deduped array
        int[] sortedUnique = nums.clone();
        Arrays.sort(sortedUnique);
        int m = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 || sortedUnique[i] != sortedUnique[i - 1]) {
                sortedUnique[m++] = sortedUnique[i];
            }
        }

        // Map every element to its compressed index (one binary search each, done once)
        int[] compressed = new int[n];
        for (int i = 0; i < n; i++) {
            compressed[i] = Arrays.binarySearch(sortedUnique, 0, m, nums[i]);
        }

        // Sliding window now runs on a plain int[] — no boxing, no hashing
        int[] freq = new int[m];
        int left = 0, maxLength = 0;

        for (int right = 0; right < n; right++) {
            int c = compressed[right];
            freq[c]++;
            while (freq[c] > k) {
                freq[compressed[left]]--;
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}