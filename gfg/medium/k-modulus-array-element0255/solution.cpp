class Solution {
  public:
    int sameMod(vector<int> &arr) {

        int g = 0;

        for (int i = 1; i < arr.size(); i++) {
            g = gcd(g, abs(arr[i] - arr[0]));
        }

        if (g == 0)
            return -1;

        int count = 0;

        for (int k = 1; k * k <= g; k++) {
            if (g % k == 0) {
                count++;

                if (k != g / k)
                    count++;
            }
        }

            return count;
        }
    };
