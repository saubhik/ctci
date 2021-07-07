public class WordSearch {
	class Solution {
		private int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}};
		private char[][] board;
		private String word;
		
		/*
		 * Time: O(N * 3^L)
		 * Space: O(L) due to recursion stack.
		 */
		public boolean exist(char[][] board, String word) {
			int nrows;
			int ncols;
			
			nrows = board.length;
			ncols = board[0].length;
			this.board = board;
			this.word = word;
			
			for (int i = 0; i < nrows; ++i) {
				for (int j = 0; j < ncols; ++j) {
					if (search(i, j, 0)) {
						return true;
					}
				}
			}
			
			return false;
		}
		
		private boolean search(int i, int j, int k) {
			int newi;
			int newj;
			
			if (k == word.length()) {
				return true;
			}
			
			if (0 <= i && i < board.length && 
				0 <= j && j < board[0].length &&
				board[i][j] == word.charAt(k)) {
				
				board[i][j] = '.';
				
				for (int[] direction : directions) {
					newi = i + direction[0];
					newj = j + direction[1];
	
					if (search(newi, newj, k + 1)) {
						board[i][j] = word.charAt(k);
						return true;
					}
				}
				
				board[i][j] = word.charAt(k);
			}
			
			return false;
		}
	}
}
