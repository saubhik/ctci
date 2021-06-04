package LinkedLists;

/*
 * Partition: Write code to partition a linked list around a value x, such
 * that all nodes less than x come before all nodes greater than or equal to
 * x. If x is contained within the list, the values of x only need to be
 * after the elements of less than x. The partition element can appear
 * anywhere in the right partition, it does not need to appear between the
 * left and the right partitions.
 * INPUT: 3->5->8->5->10->2->1, partition = 5
 * OUTPUT: 3->1->2->10->5->5->8
 */
public class Partition {
    public static LinkedListNode partition(LinkedListNode node, int p) {
        /*
         * Keep stacking lesser elements on top of head, and move head up.
         * Keep stacking higher elements below tail, and move tail down.
         */
        if (node == null)
            return null;

        LinkedListNode head = node;
        LinkedListNode tail = node;
        LinkedListNode tmp;

        while (node != null) {
            tmp = node.next;
            if (node.data < p) {
                // Move node to head.
                node.next = head;
                head = node;
            } else {
                // Move node to tail.
                tail.next = node;
                tail = tail.next;
            }
            node = tmp;
        }

        // Chop off tail.
        tail.next = null;

        return head;
    }

    public static void main(String[] args) {
        LinkedListNode node = new LinkedListNode(-1), curr;
        curr = node;
        for (int elem : new int[]{3, 5, 8, 5, 10, 2, 1}) {
            curr.next = new LinkedListNode(elem);
            curr = curr.next;
        }
        curr.next = null;

        curr = partition(node.next, 5);

        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }
}
