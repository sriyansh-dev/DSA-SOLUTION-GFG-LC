# Secret Cipher

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

Geek wants to send a secret message to his friend Keeg. Instead of sending the original message directly, he encrypts it by inserting the character '*'.

Keeg decodes the message as follows:

- Traverse the encoded string from left to right and initialize the original string as empty.
- Whenever a normal character appears, append it to the current original string.
- Whenever '*' is encountered, remove it and append all characters before it to the end of the current original string.
- Repeat until no '*' remains.

Given the original string s, find the lexicographically smallest encrypted string that decodes to s.

 **Examples :** 

```
Input: s = "ababcababcd"
Output: ab *c* d
Explanation: We can encrypt the string in following way : "ababcababcd" -> "ababc *d" -> "ab* c*d"

```

```
Input: s = "zzzzzzz"
Output: z *z* z
Explanation: The string can be encrypted in 2 ways: "z *z* z" and "z**zzz". Out of the two "z *z* z" is smaller in length.
```

 **Constraints:** 
1 ≤ |s| ≤ 105

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-18T15:49:47.584Z  

```java
public class Solution {
    public String compress(String s) {
        int n = s.length();
        int[] z = new int[n];
        for (int i = 1, l = 0, r = 0; i < n; i++) {
            if (i <= r) {
                z[i] = Math.min(r - i + 1, z[i - l]);
            }
            while (i + z[i] < n &&
                   s.charAt(z[i]) == s.charAt(i + z[i])) {
                z[i]++;
            }
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            if (i % 2 == 0) {
                int half = i / 2;
                if (z[half] >= half) {
                    dp[i] = Math.min(dp[i], dp[half] + 1);
                }
            }
        }
        StringBuilder ans = new StringBuilder();
        int i = n;
        while (i > 0) {
            if (i % 2 == 0) {
                int half = i / 2;

                if (z[half] >= half &&
                    dp[i] == dp[half] + 1) {

                    ans.append('*');
                    i = half;
                    continue;
                }
            }

            ans.append(s.charAt(i - 1));
            i--;
        }

        return ans.reverse().toString();
    }
}
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/secret-cipher--141631/1)