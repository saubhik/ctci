public class DesignTicTacToe {
	class TicTacToe {
		private int[] playerRowCount;
		private int[] playerColCount;
		private int playerDiagCount;
		private int playerAntiDiagCount;
		private int n;
		
		/** Initialize your data structure here. */
		public TicTacToe(int n) {
			this.n = n;
			this.playerRowCount = new int[n];
			this.playerColCount = new int[n];
			this.playerDiagCount = 0;
			this.playerAntiDiagCount = 0;
		}
		
		/** Player {player} makes a move at ({row}, {col}).
			@param row The row of the board.
			@param col The column of the board.
			@param player The player, can be either 1 or 2.
			@return The current winning condition, can be either:
					0: No one wins.
					1: Player 1 wins.
					2: Player 2 wins. */
		/*
		 * Time: O(1).
		 * Space: O(n).
		 */
		public int move(int row, int col, int player) {
			int currentPlayer;
			
			currentPlayer = player == 1 ? 1 : -1;
			
			this.playerRowCount[row] += currentPlayer;
			if (Math.abs(this.playerRowCount[row]) == this.n) {
				return player;
			}
	
			this.playerColCount[col] += currentPlayer;
			if (Math.abs(this.playerColCount[col]) == this.n) {
				return player;
			}
	
			if (row == col) {
				this.playerDiagCount += currentPlayer;
				if (Math.abs(this.playerDiagCount) == this.n) {
					return player;
				}
			}
	
			if (row + col == this.n - 1) {
				this.playerAntiDiagCount += currentPlayer;
				if (Math.abs(this.playerAntiDiagCount) == this.n) {
					return player;
				}
			}
			
			return 0;
		}
	}
	
	/**
	 * Your TicTacToe object will be instantiated and called as such:
	 * TicTacToe obj = new TicTacToe(n);
	 * int param_1 = obj.move(row,col,player);
	 */
}
