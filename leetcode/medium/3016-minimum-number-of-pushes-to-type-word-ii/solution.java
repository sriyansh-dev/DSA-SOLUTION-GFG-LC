import java.util.Arrays;

class Solution {
    public int minimumPushes(String word) {
        // Frequency array to store count of each letter
        int[] frequency = new int[26];
        
        // Count occurrences of each letter
        for (char c : word.toCharArray()) {
            frequency[c - 'a']++;
        }
        
        // Sort frequencies in ascending order
        Arrays.sort(frequency);
        
        int totalPushes = 0;
        
        // Calculate total number of presses by iterating from the end (descending order)
        for (int i = 25; i >= 0; i--) {
            if (frequency[i] == 0) break;
            // (25 - i) mimics the 0-indexed position in a descending array
            totalPushes += ((25 - i) / 8 + 1) * frequency[i];
        }
        
        return totalPushes;
    }
}