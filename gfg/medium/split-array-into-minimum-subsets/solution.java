class Solution {
    int minSubsets(int arr[]) {
        int n = arr.length;
        int sets = 0;
        HashSet<Integer> o = new HashSet<Integer>();
        
        for (int x = 0; x < n; x++) {
            int data = arr[x];
            
            boolean left = o.contains(data - 1);
            boolean right = o.contains(data + 1);
        
            if (!left && !right) sets++;
            if (left && right) sets--;
        
            o.add(data);
        }
        
        return sets;
    }
}