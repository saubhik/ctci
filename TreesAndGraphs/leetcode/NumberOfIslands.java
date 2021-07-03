public class NumberOfIslands {
	class Solution {
		private int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}};
		
		public int numIslands(char[][] grid) {
			int numRows;
			int numCols;
			int numIslands;
			
			numRows = grid.length;
			numCols = grid[0].length;
			numIslands = 0;
			
			for (int i = 0; i < numRows; ++i) {
				for (int j = 0; j < numCols; ++j) {
					if (grid[i][j] == '1') {
						dfs(grid, i, j);
						++numIslands;
					}
				}
			}
			
			return numIslands;
		}
		
		private void dfs(char[][] grid, int i, int j) {
			int newi;
			int newj;
			
			for (int[] dir : directions) {
				newi = i + dir[0];
				newj = j + dir[1];
				
				if (0 <= newi && newi < grid.length && 
					0 <= newj && newj < grid[0].length) {
					
					/* Either visited or water. */
					if (grid[newi][newj] == '0') {
						continue;
					}
	
					/* 0 also means visited. */
					grid[newi][newj] = '0';
	
					dfs(grid, newi, newj);
				}
			}
		}
	}
}
