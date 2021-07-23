import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SerializeAndDeserializeBinaryTree {
	/**
	 * Definition for a binary tree node.
	 * public class TreeNode {
	 *     int val;
	 *     TreeNode left;
	 *     TreeNode right;
	 *     TreeNode(int x) { val = x; }
	 * }
	 */
	public class Codec {

		// Encodes a tree to a single string.
		/*
		* Level Order Traversal using BFS.
		*/
		public String serialize(TreeNode root) {
			Queue<TreeNode> queue;
			List<Integer> list;
			TreeNode node;
			
			queue = new LinkedList<>();
			list = new ArrayList<>();
			queue.add(root);
			
			while (!queue.isEmpty()) {
				for (int i = queue.size() - 1; i >= 0; --i) {
					node = queue.poll();
					
					if (node == null) {
						list.add(null);
					} else {
						list.add(node.val);
						queue.offer(node.left);
						queue.offer(node.right);
					}
				}
			}

			return list.toString();
		}

		// Decodes your encoded data to tree.
		/*
		* [1,2,3,null,null,4,5]
		* Step 1:
		* Create node(1). Offer to queue. [node(1)]
		* Step 2:
		* Poll queue.
		* Create node(2) and node(3). Connect with node(1).
		* Offer to queue. [node(2),node(3)]
		* Step 3:
		* Poll queue.
		* Connect null and null with node(2).
		* Nothing to offer to queue.
		* Step 4:
		* Poll queue.
		* Create node(4) and node(5). Connect with node(3).
		* Offer to queue. [node(4),node(5)].
		* 
		*/
		public TreeNode deserialize(String data) {
			String[] nodeValues;
			Queue<TreeNode> queue;
			TreeNode node;
			TreeNode root;
			int index;
			String val;
			String leftVal;
			String rightVal;
			
			nodeValues = data.replace("[", "").replace("]", "").replace(" ","").split(",");
			queue = new LinkedList<>();
			
			index = 0;
			val = nodeValues[index++];
			if (val.equals("null")) {
				return null;
			}
			
			root = new TreeNode(Integer.parseInt(val));
			queue.add(root);
			
			while (!queue.isEmpty()) {
				node = queue.poll();
				
				leftVal = nodeValues[index++];
				rightVal = nodeValues[index++];
				
				if (!leftVal.equals("null")) {
					node.left = new TreeNode(Integer.parseInt(leftVal));
					queue.offer(node.left);
				}
				
				if (!rightVal.equals("null")) {
					node.right = new TreeNode(Integer.parseInt(rightVal));
					queue.offer(node.right);
				}
			}
			
			return root;
		}
	}

	// Your Codec object will be instantiated and called as such:
	// Codec ser = new Codec();
	// Codec deser = new Codec();
	// TreeNode ans = deser.deserialize(ser.serialize(root));
}