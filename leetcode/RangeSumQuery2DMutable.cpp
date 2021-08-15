#include <vector>


class NumMatrix {
private:
    std::vector<std::vector<int>> bit;
    int rows;
    int cols;

    int lsb(int n) {
        return n & (-n);
    }

    /* O(MN) time. */
    void buildBIT(std::vector<std::vector<int>>& matrix) {
        int k;

        for (int i = 1; i <= rows; ++i) {
            for (int j = 1; j <= cols; ++j) {
                bit[i][j] = matrix[i-1][j-1];
            }
        }

        for (int i = 1; i <= rows; ++i) {
            for (int j = 1; j <= cols; ++j) {
                k = j + lsb(j);
                if (k <= cols) {
                    bit[i][k] += bit[i][j];
                }
            }
        }

        for (int j = 1; j <= cols; ++j) {
            for (int i = 1; i <= rows; ++i) {
                k = i + lsb(i);
                if (k <= rows) {
                    bit[k][j] += bit[i][j];
                }
            }
        }
    }

    /* O(logMlogN) time. */
    void updateBIT(int row, int col, int diff) {
        for (int i = row; i <= rows; i += lsb(i)) {
            for (int j = col; j <= cols; j += lsb(j)) {
                bit[i][j] += diff;
            }
        }
    }

    /* O(logMlogN) time. */
    int queryBIT(int row, int col) {
        int ans = 0;
        for (int i = row; i >= 1; i -= lsb(i)) {
            for (int j = col; j >= 1; j-= lsb(j)) {
                ans += bit[i][j];
            }
        }
        return ans;
    }

public:
    NumMatrix(std::vector<std::vector<int>>& matrix) {
        rows = matrix.size();
        cols = matrix[0].size();

        bit.resize(rows+1);
        for (int i = 1; i <= rows; ++i) {
            bit[i].resize(cols + 1);
        }

        buildBIT(matrix);
    }

    void update(int row, int col, int val) {
        ++row, ++col;
        int a = queryBIT(row, col);
        int b = queryBIT(row - 1, col - 1);
        int c = queryBIT(row - 1, col);
        int d = queryBIT(row, col - 1);
        int old_val = (a + b) - (c + d);
        updateBIT(row, col, val - old_val);
    }

    int sumRegion(int row1, int col1, int row2, int col2) {
        ++row1, ++col1, ++row2, ++col2;
        int a = queryBIT(row2, col2);
        int b = queryBIT(row1 - 1, col1 - 1);
        int c = queryBIT(row1 - 1, col2);
        int d = queryBIT(row2, col1 - 1);
        return (a + b) - (c + d);
    }
};

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix* obj = new NumMatrix(matrix);
 * obj->update(row,col,val);
 * int param_2 = obj->sumRegion(row1,col1,row2,col2);
 */

