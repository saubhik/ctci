#include <vector>
#include <algorithm>

class Solution {
public:
    int maxValue(std::vector<std::vector<int>>& events, int k) {
        int n = events.size();
        std::vector<std::vector<int>> dp(n, std::vector<int>(k + 1, -1));

        sort(begin(events), end(events));

        return dfs(0, 0, k, events, dp);
    }

private:
    int dfs(
    int start,
    int index,
    int rem,
    std::vector<std::vector<int>>& events,
    std::vector<std::vector<int>>& dp) {
        if (rem == 0 || index >= events.size()) {
            return 0;
        }

        if (dp[index][rem] != -1) {
            return dp[index][rem];
        }

        int max_value = 0;

        for (int i = index; i < events.size(); ++i) {
            if (events[i][0] >= start) {
                max_value = std::max(max_value,
                                events[i][2] + dfs(
                                events[i][1] + 1, i + 1, rem - 1, events, dp));
            }
        }

        dp[index][rem] = max_value;
        return max_value;
    }
};
