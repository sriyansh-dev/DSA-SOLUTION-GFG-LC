class Solution {

    public static int countPairs(int arr[], int k) {

        Arrays.sort(arr);

        int i = 0, cnt = 0;

        

        // Expansion pointer

        for (int j = 0; j < arr.length; j++) {

            // Shrink window from left until the difference is strictly less than k

            while (arr[j] - arr[i] >= k) {

                i++;

            }

            // All elements from index i to j-1 form a valid pair with arr[j]

            cnt += (j - i);

        }

        return cnt;

    }

}
