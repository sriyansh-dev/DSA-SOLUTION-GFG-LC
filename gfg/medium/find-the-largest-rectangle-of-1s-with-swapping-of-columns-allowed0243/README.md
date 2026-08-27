# find-the-largest-rectangle-of-1s-with-swapping-of-columns-allowed0243

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

_Description not available._

## Solution

**Language:** C++  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-27T07:52:53.911Z  

```cpp
class Solution {
  public:
    int maxArea(vector<vector<int>>& mat) {
        // code here

        int n = mat.size();
        int m = mat[0].size();
        vector<int> heights(m, 0);
        int ans = 0;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < m; j++) {

                if (mat[i][j] == 1) {
                    heights[j]++;
                } else {
                    heights[j] = 0;
                }
            }


            vector<int> sortedH = heights;
            sort(begin(sortedH), end(sortedH), greater<int>());

            for (int j = 0; j < m; j++) {

                int w = sortedH[j];
                int h = j+1;

                ans = max(ans, w * h);
            }
        }

        return ans;
    }
};
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/find-the-largest-rectangle-of-1s-with-swapping-of-columns-allowed0243/1)