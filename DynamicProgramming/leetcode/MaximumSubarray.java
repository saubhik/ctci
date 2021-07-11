public class MaximumSubarray {
	class Solution {
		/*
		 * a=[5,4,-1,7,8]
		 * Let PS=[5,9,8,15,23] be the partial
		 * sums. Then PS[j] - PS[i] represents the
		 * sum of subarray A[i,j]. I need to find the
		 * maximum such difference PS[j] - PS[i].
		 * 
		 * a=[-2,1,-3,4,-1,2,1,-5,4]
		 * ps=[-2,-1,-4,0,-1,1,2,-3,1]
		 * Quadratic solution is trivial.
		 * We can look at minSoFar and always
		 * consider the difference and keep the maximum
		 * difference.
		 */
		public int maxSubArray(int[] nums) {
			int minSoFar;
			int maxDiff;
			int partialSum;
			
			partialSum = 0;
			minSoFar = 0;
			maxDiff = Integer.MIN_VALUE;
			
			for (int num : nums) {
				partialSum += num;
				maxDiff = Math.max(maxDiff, partialSum - minSoFar);
				minSoFar = Math.min(minSoFar, partialSum);
			}
			
			return maxDiff;
		}
	}
}