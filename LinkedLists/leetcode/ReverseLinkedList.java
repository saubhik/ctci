public class ReverseLinkedList {
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
        * Time Complexity: O(n).
        * Space Complexity: O(n).
        */
        public ListNode reverseList(ListNode node) {
            if (node == null || node.next == null) {
                return node;
            }
            
            ListNode reversed;
            
            reversed = reverseList(node.next);

            node.next.next = node;
            node.next = null;
            
            return reversed;
        }
    }

    class SolutionTwo {
        /*
         * 1 -> 2 -> 3 -> 4 -> 5
         * Reverse list using iteration.
         * Use 3 variables to exchange stuff.
         *
         * Time Complexity: O(n)
         * Space Complexity: O(1)
         */
        public ListNode reverseList(ListNode head) {
            ListNode curr;
            ListNode prev;
            ListNode tmp;
            
            curr = head;
            prev = null;
            
            while (curr != null) {
                tmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = tmp;
            }
            
            return prev;
        }
    }
}
