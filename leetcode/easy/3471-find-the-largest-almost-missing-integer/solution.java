import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        
        // Count total occurrences of each number in the array
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }
        
        // Case 1: Subarray size is 1
        if (k == 1) {
            int maxVal = -1;
            for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
                if (entry.getValue() == 1) {
                    maxVal = Math.max(maxVal, entry.getKey());
                }
            }
            return maxVal;
        }
        
        // Case 2: Subarray size is equal to the full length of the array
        if (k == n) {
            int maxVal = -1;
            for (int num : nums) {
                maxVal = Math.max(maxVal, num);
            }
            return maxVal;
        }
        
        // Case 3: 1 < k < n
        int ans = -1;
        // Check first element
        if (counts.get(nums[0]) == 1) {
            ans = Math.max(ans, nums[0]);
        }
        // Check last element
        if (counts.get(nums[n - 1]) == 1) {
            ans = Math.max(ans, nums[n - 1]);
        }
        
        return ans;
    }
}
