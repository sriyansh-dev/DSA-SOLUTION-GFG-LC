class Solution {
    public static int findMax(int n) {
        String s = Integer.toString(n);
        int len = s.length();

        int bestNum = n;
        int maxSum = getSum(n);

        for (int i = 0; i < len; i++) {
            char[] arr = s.toCharArray();

            if (arr[i] > '0') {
                arr[i]--;
                for (int j = i + 1; j < len; j++) {
                    arr[j] = '9';
                }

                int cand = Integer.parseInt(new String(arr));
                int candSum = getSum(cand);

                if (candSum > maxSum || (candSum == maxSum && cand > bestNum)) {
                    maxSum = candSum;
                    bestNum = cand;
                }
            }
        }

        return bestNum;
    }

    private static int getSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}