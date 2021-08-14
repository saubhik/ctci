#include <vector>

class NumArray {
private:
    std::vector<int> tree;
    int size;
public:
    NumArray(std::vector<int>& nums) {
        size = nums.size();
        build(nums);
    }

    void build(std::vector<int>& nums) {
        tree.resize(2*size);

        for (int i = 0; i < size; ++i) {
            tree[i+size] = nums[i];
        }

        for (int i = size-1; i > 0; --i) {
            tree[i] = tree[i*2] + tree[i*2+1];
        }
    }

    void update(int index, int val) {
        /* Go to the leaf. */
        index += size;

        /* Update from bottom up. */
        tree[index] = val;
        while (index > 1) {
            index /= 2;
            tree[index] = tree[index*2] + tree[index*2+1];
        }
    }

    int sumRange(int left, int right) {
        int result = 0;

        /* Go to the leaves. */
        left += size;
        right += size;

        while (left <= right) {
            if (left % 2 == 1) {
                result += tree[left];
                left++;
            }

            left /= 2;

            if (right % 2 == 0) {
                result += tree[right];
                --right;
            }

            right /= 2;
        }

        return result;
    }
};

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray* obj = new NumArray(nums);
 * obj->update(index,val);
 * int param_2 = obj->sumRange(left,right);
 */
