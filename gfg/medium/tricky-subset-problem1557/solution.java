class Solution {
    public boolean isPossible(int[] arr, int s, int x) {
        ArrayList<Long> nums = new ArrayList<>();

        long total = s;
        nums.add((long) s);

        // Generate the numbers written on the paper
        for (int a : arr) {
            long next = total + a;

            // All future numbers will be even larger
            if (next > x)
                break;

            nums.add(next);
            total += next;
        }

        // Greedily try to form x
        long target = x;

        for (int i = nums.size() - 1; i >= 0; i--) {
            if (nums.get(i) <= target) {
                target -= nums.get(i);
            }

            if (target == 0)
                return true;
        }

        return false;
    }
}