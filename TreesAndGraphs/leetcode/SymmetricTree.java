import java.util.LinkedList;
import java.util.Queue;

public class SymmetricTree {
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
		public boolean isSymmetric(TreeNode root) {        
			return checkValEqual(root, root);
		}
		
		private boolean checkValEqual(TreeNode node1, TreeNode node2) {
			if (node1 == null && node2 == null) {
				return true;
			}
			
			if (node1 == null || node2 == null) {
				return false;
			}
					
			return node1.val == node2.val && 
				checkValEqual(node1.left, node2.right) &&
				checkValEqual(node1.right, node2.left);
		}
	}

	class SolutionTwo {
		/* BFS style iterative approach */
		public boolean isSymmetric(TreeNode root) {
			Queue<TreeNode> q;
			TreeNode t1;
			TreeNode t2;
			
			q = new LinkedList<>();
			q.add(root);
			q.add(root);
			
			while (!q.isEmpty()) {
				t1 = q.poll();
				t2 = q.poll();
				
				if (t1 == null && t2 == null) {
					continue;
				}
				
				if (t1 == null || t2 == null) {
					return false;
				}
				
				if (t1.val != t2.val) {
					return false;
				}
				
				q.add(t1.left);
				q.add(t2.right);
				q.add(t1.right);
				q.add(t2.left);
			}
			
			return true;
		}
	}
}
