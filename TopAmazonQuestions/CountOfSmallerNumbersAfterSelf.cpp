#include <vector>
#include <algorithm>


class Solution {
public:
    std::vector<int> countSmaller(std::vector<int>& nums) {
        int counts;
        const int offset = 1e4;
        const int size = 2 * 1e4 + 1;
        std::vector<int> tree(size*2);
        std::vector<int> result;

        for (int i = nums.size() - 1; i >= 0; --i) {
            counts = query(0, nums[i]+offset, tree, size);
            result.push_back(counts);
            update(nums[i]+offset, 1, tree, size);
        }

        reverse(begin(result), end(result));

        return result;
    }

private:
    void update(int index, int value, std::vector<int>& tree, int size) {
        /*
         * Move the corresponding leaf, and
         * update from bottom up.
         */
        index += size;
        tree[index] += value;
        while (index > 1) {
            index /= 2;
            tree[index] = tree[index*2] + tree[index*2+1];
        }
    }

    int query(int left, int right, std::vector<int>& tree, int size) {
        int result = 0;

        /*
         * Move to the leaves.
         */
        left += size;
        right += size;

        while (left < right) {
            /*
             * If left is right node, add value
             * and move to parent's right node.
             */
            if (left % 2 == 1) {
                result += tree[left];
                ++left;
            }

            left /= 2;

            if (right % 2 == 1) {
                --right;
                result += tree[right];
            }

            right /= 2;
        }

        return result;
    }
};

