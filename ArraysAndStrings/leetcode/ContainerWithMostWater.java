public class ContainerWithMostWater {
	class Solution {
		/*
		 * Two Pointers:
		 * Keep one pointer at left.
		 * Keep another pointer at right.
		 * Get the area. Store it.
		 *
		 * Move the shorter pointer because moving the
		 * larger one cannot give an increase in area.
		 *
		 * O(n) time, since only one pointer is moving.
		 * O(1) space.
		 */
		public int maxArea(int[] height) {
			int left;
			int right;
			int maxArea;
			
			left = 0;
			right = height.length - 1;
			maxArea = -1;
			
			while (left < right) {
				maxArea = Math.max(Math.min(height[left], height[right]) * (right - left), maxArea);
				
				if (height[left] < height[right]) {
					++left;
				} else {
					--right;
				}
			}
			
			return maxArea;
		}
	}
}
