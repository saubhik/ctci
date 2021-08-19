#include <vector>


class Solution {
public:
    /*
     * Sliding Window.
     * Keep moving right pointer. In each iteration try to shrink left pointer,
     * keeping K distinct elements. Number of prefix positions gets carried to
     * next iteration, unless you encounter an unseen integer.
     */
    int subarraysWithKDistinct(std::vector<int>& nums, int k) {
        const int n = nums.size();
        int ans = 0;
        std::vector<int> map(n + 1);

        for (int i = 0, j = 0, count = 0, prefix = 1; j < n; ++j) {
            if (map[nums[j]]++ == 0) {
                ++count;
            }

            if (count > k) {
                --map[nums[i++]];
                --count;
                prefix = 1;
            }

            while (map[nums[i]] > 1) {
                --map[nums[i++]];
                ++prefix;
            }

            if (count == k) {
                ans += prefix;
            }
        }

        return ans;
    }
};
