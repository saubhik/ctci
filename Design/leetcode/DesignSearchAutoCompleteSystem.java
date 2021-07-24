import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class DesignSearchAutoCompleteSystem {
	class AutocompleteSystem {
		private class TrieNode {
			int count;
			TrieNode[] children;
			
			TrieNode() {
				this.children = new TrieNode[27];
				this.count = 0;
			}
		}
		
		private class Trie {
			TrieNode root;
			
			Trie() {
				this.root = new TrieNode();
			}
			
			void insert(String word, int count) {
				TrieNode curr;
				
				curr = this.root;
				for (char ch : word.toCharArray()) {
					if (curr.children[index(ch)] == null) {
						curr.children[index(ch)] = new TrieNode();
					}
					curr = curr.children[index(ch)];
				}
				
				curr.count = count;
			}
		}
		
		private Trie trie;
		private TrieNode node;
		private StringBuilder prefix;
		private PriorityQueue<Pair<Integer,String>> heap;
		
		private int index(char ch) {
			return ch==' ' ? 26 : ch-'a';
		}
		
		public AutocompleteSystem(String[] sentences, int[] times) {
			int n;
			
			this.trie = new Trie();
			this.node = trie.root;
			this.prefix = new StringBuilder();
			this.heap = new PriorityQueue<>(
				(x,y) -> x.getKey()==y.getKey() ? 
				/* Larger sentence gets popped first. */
				y.getValue().compareTo(x.getValue()) : 
				x.getKey()-y.getKey());
			n = sentences.length;
			
			for (int i = 0; i < n; ++i) {
				trie.insert(sentences[i], times[i]);
			}
		}
		
		public List<String> input(char c) {
			List<String> list;
			
			list = new LinkedList<>();
			
			this.heap.clear();
			
			if (c == '#') {
				this.node.count += 1;
				this.node = this.trie.root;
				this.prefix = new StringBuilder();
			} else {
				this.prefix.append(c);
				if (this.node.children[index(c)] == null) {
					this.node.children[index(c)] = new TrieNode();
				}
				this.node = this.node.children[index(c)];
				this.backtrack(node);
			}
					
			while (!this.heap.isEmpty()) {
				list.add(0,this.heap.poll().getValue());
			}
			
			return list;
		 }
		
		private void backtrack(TrieNode node) {
			TrieNode next;
			
			if (node.count > 0) {
				heap.add(new Pair<>(node.count, prefix.toString()));
				if (heap.size() > 3) {
					heap.poll();
				}
			}
			
			for (char ch : " abcdefghijklmnopqrstuvwxyz".toCharArray()) {
				next = node.children[index(ch)];
				if (next != null) {
					prefix.append(ch);
					backtrack(next);
					prefix.setLength(prefix.length()-1);
				}
			}
		}
	}
	
	/**
	 * Your AutocompleteSystem object will be instantiated and called as such:
	 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
	 * List<String> param_1 = obj.input(c);
	 */
}
