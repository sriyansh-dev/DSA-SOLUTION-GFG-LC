class Solution {
    public int maxFruits(ArrayList<Integer> arr, int m) {
        int maxSum = 0;
        int currSum = 0;

        for(int i=0;i<m && i < arr.size();i++){
            currSum += arr.get(i);
        }

        maxSum = Math.max(maxSum, currSum);

        if(arr.size() <= m){
            return maxSum;
        }

        for(int i=m;i<arr.size();i++){
            currSum -= arr.get(i-m);
            currSum += arr.get(i);
            maxSum = Math.max(maxSum, currSum);
        }

        //Now currSum contains sum of last k elements;
        //Lets overlap the window to calculate the running sum

        for(int i=0;i<m;i++){
            currSum -= arr.get(arr.size()-m+i);
            currSum += arr.get(i);
            maxSum = Math.max(maxSum, currSum);
        }

        return (maxSum);

    }
}