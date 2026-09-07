class Solution {
    public int minCount(int[] arr) {
        int n = arr.length;
        int MAX = 102;

        // inc = last value of increasing subsequence
        // dec = last value of decreasing subsequence
        // 101 = no element selected in increasing subsequence
        // 0   = no element selected in decreasing subsequence

        int[][] dp = new int[MAX][MAX];

        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                dp[i][j] = -1;
            }
        }

        dp[101][0] = 0;

        for (int x : arr) {
            int[][] next = new int[MAX][MAX];

            for (int i = 0; i < MAX; i++) {
                for (int j = 0; j < MAX; j++) {
                    next[i][j] = -1;
                }
            }

            for (int inc = 0; inc < MAX; inc++) {
                for (int dec = 0; dec < MAX; dec++) {

                    if (dp[inc][dec] == -1) {
                        continue;
                    }

                    int selected = dp[inc][dec];

                    // 1. Do not use x
                    next[inc][dec] = Math.max(
                        next[inc][dec],
                        selected
                    );

                    // 2. Put x in increasing subsequence
                    if (inc == 101 || x > inc) {
                        next[x][dec] = Math.max(
                            next[x][dec],
                            selected + 1
                        );
                    }

                    // 3. Put x in decreasing subsequence
                    if (dec == 0 || x < dec) {
                        next[inc][x] = Math.max(
                            next[inc][x],
                            selected + 1
                        );
                    }
                }
            }

            dp = next;
        }

        int maxSelected = 0;

        for (int inc = 0; inc < MAX; inc++) {
            for (int dec = 0; dec < MAX; dec++) {
                maxSelected = Math.max(maxSelected, dp[inc][dec]);
            }
        }

        return n - maxSelected;
    }
}