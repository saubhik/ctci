import java.util.ArrayList;
import java.util.List;

public class WordBreakII {
	class Solution {
		private String word;
		private Trie trie;
		private List<String> results;
	
		private class TrieNode {
			TrieNode[] children;
			boolean isEnd;
			
			TrieNode() {
				this.children = new TrieNode[26];
				this.isEnd = false;
			}
		}
		
		private class Trie {
			TrieNode root;
			
			Trie() {
				this.root = new TrieNode();
			}
			
			void insert(String word) {
				TrieNode curr;
				
				curr = this.root;
				for (char c : word.toCharArray()) {
					if (curr.children[c-'a']==null) {
						curr.children[c-'a']=new TrieNode();
					}
					curr = curr.children[c-'a'];
				}
				
				curr.isEnd = true;
			}    
		}
		
		public List<String> wordBreak(String s, List<String> wordDict) {
			this.trie = new Trie();
			this.word = s;
			this.results = new ArrayList<>();
			
			for (String word : wordDict) {
				trie.insert(word);
			}
			
			backtrack(trie.root, 0, new StringBuilder());
			
			return results;
		}
		
		private void backtrack(
			TrieNode node, int index, StringBuilder sentence) {
			char c;
			
			if (index == this.word.length()) {
				if (node.isEnd) {
					results.add(sentence.toString());
				}
				return;
			}
			
			c = this.word.charAt(index);
			
			if (node.isEnd) {
				if (trie.root.children[c-'a']!=null) {
					sentence.append(" ");
					sentence.append(c);
					backtrack(trie.root.children[c-'a'],index+1,sentence);
					sentence.setLength(sentence.length()-2);
				}
			}
	
			if (node.children[c-'a']!=null) {
				sentence.append(c);
				backtrack(node.children[c-'a'],index+1,sentence);
				sentence.setLength(sentence.length()-1);
			}
		}
	}
}