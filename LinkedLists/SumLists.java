package LinkedLists;

public class SumLists {
    /* Digits in reverse order -----------------------------------------------*/
    /* Iterative solution */
    public static LinkedListNode sumLists(LinkedListNode node1,
                                          LinkedListNode node2) {
        LinkedListNode pseudoHead = new LinkedListNode();
        LinkedListNode node = pseudoHead;
        int sum, carry = 0;
        while (node1 != null && node2 != null) {
            sum = node1.data + node2.data + carry;
            carry = sum / 10;

            node.next = new LinkedListNode(sum % 10);
            node = node.next;

            node1 = node1.next;
            node2 = node2.next;
        }

        while (node1 != null) {
            sum = node1.data + carry;
            carry = sum / 10;

            node.next = new LinkedListNode(sum % 10);
            node = node.next;

            node1 = node1.next;
        }

        while (node2 != null) {
            sum = node2.data + carry;
            carry = sum / 10;

            node.next = new LinkedListNode(sum % 10);
            node = node.next;

            node2 = node2.next;
        }

        node.next = null;

        return pseudoHead.next;
    }

    /* Digits in correct order -----------------------------------------------*/
    /*
     * Recursive solution.
     * INPUT:  6 -> 1 -> 7
     *         2 -> 9 -> 5
     * OUTPUT: 9 -> 1 -> 2
     */
    public static LinkedListNode helper(LinkedListNode node1,
                                        LinkedListNode node2,
                                        int[] carry) {
        if (node1 == null || node2 == null)
            return null;

        LinkedListNode nextNode = helper(node1.next, node2.next, carry);

        carry[0] += node1.data + node2.data;

        LinkedListNode node = new LinkedListNode(carry[0] % 10, nextNode);

        carry[0] /= 10;

        return node;
    }

    public static int getLinkedListLength(LinkedListNode node) {
        int length = 0;
        while (node != null) {
            length++;
            node = node.next;
        }
        return length;
    }

    public static LinkedListNode padLinkedList(LinkedListNode node,
                                               int padLength) {
        LinkedListNode tmp;
        while (padLength-- > 0) {
            tmp = new LinkedListNode(0, node);
            node = tmp;
        }
        return node;
    }

    public static LinkedListNode sumListsRev(LinkedListNode node1,
                                             LinkedListNode node2) {
        int length1 = getLinkedListLength(node1);
        int length2 = getLinkedListLength(node2);

        if (length1 > length2)
            node2 = padLinkedList(node2, length1 - length2);
        else
            node1 = padLinkedList(node1, length2 - length1);

        int[] carry = new int[]{0};
        LinkedListNode node = helper(node1, node2, carry);

        if (carry[0] != 0) {
            LinkedListNode head = new LinkedListNode();
            head.data = carry[0];
            head.next = node;
            return head;
        }

        return node;
    }


    public static void main(String[] args) {
        // 7 -> 1 -> 6
        LinkedListNode node1 = new LinkedListNode(7,
                new LinkedListNode(1,
                        new LinkedListNode(6, null)));

        // 5 -> 9
        LinkedListNode node2 = new LinkedListNode(5,
                new LinkedListNode(9, null));

        // 7 -> 7 -> 5
        LinkedListNode node = sumListsRev(node1, node2);

        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }
}
