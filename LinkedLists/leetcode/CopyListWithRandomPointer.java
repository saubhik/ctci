import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPointer {
	class Node {
		int val;
		Node next;
		Node random;

		public Node(int val) {
			this.val = val;
			this.next = null;
			this.random = null;
		}
	}

	class Solution {
		/*
		 * Time: O(n).
		 * Space: O(n).
		 */
		public Node copyRandomList(Node head) {
			Node copyHead;
			Node copyCurr;
			Node curr;
			Map<Node,Node> map;
			
			if (head == null) {
				return null;
			}
			
			map = new HashMap<>();
			copyHead = new Node(head.val);
			map.put(head, copyHead);
			copyCurr = copyHead;
			curr = head.next;
			
			while (curr != null) {
				copyCurr.next = new Node(curr.val);
				copyCurr = copyCurr.next;
				map.put(curr, copyCurr);
				curr = curr.next;
			}
			
			curr = head;
			copyCurr = copyHead;
			
			while (curr != null) {
				copyCurr.random = map.get(curr.random);
				copyCurr = copyCurr.next;
				curr = curr.next;
			}
			
			return copyHead;
		}
	}

	class SolutionTwo {
		private Map<Node,Node> map = new HashMap<>();
		
		public Node copyRandomList(Node head) {
			if (head == null) {
				return null;
			}
			
			if (this.map.containsKey(head)) {
				return this.map.get(head);
			}
			
			Node node = new Node(head.val);
			
			this.map.put(head, node);
			
			node.next = this.copyRandomList(head.next);
			node.random = this.copyRandomList(head.random);
			
			return node;
		}
	}

	class SolutionThree {
		/*
		* Interweave cloned nodes among original
		* nodes.
		*
		* Time: O(n).
		* Space: O(1).
		*/
		public Node copyRandomList(Node head) {
			Node curr;
			Node clonedNode;
			Node clonedHead;
			
			if (head == null) {
				return null;
			}
			
			curr = head;
			while (curr != null) {
				clonedNode = new Node(curr.val);
				clonedNode.next = curr.next;
				curr.next = clonedNode;        
				curr = clonedNode.next;
			}
			
			curr = head;
			while (curr != null) {
				clonedNode = curr.next;
				clonedNode.random = curr.random == null ? 
					null : curr.random.next;
				curr = clonedNode.next;
			}
			
			curr = head;
			clonedHead = head.next;
			while (curr != null) {
				/* curr -> clonedNode -> xxx */
				clonedNode = curr.next;
				curr.next = clonedNode.next;
				curr = curr.next;
				if (curr == null) break;
				clonedNode.next = curr.next;
			}
			
			return clonedHead;
		}
	}
}