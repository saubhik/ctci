#include <algorithm>
#include <vector>

class Solution {
public:
  int countSquares(std::vector<std::vector<int>> &matrix) {
    const int m = matrix.size();
    const int n = matrix[0].size();
    int res = 0;

    for (int i = 0; i < m; ++i) {
      for (int j = 0; j < n; ++j) {
        if (i && j && matrix[i][j]) {
          matrix[i][j] += std::min(
              {matrix[i - 1][j], matrix[i - 1][j - 1], matrix[i][j - 1]});
        }
        res += matrix[i][j];
      }
    }

    return res;
  }
};
