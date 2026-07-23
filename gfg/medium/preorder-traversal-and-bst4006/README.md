# preorder-traversal-and-bst4006

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

_Description not available._

## Solution

**Language:** C++  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-07-23T10:56:46.922Z  

```cpp
class Solution {
  public:
    bool canRepresentBST(vector<int> &arr) {
        stack<int> st;

        int lowerBound = INT_MIN;

        for (int value : arr) {

            if (value < lowerBound)
                return false;

            while (!st.empty() && value > st.top()) {

                lowerBound = st.top();

                st.pop();
            }

            st.push(value);
        }

        return true;
    }
};
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/preorder-traversal-and-bst4006/1)