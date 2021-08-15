#include <vector>
#include <algorithm>

class Solution {
private:
    std::vector<int> tree;

    int lsb(int n) {
        return n & (-n);
    }

    void update(int index) {
        for (int i = index; i < tree.size(); i += lsb(i)) {
            tree[i] += 1;
        }
    }

    int query(int index) {
        int ans = 0;
        for (int i = index; i > 0; i -= lsb(i)) {
            ans += tree[i];
        }
        return ans;
    }

    int index(std::vector<int>& arr, int val) {
        return upper_bound(begin(arr), end(arr), val) - begin(arr);
    }

    int less_than_half(int num) {
        if (num % 2) {
            return num > 0 ? num / 2 : num / 2 - 1;
        } else {
            return num / 2 - 1;
        }
    }

public:
    /*
     * Iterate from right to left over nums, and update BIT.
     * Also, query the sum of counts.
     */
    int reversePairs(std::vector<int>& nums) {
        const int n = nums.size();
        std::vector<int> copy(nums);
        int ans = 0;

        tree.resize(n+1);
        sort(begin(copy), end(copy));

        for (int i = n - 1; i >= 0; --i) {
            ans += query(index(copy, less_than_half(nums[i])));
            update(index(copy, nums[i]));
        }

        return ans;
    }
};

