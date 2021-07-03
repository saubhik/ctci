import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeZigZagLevelOrderTraversal {
	class Solution {
		public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
			Queue<TreeNode> queue;
			List<List<Integer>> levels;
			List<Integer> nodes;
			TreeNode node;
			int levelSize;
			int level;
			
			queue = new LinkedList<>();
			levels = new ArrayList<>();
			level = 0;
			
			if (root == null) {
				return levels;
			}
			
			queue.add(root);
			
			while (!queue.isEmpty()) {
				levels.add(new LinkedList<>());
				levelSize = queue.size();
				
				for (int i = 0; i < levelSize; ++i) {
					node = queue.poll();
					
					nodes = levels.get(level);
					
					if (level % 2 == 0) {
						nodes.add(node.val);
					} else {
						nodes.add(0, node.val);
					}
					
					if (node.left != null) {
						queue.add(node.left);
					}
					
					if (node.right != null) {
						queue.add(node.right);
					}
				}
				
				++level;
			}
			
			return levels;
		}
	}

	class SolutionTwo {
		public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
			List<List<Integer>> results;
			Queue<TreeNode> queue;
			LinkedList<Integer> level;
			TreeNode node;
			boolean leftToRight;
			
			results = new ArrayList<>();
			queue = new LinkedList<>();
			level = new LinkedList<>();
			leftToRight = true;
			
			if (root == null) {
				return results;
			}
			
			queue.add(root);
			queue.add(null);
			
			while (!queue.isEmpty()) {
				node = queue.poll();
				
				if (node != null) {
					if (leftToRight) {
						level.addLast(node.val);
					} else {
						level.addFirst(node.val);
					}
					
					if (node.left != null) {
						queue.add(node.left);
					}
					
					if (node.right != null) {
						queue.add(node.right);
					}
				} else {
					results.add(level);
					level = new LinkedList<>();
					
					if (!queue.isEmpty()) {
						queue.add(null);
					}
					
					leftToRight = !leftToRight;
				}
			}
			
			return results;
		}
	}
}