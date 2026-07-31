import java.util.Arrays;

class Solution {
    public int minimumPushes(String word) {
        int[] frequency = new int[26];
        int n = word.length();
        
        // Cache length and use charAt() to avoid O(N) memory allocation overhead
        for (int i = 0; i < n; i++) {
            frequency[word.charAt(i) - 'a']++;
        }
        
        Arrays.sort(frequency);
        
        int totalPushes = 0;
        
        for (int i = 25; i >= 0; i--) {
            if (frequency[i] == 0) break;
            
            // Bitwise right shift (>> 3) is mathematically equivalent to division by 8 
            // but executes slightly faster at the CPU level
            totalPushes += (((25 - i) >> 3) + 1) * frequency[i];
        }
        
        return totalPushes;
    }
}