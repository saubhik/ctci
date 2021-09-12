#include <vector>

class Solution {
public:
  int maxAreaOfIsland(std::vector<std::vector<int>> &grid) {
    const int rows = grid.size();
    const int cols = grid[0].size();
    std::vector<std::vector<bool>> visited(rows,
                                           std::vector<bool>(cols, false));
    int area = 0;

    for (int i = 0; i < rows; ++i) {
      for (int j = 0; j < cols; ++j) {
        if (grid[i][j] == 1 && !visited[i][j]) {
          area = std::max(area, dfs(i, j, grid, visited));
        }
      }
    }

    return area;
  }

private:
  const int di[4] = {1, 0, -1, 0};
  const int dj[4] = {0, -1, 0, 1};
  int dfs(int i, int j, std::vector<std::vector<int>> &grid,
          std::vector<std::vector<bool>> &visited) {
    int area;
    int i_next;
    int j_next;

    area = 1;
    visited[i][j] = true;

    for (int k = 0; k < 4; ++k) {
      i_next = i + di[k];
      j_next = j + dj[k];
      if (0 <= i_next && i_next < grid.size() && 0 <= j_next &&
          j_next < grid[0].size() && grid[i_next][j_next] == 1 &&
          !visited[i_next][j_next]) {
        area += dfs(i_next, j_next, grid, visited);
      }
    }

    return area;
  }
};
