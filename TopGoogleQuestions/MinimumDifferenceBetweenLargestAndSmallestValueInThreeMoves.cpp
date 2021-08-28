#include <vector>
#include <algorithm>
#include <functional>

class Solution {
public:
    /*
     * There are 4 options:
     * - remove 3 smallest
     * - remove 3 largest
     * - remove 2 smallest, 1 largest
     * - remove 1 smallest, 2 largest
     * Minimum of the above cases is the answer.
     */
    int minDifference(std::vector<int>& nums) {
        const int n = nums.size();
        if (n < 5) return 0;
        partial_sort(begin(nums), begin(nums)+4, end(nums));
        partial_sort(rbegin(nums), rbegin(nums)+4, rend(nums), std::greater<>());
        return std::min({nums[n-1]-nums[3], nums[n-2]-nums[2], nums[n-3]-nums[1], nums[n-4]-nums[0]});
    }
};
