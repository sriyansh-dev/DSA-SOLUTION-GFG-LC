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
