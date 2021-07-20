import java.util.ArrayList;
import java.util.List;

public class SearchSuggestionsSystem {
	class Solution {
		public List<List<String>> 
			suggestedProducts(String[] products, String searchWord) {
			Trie trie;
			List<List<String>> results;
			String prefix;
			
			trie = new Trie();
			results = new ArrayList<>();
			prefix = new String();
			
			/* O(kp), where p is the number of products */
			for (String w : products) {
				trie.insert(w);
			}
			
			/* O(k^3), where n is the length of search word. */
			for (char c : searchWord.toCharArray()) {
				prefix += c;
				results.add(trie.getWordsStartingWith(prefix));
			}
			
			return results;
		}
		
		class Trie {
			class Node {
				boolean isWord = false;
				Node[] children = new Node[26];
			};
			
			Node root;
			
			Trie() {
				root = new Node();
			}
			
			/* O(k) where k is maximum length of word. */
			public void insert(String s) {
				Node curr;
				
				curr = root;
				
				for (char c : s.toCharArray()) {
					if (curr.children[c-'a'] == null) {
						curr.children[c-'a'] = new Node();
					}
					curr = curr.children[c-'a'];
				}
				
				curr.isWord = true;
			}
			
			/* O(k^2) where k is maximum length of word. */
			public List<String> getWordsStartingWith(String prefix) {
				Node curr;
				List<String> words;
				
				words = new ArrayList<>();
				curr = root;
				
				/* O(k) where k is maximum length of word. */
				for (char c : prefix.toCharArray()) {
					if (curr.children[c-'a'] != null) {
						curr = curr.children[c-'a'];
					} else {
						return words;
					}
				}
				
				/* O(k^2) */
				dfs(curr, prefix, words);
				
				return words;
			}
			
			/* O(k^2) where k is maximum length of word. */
			private void dfs(Node node, String word, List<String> words) {
				if (words.size() == 3 || node == null) {
					return;
				}
				
				if (node.isWord) {
					words.add(word.toString());
				}
				
				for (int i = 0; i < 26; ++i) {
					dfs(node.children[i], word+(char)('a'+i), words);
				}
			}
		}
	}
}