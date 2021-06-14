import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ThreeSum {
    /*
     * HashSet based solution, using TwoSum.
     * Use sorting to remove duplicates easily by
     * ignoring consecutive equal elements.
     * 
     * Time Complexity: O(n^2)
     * Space Complexity: O(n), due to the hash set.
     */
    public List<List<Integer>> threeSum(int[] nums) {
        /* We sort to remove duplicates easily. */
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; ++i) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            twoSum(nums, i + 1, -nums[i], result);
        }
        return result;
    }
    
    public void twoSum(int[] nums, int startIndex, int target, List<List<Integer>> ans) {
        Set<Integer> set = new HashSet<>();
        for (int i = startIndex; i < nums.length; ++i) {
            if (set.contains(nums[i])) {
                ans.add(Arrays.asList(-target, target - nums[i], nums[i]));
                /* Do not consider the duplicates for the 3rd item. */
                for (; i + 1 < nums.length && nums[i] == nums[i+1]; ++i);
            } else set.add(target - nums[i]);
        }
    }
}

class Solution2 {
    /* This does not require any sorting to remove duplicates.
     * We use a Set<List<Integer>> type.
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> resultSet = new HashSet<>();
        Set<Integer> set;
        for (int i = 0, target; i < nums.length; ++i) {
            target = -nums[i];
            set = new HashSet<>();
            for (int j = i + 1; j < nums.length; ++j) {
                if (set.contains(nums[j])) {
                    List<Integer> triplet = Arrays.asList(nums[i], target - nums[j], nums[j]);
                    Collections.sort(triplet);
                    resultSet.add(triplet);
                } else set.add(target - nums[j]);
            }
        }
        return new ArrayList<>(resultSet);
    }
}