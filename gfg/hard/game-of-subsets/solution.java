class Solution {
    public int countSubsets(int[] arr) {
        final int MOD = 1000000007;
        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};

        int[] freq = new int[31];
        for (int x : arr) {
            freq[x]++;
        }

        int[] mask = new int[31];
        for (int i = 0; i < 31; i++) {
            mask[i] = -1;
        }

        for (int x = 2; x <= 30; x++) {
            int t = x;
            int m = 0;
            boolean ok = true;

            for (int i = 0; i < 10; i++) {
                int cnt = 0;
                while (t % primes[i] == 0) {
                    t /= primes[i];
                    cnt++;
                }
                if (cnt > 1) {
                    ok = false;
                    break;
                }
                if (cnt == 1) {
                    m |= (1 << i);
                }
            }

            if (ok) {
                mask[x] = m;
            }
        }

        long[] dp = new long[1 << 10];
        dp[0] = 1;

        for (int x = 2; x <= 30; x++) {
            if (freq[x] == 0 || mask[x] == -1) continue;

            int m = mask[x];
            for (int s = (1 << 10) - 1; s >= 0; s--) {
                if ((s & m) == 0) {
                    dp[s | m] = (dp[s | m] + dp[s] * freq[x]) % MOD;
                }
            }
        }

        long ans = 0;
        for (int s = 1; s < (1 << 10); s++) {
            ans = (ans + dp[s]) % MOD;
        }

        long pw = 1;
        int count1 = freq[1];
        while (count1-- > 0) {
            pw = (pw * 2) % MOD;
        }

        ans = (ans * pw) % MOD;

        return (int) ans;
    }
}