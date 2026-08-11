import java.util.ArrayList;

class Solution {
    public boolean isValid(int i, int j, int[][] psum, int k, int curSize) {
        int n = psum.length;
        int m = psum[0].length;
        
        int ui = i + curSize; 
        int uj = j + curSize; 
        int li = i - curSize; 
        int lj = j - curSize; 
        
        // Boundary checks
        if (li < 0 || lj < 0 || ui >= n || uj >= m) {
            return false;
        }
        
        int count1s = psum[ui][uj]; 
        if (lj > 0) count1s -= psum[ui][lj - 1];
        if (li > 0) count1s -= psum[li - 1][uj];
        if (li > 0 && lj > 0) count1s += psum[li - 1][lj - 1];
        
        return count1s <= k;
    }
    
    // Changed return type from int[] to ArrayList<Integer>
    public ArrayList<Integer> largestSquare(int[][] mat, int[][] queries, int k) {
        int n = mat.length;
        int m = mat[0].length;
        
        // PrefSum Logic
        int[][] psum = new int[n][m];
        psum[0][0] = mat[0][0];
        
        for (int i = 1; i < m; i++) psum[0][i] = mat[0][i] + psum[0][i - 1];
        for (int i = 1; i < n; i++) psum[i][0] = mat[i][0] + psum[i - 1][0];
        
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                psum[i][j] = mat[i][j] 
                           + psum[i - 1][j] 
                           + psum[i][j - 1] 
                           - psum[i - 1][j - 1];    
            }  
        }
        
        ArrayList<Integer> ans = new ArrayList<>();
        
        for (int q = 0; q < queries.length; q++) {
            int curSize = 0;
            int i = queries[q][0];
            int j = queries[q][1];
            
            while (isValid(i, j, psum, k, curSize)) {
                curSize++;
            }
            
            ans.add(2 * curSize - 1);
        }
        
        return ans;
    }
}