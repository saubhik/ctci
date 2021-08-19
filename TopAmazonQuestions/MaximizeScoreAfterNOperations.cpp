#include <vector>
#include <algorithm>

class Solution {
public:
    int dp[8][16384] = {};
    int maxScore(std::vector<int>& nums, int iter = 1, int mask = 0) {
        const int n = nums.size();
        if (iter > n / 2) {
            return 0;
        }

        if (!dp[iter][mask]) {
            for (int i = 0; i < n; ++i) {
                for (int j = i + 1; j < n; ++j) {
                    int new_mask = (1 << i) | (1 << j);
                    if ((mask & new_mask) == 0) {
                        dp[iter][mask] = std::max(
                        iter * std::__gcd(nums[i], nums[j]) +
                        maxScore(nums, iter + 1, mask | new_mask),
                        dp[iter][mask]);
                    }
                }
            }
        }

        return dp[iter][mask];
    }
};

