# isomorphic-strings-1587115620

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

_Description not available._

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-06T17:29:08.246Z  

```java
class Solution {
    public boolean areIsomorphic(String s1, String s2) {
        
        //Terminate if the lengths are not same
        if(s1.length() != s2.length())
            return false;
            
        //Create a hashtable for lowercase character mapping(size = 26)
        Hashtable<Character, Character> hash = new Hashtable<>(26);
        
        //Traverse through the strings
        for(int i = 0;i < s1.length();i++){
            char a = s1.charAt(i), b = s2.charAt(i);
            
            //If a is an unvisited character,
            //and b is an unmapped character,
            //map (a -> b)
            //If b is an already mapped character,
            //it can't be mapped to a so, return false.
            if(!hash.containsKey(a)){
                if(!hash.containsValue(b))
                    hash.put(a, b);
                else
                    return false;
            }
            
            //If a is a visited character,
            //check if it's mapped character is actually b.
            //If no, return false.
            else{
                if(hash.get(a) != b)
                    return false;
            }
        }
        
        //If the strings survived the loop
        return true;
    }
}
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/isomorphic-strings-1587115620/1)