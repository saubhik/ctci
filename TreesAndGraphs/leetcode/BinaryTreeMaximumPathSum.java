public class BinaryTreeMaximumPathSum {
	class Solution {
		private int maxPathSum;
		
		/*
		 * Consider the contribution to maxPathSum
		 * if a node is included. A node can either
		 * be included or will be the highest node
		 * of the path.
		 */
		public int maxPathSum(TreeNode root) {
			maxPathSum = Integer.MIN_VALUE;
			maxGain(root);
			return maxPathSum;
		}
		
		private int maxGain(TreeNode node) {
			int leftGain;
			int rightGain;
			int pathSum;
			
			if (node == null) {
				return 0;
			}
			
			leftGain = Math.max(maxGain(node.left), 0);
			rightGain = Math.max(maxGain(node.right), 0);
			
			pathSum = leftGain + node.val + rightGain;
			maxPathSum = Math.max(maxPathSum, pathSum);
			
			return Math.max(leftGain, rightGain) + node.val;
		}
	}
}
