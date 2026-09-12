# maximum-product4633

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

_Description not available._

## Solution

**Language:** C++  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-09-12T16:25:52.339Z  

```cpp
class Solution {
  public:
    int maxProduct(vector<int> &arr, int K) {
        sort(arr.begin(), arr.end());
        int ans = 1, i = 0, j = arr.size()-1, k = K;

        while(k)
        {
            // we can take 2 -ve if they are greater together else max positive
            if(k > 1 && i < j && (arr[i] * arr[i+1]) >= (arr[j-1] * arr[j]))
            {
                ans *= arr[i] * arr[i+1];
                i += 2;
                k -= 2;
            }
            else
            {
                ans *= arr[j--];
                k--;
            }
        }

        // if overall ans is negative we need min negative
        // as above strategy maximizes the answer
        if(ans < 0)
        {
            // sort by abs value
            sort(arr.begin(), arr.end(), [](int &a, int &b){
                return abs(a) < abs(b);
            });

            ans = 1, i = 0;
            while(K--)
                ans *= arr[i++];
        }

        return ans;
    }
};
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/maximum-product4633/1)