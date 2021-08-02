import java.util.Arrays;

public class MaximumUnitsOnATruck {
    class Solution {
        /*
         * Sort based on units.
         * [[5,10],[2,5],[4,7],[3,9]],10
         * After sorting:
         * [[5,10],[3,9],[4,7],[2,5]]
         * (5*10)+(3*9)+(2*7)=50+27+14=77+14=91.
         *
         * Time: O(nlgn) where n is the number of boxTypes.
         * Space: O(1).
         */
        public int maximumUnits(int[][] boxTypes, int truckSize) {
            int units;
            
            units = 0;
            
            Arrays.sort(boxTypes, (x,y)->y[1]-x[1]);
            
            for (int[] boxType : boxTypes) {
                units += boxType[0]*boxType[1];
                truckSize -= boxType[0];
                if (truckSize < 0) {
                    units += truckSize*boxType[1];
                    break;
                }
                
            }
            
            return units;
        }
    }
}