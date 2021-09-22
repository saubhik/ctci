#include <vector>
#include <queue>
#include <unordered_set>

class Solution {
public:
  int shortestPath(std::vector<std::vector<int> > &grid, int k) {
    int dirs[4][2] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int m = grid.size();
    int n = grid[0].size();
    std::queue<QState> q;
    std::unordered_set<QState, QStateHash> us;

    QState state = QState(0, 0, k, 0);
    q.push(state);
    us.insert(state);

    while (!q.empty()) {
      auto st = q.front();
      q.pop();

      if (st.row == m - 1 && st.col == n - 1)
        return st.path;

      for (auto &d : dirs) {
        int nr = st.row + d[0];
        int nc = st.col + d[1];
        if (0 <= nr && nr < m && 0 <= nc && nc < n) {
          if (grid[nr][nc] == 0) {
            auto state = QState(nr, nc, st.quota, st.path + 1);
            if (us.find(state) == us.end()) {
              q.push(state);
              us.insert(state);
            }
          } else if (st.quota > 0) {
            auto state = QState(nr, nc, st.quota - 1, st.path + 1);
            if (us.find(state) == us.end()) {
              q.push(state);
              us.insert(state);
            }
          }
        }
      }
    }

    return -1;
  }

private:
  struct QState {
    int row;
    int col;
    int quota;
    int path;

    QState(int r, int c, int q, int p) : row(r), col(c), quota(q), path(p){};

    bool operator==(const QState &s) const {
      return this->row == s.row && this->col == s.col && this->quota == s.quota;
    }
  };

  class QStateHash {
  public:
    int operator()(const QState &s) const {
      return (s.row + 1) * (s.col + 1) * (s.quota + 1);
    }
  };
};
