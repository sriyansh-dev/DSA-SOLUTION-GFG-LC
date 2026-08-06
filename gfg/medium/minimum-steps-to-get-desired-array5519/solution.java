class Solution {
    public int countMinOperations(int[] arr) {
        int operation = 0;
        while (true) {
            int zeroCnt = 0;
            // check if all elements are processed / zero
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == 0) {
                    zeroCnt++;
                } else if (arr[i] % 2 == 1) {
                    arr[i]--;
                    operation++;
                }
            }
            
            if (zeroCnt == arr.length) {
                break;
            }
            
            boolean allEven = true;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] % 2 == 1) {
                    allEven = false;
                    break;
                }
            }
            
            if (allEven) {
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = arr[i] / 2;
                }
                operation++;
            }
        }
        return operation - 1;
    }
}