public class SubtreeOfAnotherTree {
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
		public boolean isSubtree(TreeNode root, TreeNode subRoot) {
			return this.isSubtree(root, subRoot, false);
		}
		
		public boolean isSubtree(TreeNode root, TreeNode subRoot, boolean rootFound) {
			if (root == null) {
				return subRoot == null;
			}
			
			if (subRoot == null) {
				return root == null;
			}
			
			if (root.val == subRoot.val) {
				if (isSubtree(root.left, subRoot.left, true) && 
					isSubtree(root.right, subRoot.right, true)) {
					return true;
				}
			}
			
			if (rootFound) {
				return false;
			}
			
			return isSubtree(root.left, subRoot, false) || 
				isSubtree(root.right, subRoot, false);
		}
	}
}