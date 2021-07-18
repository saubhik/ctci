import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinimumWindowSubstring {
	class Solution {
		/*
		 * "ADOBECODEBANC", "ABC"
		 * ADOBEC, BECODEBA, CODEBA, BANC
		 * Sliding window.
		 * Increase right till all characters read.
		 * Increase left until we miss one character.
		 * Time: O(|S|+|T|)
		 * Space: O(|S|+|T|)
		 */
		public String minWindow(String s, String t) {
			char ch;
			Map<Character,Integer> charCount;
			Map<Character,Integer> currCharCount;
			int uniqueChars;
			int left;
			int right;
			int minLeft;
			int minRight;
			
			charCount = new HashMap<>();
			currCharCount = new HashMap<>();
			uniqueChars = 0;
			left = 0;
			right = 0;
			minLeft = -1;
			minRight = s.length();
			
			if (s.length() < t.length()) {
				return "";
			}
			
			for (char c : t.toCharArray()) {
				charCount.put(c, charCount.getOrDefault(c,0)+1);
			}
					
			while (right < s.length()) {
				ch = s.charAt(right);
	
				if (charCount.containsKey(ch)) {
					currCharCount.put(ch,currCharCount.getOrDefault(ch,0)+1);
					if (currCharCount.get(ch).intValue() == 
						charCount.get(ch).intValue()) {
						++uniqueChars;
					}
				}
	
				while (uniqueChars == charCount.size() && left <= right) {
					ch = s.charAt(left);
					
					if (charCount.containsKey(ch)) {
						currCharCount.put(ch, currCharCount.get(ch)-1);
						if (currCharCount.get(ch).intValue() < 
							charCount.get(ch).intValue()) {
							--uniqueChars;
							if (right-left < minRight-minLeft) {
								minLeft = left;
								minRight = right;
							}
						}
					}
					
					++left;
				}
	
				++right;
			}
			
			return minLeft == -1 ? "" : s.substring(minLeft,minRight+1);
		}
	}

	class SolutionTwo {
		/*
		 * "ADOBECODEBANC", "ABC"
		 * ADOBEC, BECODEBA, CODEBA, BANC
		 * Sliding window.
		 * Increase right till all characters read.
		 * Increase left until we miss one character.
		 * Time: O(2*|filteredS|+|S|+|T|)
		 * Space: O(|filteredS|+|S|+|T|)
		 */
		public String minWindow(String s, String t) {
			char ch;
			Map<Character,Integer> charCount;
			Map<Character,Integer> currCharCount;
			List<Pair<Character,Integer>> filteredS;
			int uniqueChars;
			int left;
			int right;
			int minLeft;
			int minRight;
			int leftIndex;
			int rightIndex;
			
			charCount = new HashMap<>();
			currCharCount = new HashMap<>();
			filteredS = new ArrayList<>();
			uniqueChars = 0;
			left = 0;
			right = 0;
			minLeft = -1;
			minRight = s.length();
			
			if (s.length() < t.length()) {
				return "";
			}
			
			for (char c : t.toCharArray()) {
				charCount.put(c, charCount.getOrDefault(c,0)+1);
			}
			
			for (int i = 0; i < s.length(); ++i) {
				ch = s.charAt(i);
				if (charCount.containsKey(ch)) {
					filteredS.add(new Pair<>(ch,i));
				}
			}
					
			while (right < filteredS.size()) {
				ch = filteredS.get(right).getKey();
	
				if (charCount.containsKey(ch)) {
					currCharCount.put(ch,currCharCount.getOrDefault(ch,0)+1);
					if (currCharCount.get(ch).intValue() == 
						charCount.get(ch).intValue()) {
						++uniqueChars;
					}
				}
	
				while (uniqueChars == charCount.size() && left <= right) {
					ch = filteredS.get(left).getKey();
					
					if (charCount.containsKey(ch)) {
						currCharCount.put(ch, currCharCount.get(ch)-1);
						if (currCharCount.get(ch).intValue() < 
							charCount.get(ch).intValue()) {
							--uniqueChars;
							
							leftIndex = filteredS.get(left).getValue();
							rightIndex = filteredS.get(right).getValue();
							if (rightIndex-leftIndex < minRight-minLeft) {
								minLeft = leftIndex;
								minRight = rightIndex;
							}
						}
					}
					
					++left;
				}
	
				++right;
			}
			
			return minLeft == -1 ? "" : s.substring(minLeft,minRight+1);
		}
	}
}