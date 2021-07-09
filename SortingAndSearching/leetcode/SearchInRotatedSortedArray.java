import java.util.Arrays;

public class SearchInRotatedSortedArray {
	class Solution {
		/*
		 * [4,5,6,7,0,1,2], target=0
		 * 1. First find the pivot index. (smallest element)
		 * 2. Find the target in first or second part.
		 */
		public int search(int[] nums, int target) {
			int lo;
			int hi;
			int mid;
			int index;
			
			lo = 0;
			hi = nums.length - 1;
			
			if (nums[lo] < nums[hi]) {
				index = Arrays.binarySearch(nums, lo, nums.length, target);
				return index >= 0 ? index : -1;
			}
			
			while (lo < hi) {
				mid = lo + (hi-lo)/2;
				if (nums[mid] > nums[hi]) {
					lo = mid + 1;
				} else {
					hi = mid;
				}
			}
			
			if (target == nums[0]) {
				return 0;
			}
			
			index = (target < nums[0]) ? 
				Arrays.binarySearch(nums, hi, nums.length, target) : 
				Arrays.binarySearch(nums, 0, hi, target);
			
			return index >= 0 ? index : -1;
		}
	}

	class SolutionTwo {
		/* One-pass binary search. */
		public int search(int[] nums, int target) {
			int lo;
			int hi;
			int mid;
			
			lo = 0;
			hi = nums.length - 1;
			
			while (lo <= hi) {
				mid = hi - (hi-lo)/2;
				if (nums[mid] == target) {
					return mid;
				}
				if (nums[mid] > nums[0]) {
					/* mid is in the first sorted part. */
					if (nums[lo] <= target && target < nums[mid]) {
						hi = mid - 1;
					} else {
						lo = mid + 1;
					}
				} else {
					/* mid is in the second sorted part. */
					if (nums[mid] < target && target <= nums[hi]) {
						lo = mid + 1;
					} else {
						hi = mid - 1;
					}
				}
			}
			
			return -1;
		}
	}
}