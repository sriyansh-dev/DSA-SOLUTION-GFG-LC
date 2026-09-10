# possible-pairs1550

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

_Description not available._

## Solution

**Language:** C++  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-09-10T17:47:09.716Z  

```cpp
class Solution {
  public:
    virtual int gcd_cal(int a, int b){
      if(b==0) return a;
      return gcd_cal(b, a%b);
    }
    
    virtual int lcm_cal(int a, int b){
      return ((a*b)/(gcd_cal(a, b)));    
    }
  
    virtual int pairCount(int x, int y){
      int p=x*y;
      
      int sqrt_p=sqrt(p);
      
      int ans=0;
      
      for(int i=1; i<=sqrt_p; i++){
       if(p%i==0){
         int a=i, b=p/i;
         if(gcd_cal(a, b)==x && lcm_cal(a, b)==y){
          ans++;
          if(a!=b) ans++;     
         }
       }   
      }
      
      return ans;
    }
};
```

---

[View on GeeksforGeeks](https://practice.geeksforgeeks.org/problems/possible-pairs1550/1)