class Solution {
    /**
     * Determines if two rectangles overlap.
     * Each rectangle is represented by its bottom-left and top-right corners.
     * 
     * @param rec1 
     * @param rec2 
     * @return
     */
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        
        int leftX1 = rec1[0];   
        int bottomY1 = rec1[1];  
        int rightX1 = rec1[2];  
        int topY1 = rec1[3];    
      
        int leftX2 = rec2[0];   
        int bottomY2 = rec2[1]; 
        int rightX2 = rec2[2];   
        int topY2 = rec2[3];           
       
        return !(bottomY2 >= topY1 || topY2 <= bottomY1 || leftX2 >= rightX1 || rightX2 <= leftX1);
    }
}
