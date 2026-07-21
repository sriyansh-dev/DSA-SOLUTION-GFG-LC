

class Solution:
    def maxIndexDifference(self, s):
        index=-1
        c='a'
        diff=-1
        
        for i in range(len(s)):
            if index==-1 and s[i]==c:
                index=i
                c=chr(ord(c)+1)
                diff=0
            elif c==s[i] or ord(c)>ord(s[i]) and s[i]!='a':
                diff=i-index
                if c==s[i]:
                    c=chr(ord(c)+1)
        return diff
