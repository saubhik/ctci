import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

public class BinaryTreeLevelOrderTraversal {
	class Solution {
		/* Straight-forward BFS
		 * Time: O(n).
		 * Space: O(h) = O(n) in worst case.
		 */
		public List<List<Integer>> levelOrder(TreeNode root) {
			Queue<TreeNode> nodeQ;
			TreeNode node;
			List<List<Integer>> levels;
			int levelSize;
			int level;
			
			nodeQ = new LinkedList<>();
			levels = new ArrayList<>();
			level = 0;
			
			if (root == null) {
				return levels;
			}
			
			nodeQ.add(root);
			
			while (!nodeQ.isEmpty()) {
				levels.add(new ArrayList<>());
				
				/* Use levelSize to process level by level. */
				levelSize = nodeQ.size();
				
				for (int i = 0; i < levelSize; ++i) {
					node = nodeQ.poll();
					
					levels.get(level).add(node.val);
					
					if (node.left != null) {
						nodeQ.add(node.left);
					}
	
					if (node.right != null) {
						nodeQ.add(node.right);
					}
				}
				
				++level;
			}
			
			return levels;
		}
	}

	class SolutionTwo {
		private List<List<Integer>> levels;
		
		public List<List<Integer>> levelOrder(TreeNode root) {
			levels = new ArrayList<>();
			
			if (root == null) {
				return levels;
			}
			
			helper(root, 0);
			return levels;
		}
		
		private void helper(TreeNode node, int level) {
			if (levels.size() == level) {
				levels.add(new ArrayList<>());
			}
			
			levels.get(level).add(node.val);
			
			if (node.left != null) {
				helper(node.left, level + 1);
			}
			
			if (node.right != null) {
				helper(node.right, level + 1);
			}
		}
	}
}
