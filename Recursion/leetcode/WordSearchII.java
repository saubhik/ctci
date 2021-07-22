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
}