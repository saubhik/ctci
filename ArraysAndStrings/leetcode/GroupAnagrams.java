import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {
	class Solution {
		/*
		 * ["eat","tea","tan","ate","nat","bat"]
		 * Sort each element and put in the right group.
		 * "aet": ["eat", "tea", "ate"]
		 * "ant": ["tan", "nat"]
		 * "abt": ["bat"]
		 * 
		 * Time Complexity: O(nklogk)
		 * Space Complexity: O(nk)
		 */
		public List<List<String>> groupAnagrams(String[] strs) {
			Map<String, List<String>> map;
			List<String> list;
			char[] chars;
			String sortedElem;
	
			map = new HashMap<>();
	
			for (String elem : strs) {
				chars = elem.toCharArray();
				Arrays.sort(chars);
				sortedElem = new String(chars);
				
				if (map.containsKey(sortedElem)) {
					list = map.get(sortedElem);
				} else {
					list = new ArrayList<>();
				}
				
				list.add(elem);
				
				map.put(sortedElem, list);
			}
			
			return new ArrayList<>(map.values());
		}
	}

	class SolutionTwo {
		/*
		 * Time Complexity: O(nk)
		 * Space Complexity: O(nk)
		 */
		public List<List<String>> groupAnagrams(String[] strs) {
			/* Hash map of character counts */
			Map<String, List<String>> map;
			String code;
			
			map = new HashMap<>();
			
			for (String s : strs) {
				code = getCharacterCode(s);
				if (!map.containsKey(code)) {
					map.put(code, new ArrayList<>());
				}
				map.get(code).add(s);
			}
			
			return new ArrayList<>(map.values());
		}
		
		private String getCharacterCode(String s) {
			int[] counts;
			
			counts = new int[26];
			
			for (char ch : s.toCharArray()) {
				++counts[ch - 'a'];
			}
			
			StringBuilder sb = new StringBuilder("");
			for (int i = 0; i < 26; ++i) {
				sb.append("#");
				sb.append(counts[i]);
			}
			
			return sb.toString();
		}
	}
}
