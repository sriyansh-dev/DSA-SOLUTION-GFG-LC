# Maximum Number of Non-Overlapping Substrings

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

Given a string `s` of lowercase letters, you need to find the maximum number of  **non-empty**  substrings of `s` that meet the following conditions:

- The substrings do not overlap, that is for any two substrings s[i..j] and s[x..y], either j < x or i > y is true.
- A substring that contains a certain character c must also contain all occurrences of c.

Find  *the maximum number of substrings that meet the above conditions*. If there are multiple solutions with the same number of substrings,  *return the one with minimum total length.* It can be shown that there exists a unique solution of minimum total length.

Notice that you can return the substrings in  **any**  order.

 

 **Example 1:** 

```
Input: s = "adefaddaccc"
Output: ["e","f","ccc"]
Explanation: The following are all the possible substrings that meet the conditions:
[
  "adefaddaccc"
  "adefadda",
  "ef",
  "e",
  "f",
  "ccc",
]
If we choose the first string, we cannot choose anything else and we'd get only 1. If we choose "adefadda", we are left with "ccc" which is the only one that doesn't overlap, thus obtaining 2 substrings. Notice also, that it's not optimal to choose "ef" since it can be split into two. Therefore, the optimal way is to choose ["e","f","ccc"] which gives us 3 substrings. No other solution of the same number of substrings exist.

```

 **Example 2:** 

```
Input: s = "abbaccd"
Output: ["d","bb","cc"]
Explanation: Notice that while the set of substrings ["d","abba","cc"] also has length 3, it's considered incorrect since it has larger total length.

```

 

 **Constraints:** 

- 1 <= s.length <= 105
- s contains only lowercase English letters.

## Solution

**Language:** C++  
**Runtime:** 7 ms (beats 84.19%)  
**Memory:** 23.3 MB (beats 71.64%)  
**Submitted:** 2026-09-18T16:55:49.759Z  

```cpp
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

class Solution {
public:
    vector<string> maxNumOfSubstrings(string s) {
        int n = s.length();
        vector<int> first(26, -1);
        vector<int> last(26, -1);

        // Step 1: Record the first and last occurrence of each character
        for (int i = 0; i < n; ++i) {
            int ch = s[i] - 'a';
            if (first[ch] == -1) {
                first[ch] = i;
            }
            last[ch] = i;
        }

        // Step 2: Find all valid intervals starting at first[ch]
        vector<pair<int, int>> intervals;

        for (int i = 0; i < 26; ++i) {
            if (first[i] == -1) continue;

            int left = first[i];
            int right = last[i];
            bool valid = true;

            // Expand the interval to encompass all characters found inside it
            for (int j = left; j <= right; ++j) {
                int ch = s[j] - 'a';
                // If a character appears before the current left boundary,
                // a valid minimal substring cannot start at 'left'
                if (first[ch] < left) {
                    valid = false;
                    break;
                }
                right = max(right, last[ch]);
            }

            if (valid) {
                intervals.push_back({left, right});
            }
        }

        // Step 3: Greedy interval scheduling (sort by end time)
        sort(intervals.begin(), intervals.end(), [](const pair<int, int>& a, const pair<int, int>& b) {
            return a.second < b.second;
        });

        vector<string> result;
        int prev_end = -1;

        for (const auto& interval : intervals) {
            if (interval.first > prev_end) {
                result.push_back(s.substr(interval.first, interval.second - interval.first + 1));
                prev_end = interval.second;
            }
        }

        return result;
    }
};

```

---

[View on LeetCode](https://leetcode.com/problems/maximum-number-of-non-overlapping-substrings/)