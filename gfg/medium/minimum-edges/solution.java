class Solution {
    public static int minimumEdgeReversal(int[][] edges, int n, int src, int dst) {

        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        // Original edge = cost 0
        // Reversed edge = cost 1
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];

            adj.get(a).add(new int[]{b, 0});
            adj.get(b).add(new int[]{a, 1});
        }

        // Dijkstra
        PriorityQueue<int[]> pq =
            new PriorityQueue<>((a, b) -> a[1] - b[1]);

        boolean[] visited = new boolean[n + 1];

        pq.add(new int[]{src, 0});

        while (!pq.isEmpty()) {

            int[] curr = pq.poll();

            int currNode = curr[0];
            int currWeight = curr[1];

            if (visited[currNode]) {
                continue;
            }

            visited[currNode] = true;

            if (currNode == dst) {
                return currWeight;
            }

            for (int[] edge : adj.get(currNode)) {

                int neighbour = edge[0];
                int weight = edge[1];

                if (!visited[neighbour]) {
                    pq.add(new int[]{
                        neighbour,
                        currWeight + weight
                    });
                }
            }
        }

        return -1;
    }
}