# Maximize Active Section with Trade II

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given a binary string `s` of length `n`, where:

- '1' represents an active section.
- '0' represents an inactive section.

You can perform  **at most one trade**  to maximize the number of active sections in `s`. In a trade, you:

- Convert a contiguous block of '1's that is surrounded by '0's to all '0's.
- Afterward, convert a contiguous block of '0's that is surrounded by '1's to all '1's.

Additionally, you are given a  **2D array**  `queries`, where `queries[i] = [li, ri]` represents a substring `s[li...ri]`.

For each query, determine the  **maximum**  possible number of active sections in `s` after making the optimal trade on the substring `s[li...ri]`.

Return an array `answer`, where `answer[i]` is the result for `queries[i]`.

 **Note** 

- For each query, treat s[li...ri] as if it is augmented with a '1' at both ends, forming t = '1' + s[li...ri] + '1'. The augmented '1's do not contribute to the final count.
- The queries are independent of each other.

 

 **Example 1:** 

 **Input:**  s = "01", queries = [[0,1]]

 **Output:**  [1]

 **Explanation:** 

Because there is no block of `'1'`s surrounded by `'0'`s, no valid trade is possible. The maximum number of active sections is 1.

 **Example 2:** 

 **Input:**  s = "0100", queries = [[0,3],[0,2],[1,3],[2,3]]

 **Output:**  [4,3,1,1]

 **Explanation:** 

- Query [0, 3] → Substring "0100" → Augmented to "101001" Choose "0100", convert "0100" → "0000" → "1111". The final string without augmentation is "1111". The maximum number of active sections is 4.
- Query [0, 2] → Substring "010" → Augmented to "10101" Choose "010", convert "010" → "000" → "111". The final string without augmentation is "1110". The maximum number of active sections is 3.
- Query [1, 3] → Substring "100" → Augmented to "11001" Because there is no block of '1's surrounded by '0's, no valid trade is possible. The maximum number of active sections is 1.
- Query [2, 3] → Substring "00" → Augmented to "1001" Because there is no block of '1's surrounded by '0's, no valid trade is possible. The maximum number of active sections is 1.

 **Example 3:** 

 **Input:**  s = "1000100", queries = [[1,5],[0,6],[0,4]]

 **Output:**  [6,7,2]

 **Explanation:** 

- Query [1, 5] → Substring "00010" → Augmented to "1000101" Choose "00010", convert "00010" → "00000" → "11111". The final string without augmentation is "1111110". The maximum number of active sections is 6.
- Query [0, 6] → Substring "1000100" → Augmented to "110001001" Choose "000100", convert "000100" → "000000" → "111111". The final string without augmentation is "1111111". The maximum number of active sections is 7.
- Query [0, 4] → Substring "10001" → Augmented to "1100011" Because there is no block of '1's surrounded by '0's, no valid trade is possible. The maximum number of active sections is 2.

 **Example 4:** 

 **Input:**  s = "01010", queries = [[0,3],[1,4],[1,3]]

 **Output:**  [4,4,2]

 **Explanation:** 

- Query [0, 3] → Substring "0101" → Augmented to "101011" Choose "010", convert "010" → "000" → "111". The final string without augmentation is "11110". The maximum number of active sections is 4.
- Query [1, 4] → Substring "1010" → Augmented to "110101" Choose "010", convert "010" → "000" → "111". The final string without augmentation is "01111". The maximum number of active sections is 4.
- Query [1, 3] → Substring "101" → Augmented to "11011" Because there is no block of '1's surrounded by '0's, no valid trade is possible. The maximum number of active sections is 2.

 

 **Constraints:** 

- 1 <= n == s.length <= 105
- 1 <= queries.length <= 105
- s[i] is either '0' or '1'.
- queries[i] = [li, ri]
- 0 <= li <= ri < n

## Solution

**Language:** Java  
**Runtime:** 249 ms (beats 30.00%)  
**Memory:** 276.1 MB (beats 5.00%)  
**Submitted:** 2026-07-22T12:06:24.901Z  

