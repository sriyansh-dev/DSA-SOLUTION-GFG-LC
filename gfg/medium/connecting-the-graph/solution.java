class Solution {
    int minEdgesReq(int n, int[][] edges) {
        int m = edges.length;
        
        // Not enough edges to connect the graph
        if (m < n - 1) {
            return -1;
        }
        
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        int components = n;
        
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            
            int rootU = find(parent, u);
            int rootV = find(parent, v);
            
            if (rootU != rootV) {
                parent[rootU] = rootV;
                components--;
            }
        }
        
        // Operations needed = components - 1
        return components - 1;
    }
    
    private int find(int[] parent, int i) {
        if (parent[i] != i) {
            parent[i] = find(parent, parent[i]); // Path compression
        }
        return parent[i];
    }
}