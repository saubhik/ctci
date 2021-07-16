public class MedianOfTwoSortedArrays {
	class Solution {
		/*
		 * Binary search on smaller array to find the right
		 * partition index.
		 * Time: O(log(min(m,n)))
		 * Space: O(1)
		 */
		public double 
			findMedianSortedArrays(int[] nums1, int[] nums2) {
			int m = nums1.length;
			int n = nums2.length;
			
			if (m>n) {
				return this.findMedianSortedArrays(nums2,nums1);
			}
			
			int lo = 0;
			int hi = m;
			
			while (lo <= hi) {
				int i = (lo+hi)/2;
				int j = (m+n)/2-i;
				
				int l1 = (i==0) ? Integer.MIN_VALUE : nums1[i-1];
				int l2 = (j==0) ? Integer.MIN_VALUE : nums2[j-1];
				int r1 = (i<m) ? nums1[i] : Integer.MAX_VALUE;
				int r2 = (j<n) ? nums2[j] : Integer.MAX_VALUE;
				
				if (l2>r1) {
					lo = i+1;
				} else if (l1>r2) {
					hi = i-1;
				} else {
					if ((m+n)%2==1) {
						return Math.min(r1,r2);
					} else {
						return (Math.min(r1,r2)+Math.max(l1,l2))/(double)2;
					}
				}
			}
			
			return -1;
		}
	}
}
