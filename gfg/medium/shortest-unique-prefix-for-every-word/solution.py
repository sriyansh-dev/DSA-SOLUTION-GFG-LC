class Solution:
    def findPrefixes(self, arr):
        # code here
        s = dict()
        for i in arr:
            for j in range(1, len(i) + 1):
                s[i[:j]] = s.get(i[:j], 0) + 1
        res = []
        for i in arr:
            for j in range(1, len(i) + 1):
                if s[i[:j]] == 1:
                    res.append(i[:j])
                    break
                if j == len(i):
                    res.append(i)
        return res