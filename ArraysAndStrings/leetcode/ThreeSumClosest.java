import java.util.Arrays;

public class ThreeSumClosest {
	class Solution {
		/*
		 * Two Pointers approach on sorted array.
		 * BCR is O(n^2). Sort, and use two pointers. This
		 * is a better technique for 3Sum problems.
		 */
		public int threeSumClosest(int[] nums, int target) {
			int lo;
			int hi;
			int sum;
			int minDiff;
			
			minDiff = Integer.MAX_VALUE;
			
			Arrays.sort(nums);
			
			for (int i = 0; i < nums.length; ++i) {
				lo = i + 1;
				hi = nums.length - 1;
				
				while (lo < hi) {
					sum = nums[i] + nums[lo] + nums[hi];
					
					if (sum < target) {
						++lo;
					} else {
					   --hi;
					}
					
					if (Math.abs(target - sum) < Math.abs(minDiff)) {
						minDiff = target - sum;
					}
				}
			}
			
			return target - minDiff;
		}
	}

	class SolutionTwo {
		/*
		 * For each index, we can do 2Sum.
		 * We can use binary search.
		 *
		 * [-1,0,1,1,55], target=3
		 * -1,0. Find 4 in [1,1,55].
		 * Use binary search.
		 *
		 * Time: O(n * n * logn).
		 * Space: O(logn) for sorting.
		 */
		public int threeSumClosest(int[] nums, int target) {
			int complement;
			int diff;
			int lo;
			int hi;
			
			diff = Integer.MAX_VALUE;
			
			Arrays.sort(nums);
			
			for (int i = 0; i < nums.length && diff != 0; ++i) {
				for (int j = i + 1; j < nums.length - 1; ++j) {
					/* Find closest number to (twoSumTarget - nums[j]).
					 * We can search a sorted list in logn time. */
					complement = target - nums[i] - nums[j];
					
					hi = Arrays.binarySearch(nums, j + 1, nums.length, complement);
					if (hi < 0) {
						hi = -hi - 1;
					}
					
					lo = hi - 1;
					
					if (hi < nums.length && Math.abs(nums[hi] - complement) < Math.abs(diff)) {
						diff = complement - nums[hi];
					}
					
					if (lo > j && Math.abs(nums[lo] - complement) < Math.abs(diff)) {
						diff = complement - nums[lo];
					}
				}
			}
			
			return target - diff;
		}
	}
}
