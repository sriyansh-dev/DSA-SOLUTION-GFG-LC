class Solution {
    public int maxTask(int[] h, int[] l) {
        int n = h.length;
        int no = 0;
        int did = Math.max(h[0], l[0]);
        for (int i = 1; i < n; i++) {
            int newNo = Math.max(no, did);
            int newDid = Math.max(no + Math.max(h[i], l[i]), did + l[i]);
            no = newNo;
            did = newDid;
        }
        return Math.max(no, did);
    }
}