public class LongestPalindromicSubstring {
	class Solution {
		private String s;
		
		/*
		 * Expand around the 2n-1 centers (gaps between
		 * the letters).
		 *
		 * Time: O(n^2).
		 * Space: O(1).
		 */
		public String longestPalindrome(String s) {
			int len;
			int len1;
			int len2;
			int start;
			int end;
			
			start = 0;
			end = 0;
			this.s = s;
			
			for (int i = 0; i < s.length(); ++i) {
				len1 = expandAroundCenter(i,i);
				len2 = expandAroundCenter(i,i+1);
				len = Math.max(len1, len2);
				if (len > end - start) {
					start = i - (len-1)/2;
					end = i + len/2;
				}
			}
			
			return s.substring(start, end+1);
		}
		
		private int expandAroundCenter(int i, int j) {
			while (0 <= i && j < s.length() && s.charAt(i) == s.charAt(j)) {
				--i;
				++j;
			}
			
			return j-i-1;
		}
	}
}