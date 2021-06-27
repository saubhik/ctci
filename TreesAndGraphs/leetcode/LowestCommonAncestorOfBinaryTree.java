public class LowestCommonAncestorOfBinaryTree {
	class Solution1 {
		private TreeNode lca = null;

		/*
		* DFS style.
		* Find one of the nodes. Mark the top nodes as parent of one.
		* Keep searching for the other node. If found return.
		*/
		public TreeNode lowestCommonAncestor(TreeNode node, TreeNode p, TreeNode q) {
			dfs(node, p, q);
			return lca;
		}
		
		private boolean dfs(TreeNode node, TreeNode p, TreeNode q) {
			int left;
			int right;
			int current;
			
			if (node == null) {
				return false;
			}
			
			current = (node == p || node == q) ? 1 : 0;
			left = dfs(node.left, p, q) ? 1 : 0;
			right = dfs(node.right, p, q) ? 1 : 0;
			
			if (current + left + right >= 2) {
				lca = node;
			}
			
			return current + left + right > 0;
		}
	}
}
