public class MergeTwoSortedLists {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        /*
        * l1 = [1,2,4], l2 = [1,3,4]
        * Compare 1 from l1 and 1 from l2.
        *
        * Time Complexity: O(n)
        * Space Complexity: O(1)
        */
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode l;
            ListNode sentinel;
            
            l = new ListNode();
            sentinel = l;
            
            while (l1 != null && l2 != null) {
                if (l1.val <= l2.val) {
                    l.next = new ListNode(l1.val);
                    l1 = l1.next;
                } else {
                    l.next = new ListNode(l2.val);
                    l2 = l2.next;
                }
                l = l.next;
            }
            
            l.next = l1 != null ? l1 : l2;
            
            return sentinel.next;
        }
    }
}
