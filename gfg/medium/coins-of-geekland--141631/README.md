# coins-of-geekland--141631

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

_Description not available._

## Solution

**Language:** C++  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-07-25T12:38:20.046Z  

```cpp
class Solution {
	public:
	int maximumSum(vector<vector<int>> & mat, int k) {
		int n = mat.size();
		
		vector<vector<int>> vec(n + 1, vector<int>(n + 1, 0));
		
		int res = INT_MIN;
		
		for (int i = 0; i<n; i++)
		{
			for (int j = 0; j<n; j++)
			{
				vec[i + 1][j + 1] = vec[i][j + 1] + vec[i + 1][j] - vec[i][j] + mat[i][j];
				
				if (i+1 >= k && j+1 >= k)
					res = max(res, vec[i + 1][j + 1] - vec[i + 1 - k][j + 1] - vec[i + 1][j + 1 - k] + vec[i + 1 - k][j + 1 - k]);
			}
		}
		
		return res;
	}
};

```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/coins-of-geekland--141631/1)