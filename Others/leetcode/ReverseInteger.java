public class ReverseInteger {
	class Solution {
		public int reverse(int x) {
			int ans;
			int tmp;
			
			ans = 0;
			while (x != 0) {
				tmp = x%10;
				
				if ((tmp>0 && ans>(Integer.MAX_VALUE-tmp)/10) ||
					(tmp<0 && ans<(Integer.MIN_VALUE-tmp)/10)) {
					return 0;
				}
				
				ans = 10*ans + tmp;
				x /= 10;
			}
			
			return ans;
		}
	}
}
