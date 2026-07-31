class Solution {
    public int minimumPushes(String word) {
        int[] freq = new int[26];
        
        // .getBytes() is vastly faster than .toCharArray() or .charAt() for ASCII
        for (byte b : word.getBytes()) {
            freq[b - 'a']++;
        }
        
        java.util.Arrays.sort(freq);
        
        int totalPushes = 0;
        
        // Fused the condition directly into the loop header to save operations
        for (int i = 25; i >= 0 && freq[i] > 0; i--) {
            totalPushes += ((25 - i) / 8 + 1) * freq[i];
        }
        
        return totalPushes;
    }
}