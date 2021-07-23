import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class CutOffTreesForGolfEvent {
	class Solution {
		private int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
		private List<List<Integer>> forest;
		private int numRows;
		private int numCols;
		
		/*
		 * BFS+Heap.
		 * Get the minimum number of steps to the first
		 * small tree. Keep doing this.
		 * Using BFS to find the minimum number of steps
		 * from start to destination.
		 * Poll from Heap to get the next smaller tree.
		 * Time: O(m^2 * n^2)
		 * Space: O(mn)
		 */
		public int cutOffTree(List<List<Integer>> forest) {
			PriorityQueue<int[]> heap;
			int height;
			int sum;
			int steps;
			int[] start;
			int[] destination;
			
			heap = new PriorityQueue<>((x,y)->x[2]-y[2]);
			this.forest = forest;
			this.numRows = forest.size();
			this.numCols = forest.get(0).size();
			sum = 0;
			start = new int[2];
			
			/* O(mnlog(mn)) */
			for (int i = 0; i < numRows; ++i) {
				for (int j = 0; j < numCols; ++j) {
					height = forest.get(i).get(j);
					if (height > 1) {
						heap.offer(new int[] {i,j,height});
					}
				}
			}
			
			/* O(mn*mn) */
			while (!heap.isEmpty()) {
				destination = heap.poll();
				steps = getMinSteps(start, destination);
				if (steps == -1) {
					return steps;
				}
				
				sum += steps;
				start[0] = destination[0];
				start[1] = destination[1];
			}
			
			return sum;
		}
		
		private int getMinSteps(int[] start, int[] destination) {
			Queue<int[]> queue;
			int[] tree;
			boolean[][] visited;
			int nextRow;
			int nextCol;
			
			queue = new LinkedList<>();
			visited = new boolean[this.numRows][this.numCols];
			
			queue.offer(new int[] {start[0],start[1],0});
			visited[start[0]][start[1]] = true;
			
			while (!queue.isEmpty()) {
				tree = queue.poll();
							
				if (tree[0] == destination[0] &&
					tree[1] == destination[1]) {
					return tree[2];
				}
				
				for (int[] direction : directions) {
					nextRow = tree[0] + direction[0];
					nextCol = tree[1] + direction[1];
					
					if (0 <= nextRow && nextRow < this.numRows && 
						0 <= nextCol && nextCol < this.numCols && 
						this.forest.get(nextRow).get(nextCol) >= 1 && 
						!visited[nextRow][nextCol]) {
						queue.offer(new int[] {nextRow,nextCol,tree[2]+1});
						visited[nextRow][nextCol] = true;
					}
				}
			}
			
			return -1;
		}
	}
}
