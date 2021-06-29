import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MostCommonWord {
	class Solution {
		public String mostCommonWord(String paragraph, String[] banned) {
			String word;
			String ans;
			Map<String, Integer> map;
			int maxFreq;
			int freq;
			Set<String> bannedSet;
			Pair<Integer, Integer> pair;
			
			map = new HashMap<>();
			bannedSet = new HashSet<>();
			maxFreq = -1;
			ans = "";
			
			for (String bannedWord : banned) {
				bannedSet.add(bannedWord);
			}
			
			for (int i = 0; i < paragraph.length();) {
				pair = getNextWord(paragraph, i);
				word = paragraph.substring(pair.getKey(), pair.getValue()).toLowerCase();
				
				if (!bannedSet.contains(word)) {
					freq = map.getOrDefault(word, 0) + 1;
					map.put(word, freq);
					if (freq > maxFreq) {
						maxFreq = freq;
						ans = word;
					}
				}
				
				i = pair.getValue();
			}
			
			return ans;
		}
		
		private Pair<Integer, Integer> getNextWord(String para, int startIndex) {
			int endIndex;
			
			while (startIndex < para.length() && !Character.isLetter(para.charAt(startIndex))) {
				++startIndex;
			}
			
			endIndex = startIndex;
			
			while (endIndex < para.length() && Character.isLetter(para.charAt(endIndex))) {
				++endIndex;
			}
			
			return new Pair<>(startIndex, endIndex);
		}
	}
}
