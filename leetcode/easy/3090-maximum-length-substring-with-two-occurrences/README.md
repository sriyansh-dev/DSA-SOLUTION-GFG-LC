# Maximum Length Substring With Two Occurrences

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

Given a string `s`, return the  **maximum**  length of a substring such that it contains  *at most two occurrences*  of each character.

 

 **Example 1:** 

 **Input:**  s = "bcbbbcba"

 **Output:**  4

 **Explanation:** 

The following substring has a length of 4 and contains at most two occurrences of each character: `"bcbbbcba"`.

 **Example 2:** 

 **Input:**  s = "aaaa"

 **Output:**  2

 **Explanation:** 

The following substring has a length of 2 and contains at most two occurrences of each character: `"aaaa"`.

 

 **Constraints:** 

- 2 <= s.length <= 100
- s consists only of lowercase English letters.

## Solution

**Language:** Java  
**Runtime:** 1 ms (beats 100.00%)  
**Memory:** 43.1 MB (beats 99.31%)  
**Submitted:** 2026-08-14T03:08:13.298Z  

```java
class Solution {
    public int maximumLengthSubstring(String s) {
        // Array to track frequency of each character (a-z)
        int[] charFrequency = new int[26];
        int maxLength = 0;
      
        // Use sliding window technique with two pointers
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            // Get the index of current character (0-25 for a-z)
            int currentCharIndex = s.charAt(right) - 'a';
          
            // Increment frequency of current character
            charFrequency[currentCharIndex]++;
          
            // If any character appears more than 2 times, shrink window from left
            while (charFrequency[currentCharIndex] > 2) {
                int leftCharIndex = s.charAt(left) - 'a';
                charFrequency[leftCharIndex]--;
                left++;
            }
          
            // Update maximum length found so far
            // Window size is (right - left + 1)
            maxLength = Math.max(maxLength, right - left + 1);
        }
      
        return maxLength;
    }
}

```

---

[View on LeetCode](https://leetcode.com/problems/maximum-length-substring-with-two-occurrences/)