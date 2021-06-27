/*
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class DiameterOfBinaryTree {
    private int diameter;
    
    /*
     * At every node, get the depth of the left
     * and the right subtree. Add them up. Take the max.
     *
     * Time: O(n). getDepth enters and exits every node once.
     * Space: O(n) worst case. In case of balanced tree,
     * O(logn).
     */
    public int diameterOfBinaryTree(TreeNode root) {
        diameter = 0;
        
        getDepth(root);
        
        return diameter;
    }
    
    private int getDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        int leftDepth;
        int rightDepth;
        
        leftDepth = getDepth(node.left);
        rightDepth = getDepth(node.right);
        
        diameter = Math.max(diameter, leftDepth + rightDepth);
        
        return 1 + Math.max(leftDepth, rightDepth);
    }
}