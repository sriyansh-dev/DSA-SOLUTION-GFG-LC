class Solution {
    public int totalNumbers(int[] digits) {
        // Use a HashSet to store unique 3-digit numbers
        Set<Integer> uniqueNumbers = new HashSet<>();
        int arrayLength = digits.length;
      
        // Iterate through all possible positions for the ones digit (must be even)
        for (int onesIndex = 0; onesIndex < arrayLength; ++onesIndex) {
            // Skip if the digit at this position is odd
            if (digits[onesIndex] % 2 == 1) {
                continue;
            }
          
            for (int tensIndex = 0; tensIndex < arrayLength; ++tensIndex) {
                if (onesIndex == tensIndex) {
                    continue;
                }
              
             
                for (int hundredsIndex = 0; hundredsIndex < arrayLength; ++hundredsIndex) {
                
                    if (digits[hundredsIndex] == 0 || 
                        hundredsIndex == onesIndex || 
                        hundredsIndex == tensIndex) {
                        continue;
                    }
                    int threeDigitNumber = digits[hundredsIndex] * 100 + 
                                         digits[tensIndex] * 10 + 
                                         digits[onesIndex];
                    uniqueNumbers.add(threeDigitNumber);
                }
            }
        }
              return uniqueNumbers.size();
    }
}
