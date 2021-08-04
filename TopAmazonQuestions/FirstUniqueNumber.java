import java.util.HashSet;
import java.util.LinkedHashSet;

public class FirstUniqueNumber {
    class FirstUnique {
        private LinkedHashSet<Integer> queueSet;
        private HashSet<Integer> uniqueSet;
        
        /* O(n) space, where n is the number of values added. */
        public FirstUnique(int[] nums) {
            queueSet = new LinkedHashSet<>();
            uniqueSet = new HashSet<>();
            
            /* O(k=len(nums)) time. */
            for (int num : nums) {
                this.add(num);
            }
        }
        
        /* O(1) time. */
        public int showFirstUnique() {
            if (queueSet.isEmpty()) {
                return -1;
            }
            
            return queueSet.iterator().next();
        }
        
        /* O(1) time. */
        public void add(int value) {
            if (!uniqueSet.contains(value)) {
                uniqueSet.add(value);
                queueSet.add(value);
            } else {
                queueSet.remove(value);
            }
        }
    }
    
    /**
     * Your FirstUnique object will be instantiated and called as such:
     * FirstUnique obj = new FirstUnique(nums);
     * int param_1 = obj.showFirstUnique();
     * obj.add(value);
     */
}