class Solution {

    public int countTriplets(int[] arr, int l, int r) {
         Arrays.sort(arr);
         return countAtMost(arr, r) - countAtMost(arr, l - 1);
     }

     private int countAtMost(int[] arr, int x) {
         int n = arr.length;
         int count = 0;

         for (int i = 0; i < n - 2; i++) {
             int lo = i + 1, hi = n - 1;
             while (lo < hi) {
                 int sum = arr[i] + arr[lo] + arr[hi];
                 if (sum <= x) {
                     // all pairs (lo, lo+1..hi) with arr[lo] fixed give sum <= x
                     count += hi - lo;
                     lo++;
                 } else {
                     hi--;
                 }
             }
         }
         return count;
     }
}