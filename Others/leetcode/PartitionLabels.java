import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PartitionLabels {
	class Solution {
		public List<Integer> partitionLabels(String s) {
			int[] start;
			int[] end;
			int st;
			int en;
			List<Integer> ans;
			char ch;
			
			start = new int[26];
			end = new int[26];
			ans = new ArrayList<>();
			
			Arrays.fill(start, -1);
			Arrays.fill(end, -1);
			
			for (int i = 0; i < s.length(); ++i) {
				ch = s.charAt(i);
				
				if (start[ch-'a'] == -1) {
					start[ch-'a'] = i;
				}
				
				end[ch-'a'] = i;
				
				for (int j = 0; j < 26; ++j) {
					if (start[ch-'a'] <= start[j] && 
						end[j] <= end[ch-'a']) {
						start[j] = start[ch-'a'];
						end[j] = end[ch-'a'];
					}
				}
			}
			
			for (int i = 0; i < s.length();) {
				st = start[s.charAt(i)-'a'];
				en = end[s.charAt(i)-'a'];
				ans.add(en-st+1);
				i = en+1;
			}
			
			return ans;
		}
	}

	class SolutionTwo {
		/*
		 * For every character, find the position of it's
		 * last occurrence. For every character, the en of
		 * the interval will be maximum of it's last
		 * index and the en from the previous characters.
		 * When en == i, we can close the current interval.
		 * Time: O(n).
		 * Space: O(1).
		 */
		public List<Integer> partitionLabels(String s) {
			int[] last;
			int st;
			int en;
			List<Integer> ans;
			
			last = new int[26];
			ans = new ArrayList<>();
			en = 0;
			st = 0;
			
			for (int i = 0; i < s.length(); ++i) {
				last[s.charAt(i)-'a'] = i;
			}
			
			for (int i = 0; i < s.length(); ++i) {
				en = Math.max(en, last[s.charAt(i)-'a']);
				if (en == i) {
					ans.add(en-st+1);
					st = en+1;
				}
			}
			
			return ans;
		}
	}
}