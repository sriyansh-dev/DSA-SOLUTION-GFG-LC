import java.util.HashMap;

class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        int n = nums.length;

        // Compress each value to a dense index in ONE pass — O(n), no sorting
        HashMap<Integer, Integer> idMap = new HashMap<>();
        int[] compressed = new int[n];
        int nextId = 0;
        for (int i = 0; i < n; i++) {
            Integer id = idMap.get(nums[i]);
            if (id == null) {
                id = nextId++;
                idMap.put(nums[i], id);
            }
            compressed[i] = id;
        }

        // Sliding window on a plain int[] — zero boxing, zero hashing here
        int[] freq = new int[nextId];
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