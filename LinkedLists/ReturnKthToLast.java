package LinkedLists;

/*
 * Return Kth to Last: Implement an algorithm to find the kth to last element
 * of a singly linked list.
 */
public class ReturnKthToLast {
    /* Two pointers separated by K distance. Iterative solution.
     * Time: O(n).
     * Space: O(1).
     */
    public static LinkedListNode returnKthToLast(LinkedListNode head, int k) {
        LinkedListNode p1, p2;
        p1 = p2 = head;

        /* First, separate p2 by k distance from p1. */
        while (k-- > 0) {
            if (p2 == null)
                return null;
            p2 = p2.next;
        }

        /* Move both one step at a time. */
        while (p2 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p1;
    }

    public static void main(String[] args) {
        LinkedListNode node = new LinkedListNode(-1), curr;
        curr = node;
        for (int elem : new int[]{1, 3, 4, 2}) {
            curr.next = new LinkedListNode(elem);
            curr = curr.next;
        }
        curr.next = null;

        System.out.println("k=1:" + (returnKthToLast(node, 1).data == 2));
        System.out.println("k=2:" + (returnKthToLast(node, 2).data == 4));
        System.out.println("k=3:" + (returnKthToLast(node, 3).data == 3));
        System.out.println("k=4:" + (returnKthToLast(node, 4).data == 1));
    }
}
