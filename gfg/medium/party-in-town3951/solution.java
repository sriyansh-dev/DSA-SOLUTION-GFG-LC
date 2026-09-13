class Solution {

    static int partyHouse(ArrayList<ArrayList<Integer>> adj) {

        // Find the farthest node from node 0
        int[] first = bfs(adj, 0);

        // Find the farthest node from that node
        int[] second = bfs(adj, first[0]);

        int diameter = second[1];

        // Minimum possible maximum distance
        return (diameter + 1) / 2;
    }

    static int[] bfs(ArrayList<ArrayList<Integer>> adj, int start) {

        int n = adj.size();

        boolean[] visited = new boolean[n];
        int[] distance = new int[n];

        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        visited[start] = true;

        int farthestNode = start;

        while (!queue.isEmpty()) {

            int current = queue.poll();

            for (int neighbor : adj.get(current)) {

                // House numbers are 1-based
                int next = neighbor - 1;

                if (!visited[next]) {

                    visited[next] = true;
                    distance[next] = distance[current] + 1;

                    queue.add(next);

                    if (distance[next] > distance[farthestNode]) {
                        farthestNode = next;
                    }
                }
            }
        }

        return new int[] {
            farthestNode,
            distance[farthestNode]
        };
    }
}