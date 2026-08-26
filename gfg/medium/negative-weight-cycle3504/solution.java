import java.util.Arrays;

class Solution {
    public boolean isNegativeWeightCycle(int V, int[][] edges) {
        long[] dist = new long[V];
        Arrays.fill(dist, 0);
        for (int i = 0; i < V; i++) {
            boolean updated = false;
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];

                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    updated = true;
                    if (i == V - 1) {
                        return true;
                    }
                }
            }
            if (!updated) break;
        }
        return false;
    }
}