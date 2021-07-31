public class MaximumAverageSubtree {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    class Solution {
        private double maxAvg;
        
        /*
        * Post-Order DFS.
        * Time: O(N) where N is the number of nodes.
        * Space: O(N) where N is the number of nodes.
        */
        public double maximumAverageSubtree(TreeNode root) {
            this.maxAvg = -1;
            
            dfs(root);
            
            return this.maxAvg;
        }
        
        private int[] dfs(TreeNode node) {
            int[] leftPair;
            int[] rightPair;
            int sum;
            int nodes;
            
            sum = node.val;
            nodes = 1;
            
            if (node.left != null) {
                leftPair = dfs(node.left);
                sum += leftPair[0];
                nodes += leftPair[1];
                
                if (leftPair[0] > this.maxAvg * leftPair[1]) {
                    this.maxAvg = leftPair[0] / (double) leftPair[1];
                }
            }
            
            if (node.right != null) {
                rightPair = dfs(node.right);
                sum += rightPair[0];
                nodes += rightPair[1];
                
                if (rightPair[0] > this.maxAvg * rightPair[1]) {
                    this.maxAvg = rightPair[0] / (double) rightPair[1];
                }
            }
            
            if (sum > this.maxAvg * nodes) {
                this.maxAvg = sum / (double) nodes;
            }
            
            return new int[] {sum,nodes};
        }
    }
}
