public class Solution {
    public String compress(String s) {
        int n = s.length();
        int[] z = new int[n];
        for (int i = 1, l = 0, r = 0; i < n; i++) {
            if (i <= r) {
                z[i] = Math.min(r - i + 1, z[i - l]);
            }
            while (i + z[i] < n &&
                   s.charAt(z[i]) == s.charAt(i + z[i])) {
                z[i]++;
            }
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            if (i % 2 == 0) {
                int half = i / 2;
                if (z[half] >= half) {
                    dp[i] = Math.min(dp[i], dp[half] + 1);
                }
            }
        }
        StringBuilder ans = new StringBuilder();
        int i = n;
        while (i > 0) {
            if (i % 2 == 0) {
                int half = i / 2;

                if (z[half] >= half &&
                    dp[i] == dp[half] + 1) {

                    ans.append('*');
                    i = half;
                    continue;
                }
            }

            ans.append(s.charAt(i - 1));
            i--;
        }

        return ans.reverse().toString();
    }
}