```java
class Solution {
    static void buildSegmentTree(int i, int l, int r, int[] segmentTree, int[] arr) {
        if (l == r) {
            segmentTree[i] = arr[l];
            return;
        }

        int mid = l + (r - l) / 2;
        buildSegmentTree(2 * i + 1, l, mid, segmentTree, arr);
        buildSegmentTree(2 * i + 2, mid + 1, r, segmentTree, arr);
        segmentTree[i] = Math.max(segmentTree[2 * i + 1], segmentTree[2 * i + 2]);
    }

    static int[] constructST(int[] arr, int n) {
        int[] segmentTree = new int[4 * n];
        buildSegmentTree(0, 0, n - 1, segmentTree, arr);
        return segmentTree;
    }

    static int querySegmentTree(int start, int end, int i, int l, int r, int[] segmentTree) {
        if (l > end || r < start) {
            return Integer.MIN_VALUE;
        }

        if (l >= start && r <= end) {
            return segmentTree[i];
        }

        int mid = l + (r - l) / 2;
        return Math.max(querySegmentTree(start, end, 2 * i + 1, l, mid, segmentTree),
                        querySegmentTree(start, end, 2 * i + 2, mid + 1, r, segmentTree));
    }

    static int RMQ(int[] st, int n, int a, int b) {
        return querySegmentTree(a, b, 0, 0, n - 1, st);
    }

    // first index k with arr[k] >= key   (C++ lower_bound)
    static int lowerBound(int[] arr, int len, int key) {
        int lo = 0, hi = len;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] < key) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    // first index k with arr[k] > key    (C++ upper_bound)
    static int upperBound(int[] arr, int len, int key) {
        int lo = 0, hi = len;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] <= key) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();

        int activeCount = 0;
        for (int idx = 0; idx < n; idx++) {
            if (s.charAt(idx) == '1') activeCount++;
        }

        // scan zero-blocks, recording where each one sits
        int[] blockStart = new int[n];
        int[] blockEnd = new int[n];
        int m = 0;
        int i = 0;
        while (i < n) {
            if (s.charAt(i) == '0') {
                int start = i;
                while (i < n && s.charAt(i) == '0') i++;
                blockStart[m] = start;
                blockEnd[m] = i - 1;
                m++;
            } else {
                i++;
            }
        }

        //If there is only one block of zeros
        //example : s = "11000011" , answer = simply count of 1s "activeCount"
        if (m < 2) {
            List<Integer> res = new ArrayList<>();
            for (int k = 0; k < queries.length; k++) res.add(activeCount);
            return res;
        }

        int[] blockSize = new int[m];
        for (int k = 0; k < m; k++) {
            blockSize[k] = blockEnd[k] - blockStart[k] + 1;
        }

        // pairSum[k] = blockSize[k] + blockSize[k+1], gain from activating adjacent blocks
        int N = m - 1; //This many pair sum will be there in pairSum
        int[] pairSum = new int[N];
        for (int k = 0; k < N; k++) {
            pairSum[k] = blockSize[k] + blockSize[k + 1];
        }

        int[] st = constructST(pairSum, N);

        List<Integer> result = new ArrayList<>();
        for (int[] q : queries) {              // O(q * log n)
            int l = q[0];
            int r = q[1];

            // first block reaching into the window from the left
            int low  = lowerBound(blockEnd, m, l);          // log
            // last block reaching into the window from the right
            int high = upperBound(blockStart, m, r) - 1;    // log

            int maxPairSum = 0;
            if (low < high) {                  // need at least two blocks in the window
                int firstLen = blockEnd[low] - Math.max(blockStart[low], l) + 1;
                int lastLen  = Math.min(blockEnd[high], r) - blockStart[high] + 1;

                if (high - low == 1) {         // exactly two blocks only
                    maxPairSum = firstLen + lastLen;
                } else {
                    int pair1 = firstLen + blockSize[low + 1];
                    int pair2 = blockSize[high - 1] + lastLen;
                    int rmqMaxPairSum = (low + 1 <= high - 2) ? RMQ(st, N, low + 1, high - 2) : 0; 
                    maxPairSum = Math.max(pair1, Math.max(pair2, rmqMaxPairSum));
                }
            }
            result.add(maxPairSum + activeCount);
        }

        return result;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/maximize-active-section-with-trade-ii/)