class Solution {

    long nCr(int n, int r, long k) {
        if (r == 0 || r == n) return 1;
        r = Math.min(r, n - r);
        long result = 1;

        for (int i = 1; i <= r; i++) {
            result = result * (n - r + i) / i;
            if (result >= k) return k;
        }

        return result;
    }

    public String smallestPalindrome(String s, int k) {
        int n = s.length();
        char[] chars = s.toCharArray();
        
        char mid = ' ';
        if (n % 2 == 1) {
            mid = chars[n / 2];
        }

        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            if (n % 2 == 1 && i == n / 2) continue;
            count[chars[i] - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            count[i] /= 2;
        }

        int half = n / 2;
        char[] res = new char[n];
        if (n % 2 == 1) {
            res[half] = mid;
        }

        int totalLetters = half;
        long currentK = k;

        for (int i = 0; i < half; i++) {
            boolean placedCharacter = false;
            
            for (int j = 0; j < 26; j++) {
                if (count[j] > 0) {
                    count[j]--;

                    long ways = 1;
                    int letters = totalLetters - 1;
                    
                    for (int c = 0; c < 26; c++) {
                        if (count[c] > 0) {
                            ways *= nCr(letters, count[c], currentK);
                            if (ways >= currentK) {
                                ways = currentK;
                                break;
                            }
                            letters -= count[c];
                        }
                    }

                    if (ways >= currentK) {
                        res[i] = (char) (j + 'a');
                        res[n - 1 - i] = (char) (j + 'a');
                        totalLetters--;
                        placedCharacter = true;
                        break;
                    }

                    currentK -= ways;
                    count[j]++;
                }
            }

            if (!placedCharacter) return "";
        }

        return new String(res);
    }
}