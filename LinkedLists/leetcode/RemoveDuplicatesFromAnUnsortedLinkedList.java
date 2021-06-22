class ListNode {
    public ListNode next = null;
    public int val;

    ListNode(int val) {
        this.val = val;
    }
}

// https://leetcode.com/problems/remove-duplicates-from-an-unsorted-linked-list/

public class RemoveDuplicatesFromAnUnsortedLinkedList {
    public ListNode deleteDuplicatesUnsorted(ListNode head) {
        ListNode current, sentinel, pre;

        // Pseudo head, in case head is removed.
        pre = sentinel = new ListNode(-1);

        int[] counts = new int[100001];

        // Compute the value counts.
        current = head;
        while (current != null) {
            counts[current.val]++;
            current = current.next;
        }

        // Next, remove those nodes with multiple value counts.
        current = head;
        while (current != null) {
            if (counts[current.val] == 1) {
                pre.next = current;
                pre = current;
            }
            current = current.next;
        }

        // Chop off any remaining trails.
        pre.next = null;

        return sentinel.next;
    }
}
