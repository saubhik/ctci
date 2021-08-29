#include <array>

// This is the robot's control interface.
// You should not implement it, or speculate about its implementation
class Robot {
  public:
    // Returns true if the cell in front is open and robot moves into the cell.
    // Returns false if the cell in front is blocked and robot stays in the current cell.
    bool move();
    // Robot will stay in the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
    void turnLeft();
    void turnRight();
    // Clean the current cell.
    void clean();
};

class Solution {
public:
    void cleanRoom(Robot& robot) {
        for (size_t i = 0; i < 500; ++i) {
            for (size_t j = 0; j < 500; ++j) {
                visited[i][j] = false;
            }
        }
        
        dfs(250, 250, 0, robot);
    }
private:
    std::array<std::array<bool, 500>, 500> visited;
    
    int dx[4] = {0,-1,0,1};
    int dy[4] = {1,0,-1,0};
    
    void dfs(uint32_t x, uint32_t y, uint32_t d, Robot& robot) {
        int nx;
        int ny;
        int nd;
        
        visited[x][y] = true;
        
        for (int i = 0; i < 4; ++i) {
            nd = (d + i) % 4;
            nx = x + dx[nd];
            ny = y + dy[nd];
            
            if (!visited[nx][ny] && robot.move()) {
                dfs(nx, ny, nd, robot);
                come_back(robot);
            }
            
            robot.turnLeft();
        }
        
        robot.clean();
    }
    
    void come_back(Robot& robot) {
        robot.turnLeft();
        robot.turnLeft();
        robot.move();
        robot.turnLeft();
        robot.turnLeft();
    }
};