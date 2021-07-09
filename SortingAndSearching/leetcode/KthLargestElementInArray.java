import java.util.PriorityQueue;
import java.util.Random;

public class KthLargestElementInArray {
	class Solution {
		/*
		 * Time: O(nlgk)
		 * Space: O(k)
		 */
		public int findKthLargest(int[] nums, int k) {
			PriorityQueue<Integer> heap;
			
			heap = new PriorityQueue<>();
			
			for (int num : nums) {
				heap.add(num);
				if (heap.size() > k) {
					heap.poll();
				}
			}
			
			return heap.poll();
		}
	}

	class SolutionTwo {
		private int[] nums;
		
		/*
		 * Using QuickSelect.
		 * Time: O(n).
		 * Space: O(1).
		 */
		public int findKthLargest(int[] nums, int k) {
			this.nums = nums;
			return quickSelect(0, nums.length - 1, nums.length - k);
		}
		
		private int quickSelect(int left, int right, int kSmallest) {
			int pivotIndex;
			Random randomNum;
			
			if (left == right) {
				return nums[left];
			}
			
			randomNum = new Random();
			pivotIndex = left + randomNum.nextInt(right - left);
			
			pivotIndex = partition(left, right, pivotIndex);
			
			if (pivotIndex == kSmallest) {
				return nums[pivotIndex];
			} else if (pivotIndex < kSmallest) {
				return quickSelect(pivotIndex + 1, right, kSmallest);
			} else {
				return quickSelect(left, pivotIndex - 1, kSmallest);
			}
		}
		
		private int partition(int left, int right, int pivotIndex) {
			int pivot;
			int index;
			
			pivot = nums[pivotIndex];
			index = left;
			
			swap(right, pivotIndex);
			
			for (int i = left; i < right; ++i) {
				if (nums[i] < pivot) {
					swap(index, i);
					++index;
				}
			}
			
			swap(right, index);
			
			return index;
		}
		
		private void swap(int i, int j) {
			int tmp;
			
			tmp = nums[j];
			nums[j] = nums[i];
			nums[i] = tmp;
		}
	}
}
