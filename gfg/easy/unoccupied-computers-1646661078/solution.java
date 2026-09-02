class Solution {
    public int solve(int n, String s) {
        // status array to keep track of each customer (A-Z)
        // 0 = not arrived, 1 = occupying computer, -1 = rejected
        int[] status = new int[26];

        int occupied = 0;
        int rejectedCount = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = c - 'A';

            // First time seeing the customer (Arrival)
            if (status[index] == 0) {
                if (occupied < n) {
                    status[index] = 1; // Assign a computer
                    occupied++;
                } else {
                    status[index] = -1; // Reject the customer
                    rejectedCount++;
                }
            } 
            // Second time seeing the customer (Departure)
            else if (status[index] == 1) {
                // Only free up a computer if they actually had one
                occupied--;
            }
        }

        return rejectedCount;
    }
}