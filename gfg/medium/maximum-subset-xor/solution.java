class Solution {
    public int maxSubsetXOR(int[] arr) {
        int N = arr.length;
        int maxX = arr[0];
        
        // Find the maximum element in the array
        for (int i = 1; i < N; i++) {
            if (arr[i] > maxX) {
                maxX = arr[i];
            }
        }
        
        if (maxX == 0) return 0;
        
        int ans = 0;        
        while (true) {
            // Update answer with the maximum XOR possible with the current maxX
            ans = Math.max(ans, ans ^ maxX);
            
            // Reduce all elements using the current maxX
            for (int i = 0; i < N; i++) {
                arr[i] = Math.min(arr[i], arr[i] ^ maxX);
            }
            
            // Find the new maximum element in the updated array
            maxX = arr[0];
            for (int i = 0; i < N; i++) {
                maxX = Math.max(maxX, arr[i]);
            }
            
            // If the maximum element becomes 0, we can't maximize further
            if (maxX == 0) break;            
        }
        
        return ans;        
    }
}