public class TrappingRainWater {
	class Solution {
		/*
		 * Find leftMax, rightMax, and then iterate again.
		 * Time: O(n).
		 * Space: O(n).
		 */
		public int trap(int[] height) {
			int[] leftMax;
			int[] rightMax;
			int water;
			
			leftMax = new int[height.length];
			rightMax = new int[height.length];
			water = 0;
			
			for(int i=0;i<height.length;++i) {
				leftMax[i]=i==0?height[i]:Math.max(leftMax[i-1],height[i]);
			}
			
			for(int i=height.length-1;i>=0;--i) {
				rightMax[i]=i==height.length-1?height[i]:Math.max(rightMax[i+1],height[i]);
			}
			
			for(int i=1;i<height.length-1;++i) {
				water+=Math.min(leftMax[i],rightMax[i])-height[i];
			}
	
			return water;
		}
	}
}
