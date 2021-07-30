public class TheMaze {
    class Solution {
        private int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
        
        public boolean hasPath(int[][] maze, int[] start, int[] destination) {
            boolean[][] visited;
            
            visited = new boolean[maze.length][maze[0].length];
            
            return dfs(maze, visited, start, destination);
        }
        
        private boolean dfs(
            int[][] maze, boolean[][] visited, int[] start, int[] destination) {
            int x;
            int y;
            
            if (start[0] == destination[0] && 
                start[1] == destination[1]) {
                return true;
            }
            
            visited[start[0]][start[1]] = true;
            
            for (int[] direction : directions) {
                x = start[0];
                y = start[1];
                
                while (0 <= x && x < maze.length && 
                       0 <= y && y < maze[0].length && 
                       maze[x][y] == 0) {
                    x += direction[0];
                    y += direction[1];
                }
                
                x -= direction[0];
                y -= direction[1];
                
                if (!visited[x][y] && 
                    dfs(maze, visited, new int[] {x, y}, destination)) {
                    return true;
                }
            }
            
            return false;
        }
    }
}
