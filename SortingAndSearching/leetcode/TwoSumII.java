import java.util.Arrays;

public class TwoSumII {
	class Solution {
		/*
		 * Time: O(nlgn)
		 * Space: O(1)
		 */
		public int[] twoSum(int[] numbers, int target) {        
			for (int i = 0, j; i < numbers.length; ++i) {
				j = Arrays.binarySearch(
					numbers, i + 1, 
					numbers.length, target - numbers[i]);
				if (j > 0) {
					return new int[] {i + 1 , j + 1};
				}
			}
			
			return null;
		}
	}

	class SolutionTwo {
		/*
		 * [2,7,11,15], 9
		 * Two pointers: left, and right.
		 * Consider the sum, and move one pointer.
		 *
		 * Time: O(n).
		 * Space: O(1).
		 */
		public int[] twoSum(int[] numbers, int target) {
			int left;
			int right;
			int currentSum;
			
			left = 0;
			right = numbers.length - 1;
			
			while (left < right) {
				currentSum = numbers[left] + numbers[right];
				if (currentSum == target) {
					return new int[] {left+1,right+1};
				} else if (currentSum < target) {
					left += 1;
				} else {
					right -= 1;
				}
			}
			
			return null;
		}
	}
}
