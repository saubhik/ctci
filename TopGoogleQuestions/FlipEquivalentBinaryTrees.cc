/**
 * Definition for a binary tree node.
 */
struct TreeNode {
  int val;
  TreeNode *left;
  TreeNode *right;
  TreeNode() : val(0), left(nullptr), right(nullptr) {}
  TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
  TreeNode(int x, TreeNode *left, TreeNode *right)
      : val(x), left(left), right(right) {}
};

class Solution {
public:
  bool flipEquiv(TreeNode *root1, TreeNode *root2) {
    TreeNode *l1, *l2, *r1, *r2;

    if (!root1 && !root2)
      return true;

    if (root1 && root2 && root1->val == root2->val) {
      l1 = root1->left;
      l2 = root2->left;
      r1 = root1->right;
      r2 = root2->right;

      if (((!l1 && !l2) || (l1 && l2 && l1->val == l2->val)) &&
          ((!r1 && !r2) || (r1 && r2 && r1->val == r2->val))) {
        return flipEquiv(l1, l2) && flipEquiv(r1, r2);
      } else if (((!l1 && !r2) || (l1 && r2 && l1->val == r2->val)) &&
                 ((!r1 && !l2) || (r1 && l2 && r1->val == l2->val))) {
        return flipEquiv(l1, r2) && flipEquiv(r1, l2);
      }
    }

    return false;
  }
};
