class Solution {
    public int numberOfTurns(Node root, int p, int q) {
        StringBuilder pathP = new StringBuilder();
        StringBuilder pathQ = new StringBuilder();

        findPath(root, p, pathP);
        findPath(root, q, pathQ);

        // Find the common path from root
        int i = 0;

        while (i < pathP.length()
                && i < pathQ.length()
                && pathP.charAt(i) == pathQ.charAt(i)) {
            i++;
        }

        StringBuilder path = new StringBuilder();

        // p -> LCA
        // Reverse the ORDER only, not L/R directions
        for (int j = pathP.length() - 1; j >= i; j--) {
            path.append(pathP.charAt(j));
        }

        // LCA -> q
        for (int j = i; j < pathQ.length(); j++) {
            path.append(pathQ.charAt(j));
        }

        int turns = 0;

        for (int j = 1; j < path.length(); j++) {
            if (path.charAt(j) != path.charAt(j - 1)) {
                turns++;
            }
        }

        return turns == 0 ? -1 : turns;
    }

    static boolean findPath(Node root, int target, StringBuilder path) {

        if (root == null)
            return false;

        if (root.data == target)
            return true;

        // Left
        path.append('L');

        if (findPath(root.left, target, path)) {
            return true;
        }

        path.deleteCharAt(path.length() - 1);

        // Right
        path.append('R');

        if (findPath(root.right, target, path)) {
            return true;
        }

        path.deleteCharAt(path.length() - 1);

        return false;

    }
}