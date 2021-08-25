#include <vector>

class Solution {
public:
    int longestIncreasingPath(std::vector<std::vector<int>>& matrix) {
        const int rows = matrix.size();
        const int cols = matrix[0].size();
        int ans;
        std::vector<std::vector<int>> cache(rows, std::vector<int>(cols, 0));
        
        ans = 0;
    
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                ans = std::max(ans, dfs(i, j, matrix, cache));
            }
        }
        
        return ans;
    }
private:
    const int di[4] = {1,0,-1,0};
    const int dj[4] = {0,-1,0,1};
    int dfs(int i, int j, std::vector<std::vector<int>>& matrix, std::vector<std::vector<int>>& cache) {
        int length;
        int ni;
        int nj;
        
        if (cache[i][j]) {
            return cache[i][j];
        }
        
        length = 0;

        for (int k = 0; k < 4; ++k) {
            ni = i + di[k];
            nj = j + dj[k];
            if (0 <= ni && ni < matrix.size() && 
                0 <= nj && nj < matrix[0].size() &&
                matrix[ni][nj] > matrix[i][j]) {
                length = std::max(length, dfs(ni, nj, matrix, cache));
            }
        }
        
        return cache[i][j] = length + 1;
    }
};
