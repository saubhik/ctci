import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class WordLadder {
	class Solution {
		private int length;
		Map<String, List<String>> map;
		
		/*
		 * 1. Preprocess wordlist to get adjacency list.
		 * 2. Perform BFS.
		 *
		 * We can improve search time & space by using
		 * bi-directional BFS.
		 *
		 * Time: O(m^2 * n)
		 * Space: O(m^2 * n)
		 */
		public int ladderLength(
			String beginWord, 
			String endWord, 
			List<String> wordList) {
			
			if (!wordList.contains(endWord)) {
				return 0;
			}
			
			String key;
			List<String> neighbors;
			Map<String, Integer> visitedBegin;
			Map<String, Integer> visitedEnd;
			Queue<Pair<String, Integer>> queueBegin;
			Queue<Pair<String, Integer>> queueEnd;
			Integer beginRes;
			Integer endRes;
	
			
			length = beginWord.length();
			map = new HashMap<>();
			queueBegin = new LinkedList<>();
			queueEnd = new LinkedList<>();
			visitedBegin = new HashMap<>();
			visitedEnd = new HashMap<>();
			
			visitedBegin.put(beginWord, 1);
			visitedEnd.put(endWord, 1);
			
			for (String w : wordList) {
				for (int i = 0; i < length; ++i) {
					key = w.substring(0, i) + '*' + w.substring(i + 1);
					neighbors = map.getOrDefault(key, new ArrayList<>());
					neighbors.add(w);
					map.put(key, neighbors);
				}
			}
				  
			queueBegin.add(new Pair<>(beginWord, 1));
			queueEnd.add(new Pair<>(endWord, 1));
			
			while (!queueBegin.isEmpty() && !queueEnd.isEmpty()) {
				beginRes = visitNode(queueBegin, visitedBegin, visitedEnd);
				if (beginRes != null) {
					return beginRes;
				}
				
				endRes = visitNode(queueEnd, visitedEnd, visitedBegin);
				if (endRes != null) {
					return endRes;
				}
			}
			
			return 0;
		}
		
		private Integer visitNode(
			Queue<Pair<String, Integer>> queue,
			Map<String, Integer> visited,
			Map<String, Integer> otherVisited) {
			
			Pair<String, Integer> pair;
			List<String> neighbors;
			String word;
			String key;
			int level;
			
			pair = queue.remove();
			word = pair.getKey();
			level = pair.getValue();
			
			for (int i = 0; i < length; ++i) {
				key = word.substring(0, i) + '*' + word.substring(i + 1);
				neighbors = map.getOrDefault(key, new ArrayList<>());
				
				for (String neighbor : neighbors) {
					if (otherVisited.containsKey(neighbor)) {
						return level + otherVisited.get(neighbor);
					}
	
					if (!visited.containsKey(neighbor)) {
						visited.put(neighbor, level + 1);
						queue.add(new Pair<>(neighbor, level + 1));
					}
				}
			}
			
			return null;
		}
	}
}
