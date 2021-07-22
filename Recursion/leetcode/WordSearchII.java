import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordSearchII {
	class Solution {
		private int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
		private List<String> words;
		private char[][] board;
		private boolean[][] visited;
		private int numRows;
		private int numCols;
		
		private class TrieNode {
			Map<Character,TrieNode> children;
			String word;
			
			public TrieNode() {
				this.children = new HashMap<>();
				this.word = null;
			};
		}
		
		private class Trie {
			TrieNode root;
			
			public Trie() {
				this.root = new TrieNode();
			}
			
			void insert(String word) {
				TrieNode node;
				
				node = this.root;
				for (char ch : word.toCharArray()) {
					if (!node.children.containsKey(ch)) {
						node.children.put(ch, new TrieNode());
					}
					
					node = node.children.get(ch);
				}
				
				node.word = word;
			}
		}
		
		public List<String> findWords(char[][] board, String[] words) {
			Trie trie;
			
			trie = new Trie();
			this.words = new ArrayList<>();
			this.board = board;
			this.numRows = board.length;
			this.numCols = board[0].length;
			this.visited = new boolean[numRows][numCols];
			
			for (String w : words) {
				trie.insert(w);
			}
			
			for (int row = 0; row < numRows; ++row) {
				for (int col = 0; col < numCols; ++col) {
					backtrack(row, col, trie.root);
				}
			}
			
			return this.words;
		}
		
		private void backtrack(int row, int col, TrieNode currNode) {
			int newRow;
			int newCol;
			
			if (0 <= row && row < numRows && 
				0 <= col && col < numCols) {
				
				if (visited[row][col] || 
					!currNode.children.containsKey(board[row][col])) {
					return;
				}
				
				visited[row][col] = true;
				currNode = currNode.children.get(board[row][col]);
				
				if (currNode.word != null) {
					words.add(currNode.word);
					currNode.word = null;
				}
				
				for (int[] d : directions) {
					newRow = row + d[0];
					newCol = col + d[1];
					backtrack(newRow, newCol, currNode);
				}
				
				visited[row][col] = false;
			}
		}
	}

	class SolutionTwo {
		private int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
		private List<String> words;
		private char[][] board;
		private int numRows;
		private int numCols;
		
		private class TrieNode {
			Map<Character,TrieNode> children;
			String word;
			
			public TrieNode() {
				this.children = new HashMap<>();
				this.word = null;
			};
		}
		
		private class Trie {
			TrieNode root;
			
			public Trie() {
				this.root = new TrieNode();
			}
			
			void insert(String word) {
				TrieNode node;
				
				node = this.root;
				for (char ch : word.toCharArray()) {
					if (!node.children.containsKey(ch)) {
						node.children.put(ch, new TrieNode());
					}
					
					node = node.children.get(ch);
				}
				
				node.word = word;
			}
		}
		
		/*
		 * Time: O(M * (4 * 3^(L-1))).
		 *  where M is the number of cells in the board, and
		 *  L is the maximum word length.
		 * Space: O(N) where N is the total number of letters in words
		 *  array.
		 */
		public List<String> findWords(char[][] board, String[] words) {
			Trie trie;
			
			trie = new Trie();
			this.words = new ArrayList<>();
			this.board = board;
			this.numRows = board.length;
			this.numCols = board[0].length;
			
			for (String w : words) {
				trie.insert(w);
			}
			
			for (int row = 0; row < numRows; ++row) {
				for (int col = 0; col < numCols; ++col) {
					backtrack(row, col, trie.root);
				}
			}
			
			return this.words;
		}
		
		/* Upper Bound: O(4*3^(L-1)) where L is the maximum length 
		 * of a word. Without trie-trimming considered.
		 */
		private void backtrack(int row, int col, TrieNode currNode) {
			TrieNode parentNode;
			char letter;
			int newRow;
			int newCol;
			
			if (0 <= row && row < numRows && 
				0 <= col && col < numCols) {
				
				letter = board[row][col];
	
				if (!currNode.children.containsKey(letter)) {
					return;
				}
				
				parentNode = currNode;
				currNode = currNode.children.get(letter);
				board[row][col] = '#';
	
				if (currNode.word != null) {
					words.add(currNode.word);
					currNode.word = null;
				}
				
				for (int[] d : directions) {
					newRow = row + d[0];
					newCol = col + d[1];
					backtrack(newRow, newCol, currNode);
				}
				
				board[row][col] = letter;
				
				/* Trim the trie. */
				if (currNode.children.isEmpty()) {
					parentNode.children.remove(letter);
				}
			}
		}
	}
}