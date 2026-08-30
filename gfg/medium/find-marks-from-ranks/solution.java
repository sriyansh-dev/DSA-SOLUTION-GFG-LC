class Solution {
    public ArrayList<Integer> getMarks(int[] l, int[] r, int[] ranks) {
        ArrayList<Integer> marks = new ArrayList<Integer>();
        int[] cummLength = new int[r.length + 1];

        for (int i = 1; i <= l.length; i++) {
            cummLength[i] = cummLength[i - 1] + r[i - 1] - l[i - 1] + 1;
        }

        for (int rank: ranks) {
            int i = findRange(cummLength, rank);
            marks.add(l[i - 1] + rank - cummLength[i - 1] - 1);
        }

        return marks;
    }

    private int findRange(int[] cummLength, int rank) {
        int low = 0;
        int high = cummLength.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (rank == cummLength[mid]) return mid;
            else if (rank > cummLength[mid]) low = mid + 1;
            else high = mid - 1;
        }

        return low;
    }
}