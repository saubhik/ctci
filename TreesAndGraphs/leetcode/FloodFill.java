public class FloodFill {
	class Solution {
		private int[][] image;
		private int nrows;
		private int ncols;
		private int color;
		private int newColor;
		private int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
		
		/*
		 * Using DFS.
		 * Time: O(n).
		 * Space: O(n).
		 */
		public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
			this.image = image;
			this.nrows = image.length;
			this.ncols = image[0].length;
			this.color = image[sr][sc];
			this.newColor = newColor;
			
			if (this.color == this.newColor) {
				return image;
			}
			
			dfs(sr, sc);
			
			return this.image;
		}
		
		private void dfs(int row, int col) {
			int newRow;
			int newCol;
	
			if (0 <= row && row < nrows && 
				0 <= col && col < ncols && 
				image[row][col] == color) {
				image[row][col] = newColor;
				for (int[] direction : directions) {
					newRow = row + direction[0];
					newCol = col + direction[1];
					dfs(newRow, newCol);
				}
			}
		}
	}
}
