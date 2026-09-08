class Solution {
    private final int[] nRow = {-1, -1, -1, 0, 0, 1, 1, 1};
        private final int[] nCol = {-1, 0, 1, -1, 1, -1, 0, 1};

        private boolean helper(char[][] grid, String word, int x, int y, int index, int n, int m) {
            for (int i = 0; i < word.length(); i++) {
                int newx = x + nRow[index] * i;
                int newy = y + nCol[index] * i;

                if (newx < 0 || newx >= n || newy < 0 || newy >= m || grid[newx][newy] != word.charAt(i)) {
                    return false;
                }
            }
            return true;
        }

        public ArrayList<ArrayList<Integer>> searchWord(char[][] mat, String word) {
            int n = mat.length;
            int m = mat[0].length;

            ArrayList<ArrayList<Integer>> result = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (mat[i][j] == word.charAt(0)) {
                        for (int k = 0; k < 8; k++) {
                            if (helper(mat, word, i, j, k, n, m)) {
                                ArrayList<Integer> coord = new ArrayList<>();
                                coord.add(i);
                                coord.add(j);
                                result.add(coord);
                                break;
                            }
                        }
                    }
                }
            }

            return result;  
    }
};