#include <vector>

class Solution {
public:
    /*
     * Time: O(n).
     * Space: O(1).
     */
    bool canJump(std::vector<int>& nums) {
        int reach = 0;

        for (int i = 0; i < nums.size() && i <= reach; ++i) {
            reach = std::max(reach, i + nums[i]);
            if (reach >= nums.size() - 1) {
                return true;
            }
        }

        return false;
    }
};

