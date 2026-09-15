# leaf-under-budget

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

_Description not available._

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-09-15T18:16:41.729Z  

```java
import java.util.*;

class Solution {

    void collect(Node node, int depth, ArrayList<Integer> leaves) {
        if (node == null) return;

        // Leaf node
        if (node.left == null && node.right == null) {
            leaves.add(depth);
            return;
        }

        collect(node.left, depth + 1, leaves);
        collect(node.right, depth + 1, leaves);
    }

    public int getCount(Node root, int k) {
        ArrayList<Integer> leaves = new ArrayList<>();

        // Start depth from 1
        collect(root, 1, leaves);

        // Sort leaf depths
        Collections.sort(leaves);

        int count = 0;

        for (int cost : leaves) {
            if (cost > k) break;

            k -= cost;
            count++;
        }

        return count;
    }
}

```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/leaf-under-budget/1)