public class ImplementStrStr {
	class Solution {
		public int strStr(String haystack, String needle) {
			if (needle.length() > haystack.length()) {
				return -1;
			}
			
			if (needle.length() == 0) {
				return 0;
			}
			
			for (int i = 0, curr, j; i <= haystack.length() - needle.length(); ++i) {
				curr = i;
				j = 0;
				
				while (curr < haystack.length() && j < needle.length()) {
					if (haystack.charAt(curr) != needle.charAt(j)) {
						break;
					}
					++curr;
					++j;
				}
				
				if (j == needle.length()) {
					return i;
				}
			}
			
			return -1;
		}
	}
}
