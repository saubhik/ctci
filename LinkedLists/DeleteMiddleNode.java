package LinkedLists;

/*
 * Delete Middle Node: Implement an algorithm to delete a node in the middle
 * (i.e., any node but the first and last node, not necessarily the exact
 * middle) of a singly linked list, given only access to that node.
 * Example:
 * INPUT: the node c from the linked list a->b->c->d->e->f
 * RESULT: nothing is returned, but the new linked list looks like a->b->d->e->f
 */
public class DeleteMiddleNode {
    public static boolean deleteMiddleNode(LinkedListNode node) {
        if (node == null || node.next == null)
            return false;
        node.data = node.next.data;
        node.next = node.next.next;
        return true;
    }

    public static void main(String[] args) {
        LinkedListNode node = new LinkedListNode(0), curr, nodeToRemove;
        curr = node;
        nodeToRemove = null;
        for (int elem : new int[]{1, 2, 3, 4}) {
            curr.next = new LinkedListNode(elem);
            curr = curr.next;
            if (elem == 3)
                nodeToRemove = curr;
        }
        curr.next = null;

        deleteMiddleNode(nodeToRemove);

        curr = node;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
    }
}
