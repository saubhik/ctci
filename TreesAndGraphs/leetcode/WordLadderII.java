import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordLadderII {
	class Solution {
		private Map<String,List<String>> graph;
		private List<List<String>> paths;
		private List<String> path;
		private Set<String> wordSet;
		
		/*
		 * BFS to create DAG, and then backtrack.
		 * Time: O(NK^2 + x), where x is the number of paths from
		 *  beginWord to endWord.
		 * Space: O(NK)
		 */
		public List<List<String>> 
			findLadders(String beginWord, 
						String endWord, List<String> wordList) {
			graph = new HashMap<>();
			paths = new ArrayList<>();
			wordSet = new HashSet<>(wordList);
			path = new ArrayList<>();
			
			bfs(beginWord);
			path.add(beginWord);
			System.out.println(graph);
			backtrack(beginWord, endWord);
			
			return paths;
		}
		
		private List<String> findNeighbors(String word) {
			List<String> neighbors;
			String neighbor;
			
			neighbors = new ArrayList<>();
			
			for (int i = 0; i < word.length(); ++i) {
				for (char c = 'a'; c <= 'z'; ++c) {
					if (c != word.charAt(i)) {
						neighbor = word.substring(0,i) + 
							c + word.substring(i+1);
						if (wordSet.contains(neighbor)) {
							neighbors.add(neighbor);
						}
					}
				}
			}
			
			return neighbors;
		}
		
		private void bfs(String beginWord) {
			List<String> neighbors;
			Deque<String> q;
			Set<String> enqueued;
			List<String> wordsInLevel;
			String word;
			
			q = new ArrayDeque<>();
			/* no edges between words in same level */
			enqueued = new HashSet<>();
			/* no edges back to previous level */
			wordsInLevel = new ArrayList<>();
			
			q.add(beginWord);
			enqueued.add(beginWord);
			wordSet.remove(beginWord);
			
			while (!q.isEmpty()) {
				wordsInLevel.clear();
				for (int i = q.size() - 1; i >= 0; --i) {
					word = q.pop();
					
					if (!graph.containsKey(word)) {
						graph.put(word, new ArrayList<>());
					}
					
					neighbors = findNeighbors(word);
					for (String neighbor : neighbors) {
						wordsInLevel.add(neighbor);
						graph.get(word).add(neighbor);
						if (!enqueued.contains(neighbor)) {
							q.add(neighbor);
							enqueued.add(neighbor);
						}
					}
				}
				
				/* Remove words of previous level */
				for (String w : wordsInLevel) {
					wordSet.remove(w);
				}
			}
		}
		
		private void backtrack(String source, String destination) {
			List<String> neighbors;
			
			if (source.equals(destination)) {
				paths.add(new ArrayList<>(path));
				return;
			}
			
			if (!graph.containsKey(source)) {
				return;
			}
			
			neighbors = graph.get(source);
			for (String neighbor : neighbors) {
				path.add(neighbor);
				backtrack(neighbor, destination);
				path.remove(path.size() - 1);
			}
		}
	}	
}
