import java.util.ArrayDeque;
import java.util.Queue;

public class ShortestPathInBinaryMatrix {
    class Solution {
        public int shortestPathBinaryMatrix(int[][] grid) {
            Queue<int[]> queue;
            int[] cell;
            int[] nextCell;
            int[][] directions;
            boolean[][] visited;
            int n;
            int level;
            
            if (grid[0][0] == 1) {
                return -1;
            }
            
            n = grid.length;
            queue = new ArrayDeque<>();
            visited = new boolean[n][n];
            directions = new int[][] 
                {{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1},{-1,0},{-1,1}};
            level = 1;
            
            visited[0][0] = true;
            queue.offer(new int[] {0,0});
            
            while (!queue.isEmpty()) {
                for (int i=queue.size()-1; i>=0; --i) {
                    cell = queue.poll();
                    if (cell[0] == n-1 && cell[1] == n-1) {
                        return level;
                    }
                    
                    for (int[] direction : directions) {
                        nextCell = new int[] 
                            {cell[0]+direction[0], cell[1]+direction[1]};
                        if (0 <= nextCell[0] && nextCell[0] < n && 
                            0 <= nextCell[1] && nextCell[1] < n &&
                            !visited[nextCell[0]][nextCell[1]] &&
                            grid[nextCell[0]][nextCell[1]] == 0) {
                            visited[nextCell[0]][nextCell[1]] = true;
                            queue.offer(nextCell);
                        }
                    }
                }
                
                ++level;
            }
            
            return -1;
        }
    }
}