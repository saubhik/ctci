import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    /*
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    public int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; ++i) {
            for (int j = i + 1; j < nums.length; ++j) {
                if (nums[i] + nums[j] == target) {
                    return new int[] { i , j };
                }
            }
        }
        return null;
    }
    
    /*
     * [2,3,5,6], 9
     * Consider 2. We need 7. {7: 0}
     * Consider 3. We need 6. {7: 0, 6: 1}
     * Consider 5. we need 4. {7: 0, 6: 1, 4: 2}
     * Consider 6. There is 6 in hashmap. Return [1, 3].
     *
     * Time Complexity: O(n).
     * Space Complexity: O(n).
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            if (map.containsKey(nums[i])) {
                return new int[] { map.get(nums[i]), i };
            } else map.put(target - nums[i], i);
        }
        return null;
    }
}