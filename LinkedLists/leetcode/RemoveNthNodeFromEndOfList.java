public class RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p1 = head, p2 = head;

        /* Move p2 (n+1) positions into the list. */
        while (n-- >= 0) {
            if (p2 == null) {
                assert head != null;
                return head.next;
            }
            p2 = p2.next;
        }

        /* Move both p1, p2 one step at a time. */
        while (p2 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        /* Remove p1.next */
        assert p1 != null;
        p1.next = p1.next.next;

        return head;
    }
} 
