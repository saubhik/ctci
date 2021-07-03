import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class ValidateBinarySearchTree {
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
		/*
		* Time: O(n)
		* Space: O(n)
		*/
		public boolean isValidBST(TreeNode root) {
			return isValidBST(root, null, null);
		}
		
		/* Use boxing to deal with integer limit issues */
		private boolean isValidBST(TreeNode node, Integer min, Integer max) {
			if (node == null) {
				return true;
			}
			
			if ((min == null || min < node.val) && (max == null || node.val < max)) {
				return isValidBST(node.left, min, node.val) &&
					isValidBST(node.right, node.val, max);
			}
			
			return false;
		}
	}

	class SolutionTwo {
		private Deque<TreeNode> nodes = new LinkedList<>();
		private Deque<Integer> lows = new LinkedList<>();
		private Deque<Integer> highs = new LinkedList<>();
		
		/*
		 * Time: O(n)
		 * Space: O(n)
		 */
		public boolean isValidBST(TreeNode root) {
			Integer low;
			Integer high;
			TreeNode node;
			
			updateStacks(root, null, null);
			
			while (!nodes.isEmpty()) {
				node = nodes.poll();
				low = lows.poll();
				high = highs.poll();
				
				if (node == null) {
					continue;
				}
				
				if ((low != null && low >= node.val) || (high != null && node.val >= high)) {
					return false;
				}
				
				updateStacks(node.left, low, node.val);
				updateStacks(node.right, node.val, high);
			}
			
			return true;
		}
		
		private void updateStacks(TreeNode node, Integer low, Integer high) {
			nodes.add(node);
			lows.add(low);
			highs.add(high);
		}
	}

	class SolutionThree {
		/* The previous element in order traversal is the
		 * right most leaf of the left sub tree.
		 */
		private Integer prev;
		
		public boolean isValidBST(TreeNode root) {
			prev = null;
			return inorder(root);
		}
		
		private boolean inorder(TreeNode node) {
			if (node == null) {
				return true;
			}
			
			if (!inorder(node.left)) {
				return false;
			}
			
			if (prev != null && node.val <= prev) {
				return false;
			}
			
			prev = node.val;
			return inorder(node.right);
		}
	}

	class SolutionFour {
		public boolean isValidBST(TreeNode node) {
			Integer prev;
			Deque<TreeNode> nodes;
			
			prev = null;
			nodes = new ArrayDeque<>();
			
			while (!nodes.isEmpty() || node != null) {
				while (node != null) {
					nodes.push(node);
					node = node.left;
				}
				
				node = nodes.pop();
				
				if (prev != null && node.val <= prev) {
					return false;
				}
				
				prev = node.val;
				node = node.right;
			}
			
			return true;
		}
	}
}