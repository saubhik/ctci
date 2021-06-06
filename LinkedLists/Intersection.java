package LinkedLists;

/*
 * Intersection: Given 2 linked lists, determine if the two lists intersect. Return the intersecting node. Note that the
 * intersection is defined based on the reference, not value. That is, if the kth node of the first linked list is the
 * exact same node (by reference) as the jth node of the second linked list, then they are intersecting.
 *
 * LeetCode: https://leetcode.com/problems/intersection-of-two-linked-lists/
 */
public class Intersection {
    public static LinkedListNode intersection(LinkedListNode node1, LinkedListNode node2) {
        /* Get the lengths and make them equal by chopping the head of the longer one. */
        int length1 = getListLength(node1);
        int length2 = getListLength(node2);

        if (length1 < length2)
            node2 = chopHead(node2, length2 - length1);
        else if (length2 < length1)
            node1 = chopHead(node1, length1 - length2);

        /* Now check when the nodes are equal. */
        while (node1 != null && node2 != null) {
            if (node1 == node2)
                return node1;
            node1 = node1.next;
            node2 = node2.next;
        }

        return null;
    }

    public static int getListLength(LinkedListNode node) {
        int length = 0;
        while (node != null) {
            ++length;
            node = node.next;
        }
        return length;
    }

    public static LinkedListNode chopHead(LinkedListNode node, int chopLength) {
        while (chopLength-- > 0)
            node = node.next;
        return node;
    }

    public static void main(String[] args) {
        LinkedListNode common = new LinkedListNode(7, new LinkedListNode(2, new LinkedListNode(1, null)));
        LinkedListNode node1 = new LinkedListNode(3, new LinkedListNode(1, new LinkedListNode(5, new LinkedListNode(9, common))));
        LinkedListNode node2 = new LinkedListNode(4, new LinkedListNode(6, common));

        LinkedListNode node = intersection(node1, node2);

        System.out.println(node.data);
    }
}
